package de.fh.kiel.advancedjava.pojomodel.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UtilTest {

    @Test
    void parsePackageName() {
        assertEquals("package", ParseUtil.parsePackageName("package.class"));
        assertEquals("package", ParseUtil.parsePackageName("package"));

    }

    @Test
    void parseClassName() {
        assertEquals("class", ParseUtil.parseClassName("package.class"));
        assertEquals("class", ParseUtil.parseClassName("class"));
    }

    @Test
    void parseCompletePath() {
        assertEquals("package.class", ParseUtil.parseCompletePath("package", "class"));
    }
}
