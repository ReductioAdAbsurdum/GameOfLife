package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.model.CellPosition;
import MoonBurn.GoL.util.event.IEvent;

public class BoardPressEvent implements IEvent
{
    private CellPosition cellPosition;

    public BoardPressEvent(CellPosition cellPosition)
    {
        this.cellPosition = cellPosition;
    }

    public CellPosition getCellPosition()
    {
        return cellPosition;
    }
}
