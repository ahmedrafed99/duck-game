package test;

import logic.Physics;
import logic.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsTest {

    @org.junit.jupiter.api.Test
    void checkCollisions() {
    }

    @ParameterizedTest
    @MethodSource("provideDataForGetPathTo")
    void getPathTo_ShouldReturnExpectedPath(Point start, Point end, List<Point> expectedPath) {
        List<Point> path = Physics.getPathTo(start, end);
        assertEquals(expectedPath, path);
    }

    private static Stream<Arguments> provideDataForGetPathTo() {
        return Stream.of(
                Arguments.of(
                        new Point(1, 1),
                        new Point(3, 2),
                        Arrays.asList(
                            new Point(1, 0),
                            new Point(1, 0),
                            new Point(0, 1)
                        )
                ),
                Arguments.of(
                        new Point(9, 4),
                        new Point(3, 1),
                        Arrays.asList(
                                new Point(-1, 0),
                                new Point(-1, 0),
                                new Point(-1, 0),
                                new Point(-1, 0),
                                new Point(-1, 0),
                                new Point(-1, 0),
                                new Point(0, -1),
                                new Point(0, -1),
                                new Point(0, -1)
                        )
                )
        );
    }
}