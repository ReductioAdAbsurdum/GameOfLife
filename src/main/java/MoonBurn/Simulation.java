package MoonBurn;


public class Simulation
{
    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int width;
    public int height;

    public int[][] board;

    public Simulation(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void printBoard()
    {
        StringBuilder line = new StringBuilder();
        line.append("_".repeat(Math.max(0, width + 2)));

        System.out.println(line);

        for (int y = 0; y < height; y++)
        {
            StringBuilder row = new StringBuilder("|");
            for (int x = 0; x < width; x++)
            {
                if (this.board[x][y] == DEAD)
                {
                    row.append("O");
                } else
                {
                    row.append("X");
                }
            }
            row.append("|");
            System.out.println(row);
        }

        System.out.println(line);
    }

    public void step()
    {
        int[][] newBoard = new int[width][height];

        for (int x = 0; x < this.width; x++)
        {
            for (int y = 0; y < this.height; y++)
            {
               int aliveNeighbours = countAliveNeighbours(x,y);

               if (this.board[x][y] == ALIVE && (aliveNeighbours == 2 || aliveNeighbours == 3))
               {
                   newBoard[x][y] = ALIVE;
               }
               if(this.board[x][y] == DEAD && aliveNeighbours == 3)
               {
                   newBoard[x][y] = ALIVE;
               }
            }
        }

        this.board = newBoard;
    }

    public void setAlive(int x, int y)
    {
        if (x >= width || x < 0)
        {
            return;
        }
        if (y >= height || y < 0)
        {
            return;
        }

        this.board[x][y] = ALIVE;
    }

    public void setADead(int x, int y)
    {
        if (x >= width || x < 0)
        {
            return;
        }
        if (y >= height || y < 0)
        {
            return;
        }

        this.board[x][y] = DEAD;
    }

    public int getCellValue(int x, int y)
    {
        if (x >= width || x < 0)
        {
            return DEAD;
        }
        if (y >= height || y < 0)
        {
            return DEAD;
        }
        return board[x][y];
    }

    public int countAliveNeighbours(int x, int y)
    {
        int count = 0;

        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if (i == 0 && j == 0)
                {
                    continue;
                }
                count += getCellValue(x + i, y + j);
            }
        }

        return count;
    }
}
