package MoonBurn;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar
{
    private MainView mainView;

    public Toolbar(MainView mainView)
    {
        this.mainView = mainView;

        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDrawButton);

        Button erase = new Button("Erase");
        erase.setOnAction(this::handleEraseButton);
        
        Button clear = new Button("Clear");
        clear.setOnAction(this::handleClearButton);

        Button step = new Button("Step");
        step.setOnAction(this::handleStepButton);

        Button start = new Button("Start");
        start.setOnAction(this::handleStartButton);

        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStopButton);

        this.getItems().addAll(draw,erase,clear,step,start,stop);
    }

    private void handleStopButton(ActionEvent actionEvent)
    {
        mainView.getSimulator().stop();
        mainView.setApplicationState(mainView.EDITING);
    }

    private void handleStartButton(ActionEvent actionEvent)
    {
        mainView.getSimulator().start();
        mainView.setApplicationState(mainView.SIMULATING);
    }

    /**
     * Handles clear button press event.
     */
    private void handleClearButton(ActionEvent actionEvent)
    {
        mainView.setApplicationState(MainView.EDITING);

        mainView.getSimulation().clearBoard();
        mainView.draw();
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButton(ActionEvent actionEvent)
    {
        mainView.setApplicationState(MainView.SIMULATING);

        mainView.getSimulation().step();
        mainView.draw();
    }

    /**
     * Handles erase button press event.
     * @param actionEvent
     */
    private void handleEraseButton(ActionEvent actionEvent)
    {
        mainView.setApplicationState(MainView.EDITING);

        mainView.setDrawMode(Simulation.DEAD);
    }

    /**
     * Handles draw button press event.
     * @param actionEvent
     */
    private void handleDrawButton(ActionEvent actionEvent)
    {
        mainView.setApplicationState(MainView.EDITING);

        mainView.setDrawMode(Simulation.ALIVE);
    }
}
