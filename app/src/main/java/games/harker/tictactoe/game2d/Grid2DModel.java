package games.harker.tictactoe.game2d;

public class Grid2DModel {
    public static final int X = 1;
    public static final int O = -1;
    public static final int NONE = 0;

    private int[][] grid = new int[3][3];
    private int winner = NONE;

    public Grid2DModel()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                grid[i][j] = NONE;
            }
        }
    }

    public int getAt(int x, int y)
    {
        //TODO: Add validation?
        return grid[x][y];
    }

    public boolean setAt(int x, int y, int owner)
    {
        if(getAt(x, y) == NONE)
        {
            grid[x][y] = owner;
            return true;
        }
        return false;
    }

    public boolean hasWinner()
    {
        for(int i = 0; i < 3; i++)
        {
            if(winner == NONE && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2])
            {
                winner = grid[i][1];
            }
        }
        for(int i = 0; i < 3; i++)
        {
            if(winner == NONE && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i])
            {
                winner = grid[1][i];
            }
        }
        if(winner == NONE && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
        {
            winner = grid[1][1];
        }
        if(winner == NONE && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
        {
            winner = grid[1][1];
        }

        return winner != NONE;
    }
}
