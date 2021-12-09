package MoonBurn.GoL.util.event;

public interface IEventListener<T extends IEvent>
{
    void handle(T event);
}
