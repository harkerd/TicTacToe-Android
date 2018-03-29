package games.harker.tictactoe.game2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class Draw2DView extends View {
    private Paint paint = new Paint();

    private int box_length = 180;
    private int letter_length = 160;
    private int letter_buffer = (box_length - letter_length) / 2;
    private int[] widthPoints = new int[4];
    private int[] heightPoints = new int[4];
    private Grid2DModel grid;
    private PointF point;
    private static final boolean isTest = false;

    private void init() {
        widthPoints[0] = 270;
        widthPoints[1] = widthPoints[0] + box_length;
        widthPoints[2] = widthPoints[0] + (box_length * 2);
        widthPoints[3] = widthPoints[0] + (box_length * 3);


        heightPoints[0] = 380;
        heightPoints[1] = heightPoints[0] + box_length;
        heightPoints[2] = heightPoints[0] + (box_length * 2);
        heightPoints[3] = heightPoints[0] + (box_length * 3);

        grid = new Grid2DModel();
    }

    public Draw2DView(Context context) {
        super(context);
        init();
    }

    public Draw2DView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Draw2DView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setDrawPoint(float x, float y)
    {
        this.point = new PointF(x, y);
    }

    public boolean isOnBoard(float x, float y)
    {
        return widthPoints[0] <= x && x < widthPoints[3] && heightPoints[0] <= y && y < heightPoints[3];
    }

    public Point getQuadrant(float x, float y)
    {
        if(!isOnBoard(x, y)) return new Point(-1,-1);

        Point quadrant = new Point();
        for(int i = 0; i < 3; i++)
        {
            if(widthPoints[i] <= x && x < widthPoints[i + 1])
            {
                quadrant.x = i;
            }
        }
        for (int j = 0; j < 3; j++)
        {
            if(heightPoints[j] <= y && y < heightPoints[j + 1])
            {
                quadrant.y = j;
            }
        }
        return quadrant;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //Pixel Screen is about 1075 by 1735
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10f);

        canvas.drawLine(widthPoints[1], heightPoints[0], widthPoints[1], heightPoints[3], paint);
        canvas.drawLine(widthPoints[2], heightPoints[0], widthPoints[2], heightPoints[3], paint);
        canvas.drawLine(widthPoints[0], heightPoints[1], widthPoints[3], heightPoints[1], paint);
        canvas.drawLine(widthPoints[0], heightPoints[2], widthPoints[3], heightPoints[2], paint);

        paint.setStrokeWidth(8f);

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(grid.getAt(i, j) == Grid2DModel.X)
                {
                    drawX(canvas, widthPoints[i] + letter_buffer, heightPoints[j] + letter_buffer);
                }
                else if(grid.getAt(i, j) == Grid2DModel.O)
                {
                    drawCircle(canvas, widthPoints[i] + letter_buffer, heightPoints[j] + letter_buffer);
                }
            }
        }
        if(isTest) drawPoint(canvas);
    }

    public void redraw(Grid2DModel grid) {
        this.grid = grid;
        this.invalidate();
    }

    public void drawCircle(Canvas canvas, int x, int y) {
        int radius = letter_length / 2;
        canvas.drawCircle(x + radius, y + radius, radius, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x + radius, y + radius, radius - letter_buffer, paint);
        paint.setColor(Color.BLACK);
    }

    public void drawX(Canvas canvas, int x, int y)
    {
        canvas.drawLine(x, y, x + letter_length, y + letter_length, paint);
        canvas.drawLine(x, y + letter_length, x + letter_length, y, paint);
    }

    public void drawPoint(Canvas canvas)
    {
        if(point != null)
            canvas.drawPoint(point.x, point.y, paint);
    }
}