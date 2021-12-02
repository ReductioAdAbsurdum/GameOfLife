package MoonBurn.GoL.model.board;

import MoonBurn.GoL.model.enums.CellState;

public interface IBoard
{
    CellState getState(int x, int y);
    void setState(int x, int y, CellState state);
    void clearBoard();

    IBoard deepCleanCopy();

    int getWidth();
    int getHeight();
}
