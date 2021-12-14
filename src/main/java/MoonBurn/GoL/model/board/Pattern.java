package MoonBurn.GoL.model.board;

import MoonBurn.GoL.model.enums.CellState;

public class Pattern
{
    private static int width;
    private static int height;
    private static CellState[][] matrix;

    public void createMatrixFromString(String pattern)
    {
        try
        {
            String[] hashtagBreak = pattern.split("#");
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

    public CellState[][] getMatrix()
    {
        return matrix;
    }

}
