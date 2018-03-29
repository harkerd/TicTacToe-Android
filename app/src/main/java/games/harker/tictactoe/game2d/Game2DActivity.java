package games.harker.tictactoe.game2d;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import games.harker.tictactoe.MenuActivity;
import games.harker.tictactoe.R;

public class Game2DActivity extends AppCompatActivity {
    private Draw2DView draw2DView;
    private Grid2DModel grid;
    boolean isOTurn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grid = new Grid2DModel();

        final RelativeLayout layout = new RelativeLayout(this);

        Button backButton = new Button(this);
        backButton.setText(R.string.Quit);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        backButton.setLayoutParams(params);

        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Game2DActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        layout.addView(backButton);
        layout.setBackgroundColor(Color.WHITE);

        draw2DView = new Draw2DView(this);
        draw2DView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !grid.hasWinner() && draw2DView.isOnBoard(event.getX(), event.getY()))
                {
                    Point quadrant = draw2DView.getQuadrant(event.getX(), event.getY());
                    if(grid.setAt(quadrant.x, quadrant.y, isOTurn ? Grid2DModel.O : Grid2DModel.X))
                    {
                        draw2DView.setDrawPoint(event.getX(), event.getY());
                        draw2DView.redraw(grid);
                        isOTurn = !isOTurn;
                        if(grid.hasWinner())
                        {
                            layout.setBackgroundColor(Color.RED);
                        }
                    }
                }
                return true;
            }
        });
        layout.addView(draw2DView);

        setContentView(layout);
    }
}
