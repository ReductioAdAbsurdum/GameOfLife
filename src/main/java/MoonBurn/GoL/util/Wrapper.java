package MoonBurn.GoL.util;

public class Wrapper<T>
{
    private T wrappedValue;

    public Wrapper(T wrappedValue)
    {
        this.wrappedValue = wrappedValue;
    }

    /**
     * Return private wrappedValue field
     * @return T wrapped value
     */
    public T getWrappedValue()
    {
        return wrappedValue;
    }

    /**
     * Set private wrappedValue filed
     * @param wrappedValue generic value value
     */
    public void setWrappedValue(T wrappedValue)
    {
        this.wrappedValue = wrappedValue;
    }
}
