package MoonBurn.GoL;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.model.rules.ConwayRules;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.BoardView;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Shell extends VBox
{
    private final Color canvasBackgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);

    private Canvas canvas;
    private int canvasHeight;
    private int canvasWidth;

    private Simulation simulation;

    private double cellWidth;
    private double cellHeight;

    private Simulator simulator;

    private EditorViewModel editorViewModel;

    public Shell(int canvasWidth, int canvasHeight, IBoard board, ApplicationViewModel avm, BoardViewModel bvm, EditorViewModel evm, BoardView boardCanvas)
    {
        this.editorViewModel = evm;

        bvm.addBoardListener((b) -> onBoardChanged(b));

        this.setOnKeyPressed(this::onKeyPressed);

        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;

        simulation = new Simulation(board, new ConwayRules());

        this.simulator =  new Simulator(bvm,simulation);

        cellWidth = (double)canvasWidth / (double)board.getWidth();
        cellHeight = (double)canvasHeight / (double)board.getHeight();

        canvas = new Canvas(canvasWidth, canvasHeight);
        canvas.setOnMousePressed(this::onBoardEdit);
        canvas.setOnMouseDragged(this::onBoardEdit);

        Toolbar toolbar = new Toolbar(this, avm, bvm, evm);
        getChildren().addAll(toolbar,boardCanvas);
    }

    /**
     * Method is called when the board change is broadcast.
     * @param board
     */
    private void onBoardChanged(IBoard board)
    {
        draw(board);
    }

    /**
     * Handles keyboard key press event functionalities.
     */
    private void onKeyPressed(KeyEvent keyEvent)
    {
        KeyCode key = keyEvent.getCode();
        switch (key)
        {
            case D:
                editorViewModel.setDrawMode(CellState.ALIVE);
                System.out.println("Draw mode set to ALIVE");
            break;

            case E:
                editorViewModel.setDrawMode(CellState.DEAD);
                System.out.println("Draw mode set to ERASE");
            break;
        }
    }

    /**
     * Handles mouse click and mouse drag events.
     */
    private void onBoardEdit(MouseEvent mouseEvent)
    {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        editorViewModel.boardPressed(x,y);
    }

    /**
     * Draws current simulation state to the canvas.
     */
    public void draw(IBoard board)
    {
        GraphicsContext graphCont = canvas.getGraphicsContext2D();

        graphCont.setFill(canvasBackgroundColor);
        graphCont.fillRect(0,0, canvasWidth, canvasHeight);

        graphCont.setFill(aliveCellColor);
        for (int x = 0; x < board.getWidth(); x++)
        {
            for (int y = 0; y < board.getHeight(); y++)
            {
                if(board.getState(x,y) == CellState.ALIVE)
                {
                    graphCont.fillRect(x * cellWidth, y * cellHeight, cellWidth , cellHeight);
                }
            }
        }

        graphCont.setFill(gridlinesColor);
        graphCont.setLineWidth(Math.min(cellHeight,cellWidth)/25);
        for (int x = 0; x <= board.getWidth(); x++)
        {
            graphCont.strokeLine(x * cellWidth,0,x * cellWidth, canvasHeight);
        }
        for (int y = 0; y <= board.getHeight(); y++)
        {
            graphCont.strokeLine(0,y*cellHeight, canvasWidth,y*cellHeight);
        }
    }

    /**
     * Returns simulator that is currently in use.
     * @return Simulator
     */
    public Simulator getSimulator()
    {
        return simulator;
    }

}
