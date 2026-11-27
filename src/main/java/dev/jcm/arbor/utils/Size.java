package dev.jcm.arbor.utils;

import dev.jcm.arbor.Arbor;

@FunctionalInterface
public interface Size {
    Size ZERO = (s) -> SimpleVec2i.zero();

    static Size percentage(float percentage) {
        if (percentage < 0 || percentage > 1) {
            Arbor.LOGGER.error("Size percentage can not be less than 0 or greater than 1");
            return ZERO;
        }

        return size -> new SimpleVec2i(Math.round(size.x * percentage), Math.round(size.y * percentage));
    }

    static Size percentage(float percentageX, float percentageY) {
        if (percentageX < 0 || percentageX > 1 || percentageY < 0 || percentageY > 1) {
            Arbor.LOGGER.error("Size percentage can not be less than 0 or greater than 1");
            return ZERO;
        }

        return size -> new SimpleVec2i(Math.round(size.x * percentageX), Math.round(size.y * percentageY));
    }

    static Size staticSize(SimpleVec2i size) {
        return s -> size;
    }

    static Size staticSize(int width, int height) {
        SimpleVec2i size = new SimpleVec2i(width, height);
        return s -> size;
    }

    SimpleVec2i get(SimpleVec2i maxSize);
}
