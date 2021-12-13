package MoonBurn.GoL.util;

import java.util.LinkedList;
import java.util.List;

public class Property<T>
{
    private T value;
    private List<ISimpleChangeListener<T>> listenerList;

    public Property(T value)
    {
        this.value = value;
        listenerList = new LinkedList<>();
    }

    /**
     *  Calls constructor that has value parameter and sends null value to it
     */
    public Property()
    {
       this(null);
    }

    /**
     * Adds implementation of functional interface IISimpleChangeListener<T> to the listener list.
     * In this case that implementation is Lambda to function with next signature:
     * void valueChanged(V value);
     * @param listener Lambda
     */
    public void addListener(ISimpleChangeListener<T> listener)
    {
        listenerList.add(listener);
    }

    /**
     * Returns the private property value
     * @return value
     */
    public T getValue()
    {
        return value;
    }

    /**
     * Sets the private property value to the new value. Includes the guard logic
     * that guaranties that only new value will trigger notifyListeners() method.
     * @param newValue new value that private value property will be set to.
     */
    public void setValue(T newValue)
    {
        //Guard logic
        if(newValue.equals(value))
        {
            return;
        }
        this.value = newValue;
        notifyListeners();
    }

    /**
     * Triggers notifyListeners() method without changing value field.
     * Used when value is changed externally - without of use of the setValue() method.
     */
    public void notifyOfExternalChange()
    {
        notifyListeners();
    }

    /**
     * Since we have added Lambda-s implementation of functional interface
     * ISimpleChangeListener, with this method we are calling that functions.
     */
    private void notifyListeners()
    {
        for(ISimpleChangeListener<T> scl : listenerList)
        {
            scl.valueChanged(value);
        }
    }
}
