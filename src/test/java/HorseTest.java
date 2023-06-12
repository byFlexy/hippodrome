import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameExceptionMessage() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
    public void blankNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
    public void blankNameExceptionMessage(String name) {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void minusSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 1));
    }

    @Test
    public void minusSpeedExceptionMessage() {
        try {
            new Horse("Horse", -1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void minusDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -1));
    }

    @Test
    public void minusDistanceExceptionMessage() {
        try {
            new Horse("Horse", 1, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getName() {
        Horse horse = new Horse("Horse", 1, 1);
        assertEquals("Horse", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Horse", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Horse", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    void getZeroDistance() {
        Horse horse = new Horse("Horse", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveGetRandom() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("Horse", 1, 1).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = 0.2)
    void moveDistance(Double random) {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 1, 1);
            double paramDistance = horse.getDistance();
            horseMockedStatic.when(()->Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(random);
            horse.move();
            assertEquals(paramDistance+horse.getSpeed()*random, horse.getDistance());
        }
    }
}