package MoonBurn.GoL.model;

public interface IRules
{
    CellState getNextState(int x, int y, IBoard board);
}
