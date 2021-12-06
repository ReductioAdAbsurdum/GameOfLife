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

    private boolean isDrawingEnabled = true;

    public EditorViewModel(BoardViewModel boardViewModel)
    {
        this.boardViewModel = boardViewModel;
        drawModeListeners = new LinkedList<ISimpleChangeListener<CellState>>();
    }

    public void boardPressed(int x, int y)
    {
        if(isDrawingEnabled == false)
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

    public void onAppStateChanged(ApplicationState state)
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

    public void addDrawModeListeners(ISimpleChangeListener listener)
    {
        drawModeListeners.add(listener);
    }

    public void setDrawMode(CellState drawMode)
    {
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
