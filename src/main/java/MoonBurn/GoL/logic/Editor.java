package MoonBurn.GoL.logic;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.Property;
import MoonBurn.GoL.util.event.classes.BoardPressEvent;
import MoonBurn.GoL.util.event.classes.DrawModeEvent;
import MoonBurn.GoL.viewmodel.BoardVM;

public class Editor
{
    private Property<CellState> drawModeProp;

    private BoardVM boardVM;
    private boolean isDrawingEnabled = true;

    public Editor(BoardVM boardVM, ApplicationStateManager applicationStateManager)
    {
        drawModeProp = new Property<>(CellState.ALIVE);
        this.boardVM = boardVM;
        applicationStateManager.getApplicationStateProp().addListener(this::onAppStateChanged);
    }
    public void handleDrawModeEvent(DrawModeEvent event)
    {
        drawModeProp.setValue(event.getDrawMode());
    }

    public Property<CellState> getDrawModeProp()
    {
        return drawModeProp;
    }

    private void onAppStateChanged(ApplicationState state)
    {
        isDrawingEnabled = state == ApplicationState.EDITING;
    }

    public void onBoardPressed(BoardPressEvent event)
    {
        if(!isDrawingEnabled)
        {
            return;
        }

        int x = event.getCellPosition().getX();
        int y = event.getCellPosition().getY();

        //Guard logic if there is no change
        if(drawModeProp.getValue() == boardVM.getBoardProp().getValue().getState(x,y))
        {
            return;
        }

        boardVM.getBoardProp().getValue().setState(x,y,drawModeProp.getValue());
        boardVM.getBoardProp().notifyOfExternalChange();

        String logMessage = String.format("Canvas: %d | %d",x,y);
        System.out.println(logMessage);
    }
}
