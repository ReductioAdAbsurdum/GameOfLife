package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.util.Property;

public class BoardVM
{
    private Property<IBoard> boardProp;

    public BoardVM(IBoard initialBoard)
    {
       boardProp = new Property<IBoard>(initialBoard);
    }

    public Property<IBoard> getBoardProp()
    {
        return boardProp;
    }
}
