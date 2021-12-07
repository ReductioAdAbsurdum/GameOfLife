package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;

import java.util.LinkedList;
import java.util.List;

public class EditorViewModel
{
    private CellState drawMode = CellState.ALIVE;
    private List<ISimpleChangeListener<CellState>> drawModeListeners;
    private BoardViewModel boardViewModel;
    private ApplicationViewModel applicationViewModel;
    private boolean isDrawingEnabled = true;

    public EditorViewModel(BoardViewModel bvm, ApplicationViewModel avm)
    {
        this.boardViewModel = bvm;
        this.applicationViewModel = avm;
        applicationViewModel.addAppStateListener(this::onAppStateChanged);
        drawModeListeners = new LinkedList<ISimpleChangeListener<CellState>>();
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

    public void boardPressed(int x, int y)
    {
        if(!isDrawingEnabled)
        {
            return;
        }

        //Guard logic if there is no change
        if(drawMode == boardViewModel.getBoard().getState(x,y))
        {
            return;
        }

        if (drawMode == CellState.ALIVE)
        {
            boardViewModel.getBoard().setState(x,y,CellState.ALIVE);
            boardViewModel.notifyOfExternalChange();
        }
        if (drawMode == CellState.DEAD)
        {
            boardViewModel.getBoard().setState(x,y,CellState.DEAD);
            boardViewModel.notifyOfExternalChange();
        }

        String logMessage = String.format("Canvas: %d | %d",x,y);
        System.out.println(logMessage);
    }

    public void addDrawModeListeners(ISimpleChangeListener listener)
    {
        drawModeListeners.add(listener);
    }

    public void setDrawMode(CellState drawMode)
    {
        //Guard logic, so we don't notify listeners if there is no change
        if(this.drawMode == drawMode)
        {
            return;
        }
        this.drawMode = drawMode;
        notifyDrawModeListeners();
    }

    private void notifyDrawModeListeners()
    {
        for (ISimpleChangeListener<CellState> dml : drawModeListeners)
        {
            dml.valueChanged(drawMode);
        }
    }
}
