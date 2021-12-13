package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.util.event.IEvent;

public class SimulatorEvent implements IEvent
{
    /**
     enum for all types of events that Simulator class is listening for
     */
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

    /**
     * Returns private field of event type
     * @return event type
     */
    public Type getEventType()
    {
        return eventType;
    }
}
