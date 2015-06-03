package accesscode.c4q.nyc.memeifyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button camera_button;
    private Button album_button;
    private Button template_button;
    private Button exit_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera_button = (Button) findViewById(R.id.camera_button);
        album_button = (Button) findViewById(R.id.album_button);
        template_button = (Button) findViewById(R.id.template_button);
        exit_button = (Button) findViewById(R.id.exit_button);

        camera_button.setOnClickListener(this);
        album_button.setOnClickListener(this);
        template_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.camera_button:
                Intent i = new Intent(this, Camera.class);
                startActivity(i);
                break;
            case R.id.album_button:
                break;
            case R.id.template_button:
                break;
            case R.id.exit_button:
                finish();
                System.exit(0);
                break;
        }
    }
}
