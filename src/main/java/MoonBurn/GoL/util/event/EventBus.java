package MoonBurn.GoL.util.event;

import java.util.*;

public class EventBus
{
    private Map<Class, List<IEventListener>> map = new HashMap<>();

    public void emit(IEvent event)
    {
        Class eventClass = event.getClass();
        List<IEventListener> eventListeners = map.get(eventClass);

        for (IEventListener el : eventListeners)
        {
            el.handle(event);
        }
    }

    public <T extends IEvent> void listenFor(Class<T> eventClass, IEventListener<T> listener)
    {
        if(!map.containsKey(eventClass))
        {
            map.put(eventClass, new LinkedList<>());
        }
        map.get(eventClass).add(listener);
    }
}
