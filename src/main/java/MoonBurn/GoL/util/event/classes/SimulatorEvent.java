package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.util.event.IEvent;

public class SimulatorEvent implements IEvent
{
    public enum Type
    {
        START,
        STOP,
        STEP,
        CLEAR
    }

    private Type eventType;

    public SimulatorEvent(Type eventType)
    {
        this.eventType = eventType;
    }

    public Type getEventType()
    {
        return eventType;
    }
}
