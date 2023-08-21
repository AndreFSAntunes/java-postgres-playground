package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class MathUtilTest {

    @ParameterizedTest
    @MethodSource("mdcDataProvider")
    public void testMdc(int a, int b, int esperado) {
        int obtido = MathUtil.mdc(a, b);
        assertEquals(esperado, obtido);
    }

    private static Stream<Arguments> mdcDataProvider() {
        return Stream.of(
            Arguments.of(6, 3, 3),
            Arguments.of(9, 3, 3),
            Arguments.of(15, 5, 5),
            Arguments.of(7, 1, 1),
            Arguments.of(0, 1, 1),
            Arguments.of(5, 0, 5),
            Arguments.of(10, 0, 10),
            Arguments.of(-5, 1, 1),
            Arguments.of(-10, -2, 2)
            // ... add more test cases here
        );
    }

}
