package MoonBurn.GoL.logic;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.util.Property;
import MoonBurn.GoL.util.event.classes.ApplicationStateEvent;

public class ApplicationStateManager
{
    private Property<ApplicationState> applicationStateProp;

    public ApplicationStateManager(ApplicationState initialState)
    {
        applicationStateProp = new Property<>(initialState);
    }

    public Property<ApplicationState> getApplicationStateProp()
    {
        return applicationStateProp;
    }

    public void handleApplicationStateEvent(ApplicationStateEvent event)
    {
       applicationStateProp.setValue(event.getApplicationState());
    }
}
