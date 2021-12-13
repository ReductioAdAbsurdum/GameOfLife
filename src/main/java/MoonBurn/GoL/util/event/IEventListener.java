package MoonBurn.GoL.util.event;

/*
Functional Interfaces Can Be Implemented by a Lambda Expression.
A Java lambda expression implements a single method from a Java interface.
In order to know what method the lambda expression implements,
the interface can only contain a single unimplemented method.
 */

public interface IEventListener<T extends IEvent>
{
    /**
     * Signature of the lambda function that can to implement interface.
     * @param event event that is parameter of lambda function
     */
    void handle(T event);
}
