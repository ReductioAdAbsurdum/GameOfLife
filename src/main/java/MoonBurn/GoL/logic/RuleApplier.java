package MoonBurn.GoL.logic;

import MoonBurn.GoL.model.board.BoardWraper;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.rules.IRules;

public class RuleApplier
{
    private BoardWraper wrappedBoard;
    private IRules rules;

    public RuleApplier(BoardWraper wrappedBoard, IRules rules)
    {
        this.wrappedBoard = wrappedBoard;
        this.rules = rules;
    }

    /**
     * Steps board to the next state.
     */
    public void step()
    {
        IBoard nextBoard = wrappedBoard.getBoard().deepCleanCopy();

        for (int x = 0; x < nextBoard.getWidth(); x++)
        {
            for (int y = 0; y < nextBoard.getHeight(); y++)
            {
                CellState nextState = rules.getNextState(x,y, wrappedBoard.getBoard());
                nextBoard.setState(x,y,nextState);
            }
        }

        wrappedBoard.setBoard(nextBoard);
    }
}
