package MoonBurn.GoL.view;

import MoonBurn.GoL.model.CellPosition;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.EventBus;
import MoonBurn.GoL.util.event.classes.BoardPressEvent;
import MoonBurn.GoL.util.event.classes.DrawModeEvent;
import MoonBurn.GoL.viewmodel.BoardVM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class BoardView extends javafx.scene.canvas.Canvas
{
    private final Color backgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);
    private final Color highlightedCellDrawColor = new Color(0.4,0.4, 0.55,1);
    private final Color highlightedCellEraseColor = new Color(0.55,0.4, 0.4,1);

    private double cellHeight;
    private double cellWidth;

    private CellPosition cursorPosition = new CellPosition(0,0);
    private CellState drawMode = CellState.ALIVE;

    private BoardVM boardVM;
    private EventBus eventBus;

    public BoardView(double width, double height, BoardVM boardVM, EventBus eventBus)
    {
        super(width, height);
        this.boardVM = boardVM;
        this.eventBus = eventBus;
        this.boardVM.getBoardProp().addListener(this::onBoardChanged);

        cellWidth = getWidth() / (double) this.boardVM.getBoardProp().getValue().getWidth();
        cellHeight = getHeight() / (double) this.boardVM.getBoardProp().getValue().getHeight();

        this.setOnMousePressed(this::onMouseClickEvent);
        this.setOnMouseDragged(mouseEvent -> {
            onMouseMovedEvent(mouseEvent);
            onMouseClickEvent(mouseEvent);
        });
        this.setOnMouseMoved(this::onMouseMovedEvent);
    }

    /**
     * Listener for the DrawModeEvent that is broadcast when draw mode is changed
     * @param event draw mode event
     */
    public void onDrawModeEvent(DrawModeEvent event)
    {
        drawMode = event.getDrawMode();
        draw();
    }

    /**
     * Method is called when mouse is moved or dragged
     * @param mouseEvent mouse event parameter
     */
    private void onMouseMovedEvent(MouseEvent mouseEvent)
    {
        cursorPosition = createCellPosition(mouseEvent);
        draw();
    }

    /**
     * Method is called when the board change is broadcast from BoardViewModel.
     * @param board board that is sent form BoarViewModel
     */
    private void onBoardChanged(IBoard board)
    {
        draw();
    }

    /**
     * Handles mouse click and mouse drag events.
     */
    private void onMouseClickEvent(MouseEvent mouseEvent)
    {
        eventBus.emit(new BoardPressEvent(cursorPosition));
    }

    /**
     * Draws current board to the canvas.
     */
    private void draw()
    {
        GraphicsContext graphCont = getGraphicsContext2D();

        graphCont.setFill(backgroundColor);
        graphCont.fillRect(0,0, getWidth(), getHeight());

        // Draws alive cells
        graphCont.setFill(aliveCellColor);
        for (int x = 0; x < boardVM.getBoardProp().getValue().getWidth(); x++)
        {
            for (int y = 0; y < boardVM.getBoardProp().getValue().getHeight(); y++)
            {
                if(boardVM.getBoardProp().getValue().getState(x,y) == CellState.ALIVE)
                {
                    graphCont.fillRect(x * cellWidth, y * cellHeight, cellWidth , cellHeight);
                }
            }
        }

        // Draws highlighted cell

        if(drawMode == CellState.ALIVE)
        {
            graphCont.setFill(highlightedCellDrawColor);
        }
        else
        {
            graphCont.setFill(highlightedCellEraseColor);
        }
        graphCont.fillRect(cursorPosition.getX() * cellWidth, cursorPosition.getY()* cellHeight, cellWidth , cellHeight);


        // Draws gridlines
        graphCont.setFill(gridlinesColor);
        graphCont.setLineWidth(Math.min(cellHeight,cellWidth)/25);
        for (int x = 0; x <= boardVM.getBoardProp().getValue().getWidth(); x++)
        {
            graphCont.strokeLine(x * cellWidth,0,x * cellWidth, getHeight());
        }
        for (int y = 0; y <= boardVM.getBoardProp().getValue().getHeight(); y++)
        {
            graphCont.strokeLine(0,y*cellHeight, getWidth(),y*cellHeight);
        }
    }

    /**
     * Creates cell position from the MouseEvent
     * @param m MouseEvent
     * @return CellPosition that represents mouse location
     */
    private CellPosition createCellPosition(MouseEvent m)
    {
        int mouseX = (int) m.getX();
        int mouseY = (int) m.getY();

        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        return new CellPosition(x,y);
    }
}
