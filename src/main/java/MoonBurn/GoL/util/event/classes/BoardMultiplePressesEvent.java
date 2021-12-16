package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.model.CellPosition;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.IEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BoardMultiplePressesEvent implements IEvent
{
    private HashMap<CellPosition, CellState> cellPositionMap;

    public BoardMultiplePressesEvent(HashMap<CellPosition,CellState> cellPositionMap)
    {
        this.cellPositionMap = cellPositionMap;
    }

    /**
     * Returns private field of cell position list that represents place where board is pressed.
     * @return cellPositionMap
     */
    public HashMap<CellPosition, CellState> getCellPositionMap()
    {
       return cellPositionMap;
    }
}
