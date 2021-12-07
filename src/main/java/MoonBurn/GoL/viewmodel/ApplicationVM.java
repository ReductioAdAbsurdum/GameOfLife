package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.util.Property;

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
}
