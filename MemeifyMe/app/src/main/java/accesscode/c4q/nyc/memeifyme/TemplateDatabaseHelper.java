package accesscode.c4q.nyc.memeifyme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kadeemmaragh on 7/16/15.
 */
public class TemplateDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String MYDB = "TemplateMemes";
    private static final int VERSION = 1;

    private static TemplateDatabaseHelper INSTANCE;

    public static synchronized TemplateDatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TemplateDatabaseHelper(context);
        }
        return INSTANCE;
    }

    public TemplateDatabaseHelper(Context context) {
        super(context, MYDB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, TemplateInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, TemplateInfo.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {


        try {
            deleteAll();
            INSTANCE.insertRow(R.drawable.cool,"Cool");
            INSTANCE.insertRow(R.drawable.yaoming, "Yao Ming");
            INSTANCE.insertRow(R.drawable.evilplottingraccoon, "Evil Plotting Raccoon");
            INSTANCE.insertRow(R.drawable.philosoraptor, "Philosoraptor");
            INSTANCE.insertRow(R.drawable.sociallyawkwardpenguin, "Socially Awkward Penguin");
            INSTANCE.insertRow(R.drawable.successkid, "Success Kid");
            INSTANCE.insertRow(R.drawable.scumbagsteve, "Scumbag Steve");
            INSTANCE.insertRow(R.drawable.onedoesnotsimply, "One Does Not Simply");
            INSTANCE.insertRow(R.drawable.idontalways, "I Don't Always");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("insertError", e.toString());

        }


    }

    public void deleteAll() throws SQLException {
        List<TemplateInfo> list = getDao(TemplateInfo.class).queryForAll();
        getDao(TemplateInfo.class).delete(list);
    }

    public void insertRow(int resourceID, String description) throws SQLException {
        TemplateInfo templateMeme = new TemplateInfo(resourceID, description);
        getDao(TemplateInfo.class).createIfNotExists(templateMeme);


    }

    //Returns a list of all the objects in the table
    public List<TemplateInfo> loadData() throws SQLException {
        return getDao(TemplateInfo.class).queryForAll();
    }

}

