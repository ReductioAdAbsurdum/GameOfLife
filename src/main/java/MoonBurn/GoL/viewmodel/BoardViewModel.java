package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.board.IBoard;

import java.util.LinkedList;
import java.util.List;

public class BoardViewModel
{
    private IBoard board;
    private List<ISimpleChangeListener<IBoard>> boardListeners;

    public BoardViewModel()
    {
       boardListeners = new LinkedList<ISimpleChangeListener<IBoard>>();
    }

    public void addBoardListener(ISimpleChangeListener<IBoard> listener)
    {
        boardListeners.add(listener);
    }
    
    public void setBoard(IBoard board)
    {
        this.board = board;
        notifyBoardListeners();
    }

    public void notifyOfExternalChange()
    {
        notifyBoardListeners();
    }

    public IBoard getBoard()
    {
        return board;
    }

    private void notifyBoardListeners()
    {
        for(ISimpleChangeListener<IBoard> bl : boardListeners)
        {
            bl.valueChanged(board);
        }
    }
}
