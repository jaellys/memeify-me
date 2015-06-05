package accesscode.c4q.nyc.memeifyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_camera;
    private Button btn_album;
    private Button btn_template;
    private Button btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_album = (Button) findViewById(R.id.btn_album);
        btn_template = (Button) findViewById(R.id.btn_template);
        btn_exit = (Button) findViewById(R.id.btn_exit);

        btn_camera.setOnClickListener(this);
        btn_album.setOnClickListener(this);
        btn_template.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                Intent camera = new Intent(this, Camera.class);
                startActivity(camera);
                break;
            case R.id.btn_album:
                Intent album = new Intent(this, Gallery.class);
                startActivity(album);
                break;
            case R.id.btn_template:
                break;
            case R.id.btn_exit:
                finish();
                System.exit(0);
                break;
        }
    }
}
