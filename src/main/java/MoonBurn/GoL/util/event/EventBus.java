package MoonBurn.GoL.util.event;

import java.util.*;

public class EventBus
{
    private Map<Class, List<IEventListener>> map = new HashMap<>();

    /**
     * Calls implemented Lambda for list of IEventListeners that are listening for the
     * class with from which event instance is created.
     * @param event event object - instance of Class that implements IEvent
     */
    public void emit(IEvent event)
    {
        Class eventClass = event.getClass();
        List<IEventListener> eventListeners = map.get(eventClass);

        for (IEventListener el : eventListeners)
        {
            el.handle(event);
        }
    }

    /**
     * Creates mapping from class that implements IEvent to the listener(Lambda) that
     * we want to be called when event instance from that class is emitted.
     * @param eventClass Class that is blueprint for event object we want to listen.
     * @param listener Lambda that is added to listener list for the eventClass
     * @param <T> Condition that eventClass must extend IEvent interface.
     */
    public <T extends IEvent> void addMapping(Class<T> eventClass, IEventListener<T> listener)
    {
        if(!map.containsKey(eventClass))
        {
            map.put(eventClass, new LinkedList<>());
        }
        map.get(eventClass).add(listener);
    }
}
