package MoonBurn.GoL.view.patternSelect;

import javafx.scene.control.ComboBox;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ListView extends ComboBox
{
    private HashMap<String ,String> stillPatterns;
    private HashMap<String ,String> oscillatorPatterns;

    public ListView()
    {
        this.setMinSize(120,25);
        this.setMaxSize(120,25);

        stillPatterns = new HashMap<>();
        populateStillPatternList();

        oscillatorPatterns = new HashMap<>();
        populateOscillatorPatternList();

    }

    public void addStillNamesToView()
    {
        for (String name : stillPatterns.keySet())
        {
            this.getItems().add(name);
        }

    }
    public void removeStillNamesFromView()
    {
        for (String name : stillPatterns.keySet())
        {
            this.getItems().remove(name);
        }
    }

    public void addOscillatorNamesToView()
    {
        for (String name : oscillatorPatterns.keySet())
        {
            this.getItems().add(name);
        }

    }
    public void removeOscillatorNamesFromView()
    {
        for (String name : oscillatorPatterns.keySet())
        {
            this.getItems().remove(name);
        }
    }

    private void populateStillPatternList()
    {
        try {
            File file = new File("C:\\Users\\Monster\\Desktop\\GameOfLife\\src\\main\\resources\\Still");

            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String name = input.nextLine();
                String string = input.nextLine();

                stillPatterns.put(name, string);
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
    private void populateOscillatorPatternList()
    {
        try {
            File file = new File("C:\\Users\\Monster\\Desktop\\GameOfLife\\src\\main\\resources\\Oscillator");

            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String name = input.nextLine();
                String string = input.nextLine();

                oscillatorPatterns.put(name, string);
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
        if(stillPatterns.containsKey(name))
        {
            return stillPatterns.get(name);
        }
        if(oscillatorPatterns.containsKey(name))
        {
            return oscillatorPatterns.get(name);
        }
        System.out.println("Key not found");
        return null;
    }
}
