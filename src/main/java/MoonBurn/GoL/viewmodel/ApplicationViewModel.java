package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.util.Property;

import java.util.LinkedList;
import java.util.List;

public class ApplicationViewModel
{
    public Property<ApplicationState> applicationState;

    public ApplicationViewModel(ApplicationState initialState)
    {
        applicationState = new Property<ApplicationState>(initialState);
    }
}
