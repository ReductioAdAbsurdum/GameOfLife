package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.board.BoardWraper;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.util.Property;

public class BoardVM
{
    private Property<BoardWraper> wrappedBoardProp;

    public BoardVM(BoardWraper initialWrappedBoard)
    {
        wrappedBoardProp = new Property<>(initialWrappedBoard);
    }

    public Property<BoardWraper> getWrappedBoardProp()
    {
        return wrappedBoardProp;
    }
}
