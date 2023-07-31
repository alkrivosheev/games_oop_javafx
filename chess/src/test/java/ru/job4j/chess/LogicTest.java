package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException()
            throws OccupiedCellException, ImpossibleMoveException, FigureNotFoundException {
        Logic logic = new Logic();
        Cell startPos = Cell.findBy(2, 7);
        BishopBlack blackBishop = new BishopBlack(startPos);
        Cell dest = Cell.findBy(7, 2);
        logic.add(blackBishop);
        logic.add(new PawnBlack(Cell.findBy(4, 5)));
        try {
            logic.move(startPos, dest);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Occupied cell exception.");
        }
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws OccupiedCellException, ImpossibleMoveException, FigureNotFoundException {
        Logic logic = new Logic();
        Cell startPos = Cell.findBy(2, 7);
        BishopBlack blackBishop = new BishopBlack(startPos);
        Cell dest = Cell.findBy(1, 3);
        logic.add(blackBishop);
        try {
            logic.move(startPos, dest);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Could not move by diagonal from C1 to B5");
        }
    }
}