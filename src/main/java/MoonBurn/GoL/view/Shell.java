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
        eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
        eventBus.emit(new ApplicationStateEvent(ApplicationState.EDITING));

        KeyCode key = keyEvent.getCode();
        switch (key)
        {
            case D:
                eventBus.emit(new DrawModeEvent(CellState.ALIVE));
                break;

            case E:
                eventBus.emit(new DrawModeEvent(CellState.DEAD));
                break;
        }
    }

}
