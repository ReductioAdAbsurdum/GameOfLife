package MoonBurn.GoL.view;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class BoardCanvasView extends Canvas
{
    private final Color backgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);

    private double cellHeight;
    private double cellWidth;

    private EditorViewModel editorViewModel;
    private BoardViewModel boardViewModel;

    public BoardCanvasView(double width, double height, EditorViewModel evm, BoardViewModel bvm)
    {
        super(width, height);

        this.editorViewModel = evm;
        this.boardViewModel = bvm;
        boardViewModel.board.addListener(this::onBoardChanged);

        cellWidth = getWidth() / (double)boardViewModel.board.get().getWidth();
        cellHeight = getHeight() / (double)boardViewModel.board.get().getHeight();

        this.setOnMousePressed(this::onBoardMouseEvent);
        this.setOnMouseDragged(this::onBoardMouseEvent);
    }

    /**
     * Method is called when the board change is broadcast.
     * @param board board that is sent form BoarViewModel
     */
    private void onBoardChanged(IBoard board)
    {
        draw();
    }

    /**
     * Handles mouse click and mouse drag events.
     */
    private void onBoardMouseEvent(MouseEvent mouseEvent)
    {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        editorViewModel.boardPressed(x,y);
    }

    /**
     * Draws current board.
     */
    private void draw()
    {
        GraphicsContext graphCont = getGraphicsContext2D();

        graphCont.setFill(backgroundColor);
        graphCont.fillRect(0,0, getWidth(), getHeight());

        graphCont.setFill(aliveCellColor);
        for (int x = 0; x < boardViewModel.board.get().getWidth(); x++)
        {
            for (int y = 0; y < boardViewModel.board.get().getHeight(); y++)
            {
                if(boardViewModel.board.get().getState(x,y) == CellState.ALIVE)
                {
                    graphCont.fillRect(x * cellWidth, y * cellHeight, cellWidth , cellHeight);
                }
            }
        }

        graphCont.setFill(gridlinesColor);
        graphCont.setLineWidth(Math.min(cellHeight,cellWidth)/25);
        for (int x = 0; x <= boardViewModel.board.get().getWidth(); x++)
        {
            graphCont.strokeLine(x * cellWidth,0,x * cellWidth, getHeight());
        }
        for (int y = 0; y <= boardViewModel.board.get().getHeight(); y++)
        {
            graphCont.strokeLine(0,y*cellHeight, getWidth(),y*cellHeight);
        }
    }
}
