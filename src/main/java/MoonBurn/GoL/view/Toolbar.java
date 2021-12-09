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

public class Toolbar extends ToolBar
{
    private EventBus eventBus;

    public Toolbar(EventBus eventBus)
    {
        this.eventBus = eventBus;

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
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
    }

    private void handleStartButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.START));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.RUNNING));
    }

    /**
     * Handles clear button press event.
     */
    private void handleClearButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.CLEAR));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
        eventBus.emit(new DrawModeEvent(CellState.ALIVE));
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STEP));
    }

    /**
     * Handles erase button press event.
     */
    private void handleEraseButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
        eventBus.emit(new DrawModeEvent(CellState.DEAD));
    }

    /**
     * Handles draw button press event.
     */
    private void handleDrawButton(ActionEvent actionEvent)
    {
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
        eventBus.emit(new DrawModeEvent(CellState.ALIVE));
    }
}
