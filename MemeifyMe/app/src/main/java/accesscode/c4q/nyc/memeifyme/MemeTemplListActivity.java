package accesscode.c4q.nyc.memeifyme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by s3a on 7/17/15.
 */
public class MemeTemplListActivity extends Activity {

    private ListView listview;
    private Uri uri;
    private DatabaseHelper databaseHelper;
    private MemeTemplateAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO
        //setContentView(R.layout.memes_list);
        //listview = (ListView)findViewById(R.id.listView);

    }
}