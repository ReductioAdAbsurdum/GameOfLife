package MoonBurn.GoL.view;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.EventBus;
import MoonBurn.GoL.util.event.classes.ApplicationStateEvent;
import MoonBurn.GoL.util.event.classes.DrawModeEvent;
import MoonBurn.GoL.util.event.classes.SimulatorEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import java.io.File;
import java.util.Scanner;

public class Toolbar extends ToolBar
{
    private EventBus eventBus;

    public Toolbar(EventBus eventBus)
    {
        this.eventBus = eventBus;

        Button draw = new Button("Draw");
        draw.setOnAction(this::onDrawButton);

        Button erase = new Button("Erase");
        erase.setOnAction(this::onEraseButton);
        
        Button clear = new Button("Clear");
        clear.setOnAction(this::onClearButton);

        Button step = new Button("Step");
        step.setOnAction(this::onStepButton);

        Button start = new Button("Start");
        start.setOnAction(this::onStartButton);

        Button stop = new Button("Stop");
        stop.setOnAction(this::onStopButton);

        //ListView listView = new ListView();

        this.getItems().addAll(draw,erase,clear,step,start,stop);
    }

    /**
     * Handles stop button press event.
     */
    private void onStopButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
    }

    /**
     * Handles start button press event.
     */
    private void onStartButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.START));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.RUNNING));
    }

    /**
     * Handles clear button press event.
     */
    private void onClearButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.CLEAR));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
        eventBus.emit(new DrawModeEvent(CellState.ALIVE));
    }

    /**
     * Handles step button press event.
     */
    private void onStepButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STEP));
    }

    /**
     * Handles erase button press event.
     */
    private void onEraseButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
        eventBus.emit(new DrawModeEvent(CellState.DEAD));
    }

    /**
     * Handles draw button press event.
     */
    private void onDrawButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
        eventBus.emit(new DrawModeEvent(CellState.ALIVE));
    }

}
