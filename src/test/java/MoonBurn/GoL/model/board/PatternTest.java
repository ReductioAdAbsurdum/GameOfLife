package MoonBurn.GoL.model.board;

import MoonBurn.GoL.model.enums.CellState;
import org.junit.jupiter.api.Test;

class PatternTest
{
    String stillBlockString;
    CellState[][] stillBlockMatrix;

    String stillBeeHiveString;
    CellState[][] stillBeeHiveMatrix;

    public PatternTest()
    {
        setUpStillBlock();
        setUpStillBeeHive();
    }

    private void setUpStillBeeHive()
    {
        stillBeeHiveString = "4#3#010!101!101!010";
        stillBeeHiveMatrix = new CellState[][]
                {
                        {CellState.DEAD, CellState.ALIVE, CellState.DEAD},
                        {CellState.ALIVE, CellState.DEAD, CellState.ALIVE},
                        {CellState.ALIVE, CellState.DEAD, CellState.ALIVE},
                        {CellState.DEAD, CellState.ALIVE, CellState.DEAD},
                };
    }

    private void setUpStillBlock()
    {
        stillBlockString = "2#2#11!11";
        stillBlockMatrix = new CellState[][]
                {
                        {CellState.ALIVE,CellState.ALIVE},
                        {CellState.ALIVE,CellState.ALIVE}
                };
    }

    @Test
    void createMatrix_Still_Square()
    {
        Pattern pattern = new Pattern(stillBlockString,"Block");
        for (int x = 0; x < pattern.getWidth() ; x++)
        {
            for (int y = 0; y < pattern.getHeight(); y++)
            {
                assert(pattern.getMatrix()[x][y] == stillBlockMatrix[x][y]);
            }
        }
    }
    @Test
    void createMatrix_Still_BeeHive()
    {
        Pattern pattern = new Pattern(stillBeeHiveString,"Bee-Hive");
        for (int x = 0; x < pattern.getWidth() ; x++)
        {
            for (int y = 0; y < pattern.getHeight(); y++)
            {
                assert(pattern.getMatrix()[x][y] == stillBeeHiveMatrix[x][y]);
            }
        }
    }
}