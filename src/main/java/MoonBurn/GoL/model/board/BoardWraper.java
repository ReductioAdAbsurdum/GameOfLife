package MoonBurn.GoL.model.board;

public class BoardWraper
{
    private IBoard board;

    public BoardWraper(IBoard board)
    {
        this.board = board;
    }

    public IBoard getBoard()
    {
        return board;
    }

    public void setBoard(IBoard board)
    {
        this.board = board;
    }
}
