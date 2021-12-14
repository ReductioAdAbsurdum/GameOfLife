package MoonBurn.GoL.model.board;

import MoonBurn.GoL.model.enums.CellState;

public class Pattern
{
    private static int width;
    private static int height;
    private static CellState[][] matrix;

    public void createPatterFromString(String pattern)
    {
        try
        {
            String[] hashtagBreak = pattern.split("#");
            width = Integer.parseInt(hashtagBreak[0]);
            height = Integer.parseInt(hashtagBreak[1]);
            matrix = new CellState[width][height];

            String[] pipeBreak = hashtagBreak[2].split("|");
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    if (pipeBreak[x].charAt(y) == 1)
                    {
                        matrix[x][y] = CellState.ALIVE;
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
