package MoonBurn.GoL;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar
{
    private MainView mainView;
    private ApplicationViewModel applicationViewModel;
    private BoardViewModel boardViewModel;

    public Toolbar(MainView mainView, ApplicationViewModel applicationViewModel, BoardViewModel boardViewModel)
    {
        this.mainView = mainView;
        this.applicationViewModel = applicationViewModel;
        this.boardViewModel = boardViewModel;

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
        applicationViewModel.setCurrentState(ApplicationState.EDITING);
    }

    private void handleStartButton(ActionEvent actionEvent)
    {
        mainView.getSimulator().start();
        applicationViewModel.setCurrentState(ApplicationState.RUNNING);
    }

    /**
     * Handles clear button press event.
     */
    private void handleClearButton(ActionEvent actionEvent)
    {
        mainView.getSimulator().stop();
        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        mainView.getSimulation().getBoard().clearBoard();
        boardViewModel.setBoard(mainView.getSimulation().getBoard());
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButton(ActionEvent actionEvent)
    {
        applicationViewModel.setCurrentState(ApplicationState.RUNNING);

        mainView.getSimulator().doStep();
    }

    /**
     * Handles erase button press event.
     * @param actionEvent
     */
    private void handleEraseButton(ActionEvent actionEvent)
    {
        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        mainView.setDrawMode(CellState.DEAD);
    }

    /**
     * Handles draw button press event.
     * @param actionEvent
     */
    private void handleDrawButton(ActionEvent actionEvent)
    {
        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        mainView.setDrawMode(CellState.ALIVE);
    }
}
