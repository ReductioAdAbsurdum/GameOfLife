package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.enums.ApplicationState;

import java.util.LinkedList;
import java.util.List;

public class ApplicationViewModel
{
    private ApplicationState currentState;
    private List<ISimpleChangeListener<ApplicationState>> appStateListeners;

    public ApplicationViewModel(ApplicationState currentState)
    {
        this.currentState = currentState;
        appStateListeners = new LinkedList<ISimpleChangeListener<ApplicationState>>();
    }

    /**
     * Subscribe to listen for app state change
     * @param listener implementation of ISimpleChangeListener
     */
    public void listenToAppState(ISimpleChangeListener<ApplicationState> listener)
    {
        appStateListeners.add(listener);
    }

    public ApplicationState getApplicationState()
    {
        return currentState;
    }

    /**
     * Sets application state to the new state if new is different from the old.
     * Notifies all app state listeners of changed property.
     * @param newState ApplicationState
     */
    public void setCurrentState(ApplicationState newState)
    {
        if(currentState != newState )
        {
        currentState = newState;
        notifyAppStateListeners();
        }
    }

    /**
     * Quarterback method that notifies all the listeners listening for app state change.
     */
    private void notifyAppStateListeners()
    {
        for (ISimpleChangeListener<ApplicationState> asl : appStateListeners)
        {
            asl.valueChanged(currentState);
        }
    }
}
