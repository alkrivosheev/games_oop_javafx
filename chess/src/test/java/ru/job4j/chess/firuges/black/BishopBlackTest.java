package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BishopBlackTest {

    @Test
    public void position() {
        Cell startPos = Cell.findBy(2,0);
        BishopBlack blackBishop = new BishopBlack(startPos);
        assertThat(blackBishop.position().name()).isEqualTo("C8");
    }

    @Test
    public void copy() {
        Cell startPos = Cell.findBy(2,0);
        BishopBlack blackBishop = new BishopBlack(startPos);
        Cell dest = Cell.findBy(4,2);
        assertThat(blackBishop.copy(dest).position().name()).isEqualTo("E6");
    }

    @Test
    public void waySuccessD2E3F4G5() {
        Cell startPos = Cell.findBy(2,7);
        BishopBlack blackBishop = new BishopBlack(startPos);
        Cell dest = Cell.findBy(6,3);
        Cell[] steps = blackBishop.way(dest);
        String strSteps = "";
        for (Cell step : steps) {
        strSteps += step.name();
        }
        assertThat(strSteps).isEqualTo("D2E3F4G5");

    }
    @Test
    public void wayException() {
        Cell startPos = Cell.findBy(2,7);
        BishopBlack blackBishop = new BishopBlack(startPos);
        Cell dest = Cell.findBy(7,3);
        try {
            blackBishop.way(dest);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Could not move by diagonal from C1 to H5");
        }
    }
}