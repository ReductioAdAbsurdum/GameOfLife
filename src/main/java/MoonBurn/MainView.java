package MoonBurn;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.IllegalFormatCodePointException;

public class MainView extends VBox
{
    public static final int EDITING = 0;
    public static final int SIMULATING = 1;

    private final Color canvasBackgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);

    private int drawMode = Simulation.ALIVE;

    private Canvas canvas;
    private int canvasHeight;
    private int canvasWidth;

    private Simulation simulation;
    private int simulationWidth;
    private int simulationHeight;

    private double cellWidth;
    private double cellHeight;

    private int applicationState = EDITING;

    public MainView(int canvasWidth, int canvasHeight, int simulationWidth, int simulationHeight)
    {
        this.setOnKeyPressed(this::handleKeyPressed);

        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;

        this.simulationWidth = simulationWidth;
        this.simulationHeight = simulationHeight;
        simulation = new Simulation(simulationWidth, simulationHeight);

        cellWidth = (double)canvasWidth / (double)simulationWidth;
        cellHeight = (double)canvasHeight / (double)simulationHeight;

        canvas = new Canvas(canvasWidth, canvasHeight);
        canvas.setOnMousePressed(this::handleDraw);
        canvas.setOnMouseDragged(this::handleDraw);

        Toolbar toolbar = new Toolbar(this);
        getChildren().addAll(toolbar,canvas);
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
                drawMode = Simulation.ALIVE;
                System.out.println("Draw mode set to ALIVE");
            break;

            case E:
                drawMode = Simulation.DEAD;
                System.out.println("Draw mode set to ERASE");
            break;
        }
    }

    /**
     * Handles mouse click and mouse drag events.
     */
    private void handleDraw(MouseEvent mouseEvent)
    {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        String logMessage = String.format("Canvas: MouseX: %d | %d \n        MouseY: %d | %d",mouseX,x,mouseY,y);
        System.out.println(logMessage);

        if (drawMode == Simulation.ALIVE)
        {
            simulation.setAlive(x,y);
        }
        if (drawMode == Simulation.DEAD)
        {
            simulation.setADead(x,y);
        }
        draw();
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
        for (int x = 0; x < simulationWidth; x++)
        {
            for (int y = 0; y < simulationHeight; y++)
            {
                if(simulation.getCellValue(x,y) == Simulation.ALIVE)
                {
                    graphCont.fillRect(x * cellWidth, y * cellHeight, cellWidth , cellHeight);
                }
            }
        }

        graphCont.setFill(gridlinesColor);
        graphCont.setLineWidth(Math.min(cellHeight,cellWidth)/25);
        for (int x = 0; x <= simulation.width; x++)
        {
            graphCont.strokeLine(x * cellWidth,0,x * cellWidth, canvasHeight);
        }
        for (int y = 0; y <= simulation.height; y++)
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
    public void setDrawMode(int drawMode)
    {
        this.drawMode=drawMode;
    }

    /**
     * Sets application state to given one.
     * @param applicationState given application state
     */
    public void setApplicationState(int applicationState)
    {
        if(this.applicationState==applicationState)
        {
            return;
        }

        this.applicationState = applicationState;
    }
}
