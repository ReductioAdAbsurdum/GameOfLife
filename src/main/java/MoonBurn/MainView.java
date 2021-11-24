package MoonBurn;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainView extends VBox
{
    private final int DRAW = 1;
    private final int ERASE = 0;

    private final Color canvasBackgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);

    private int drawMode = DRAW;

    private Button stepButton;

    private Canvas canvas;
    private int canvasHeight;
    private int canvasWidth;

    private Simulation simulation;
    private int simulationWidth;
    private int simulationHeight;

    private double cellWidth;
    private double cellHeight;

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

        stepButton = new Button("step");
        stepButton.setOnAction(this::handleStepButtonPressed);

        canvas = new Canvas(canvasWidth, canvasHeight);
        canvas.setOnMousePressed(this::handleDraw);
        canvas.setOnMouseDragged(this::handleDraw);
        getChildren().addAll(this.stepButton,this.canvas);
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
                drawMode = DRAW;
                System.out.println("Draw mode set to DRAW");
            break;

            case E:
                drawMode = ERASE;
                System.out.println("Draw mode set to ERASE");
            break;
        }
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButtonPressed(ActionEvent actionEvent)
    {
        simulation.step();
        System.out.println("Step button pressed");
        draw();
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

        if (drawMode == DRAW)
        {
            simulation.setAlive(x,y);
        }
        if (drawMode == ERASE)
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
}
