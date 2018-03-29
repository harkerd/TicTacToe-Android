package games.harker.tictactoe.game3d;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import games.harker.tictactoe.MenuActivity;
import games.harker.tictactoe.R;

public class Game3DActivity extends AppCompatActivity {
    private Draw3DView draw3DView;
    private Grid3DModel grid;
    private AxisView axis;
    boolean isOTurn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3d);

        grid = new Grid3DModel();
        axis = new AxisView(AxisView.X_AXIS, 0);


        Button backButton = (Button) findViewById(R.id.quit);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Game3DActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        draw3DView = new Draw3DView(this);
        draw3DView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !grid.hasWinner() && draw3DView.isOnBoard(event.getX(), event.getY()))
                {
                    Point quadrant = draw3DView.getQuadrant(event.getX(), event.getY());
                    if(grid.setAt(quadrant.x, quadrant.y, axis, isOTurn ? Grid3DModel.O : Grid3DModel.X))
                    {
                        draw3DView.setDrawPoint(event.getX(), event.getY());
                        draw3DView.redraw(grid, axis);
                        isOTurn = !isOTurn;
                        if(grid.hasWinner())
                        {
                            RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
                            background.setBackgroundColor(Color.RED);
                        }
                    }
                }
                return true;
            }
        });
        LinearLayout gameContainer = (LinearLayout) findViewById(R.id.game_container);
        gameContainer.addView(draw3DView);


        LinearLayout axisButtonList = (LinearLayout) findViewById(R.id.axis_button_list);
        for(int i = 0; i < 3; i++)
        {
            String axisString = "";
            switch (i)
            {
                case 0: axisString = "X"; break;
                case 1: axisString = "Y"; break;
                case 2: axisString = "Z"; break;
            }


            LinearLayout axisButtonLayout = new LinearLayout(this);
            LinearLayout.LayoutParams axisParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            axisParams.setLayoutDirection(LinearLayout.HORIZONTAL);
            axisButtonLayout.setLayoutParams(axisParams);

            for(int j = 0; j < 3; j++)
            {
                Button axisButton = new Button(this);
                axisButton.setText(axisString + j);

                final int finalJ = j;
                final int finalI = i;
                axisButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int axisVar = 0;
                        switch (finalI)
                        {
                            case 0: axisVar = AxisView.X_AXIS; break;
                            case 1: axisVar = AxisView.Y_AXIS; break;
                            case 2: axisVar = AxisView.Z_AXIS; break;
                        }
                        axis = new AxisView(axisVar, finalJ);
                        draw3DView.redraw(grid, axis);
                    }
                });

                axisButtonLayout.addView(axisButton);
            }
            axisButtonList.addView(axisButtonLayout);
        }
    }
}
