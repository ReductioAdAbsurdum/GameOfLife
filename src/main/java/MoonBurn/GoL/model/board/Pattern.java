package MoonBurn.GoL.model.board;

import MoonBurn.GoL.model.enums.CellState;

public class Pattern
{
    private String patternName;
    private String inputString;
    private int width;
    private int height;
    private CellState[][] matrix;

    public Pattern(String inputString, String patternName)
    {
        this.inputString = inputString;
        this.patternName = patternName;
        createMatrix();
    }
    public CellState[][] getMatrix()
    {
        return matrix;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getPatternName()
    {
        return patternName;
    }

    private void createMatrix()
    {
        try
        {
            String[] hashtagBreak = inputString.split("#");
            width = Integer.parseInt(hashtagBreak[0]);
            height = Integer.parseInt(hashtagBreak[1]);
            matrix = new CellState[width][height];

            String[] value = hashtagBreak[2].split("!");

            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    if (value[x].charAt(y) == '1')
                    {
                        matrix[x][y] = CellState.ALIVE;
                    }
                    else
                    {
                        matrix[x][y] = CellState.DEAD;
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Invalid string");
        }
    }

}
