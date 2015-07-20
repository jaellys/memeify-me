package accesscode.c4q.nyc.memeifyme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by c4q-john on 7/18/15.
 */
public class MyDBhelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE = "create table "+
            Constants.TABLE_NAME+" ("+
            Constants.KEY_ID+" integer primary key autoincrement, "+
            Constants.TEMPLATE_NAME+" text not null, "+
            Constants.TEMPLATE_IMG+" long);";


    public MyDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.v("MyDBhelper onCreate", "Creating all the tables");
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
        } catch(SQLiteException ex){
            Log.v("Create table Exception", ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.v("TaskDBAdapter", "Upgrading from version "+oldVersion+
                " to "+newVersion+", which will destroy all old data");
        sqLiteDatabase.execSQL("drop table if exists "+Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
