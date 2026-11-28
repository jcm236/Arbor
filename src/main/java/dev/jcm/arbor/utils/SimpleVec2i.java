package dev.jcm.arbor.utils;

public record SimpleVec2i(int x, int y) {

    public static SimpleVec2i zero() {
        return new SimpleVec2i(0, 0);
    }

    public static boolean within(int mouseX, int mouseY, SimpleVec2i position, SimpleVec2i size) {
        return within(mouseX, mouseY, position.x, position.y, size.x, size.y);
    }

    public static boolean within(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    public SimpleVec2i addNew(SimpleVec2i other) {
        return new SimpleVec2i(x + other.x, y + other.y);
    }

    public boolean outside(SimpleVec2i other) {
        return x > other.x || y > other.y;
    }

    public SimpleVec2i max(SimpleVec2i other) {
        return new SimpleVec2i(Math.max(x, other.x), Math.max(y, other.y));
    }

    public SimpleVec2i min(SimpleVec2i other) {
        return new SimpleVec2i(Math.min(x, other.x), Math.min(y, other.y));
    }
}
