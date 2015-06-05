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
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

public class Gallery extends ActionBarActivity {

    private ViewSwitcher switcher;
    private ImageView camera_image_vanilla, camera_image_demotivational;
    private TextView caption_top_vanilla, caption_top_demotivational, caption_bottom_vanilla, caption_bottom_demotivational;
    private EditText editor_top, editor_bottom;
    private Button save, send;
    private ToggleButton toggle;
    private static final int RESULT_LOAD_IMG = 1;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_generator);

        switcher = (ViewSwitcher) findViewById(R.id.switcher);
        camera_image_vanilla = (ImageView) findViewById(R.id.camera_image_vanilla);
        camera_image_demotivational = (ImageView) findViewById(R.id.camera_image_demotivational);
        caption_top_vanilla = (TextView) findViewById(R.id.caption_top_vanilla);
        caption_top_demotivational = (TextView) findViewById(R.id.caption_top_demotivational);
        caption_bottom_vanilla = (TextView) findViewById(R.id.caption_bottom_vanilla);
        caption_bottom_demotivational = (TextView) findViewById(R.id.caption_bottom_demotivational);
        editor_top = (EditText) findViewById(R.id.editor_top);
        editor_bottom = (EditText) findViewById(R.id.editor_bottom);
        save = (Button) findViewById(R.id.save);
        send = (Button) findViewById(R.id.send);
        toggle = (ToggleButton) findViewById(R.id.toggle);

        Intent openGallery = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        openGallery.setType("image/*");
        startActivityForResult(openGallery, RESULT_LOAD_IMG);

        //Used ViewSwitcher to toggle between vanilla and demotivational meme views
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    switcher.showNext();
                else
                    switcher.showPrevious();
            }
        });

        //Automatically type what the user inputs into the TextViews, using onTextChanged method
        editor_top.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                caption_top_vanilla.setText(editor_top.getText().toString());
                caption_top_demotivational.setText(editor_top.getText().toString());
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
                caption_bottom_vanilla.setText(editor_bottom.getText().toString());
                caption_bottom_demotivational.setText(editor_bottom.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo Dison's image save
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo Jae's image send
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                Uri selectedImage = data.getData();
                getContentResolver().notifyChange(selectedImage, null);
                ContentResolver cr = getContentResolver();
                photo = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                camera_image_vanilla.setImageBitmap(photo);
                camera_image_demotivational.setImageBitmap(photo);
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
