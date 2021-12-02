package MoonBurn.GoL;

import MoonBurn.GoL.model.CellState;
import MoonBurn.GoL.model.IBoard;
import MoonBurn.GoL.model.IRules;

public class Simulation
{
    private IBoard board;
    private IRules rules;

    public Simulation(IBoard board, IRules rules)
    {
        this.board = board;
        this.rules = rules;
    }

    /**
     * Steps board to the next state.
     */
    public void step()
    {
        IBoard nextBoard = board.deepCleanCopy();

        for (int x = 0; x < nextBoard.getWidth(); x++)
        {
            for (int y = 0; y < nextBoard.getHeight(); y++)
            {
                CellState nextState = rules.getNextState(x,y,board);
                nextBoard.setState(x,y,nextState);
            }
        }

        board = nextBoard;
    }

    public IBoard getBoard()
    {
        return board;
    }
}
