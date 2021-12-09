package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.util.Property;
import MoonBurn.GoL.util.event.classes.ApplicationStateEvent;

public class ApplicationVM
{
    private Property<ApplicationState> applicationStateProp;

    public ApplicationVM(ApplicationState initialState)
    {
        applicationStateProp = new Property<ApplicationState>(initialState);
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
