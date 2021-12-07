package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.CellPosition;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.Property;

public class EditorVM
{
    private Property<CellState> drawModeProp;
    private Property<CellPosition> cursorPositionProp;

    private BoardVM boardVM;
    private boolean isDrawingEnabled = true;

    public EditorVM(BoardVM bvm, ApplicationVM avm)
    {
        drawModeProp = new Property<CellState>(CellState.ALIVE);
        cursorPositionProp = new Property<CellPosition>();
        this.boardVM = bvm;
        avm.getApplicationStateProp().addListener(this::onAppStateChanged);
    }

    public Property<CellState> getDrawModeProp()
    {
        return drawModeProp;
    }

    private void onAppStateChanged(ApplicationState state)
    {
        if(state == ApplicationState.EDITING)
        {
            isDrawingEnabled = true;
        }
        else
        {
            isDrawingEnabled = false;
        }
    }

    public void boardPressed(CellPosition mousePosition)
    {
        if(!isDrawingEnabled)
        {
            return;
        }

        int x = mousePosition.getX();
        int y = mousePosition.getY();

        //Guard logic if there is no change
        if(drawModeProp.getValue() == boardVM.getBoardProp().getValue().getState(x,y))
        {
            return;
        }

        if (drawModeProp.getValue() == CellState.ALIVE)
        {
            boardVM.getBoardProp().getValue().setState(x,y,CellState.ALIVE);
            boardVM.getBoardProp().notifyOfExternalChange();
        }
        if (drawModeProp.getValue() == CellState.DEAD)
        {
            boardVM.getBoardProp().getValue().setState(x,y,CellState.DEAD);
            boardVM.getBoardProp().notifyOfExternalChange();
        }

        String logMessage = String.format("Canvas: %d | %d",x,y);
        System.out.println(logMessage);
    }

    public Property<CellPosition> getCursorPositionProp()
    {
        return cursorPositionProp;
    }
}
