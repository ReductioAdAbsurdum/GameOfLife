package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.util.Wrapper;
import MoonBurn.GoL.util.Property;

public class BoardVM
{
    private Property<Wrapper<IBoard>> wrappedBoardProp;

    public BoardVM(Wrapper<IBoard> initialWrappedBoard)
    {
        wrappedBoardProp = new Property<>(initialWrappedBoard);
    }

    public Property<Wrapper<IBoard>> getWrappedBoardProp()
    {
        return wrappedBoardProp;
    }
}
