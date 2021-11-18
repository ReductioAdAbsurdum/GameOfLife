package MoonBurn;

public class Simulation
{
    int width;
    int height;

    int[][] board;

    public Simulation(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public static void main(String[] args)
    {
        Simulation sim = new Simulation(5, 5);
        sim.setAlive(1, 2);
        sim.setAlive(2, 2);
        sim.setAlive(3, 2);

        sim.printBoard();
        sim.step();
        sim.printBoard();
        sim.step();
        sim.printBoard();
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
                if (this.board[x][y] == 0)
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

               if (this.board[x][y] == 1 && (aliveNeighbours == 2 || aliveNeighbours == 3))
               {
                   newBoard[x][y] = 1;
               }
               if(this.board[x][y] == 0 && aliveNeighbours == 3)
               {
                   newBoard[x][y] = 1;
               }
            }
        }

        this.board = newBoard;
    }

    public void setAlive(int x, int y)
    {
        this.board[x][y] = 1;
    }

    public void setADead(int x, int y)
    {
        this.board[x][y] = 0;
    }

    public int getCellValue(int x, int y)
    {
        if (x >= width || x < 0)
        {
            return 0;
        }
        if (y >= height || y < 0)
        {
            return 0;
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
