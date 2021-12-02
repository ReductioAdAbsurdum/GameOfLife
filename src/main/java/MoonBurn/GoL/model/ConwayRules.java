package MoonBurn.GoL.model;

public class ConwayRules implements IRules
{
    @Override
    public CellState getNextState(int x, int y, IBoard board)
    {
        int aliveNeighbours = countAliveNeighbours(x,y,board);

        if (board.getState(x,y) == CellState.ALIVE && (aliveNeighbours == 2 || aliveNeighbours == 3))
        {
            return CellState.ALIVE;
        }
        if(board.getState(x,y) == CellState.DEAD && aliveNeighbours == 3)
        {
            return CellState.ALIVE;
        }

        return CellState.DEAD;
    }

    private int countAliveNeighbours(int x, int y, IBoard board)
    {
        int count = 0;
        int radius = 1;

        for (int i = -radius; i <= radius; i++)
        {
            for (int j = -radius; j <= radius; j++)
            {
                if (i == 0 && j == 0)
                {
                    continue;
                }
                if(board.getState(x+i,y+j) == CellState.ALIVE)
                {
                    count++;
                }
            }
        }

        return count;
    }
}
