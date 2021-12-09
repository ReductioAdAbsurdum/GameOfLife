package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.IEvent;

public class DrawModeEvent implements IEvent
{
    private CellState drawMode;

    public DrawModeEvent(CellState eventValue)
    {
        this.drawMode = eventValue;
    }

    public CellState getDrawMode()
    {
        return drawMode;
    }
}
