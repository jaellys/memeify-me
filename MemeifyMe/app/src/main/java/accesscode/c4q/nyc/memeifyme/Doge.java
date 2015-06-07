package accesscode.c4q.nyc.memeifyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by jaellysbales on 6/6/15.
 */

public class Doge extends ActionBarActivity implements View.OnTouchListener {

    private Button btn_share;
    private EditText et_doge_1, et_doge_2, et_doge_3, et_doge_4, et_doge_5;
    private TextView tv_doge_1, tv_doge_2, tv_doge_3, tv_doge_4, tv_doge_5;
    private ViewGroup rootLayout;
    private int delta_x;
    private int delta_y;

    // TODO: Allow option for text sizes and color picker.
    // TODO: How to distinguish click from touch?

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doge);
        rootLayout = (ViewGroup) findViewById(R.id.root);

        initializeViews();

        layoutParams();

        setTouchListeners();

        setTypeAssets();

        setTextListeners();

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                delta_x = X - lParams.leftMargin;
                delta_y = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - delta_x;
                layoutParams.topMargin = Y - delta_y;
                view.setLayoutParams(layoutParams);
                break;
        }
        rootLayout.invalidate();
        return true;
    }

    private void initializeViews() {
        btn_share = (Button) rootLayout.findViewById(R.id.btn_share);
        et_doge_1 = (EditText) rootLayout.findViewById(R.id.et_doge_1);
        et_doge_2 = (EditText) rootLayout.findViewById(R.id.et_doge_2);
        et_doge_3 = (EditText) rootLayout.findViewById(R.id.et_doge_3);
        et_doge_4 = (EditText) rootLayout.findViewById(R.id.et_doge_4);
        et_doge_5 = (EditText) rootLayout.findViewById(R.id.et_doge_5);

        tv_doge_1 = (TextView) rootLayout.findViewById(R.id.tv_doge_1);
        tv_doge_2 = (TextView) rootLayout.findViewById(R.id.tv_doge_2);
        tv_doge_3 = (TextView) rootLayout.findViewById(R.id.tv_doge_3);
        tv_doge_4 = (TextView) rootLayout.findViewById(R.id.tv_doge_4);
        tv_doge_5 = (TextView) rootLayout.findViewById(R.id.tv_doge_5);
    }

    private void layoutParams() {

        // Not the cleanest way to do this.
        FrameLayout.LayoutParams lp1 =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        FrameLayout.LayoutParams lp2 =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        FrameLayout.LayoutParams lp3 =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        FrameLayout.LayoutParams lp4 =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        FrameLayout.LayoutParams lp5 =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        // Position the views in layout, otherwise they will overlap.
        lp1.gravity = Gravity.TOP;
        lp2.gravity = Gravity.CENTER_HORIZONTAL;
        lp3.gravity = Gravity.LEFT;
        lp4.gravity = Gravity.CENTER_HORIZONTAL;
        lp5.gravity = Gravity.CENTER_VERTICAL;

        tv_doge_1.setLayoutParams(lp1);
        tv_doge_2.setLayoutParams(lp2);
        tv_doge_3.setLayoutParams(lp3);
        tv_doge_4.setLayoutParams(lp4);
        tv_doge_5.setLayoutParams(lp5);
    }

    private void setTouchListeners() {
        tv_doge_1.setOnTouchListener(this);
        tv_doge_2.setOnTouchListener(this);
        tv_doge_3.setOnTouchListener(this);
        tv_doge_4.setOnTouchListener(this);
        tv_doge_5.setOnTouchListener(this);
    }

    private void setTypeAssets() {
        Typeface comic_sans = Typeface.createFromAsset(getAssets(), "ComicSans.ttf");
        tv_doge_1.setTypeface(comic_sans);
        tv_doge_2.setTypeface(comic_sans);
        tv_doge_3.setTypeface(comic_sans);
        tv_doge_4.setTypeface(comic_sans);
        tv_doge_5.setTypeface(comic_sans);
    }

    private void setTextListeners() {
        //Automatically type what the user inputs into the TextViews, using onTextChanged method
        et_doge_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_doge_1.setText(et_doge_1.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_doge_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_doge_2.setText(et_doge_2.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_doge_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_doge_3.setText(et_doge_3.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_doge_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_doge_4.setText(et_doge_4.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_doge_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_doge_5.setText(et_doge_5.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}