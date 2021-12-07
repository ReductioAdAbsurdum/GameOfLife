package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.util.Property;

import java.util.LinkedList;
import java.util.List;

public class BoardViewModel
{
    public Property<IBoard> board;

    public BoardViewModel(IBoard initialBoard)
    {
       board = new Property<IBoard>(initialBoard);
    }
}
