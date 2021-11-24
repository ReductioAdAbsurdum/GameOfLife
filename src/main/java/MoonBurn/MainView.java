package MoonBurn;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainView extends VBox
{
    private final Color canvasBackgroundColor = new Color(0.9,0.9,0.9,1.0);
    private final Color gridlinesColor = new Color(0,0,0,1.0);
    private final Color aliveCellColor = new Color(0.5,0.5,0.5,1.0);


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
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;

        this.simulationWidth = simulationWidth;
        this.simulationHeight = simulationHeight;
        simulation = new Simulation(simulationWidth, simulationHeight);

        cellWidth = (double)canvasWidth / (double)simulationWidth;
        cellHeight = (double)canvasHeight / (double)simulationHeight;

        stepButton = new Button("step");
        stepButton.setOnAction(actionEvent ->
        {
            simulation.step();
            draw();
        });

        canvas = new Canvas(canvasWidth, canvasHeight);

        getChildren().addAll(this.stepButton,this.canvas);

        simulation.setAlive(1, 2);
        simulation.setAlive(2, 2);
        simulation.setAlive(3, 2);

        simulation.setAlive(6, 6);
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
