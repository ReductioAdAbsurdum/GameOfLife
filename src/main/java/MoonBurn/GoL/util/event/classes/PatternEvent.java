package MoonBurn.GoL.util.event.classes;

import MoonBurn.GoL.util.event.IEvent;

public class PatternEvent implements IEvent
{
    private String patternName;
    private String patternString;

    public PatternEvent(String patternName, String patternString)
    {
        this.patternName = patternName;
        this.patternString = patternString;
    }

    public String getPatternName()
    {
        return patternName;
    }

    public String getPatternString()
    {
        return patternString;
    }
}
