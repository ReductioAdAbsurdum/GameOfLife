package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.enums.ApplicationState;

import java.util.LinkedList;
import java.util.List;

public class ApplicationViewModel
{
    private ApplicationState currentState;
    private List<ISimpleChangeListener> appStateListeners;

    public ApplicationViewModel(ApplicationState currentState)
    {
        this.currentState = currentState;
        appStateListeners = new LinkedList<ISimpleChangeListener>();
    }

    /**
     * Subscribe to listen for
     * @param listener implementation of ISimpleChangeListener
     */
    public void listenToAppState(ISimpleChangeListener listener)
    {
        appStateListeners.add(listener);
    }

    public void setCurrentState(ApplicationState newState)
    {
        if(currentState != newState )
        {
        currentState = newState;
        notifyAppStateListeners();
        }
    }

    private void notifyAppStateListeners()
    {
        for (ISimpleChangeListener asl : appStateListeners)
        {
            asl.valueChanged();
        }
    }
}
