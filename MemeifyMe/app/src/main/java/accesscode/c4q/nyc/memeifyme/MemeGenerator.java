package accesscode.c4q.nyc.memeifyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MemeGenerator extends ActionBarActivity {

    private ImageView camera_image;
    private TextView caption_top, caption_bottom;
    private EditText editor_top, editor_bottom;
    private Button save, send, vanilla, demotivational;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_generator);

        camera_image = (ImageView) findViewById(R.id.camera_image);
        caption_top = (TextView) findViewById(R.id.caption_top);
        caption_bottom = (TextView) findViewById(R.id.caption_bottom);
        editor_top = (EditText) findViewById(R.id.editor_top);
        editor_bottom = (EditText) findViewById(R.id.editor_bottom);
        save = (Button) findViewById(R.id.save);
        send = (Button) findViewById(R.id.send);
        vanilla = (Button) findViewById(R.id.vanilla);
        demotivational = (Button) findViewById(R.id.demotivational);

        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCamera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        editor_top.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caption_top.setText(editor_top.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editor_bottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caption_bottom.setText(editor_bottom.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            camera_image.setImageBitmap(photo);
        }
    }

}
