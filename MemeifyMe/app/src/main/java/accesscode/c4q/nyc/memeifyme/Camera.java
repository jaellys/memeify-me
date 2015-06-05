package accesscode.c4q.nyc.memeifyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

public class Camera extends ActionBarActivity {

    private ViewSwitcher switcher;
    private ImageView camera_image_vanilla, camera_image_demotivational;
    private TextView caption_top_vanilla, caption_top_demotivational, caption_bottom_vanilla, caption_bottom_demotivational;
    private EditText editor_top, editor_bottom;
    private Button btn_save, btn_share;
    private ToggleButton toggle;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
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
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_share = (Button) findViewById(R.id.btn_share);
        toggle = (ToggleButton) findViewById(R.id.toggle);

        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCamera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

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

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout meme = (FrameLayout) findViewById(R.id.meme);
                SaveMeme sm = new SaveMeme();
                Bitmap bitmap = sm.loadBitmapFromView(meme);
                sm.saveMeme(bitmap, "meme", getContentResolver());
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory()))); // Dison is fixing this.
                Toast.makeText(getApplicationContext(), "Meme saved!", Toast.LENGTH_LONG).show();
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FIXME: Bitmap continues to be null here.
                FrameLayout meme = (FrameLayout) findViewById(R.id.meme);
                SaveMeme sm = new SaveMeme();
                Bitmap bitmap = sm.loadBitmapFromView(meme);
                sm.saveMeme(bitmap, "meme", getContentResolver());

                String pathBm = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "meme", null);
                Uri bmUri = Uri.parse(pathBm);

                Intent attachIntent = new Intent(Intent.ACTION_SEND);
                attachIntent.putExtra(Intent.EXTRA_STREAM, bmUri);
                attachIntent.setType("image/png");
                startActivity(attachIntent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            camera_image_vanilla.setImageBitmap(photo);
            camera_image_demotivational.setImageBitmap(photo);
        }
    }
}