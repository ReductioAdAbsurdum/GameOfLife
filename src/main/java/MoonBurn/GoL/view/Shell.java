package MoonBurn.GoL.view;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.EventBus;
import MoonBurn.GoL.util.event.classes.ApplicationStateEvent;
import MoonBurn.GoL.util.event.classes.DrawModeEvent;
import MoonBurn.GoL.util.event.classes.SimulatorEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class Shell extends VBox
{
    public EventBus eventBus;

    public Shell(EventBus eventBus, BoardView boardView, Toolbar toolbar)
    {
        this.eventBus = eventBus;

        this.setOnKeyPressed(this::onKeyPressed);


        getChildren().addAll(toolbar, boardView);
    }

    /**
     * Handles keyboard key press event functionalities.
     */
    private void onKeyPressed(KeyEvent keyEvent)
    {


        KeyCode key = keyEvent.getCode();
        switch (key)
        {
            case D: // Set draw mode
                eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
                eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
                eventBus.emit(new DrawModeEvent(CellState.ALIVE));
                break;

            case E: // Set erase mode
                eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
                eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
                eventBus.emit(new DrawModeEvent(CellState.DEAD));
                break;
            case F: // step
                eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STEP));
                break;
            case R: // Start
                eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.START));
                eventBus.emit(new ApplicationStateEvent(ApplicationState.RUNNING));
                break;
            case S: // Stop
                eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
                eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));
                break;

        }
    }

}
