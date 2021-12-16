package MoonBurn.GoL.view;

import MoonBurn.GoL.model.CellPosition;
import MoonBurn.GoL.model.board.Pattern;
import MoonBurn.GoL.util.Wrapper;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.EventBus;
import MoonBurn.GoL.util.event.classes.BoardMultiplePressesEvent;
import MoonBurn.GoL.util.event.classes.BoardPressEvent;
import MoonBurn.GoL.util.event.classes.DrawModeEvent;
import MoonBurn.GoL.util.event.classes.PatternEvent;
import MoonBurn.GoL.viewmodel.BoardVM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;


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
    private boolean drawingPatternActive = false;
    private Pattern currentPattern;

    private BoardVM boardVM;
    private EventBus eventBus;

    public BoardView(double width, double height, BoardVM boardVM, EventBus eventBus)
    {
        super(width, height);
        this.boardVM = boardVM;
        this.eventBus = eventBus;
        this.boardVM.getWrappedBoardProp().addListener(this::onBoardChanged);

        cellWidth = getWidth() / (double) this.boardVM.getWrappedBoardProp().getValue().getWrappedValue().getWidth();
        cellHeight = getHeight() / (double) this.boardVM.getWrappedBoardProp().getValue().getWrappedValue().getHeight();

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
     * @param wrappedBoard board that is sent form BoarViewModel
     */
    private void onBoardChanged(Wrapper wrappedBoard)
    {
        draw();
    }

    /**
     * Handles mouse click and mouse drag events.
     */
    private void onMouseClickEvent(MouseEvent mouseEvent)
    {
        if(drawingPatternActive == true)
        {
            HashMap<CellPosition,CellState> cellMap = new HashMap<>();

            for (int x = 0; x < currentPattern.getWidth(); x++)
            {
                for (int y = 0; y < currentPattern.getHeight(); y++)
                {
                    CellPosition position = new CellPosition(cursorPosition.getX() + x,cursorPosition.getY()+ y);
                    CellState state = currentPattern.getMatrix()[x][y];
                    cellMap.put(position, state);
                }
            }

            eventBus.emit(new BoardMultiplePressesEvent(cellMap));
        }
        else
        {
            eventBus.emit(new BoardPressEvent(cursorPosition));

        }
    }

    /**
     * Draws current board to the canvas.
     */
    private void draw()
    {
        GraphicsContext graphCont = getGraphicsContext2D();

        // Fills background
        graphCont.setFill(backgroundColor);
        graphCont.fillRect(0,0, getWidth(), getHeight());

        // Draws alive cells
        graphCont.setFill(aliveCellColor);
        for (int x = 0; x < boardVM.getWrappedBoardProp().getValue().getWrappedValue().getWidth(); x++)
        {
            for (int y = 0; y < boardVM.getWrappedBoardProp().getValue().getWrappedValue().getHeight(); y++)
            {
                if(boardVM.getWrappedBoardProp().getValue().getWrappedValue().getState(x,y) == CellState.ALIVE)
                {
                    graphCont.fillRect(x * cellWidth, y * cellHeight, cellWidth , cellHeight);
                }
            }
        }

        if(drawingPatternActive) //Draws pattern
        {
            graphCont.setFill(highlightedCellDrawColor);
            for (int x = 0; x < currentPattern.getWidth(); x++)
            {
                for (int y = 0; y < currentPattern.getHeight(); y++)
                {
                    if(currentPattern.getMatrix()[x][y] == CellState.DEAD)
                    {
                        continue;
                    }
                    int movedX = cursorPosition.getX() + x;
                    int movedY = cursorPosition.getY() + y;

                    graphCont.fillRect(movedX * cellWidth, movedY * cellHeight, cellWidth , cellHeight);
                }
            }
        }
        else // Draws highlighted cell
        {
            if(drawMode == CellState.ALIVE)
            {
                graphCont.setFill(highlightedCellDrawColor);
            }
            else
            {
                graphCont.setFill(highlightedCellEraseColor);
            }
            graphCont.fillRect(cursorPosition.getX() * cellWidth, cursorPosition.getY()* cellHeight, cellWidth , cellHeight);
        }


        // Draws gridlines
        graphCont.setFill(gridlinesColor);
        graphCont.setLineWidth(Math.min(cellHeight,cellWidth)/25);
        for (int x = 0; x <= boardVM.getWrappedBoardProp().getValue().getWrappedValue().getWidth(); x++)
        {
            graphCont.strokeLine(x * cellWidth,0,x * cellWidth, getHeight());
        }
        for (int y = 0; y <= boardVM.getWrappedBoardProp().getValue().getWrappedValue().getHeight(); y++)
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

    public void onPatternSelected(PatternEvent event)
    {

        System.out.println(event.getPatternName());
        System.out.println(event.getPatternString());

        currentPattern = new Pattern(event.getPatternName(),event.getPatternString());
        drawingPatternActive = true;
    }
    public void onEscPressed()
    {
        drawingPatternActive = false;
        draw();
    }
}
