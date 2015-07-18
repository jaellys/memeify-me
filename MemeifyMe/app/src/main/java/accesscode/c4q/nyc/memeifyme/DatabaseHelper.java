package accesscode.c4q.nyc.memeifyme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by s3a on 7/16/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static DatabaseHelper dataHelper;
    private static final String MDB= "meme.db";
    private static final int VERSION = 1;

    public static synchronized DatabaseHelper getInstance (Context context){
        if (dataHelper ==null){
            dataHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return dataHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, MDB, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource,MemeTemplate.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,MemeTemplate.class,true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertNewRow(String title, Integer res_ID) throws SQLException {
        MemeTemplate memeTemplate = new MemeTemplate(title ,res_ID); //Meme Template
        getDao(MemeTemplate.class).create(memeTemplate);
    }
    public List<MemeTemplate> loadData() throws SQLException{
        return getDao(MemeTemplate.class).queryForAll();
    }
}