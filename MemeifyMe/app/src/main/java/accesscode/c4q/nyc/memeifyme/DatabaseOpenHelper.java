package accesscode.c4q.nyc.memeifyme;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by c4q-joshelynvivas on 7/23/15.
 */
public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper {
    private static final String MYDB = "mydb.db";
    private static final int VERSION = 1;

    //This is the constructor that is called by mHelper
    private static DatabaseOpenHelper mHelper;

    //It will evaluate if there is a database, and the version number
    private DatabaseOpenHelper(Context context) {
        super(context, MYDB, null, VERSION);
    }

    //This ensures there is only one helper in this class. We must prevent it from the MainActivity.
    //Hence, this will create a helper ONLY if there isn't one already
    public static DatabaseOpenHelper getInstance(Context context){
        if (mHelper == null) {
            mHelper = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return mHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        //We don't make the column. A class will do that for us based called TemplateMeme class

        try {
            TableUtils.createTable(connectionSource, TemplateMeme.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        //If the table exists, it will delete the entire table and then create it again.
        //Then it will call onCreate to make the table

        try {
            TableUtils.dropTable(connectionSource, TemplateMeme.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRow(int picture) {

        //Create instance of TemplateMeme
        TemplateMeme meme = new TemplateMeme(picture);

        try {
            getDao(TemplateMeme.class).create(meme);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


    }

}
