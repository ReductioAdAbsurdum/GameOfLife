package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.util.event.IEvent;

public class ApplicationStateEvent implements IEvent
{

    private ApplicationState applicationState;

    public ApplicationStateEvent(ApplicationState eventType)
    {
        this.applicationState = eventType;
    }

    public ApplicationState getApplicationState()
    {
        return applicationState;
    }
}
