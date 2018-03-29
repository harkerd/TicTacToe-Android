package games.harker.tictactoe.game3d;

public class Grid3DModel {
    public static final int X = 1;
    public static final int O = -1;
    public static final int NONE = 0;

    private int[][][] grid = new int[3][3][3];
    private int winner = NONE;

    public Grid3DModel()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                for(int k = 0; k < 3; k++)
                {
                    grid[i][j][k] = NONE;
                }
            }
        }
    }

    public int getAt(int i, int j, AxisView axis)
    {
        if(axis.axis == AxisView.X_AXIS)
        {
            return grid[axis.position][i][j];
        }
        else if(axis.axis == AxisView.Y_AXIS)
        {
            return grid[i][axis.position][j];
        }
        else if(axis.axis == AxisView.Z_AXIS)
        {
            return grid[i][j][axis.position];
        }
        else
            return NONE;
    }

    public boolean setAt(int i, int j, AxisView axis, int owner)
    {
        if(getAt(i, j, axis) == NONE)
        {
            int x = -3;
            int y = -3;
            int z = -3;
            if(axis.axis == AxisView.X_AXIS)
            {
                x = axis.position;
                y = i;
                z = j;
            }
            else if(axis.axis == AxisView.Y_AXIS)
            {
                x = i;
                y = axis.position;
                z = j;
            }
            else if(axis.axis == AxisView.Z_AXIS)
            {
                x = i;
                y = j;
                z = axis.position;
            }

            grid[x][y][z] = owner;
            return true;
        }
        return false;
    }

    public boolean hasWinner()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if (winner == NONE && grid[i][j][0] == grid[i][j][1] && grid[i][j][1] == grid[i][j][2])
                {
                    winner = grid[i][j][1];
                }
            }
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if (winner == NONE && grid[i][0][j] == grid[i][1][j] && grid[i][1][j] == grid[i][2][j])
                {
                    winner = grid[i][1][j];
                }
            }
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if (winner == NONE && grid[0][i][j] == grid[1][i][j] && grid[1][i][j] == grid[2][i][j])
                {
                    winner = grid[1][i][j];
                }
            }
        }

        for(int i = 0; i < 3; i++)
        {
            if (winner == NONE && grid[0][0][i] == grid[1][1][i] && grid[1][1][i] == grid[2][2][i])
            {
                winner = grid[1][1][i];
            }
            if (winner == NONE && grid[0][2][i] == grid[1][1][i] && grid[1][1][i] == grid[2][0][i])
            {
                winner = grid[1][1][i];
            }
        }

        for(int i = 0; i < 3; i++)
        {
            if (winner == NONE && grid[0][i][0] == grid[1][i][1] && grid[1][i][1] == grid[2][i][2])
            {
                winner = grid[1][i][1];
            }
            if (winner == NONE && grid[0][i][2] == grid[1][i][1] && grid[1][i][1] == grid[2][i][0])
            {
                winner = grid[1][i][1];
            }
        }

        for(int i = 0; i < 3; i++)
        {
            if (winner == NONE && grid[i][0][0] == grid[i][1][1] && grid[i][1][1] == grid[i][2][2])
            {
                winner = grid[i][1][1];
            }
            if (winner == NONE && grid[i][0][2] == grid[i][1][1] && grid[i][1][1] == grid[i][2][0])
            {
                winner = grid[i][1][1];
            }
        }

        if (winner == NONE && grid[0][0][0] == grid[1][1][1] && grid[1][1][1] == grid[2][2][2])
        {
            winner = grid[1][1][1];
        }
        if (winner == NONE && grid[0][0][2] == grid[1][1][1] && grid[1][1][1] == grid[2][2][0])
        {
            winner = grid[1][1][1];
        }
        if (winner == NONE && grid[0][2][0] == grid[1][1][1] && grid[1][1][1] == grid[2][0][2])
        {
            winner = grid[1][1][1];
        }
        if (winner == NONE && grid[0][2][2] == grid[1][1][1] && grid[1][1][1] == grid[2][0][0])
        {
            winner = grid[1][1][1];
        }

        return winner != NONE;
    }
}
