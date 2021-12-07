package MoonBurn.GoL.view;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.ApplicationVM;
import MoonBurn.GoL.viewmodel.BoardVM;
import MoonBurn.GoL.viewmodel.EditorVM;
import MoonBurn.GoL.viewmodel.SimulatorVM;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar
{
    private ApplicationVM applicationVM;
    private BoardVM boardVM;
    private EditorVM editorVM;
    private SimulatorVM simulatorVM;

    public Toolbar(ApplicationVM avm, BoardVM bvm, EditorVM evm, SimulatorVM svm)
    {
        this.applicationVM = avm;
        this.boardVM = bvm;
        this.editorVM = evm;
        this.simulatorVM = svm;

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
        simulatorVM.stop();
        applicationVM.getApplicationStateProp().setValue(ApplicationState.EDITING);
    }

    private void handleStartButton(ActionEvent actionEvent)
    {
        simulatorVM.start();
        applicationVM.getApplicationStateProp().setValue(ApplicationState.RUNNING);
    }

    /**
     * Handles clear button press event.
     */
    private void handleClearButton(ActionEvent actionEvent)
    {
        simulatorVM.stop();
        applicationVM.getApplicationStateProp().setValue(ApplicationState.EDITING);

        boardVM.getBoardProp().getValue().clearBoard();
        boardVM.getBoardProp().notifyOfExternalChange();

        editorVM.getDrawModeProp().setValue(CellState.ALIVE);
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButton(ActionEvent actionEvent)
    {
        simulatorVM.doStep();
    }

    /**
     * Handles erase button press event.
     */
    private void handleEraseButton(ActionEvent actionEvent)
    {
        simulatorVM.stop();
        applicationVM.getApplicationStateProp().setValue(ApplicationState.EDITING);

        editorVM.getDrawModeProp().setValue(CellState.DEAD);
    }

    /**
     * Handles draw button press event.
     */
    private void handleDrawButton(ActionEvent actionEvent)
    {
        simulatorVM.stop();
        applicationVM.getApplicationStateProp().setValue(ApplicationState.EDITING);

        editorVM.getDrawModeProp().setValue(CellState.ALIVE);
    }
}
