package accesscode.c4q.nyc.memeifyme;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sufeizhao on 7/13/15.
 */
public class SQLLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DB = "TemplateDB";
    public static final int VERSION = 1;
    private static SQLLiteOpenHelper INSTANCE;

    public static synchronized SQLLiteOpenHelper getInstance(Context context) {
        if(INSTANCE == null)
        {
            INSTANCE = new SQLLiteOpenHelper(context.getApplicationContext());
        }

        return INSTANCE;
    }

    public SQLLiteOpenHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertData(Resources res) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ImageEntry.TABLE_NAME, null, null);

        insertRow("cool", BitmapFactory.decodeResource(res, R.drawable.cool));
        insertRow("yao ming", BitmapFactory.decodeResource(res, R.drawable.yaoming));
        insertRow("evil plotting raccoon", BitmapFactory.decodeResource(res, R.drawable.evilplottingraccoon));
        insertRow("philosoraptor", BitmapFactory.decodeResource(res, R.drawable.philosoraptor));
        insertRow("socially awkward penguin", BitmapFactory.decodeResource(res, R.drawable.sociallyawkwardpenguin));
        insertRow("success kid", BitmapFactory.decodeResource(res, R.drawable.successkid));
        insertRow("scumbag steve", BitmapFactory.decodeResource(res, R.drawable.scumbagsteve));
        insertRow("one does not simply", BitmapFactory.decodeResource(res, R.drawable.onedoesnotsimply));
        insertRow("i don't always", BitmapFactory.decodeResource(res, R.drawable.idontalways));
    }

    private void insertRow(String image_name, Bitmap image) {
        SQLiteDatabase db = getWritableDatabase();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageByte = baos.toByteArray();
        ContentValues values = new ContentValues();
        values.put(ImageEntry.COLUMN_NAME_IMAGE_NAME, image_name);
        values.put(ImageEntry.COLUMN_NAME_IMAGEID, imageByte);

        db.insertOrThrow(
                ImageEntry.TABLE_NAME, null, values);
    }

    public byte[] loadData(String image_name) {
        String[] projection = {
                ImageEntry._ID,
                ImageEntry.COLUMN_NAME_IMAGE_NAME,
                ImageEntry.COLUMN_NAME_IMAGEID
        };

        SQLiteDatabase db = getWritableDatabase();
        byte[] image = null;

        Cursor cursor = db.query(ImageEntry.TABLE_NAME,
                projection, "image_name = ?", new String[] {image_name}, null, null, ImageEntry.COLUMN_NAME_IMAGE_NAME);
        while(cursor.moveToNext()) {
            image = cursor.getBlob(cursor.getColumnIndex(ImageEntry.COLUMN_NAME_IMAGEID));
        }

        cursor.close();
        return image;
    }

    public static abstract class ImageEntry implements BaseColumns {
        public static final String TABLE_NAME             = "template";
        public static final String COLUMN_NAME_IMAGE_NAME = "image_name";
        public static final String COLUMN_NAME_IMAGEID    = "imageID";
    }

    //Will be: CREATE TABLE person (_id INTEGER PRIMARY KEY,name TEXT,age INTEGER,favorite_color TEXT )
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ImageEntry.TABLE_NAME + " (" +
            ImageEntry._ID + " INTEGER PRIMARY KEY," +
            ImageEntry.COLUMN_NAME_IMAGE_NAME + " TEXT," +
            ImageEntry.COLUMN_NAME_IMAGEID + " BLOB" + " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ImageEntry.TABLE_NAME;
}
