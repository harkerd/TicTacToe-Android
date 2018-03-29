package games.harker.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import games.harker.tictactoe.game2d.Game2DActivity;
import games.harker.tictactoe.game3d.Game3DActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button2D = (Button) findViewById(R.id.start_button);
        button2D.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuActivity.this, Game2DActivity.class);
                startActivity(intent);
            }
        });

        Button button3D = (Button) findViewById(R.id.start_3D_button);
        button3D.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuActivity.this, Game3DActivity.class);
                startActivity(intent);
            }
        });
    }
}
