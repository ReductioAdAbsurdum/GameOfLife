package MoonBurn.GoL.view;

import MoonBurn.GoL.model.CellPosition;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.BoardVM;
import MoonBurn.GoL.viewmodel.EditorVM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Canvas extends javafx.scene.canvas.Canvas
{
    private final Color backgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);
    private final Color highlightedCellDrawColor = new Color(0.4,0.4, 0.55,1);
    private final Color highlightedCellEraseColor = new Color(0.55,0.4, 0.4,1);

    private double cellHeight;
    private double cellWidth;

    private EditorVM editorVM;
    private BoardVM boardVM;

    public Canvas(double width, double height, EditorVM evm, BoardVM bvm)
    {
        super(width, height);

        this.editorVM = evm;
        this.boardVM = bvm;
        boardVM.getBoardProp().addListener(this::onBoardChanged);
        editorVM.getCursorPositionProp().addListener(x -> draw());
        editorVM.getDrawModeProp().addListener(x -> draw());

        cellWidth = getWidth() / (double) boardVM.getBoardProp().getValue().getWidth();
        cellHeight = getHeight() / (double) boardVM.getBoardProp().getValue().getHeight();

        this.setOnMousePressed(this::onMouseClickEvent);
        this.setOnMouseDragged(mouseEvent -> {
            onMouseMovedEvent(mouseEvent);
            onMouseClickEvent(mouseEvent);
        });
        this.setOnMouseMoved(this::onMouseMovedEvent);
    }

    private void onMouseMovedEvent(MouseEvent mouseEvent)
    {
        editorVM.getCursorPositionProp().setValue(createCellPosition(mouseEvent));
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
    private void onMouseClickEvent(MouseEvent mouseEvent)
    {
        editorVM.boardPressed(createCellPosition(mouseEvent));
    }

    /**
     * Draws current board.
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
        if(editorVM.getCursorPositionProp().isPresent())
        {
        int mouseX = editorVM.getCursorPositionProp().getValue().getX();
        int mouseY = editorVM.getCursorPositionProp().getValue().getY();
        if(editorVM.getDrawModeProp().getValue() == CellState.ALIVE)
        {
            graphCont.setFill(highlightedCellDrawColor);
        }
        else
        {
            graphCont.setFill(highlightedCellEraseColor);
        }
        graphCont.fillRect(mouseX * cellWidth, mouseY * cellHeight, cellWidth , cellHeight);
        }

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

    private CellPosition createCellPosition(MouseEvent m)
    {
        int mouseX = (int) m.getX();
        int mouseY = (int) m.getY();

        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        return new CellPosition(x,y);
    }
}
