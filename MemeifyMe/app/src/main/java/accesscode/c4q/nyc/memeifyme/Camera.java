package accesscode.c4q.nyc.memeifyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Camera extends ActionBarActivity {

    private ImageView camera_image;
    private TextView top_caption, bottom_caption;
    private EditText top_editor, bottom_editor;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //start the image capture Intent
        startActivityForResult(openCamera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        camera_image = (ImageView) findViewById(R.id.camera_image);
        top_caption = (TextView) findViewById(R.id.top_caption);
        bottom_caption = (TextView) findViewById(R.id.bottom_caption);
        top_editor = (EditText) findViewById(R.id.top_editor);
        bottom_editor = (EditText) findViewById(R.id.bottom_editor);

        top_editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                top_caption.setText(top_editor.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bottom_editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bottom_caption.setText(bottom_editor.getText().toString());

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
