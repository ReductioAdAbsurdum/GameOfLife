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

    /**
     * Prints the board to the console.
     */
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

    /**
     * Steps simulation to the next state.
     */
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

    /**
     * Sets cell with given coordinates to ALIVE.
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     */
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

    /**
     * Sets cell with given coordinates to DEAD.
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     */
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

    /**
     * Returns the value of the cell with given coordinates.
     * Returns DEAD state if invalid coordinates are passed.
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     */
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

    /**
     * Returns the number of the ALIVE neighbours for the cell with given coordinates.
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     */
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

    /**
     * Clears the board.
     */
    public void clearBoard()
    {
        System.out.println("Clearing board");
        board = new int[width][height];
    }
}
