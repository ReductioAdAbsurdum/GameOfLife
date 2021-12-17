package MoonBurn.GoL.model.rules;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.CellState;

public class ConwayRulesWarpLogic implements IRules
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
        int biggestX = board.getWidth() - 1;
        int biggestY = board.getHeight() - 1;
        int lookPlaceX;
        int lookPlaceY;

        int count = 0;

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i == 0 && j == 0)
                {
                    continue;
                }
                lookPlaceX = x+i;
                lookPlaceY = y+j;

                if(lookPlaceX < 0)
                {
                    lookPlaceX = biggestX;
                }
                if(lookPlaceX > biggestX)
                {
                    lookPlaceX = 0;
                }
                if(lookPlaceY < 0)
                {
                    lookPlaceY = biggestY;
                }
                if(lookPlaceY > biggestY)
                {
                    lookPlaceY = 0;
                }

                if(board.getState(lookPlaceX,lookPlaceY) == CellState.ALIVE)
                {
                    count++;
                }
            }
        }
        return count;
    }
}
