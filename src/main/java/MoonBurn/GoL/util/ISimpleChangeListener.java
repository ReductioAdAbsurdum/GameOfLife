package MoonBurn.GoL.util;

/*
Functional Interfaces Can Be Implemented by a Lambda Expression.
A Java lambda expression implements a single method from a Java interface.
In order to know what method the lambda expression implements,
the interface can only contain a single unimplemented method.
 */

public interface ISimpleChangeListener<V>
{
    void valueChanged(V value);
}
