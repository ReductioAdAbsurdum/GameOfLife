package MoonBurn.GoL.model;

public interface IBoard
{
    CellState getState(int x, int y);
    void setState(int x, int y, CellState state);
    void clearBoard();

    IBoard deepCleanCopy();

    int getWidth();
    int getHeight();
}
