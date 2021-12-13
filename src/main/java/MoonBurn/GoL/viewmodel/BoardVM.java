package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.board.BoardWrapper;
import MoonBurn.GoL.util.Property;

public class BoardVM
{
    private Property<BoardWrapper> wrappedBoardProp;

    public BoardVM(BoardWrapper initialWrappedBoard)
    {
        wrappedBoardProp = new Property<>(initialWrappedBoard);
    }

    public Property<BoardWrapper> getWrappedBoardProp()
    {
        return wrappedBoardProp;
    }
}
