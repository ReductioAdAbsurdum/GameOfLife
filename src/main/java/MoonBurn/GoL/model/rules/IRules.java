package MoonBurn.GoL.model.rules;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.CellState;

public interface IRules
{
    CellState getNextState(int x, int y, IBoard board);
}
