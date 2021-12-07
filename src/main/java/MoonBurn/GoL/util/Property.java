package MoonBurn.GoL.util;

import MoonBurn.GoL.viewmodel.ISimpleChangeListener;

import java.util.LinkedList;
import java.util.List;

public class Property<T>
{
    private T value;
    private List<ISimpleChangeListener<T>> listenerList;

    public Property(T value)
    {
        this.value = value;
        listenerList = new LinkedList<ISimpleChangeListener<T>>();
    }

    public Property()
    {
       this(null);
    }

    public void addListener(ISimpleChangeListener<T> listener)
    {
        listenerList.add(listener);
    }

    public T get()
    {
        return value;
    }

    public void set(T newValue)
    {
        //Guard logic
        if(this.value.equals(newValue))
        {
            return;
        }
        this.value = newValue;
        notifyListeners();
    }

    public void notifyOfExternalChange()
    {
        notifyListeners();
    }

    private void notifyListeners()
    {
        for(ISimpleChangeListener<T> scl : listenerList)
        {
            scl.valueChanged(value);
        }
    }
}
