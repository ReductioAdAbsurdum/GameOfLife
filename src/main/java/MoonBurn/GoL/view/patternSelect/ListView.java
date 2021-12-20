package MoonBurn.GoL.view.patternSelect;

import javafx.scene.control.ComboBox;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ListView extends ComboBox
{
    private final String stillLifeLocation = "src/main/resources/Still";
    private final String oscillatorLocation = "src/main/resources/Oscillator";
    private final String spaceshipLocation = "src/main/resources/Spaceship";
    private final String specialLifeLocation = "src/main/resources/Special";

    private HashMap<String ,String> stillLifeMap;
    private HashMap<String ,String> oscillatorMap;
    private HashMap<String ,String> spaceshipMap;
    private HashMap<String ,String> specialLifeMap;

    public ListView()
    {
        this.setMinSize(160,25);
        this.setMaxSize(160,25);

        stillLifeMap = new HashMap<>();
        populatePatternMap(stillLifeMap, stillLifeLocation);

        oscillatorMap = new HashMap<>();
        populatePatternMap(oscillatorMap, oscillatorLocation);

        spaceshipMap = new HashMap<>();
        populatePatternMap(spaceshipMap, spaceshipLocation);

        specialLifeMap = new HashMap<>();
        populatePatternMap(specialLifeMap, specialLifeLocation);
    }

    public void addStillNamesToView()
    {
        for (String name : stillLifeMap.keySet())
        {
            this.getItems().add(name);
        }

    }
    public void removeStillNamesFromView()
    {
        for (String name : stillLifeMap.keySet())
        {
            this.getItems().remove(name);
        }
    }

    public void addOscillatorNamesToView()
    {
        for (String name : oscillatorMap.keySet())
        {
            this.getItems().add(name);
        }

    }
    public void removeOscillatorNamesFromView()
    {
        for (String name : oscillatorMap.keySet())
        {
            this.getItems().remove(name);
        }
    }

    public void addSpaceshipNamesToView()
    {
        for (String name : spaceshipMap.keySet())
        {
            this.getItems().add(name);
        }

    }
    public void removeSpaceshipNamesFromView()
    {
        for (String name : spaceshipMap.keySet())
        {
            this.getItems().remove(name);
        }
    }

    public void addSpecialLifeNamesToView()
    {
        for (String name : specialLifeMap.keySet())
        {
            this.getItems().add(name);
        }

    }
    public void removeSpecialLifeNamesFromView()
    {
        for (String name : specialLifeMap.keySet())
        {
            this.getItems().remove(name);
        }
    }

    private void populatePatternMap(HashMap<String,String> map, String patternLocation)
    {
        try {
            File file = new File(patternLocation);

            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String name = input.nextLine();
                if(name == "")
                {
                    break;
                }
                String string = input.nextLine();
                if(string == "")
                {
                    break;
                }

                map.put(name, string);
                if(input.hasNextLine() == false)
                {
                    return;
                }
                input.nextLine(); // Skip line
            }
            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getPatternStringByName(String name)
    {
        if(stillLifeMap.containsKey(name))
        {
            return stillLifeMap.get(name);
        }
        if(oscillatorMap.containsKey(name))
        {
            return oscillatorMap.get(name);
        }
        if(spaceshipMap.containsKey(name))
        {
            return spaceshipMap.get(name);
        }
        if(specialLifeMap.containsKey(name))
        {
            return specialLifeMap.get(name);
        }
        System.out.println("Key not found");
        return null;
    }
}
