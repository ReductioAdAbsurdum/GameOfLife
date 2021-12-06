package view;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import MoonBurn.GoL.viewmodel.SimulatorViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar
{
    private ApplicationViewModel applicationViewModel;
    private BoardViewModel boardViewModel;
    private EditorViewModel editorViewModel;
    private SimulatorViewModel simulatorViewModel;

    public Toolbar(ApplicationViewModel avm, BoardViewModel bvm, EditorViewModel evm, SimulatorViewModel svm)
    {
        this.applicationViewModel = avm;
        this.boardViewModel = bvm;
        this.editorViewModel = evm;
        this.simulatorViewModel = svm;

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
        simulatorViewModel.stop();
        applicationViewModel.setCurrentState(ApplicationState.EDITING);
    }

    private void handleStartButton(ActionEvent actionEvent)
    {
        simulatorViewModel.start();
        applicationViewModel.setCurrentState(ApplicationState.RUNNING);
    }

    /**
     * Handles clear button press event.
     */
    private void handleClearButton(ActionEvent actionEvent)
    {
        simulatorViewModel.stop();
        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        boardViewModel.getBoard().clearBoard();
        boardViewModel.notifyOfExternalChange();

        editorViewModel.setDrawMode(CellState.ALIVE);
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButton(ActionEvent actionEvent)
    {
        simulatorViewModel.doStep();
    }

    /**
     * Handles erase button press event.
     */
    private void handleEraseButton(ActionEvent actionEvent)
    {
        simulatorViewModel.stop();
        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        editorViewModel.setDrawMode(CellState.DEAD);
    }

    /**
     * Handles draw button press event.
     */
    private void handleDrawButton(ActionEvent actionEvent)
    {
        simulatorViewModel.stop();
        applicationViewModel.setCurrentState(ApplicationState.EDITING);

        editorViewModel.setDrawMode(CellState.ALIVE);
    }
}
