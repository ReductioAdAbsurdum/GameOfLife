package MoonBurn.GoL.model.board;

import MoonBurn.GoL.model.enums.CellState;

public class FiniteBoard implements IBoard
{
    private int width;
    private int height;
    private CellState[][] cellStates;

    public FiniteBoard(int width, int height)
    {
        this.width = width;
        this.height = height;
        cellStates = new CellState[width][height];
        InitializeBoard();
    }
    private void InitializeBoard()
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                cellStates[x][y] = CellState.DEAD;
            }
        }
    }

    @Override
    public CellState getState(int x, int y)
    {
        CellState result = CellState.DEAD;
        if (x < width && x >= 0)
        {
            if (y < height && y >= 0)
            {
                result = cellStates[x][y];
            }
        }
        return result;
    }
    @Override
    public void setState(int x, int y, CellState cellState)
    {
        if (x >= width || x < 0)
        {
            return;
        }
        if (y >= height || y < 0)
        {
            return;
        }

        this.cellStates[x][y] =cellState;
    }
    @Override
    public void clearBoard()
    {
        InitializeBoard();
    }
    @Override
    public IBoard deepCleanCopy()
    {
        return new FiniteBoard(width,height);
    }
    @Override
    public int getWidth()
    {
        return width;
    }
    @Override
    public int getHeight()
    {
        return height;
    }
}
