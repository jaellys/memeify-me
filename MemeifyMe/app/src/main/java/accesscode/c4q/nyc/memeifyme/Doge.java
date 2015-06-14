package accesscode.c4q.nyc.memeifyme;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jaellysbales on 6/6/15.
 */

public class Doge extends ActionBarActivity implements View.OnTouchListener {

    private Button btn_share;
    private EditText et_doge_1, et_doge_2, et_doge_3, et_doge_4, et_doge_5;
    private ImageView doge;
    private TextView tv_doge_1, tv_doge_2, tv_doge_3, tv_doge_4, tv_doge_5;
    private ViewGroup rootLayout;
    private int delta_x;
    private int delta_y;
    private static final int RESULT_LOAD_IMG = 1;
    private Bitmap photo;
    private String mashape_key;

    // TODO: Allow option for text sizes and color picker.
    // TODO: How to distinguish click from touch?

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doge);
        rootLayout = (ViewGroup) findViewById(R.id.root);

        Intent openGallery = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        openGallery.setType("image/*");
        startActivityForResult(openGallery, RESULT_LOAD_IMG);
        mashape_key = "";

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
        doge = (ImageView) rootLayout.findViewById(R.id.doge);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                final Uri selectedImage = data.getData();
                getContentResolver().notifyChange(selectedImage, null);
                ContentResolver cr = getContentResolver();
                photo = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                doge.setImageBitmap(photo);

                String imagePath = getImagePath(selectedImage);
                AsyncGoFetchToken goFetch = new AsyncGoFetchToken();
                goFetch.execute(imagePath);

            } catch (Exception e) {
                Log.d("exception: ", e.toString());
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getImagePath(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
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

    /**
     * Created by Ramona Harrison
     * on 6/11/15.
     */

    public class AsyncGoFetchToken extends AsyncTask<String, Void, String> {

        /**
         * The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute()
         */

        protected String doInBackground(String... imageUris) {

            try {
                return requestImageDescription(imageUris[0]);
            } catch (UnirestException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        private String requestImageDescription(String filepath) throws UnirestException, IOException {
            // These code snippets use an open-source library. http://unirest.io/java
            HttpResponse<InputStream> tokenResponse = Unirest.post("https://camfind.p.mashape.com/image_requests")
                    .header("X-Mashape-Key", mashape_key)
                    .field("image_request[image]", new File(filepath))
                    .field("image_request[locale]", "en_US").asBinary();


            String token = extractTokenFromJsonStream(tokenResponse.getBody());
            return token;

        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(String token) {
            startDelay(token);

        }

        public String extractTokenFromJsonStream(InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                return readTokenMessage(reader);
            } finally {
                reader.close();
            }

        }

        public String readTokenMessage(JsonReader reader) throws IOException {
            String token = "";

            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("token")) {
                    token = reader.nextString();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return token;
        }

        private void startDelay(String token) {
            final String theToken = token;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AsyncGoFetchMessage goFetchMessage = new AsyncGoFetchMessage();
                    goFetchMessage.execute(theToken);
                }
            }, 10000);
        }

    }

    public class AsyncGoFetchMessage extends AsyncTask<String, Void, String> {

        /**
         * The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute()
         */

        protected String doInBackground(String... imageUris) {

            try {
                return requestImageDescription(imageUris[0]);
            } catch (UnirestException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        private String requestImageDescription(String token) throws UnirestException, IOException {

            String responseUrl = "https://camfind.p.mashape.com/image_responses/" + token;
            // These code snippets use an open-source library. http://unirest.io/java
            HttpResponse<InputStream> response = Unirest.get(responseUrl)
                    .header("X-Mashape-Key", mashape_key)
                    .header("Accept", "application/json")
                    .asBinary();

            String description = extractDescriptionFromJsonStream(response.getRawBody());
            return description;

        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(String description) {

            ArrayList<TextView> textViews = new ArrayList<>();
            textViews.add(tv_doge_1);
            textViews.add(tv_doge_2);
            textViews.add(tv_doge_3);
            textViews.add(tv_doge_4);
            textViews.add(tv_doge_5);

            String dogeTalk[] = {"such", "so", "many", "very", "wow"};
            String words[] = description.split(" ");
            int i;
            for (i = 0; i < words.length && i < textViews.size(); i++) {
                textViews.get(i).setText(dogeTalk[i] + " " + words[i]);
            }
            while (i < textViews.size()) {
                textViews.get(i).setText(dogeTalk[i]);
                i++;
            }

        }

        public String extractDescriptionFromJsonStream(InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                DescriptionMessage message = readDescriptionMessage(reader);
                if (message.getStatus().equals("completed")) {
                    return message.toString();
                } else {
                    Log.d("status", message.getStatus() + " " + message.getReason());
                    return "mystery enigma puzzle tricky";
                }

            } finally {
                reader.close();
            }

        }

        public DescriptionMessage readDescriptionMessage(JsonReader reader) throws IOException {
            String status = "";
            String name = "";
            String reason = "";

            reader.beginObject();
            while (reader.hasNext()) {
                String field = reader.nextName();
                if (field.equals("status")) {
                    status = reader.nextString();
                } else if (field.equals("name")) {
                    name = reader.nextString();
                } else if (field.equals("reason")) {
                    reason = reader.nextString();
                } else {
                    reader.skipValue();
                }
            }

            reader.endObject();
            return new DescriptionMessage(status, name, reason);
        }
    }
}