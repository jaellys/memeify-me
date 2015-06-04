package accesscode.c4q.nyc.memeifyme;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
    private TextView top_caption, bottom_caption;
    private EditText top_editor, bottom_editor;
    private static final int RESULT_LOAD_IMG = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(openGallery, RESULT_LOAD_IMG);

        camera_image = (ImageView) findViewById(R.id.camera_image);
        top_caption = (TextView) findViewById(R.id.top_caption);
        bottom_caption = (TextView) findViewById(R.id.bottom_caption);
        top_editor = (EditText) findViewById(R.id.top_editor);
        bottom_editor = (EditText) findViewById(R.id.bottom_editor);

        //TextWatcher puts what the user is typing over top of the photo
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK){
        //Bitmap is a way to store the photo inside ImageView
        Bitmap bitmap;
        try {
                // Get the Image from data
                Uri selectedImage = data.getData();
            //TODO: ContentResolver method helps with error-checking and makes for simpler code??
            getContentResolver().notifyChange(selectedImage, null);
            ContentResolver cr = getContentResolver();
                //associating image URI to the bitmap variable
                bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                //loads the bitmap into the camera_image ImageView
                camera_image.setImageBitmap(bitmap);

            //TODO: do I need any of this code?
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                // Get the cursor
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                // Move to first row
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imgDecodableString = cursor.getString(columnIndex);
//                cursor.close();
//                ImageView imgView = (ImageView) findViewById(R.id.camera_image);
//                // Set the Image in ImageView after decoding the String
//                imgView.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));
//
//            } else {
//                Toast.makeText(this, "You haven't picked Image",
//                        Toast.LENGTH_LONG).show();
//            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

}
}
