package dev.jcm.arbor.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

public sealed interface MenuContext permits MenuContext.None, MenuContext.Block, MenuContext.EntityTarget, MenuContext.HeldItem, MenuContext.Data {
    MenuContext NONE = new None();

    static MenuContext block(BlockPos pos) {
        return new Block(pos);
    }

    static MenuContext entity(Entity entity) {
        return new EntityTarget(entity.getId());
    }

    static MenuContext heldItem(InteractionHand h) {
        return new HeldItem(h);
    }

    static MenuContext data(CompoundTag tag) {
        return new Data(tag);
    }

    static MenuContext read(FriendlyByteBuf buf) {
        return switch (buf.readByte()) {
            case 0 -> NONE;
            case 1 -> new Block(buf.readBlockPos());
            case 2 -> new EntityTarget(buf.readVarInt());
            case 3 -> new HeldItem(buf.readEnum(InteractionHand.class));
            case 4 -> new Data(buf.readNbt());
            default -> throw new IllegalStateException("Unknown menu context");
        };
    }

    void write(FriendlyByteBuf buf);

    record None() implements MenuContext {
        public void write(FriendlyByteBuf b) {
            b.writeByte(0);
        }
    }

    record Block(BlockPos pos) implements MenuContext {
        public void write(FriendlyByteBuf b) {
            b.writeByte(1);
            b.writeBlockPos(pos);
        }
    }

    record EntityTarget(int entityId) implements MenuContext {
        public void write(FriendlyByteBuf b) {
            b.writeByte(2);
            b.writeVarInt(entityId);
        }
    }

    record HeldItem(InteractionHand hand) implements MenuContext {
        public void write(FriendlyByteBuf b) {
            b.writeByte(3);
            b.writeEnum(hand);
        }
    }

    record Data(CompoundTag tag) implements MenuContext {
        public void write(FriendlyByteBuf b) {
            b.writeByte(4);
            b.writeNbt(tag);
        }
    }
}
