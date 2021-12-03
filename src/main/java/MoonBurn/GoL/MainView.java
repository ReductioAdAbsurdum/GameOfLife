package MoonBurn.GoL;

import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.model.rules.ConwayRules;
import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainView extends VBox
{
    private final Color canvasBackgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);

    private CellState drawMode = CellState.ALIVE;

    private Canvas canvas;
    private int canvasHeight;
    private int canvasWidth;

    private Simulation simulation;

    private double cellWidth;
    private double cellHeight;

    private Simulator simulator;
    private IBoard board;

    private ApplicationViewModel appViewModel;
    private BoardViewModel boardViewModel;


    private boolean isDrawingEnabled = true;

    public MainView(int canvasWidth, int canvasHeight, IBoard board, ApplicationViewModel avm, BoardViewModel bvm)
    {
        this.board = board;

        this.appViewModel = avm;
        this.appViewModel.listenToAppState(this::onApplicationStateChanged);

        this.boardViewModel = bvm;
        this.boardViewModel.listenToBoard(this::onBoardChanged);

        this.setOnKeyPressed(this::handleKeyPressed);

        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;

        simulation = new Simulation(board, new ConwayRules());

        this.simulator =  new Simulator(boardViewModel,simulation);

        cellWidth = (double)canvasWidth / (double)board.getWidth();
        cellHeight = (double)canvasHeight / (double)board.getHeight();

        canvas = new Canvas(canvasWidth, canvasHeight);
        canvas.setOnMousePressed(this::handleDraw);
        canvas.setOnMouseDragged(this::handleDraw);

        Toolbar toolbar = new Toolbar(this, avm, bvm);
        getChildren().addAll(toolbar,canvas);
    }



    /**
     * Method is called when application state change is broadcast.
     * @param state
     */
    private void onApplicationStateChanged(ApplicationState state)
    {
        if(state == ApplicationState.EDITING)
        {
            isDrawingEnabled = true;
        }
        else
        {
            isDrawingEnabled = false;
        }
    }

    /**
     * Method is called when the board change is broadcast.
     * @param board
     */
    private void onBoardChanged(IBoard board)
    {
        draw();
    }

    /**
     * Handles keyboard key press event functionalities.
     */
    private void handleKeyPressed(KeyEvent keyEvent)
    {
        KeyCode key = keyEvent.getCode();
        switch (key)
        {
            case D:
                drawMode = CellState.ALIVE;
                System.out.println("Draw mode set to ALIVE");
            break;

            case E:
                drawMode = CellState.DEAD;
                System.out.println("Draw mode set to ERASE");
            break;
        }
    }

    /**
     * Handles mouse click and mouse drag events.
     */
    private void handleDraw(MouseEvent mouseEvent)
    {
        if(isDrawingEnabled == false)
        {
            return;
        }

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        String logMessage = String.format("Canvas: MouseX: %d | %d \n        MouseY: %d | %d",mouseX,x,mouseY,y);
        System.out.println(logMessage);

        if (drawMode == CellState.ALIVE)
        {
            simulation.getBoard().setState(x,y,CellState.ALIVE);
        }
        if (drawMode == CellState.DEAD)
        {
            simulation.getBoard().setState(x,y,CellState.DEAD);
        }
        boardViewModel.setBoard(board);
    }

    /**
     * Draws current simulation state to the canvas.
     */
    public void draw()
    {
        GraphicsContext graphCont = canvas.getGraphicsContext2D();

        graphCont.setFill(canvasBackgroundColor);
        graphCont.fillRect(0,0, canvasWidth, canvasHeight);

        graphCont.setFill(aliveCellColor);
        for (int x = 0; x < simulation.getBoard().getWidth(); x++)
        {
            for (int y = 0; y < simulation.getBoard().getHeight(); y++)
            {
                if(simulation.getBoard().getState(x,y) == CellState.ALIVE)
                {
                    graphCont.fillRect(x * cellWidth, y * cellHeight, cellWidth , cellHeight);
                }
            }
        }

        graphCont.setFill(gridlinesColor);
        graphCont.setLineWidth(Math.min(cellHeight,cellWidth)/25);
        for (int x = 0; x <= simulation.getBoard().getWidth(); x++)
        {
            graphCont.strokeLine(x * cellWidth,0,x * cellWidth, canvasHeight);
        }
        for (int y = 0; y <= simulation.getBoard().getHeight(); y++)
        {
            graphCont.strokeLine(0,y*cellHeight, canvasWidth,y*cellHeight);
        }
    }

    /**
     * Return the simulation.
     * @return Simulation
     */
    public Simulation getSimulation()
    {
        return simulation;
    }

    /**
     * Sets draw mode to given one.
     * @param drawMode given mode
     */
    public void setDrawMode(CellState drawMode)
    {
        this.drawMode=drawMode;
    }

    /**
     * Returns simulator that is currently in use.
     * @return Simulator
     */
    public Simulator getSimulator()
    {
        return simulator;
    }

    /**
     * Returns board view model.
     * @return Simulator
     */
    public BoardViewModel getBoardViewModel()
    {
        return boardViewModel;
    }
}
