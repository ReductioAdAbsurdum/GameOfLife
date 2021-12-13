package MoonBurn.GoL.model.board;

public class BoardWrapper
{
    private IBoard board;

    public BoardWrapper(IBoard board)
    {
        this.board = board;
    }

    /**
     * Return wrapped board
     * @return
     */
    public IBoard getBoard()
    {
        return board;
    }

    public void setBoard(IBoard board)
    {
        this.board = board;
    }
}
