package accesscode.c4q.nyc.memeifyme;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button takePicture, getPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePicture = (Button) findViewById(R.id.takePicture);
        getPicture = (Button) findViewById(R.id.getPicture);

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(takePicture);
            }
        });

        getPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPicture = new Intent(Intent.ACTION_GET_CONTENT);
                getPicture.setType("image/*");
                startActivity(getPicture);
            }
        });

        Intent addMeme = new Intent(getApplicationContext(), AddMemeCaption.class);
        startActivity(addMeme);
    }
}
