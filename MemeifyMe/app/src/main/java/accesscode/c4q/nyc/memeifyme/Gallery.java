package accesscode.c4q.nyc.memeifyme;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Gallery extends ActionBarActivity {

    private ImageView camera_image;
    private TextView caption_top, caption_bottom;
    private EditText editor_top, editor_bottom;
    private static final int RESULT_LOAD_IMG = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        camera_image = (ImageView) findViewById(R.id.camera_image);
        caption_top = (TextView) findViewById(R.id.caption_top);
        caption_bottom = (TextView) findViewById(R.id.caption_bottom);
        editor_top = (EditText) findViewById(R.id.editor_top);
        editor_bottom = (EditText) findViewById(R.id.editor_bottom);


        if (camera_image.getDrawable() == null) {
            Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGallery, RESULT_LOAD_IMG);
        }

        //TextWatcher puts what the user is typing over top of the photo
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            //Bitmap is a way to store the photo inside ImageView
            Bitmap bitmap;
            try {
                // Get the Image from data
                Uri selectedImage = data.getData();
                //ContentResolver method provides applications access to the content model
                getContentResolver().notifyChange(selectedImage, null);
                ContentResolver cr = getContentResolver();
                //associating image URI to the bitmap variable
                bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                //loads the bitmap into the camera_image ImageView
                camera_image.setImageBitmap(bitmap);


            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                        .show();
            }
        }

    }

}
