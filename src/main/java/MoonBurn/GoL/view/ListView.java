package MoonBurn.GoL.view;

import javafx.scene.control.ComboBox;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ListView extends ComboBox
{
    private HashMap<String,String> nameMString;

    public ListView()
    {
       nameMString = new HashMap<>();
       populateStillMap();
    }

    private void populateStillMap()
    {
        try {
            File file = new File("C:\\Users\\Monster\\Desktop\\GameOfLife\\src\\main\\resources\\Still");

            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String name = input.nextLine();
                String line = input.nextLine();
                nameMString.put(name,line);
                input.nextLine(); // Skip line
            }
            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
