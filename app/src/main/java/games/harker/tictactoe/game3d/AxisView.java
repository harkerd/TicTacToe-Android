package games.harker.tictactoe.game3d;


public class AxisView
{
    public static final int X_AXIS = 2;
    public static final int Y_AXIS = 3;
    public static final int Z_AXIS = 4;

    public int axis;
    public int position;

    public AxisView(int axis, int position)
    {
        this.axis = axis;
        this.position = position;
    }
}
