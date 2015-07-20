package accesscode.c4q.nyc.memeifyme.database;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by c4q-john on 7/18/15.
 */
public class MyDB {

    private SQLiteDatabase db;
    private final Context context;
    private final MyDBhelper dbhelper;
    public MyDB(Context c){
        context = c;
        dbhelper = new MyDBhelper(context,Constants.DATABASE_NAME, null,Constants.DATABASE_VERSION);
    }
    public void close() {
        db.close();
    }

    public void open() throws SQLiteException {
        try{
            db = dbhelper.getWritableDatabase();
        } catch (SQLiteException ex){
            Log.v("Open db exceptionCaught", ex.getMessage());
            db = dbhelper.getReadableDatabase();
        }
    }

    //code to add imgs and their corresponding names to created database
    public void insertUser(String imgName, Bitmap img){
        SQLiteDatabase db               =   dbhelper.getWritableDatabase();

//        String delSql                       =   "DELETE FROM ACCOUNTS";
//        SQLiteStatement delStmt         =   db.compileStatement(delSql);
//        delStmt.execute();

        String sql                      =   "INSERT INTO TemplatesTable (_id,img_name,template_img) VALUES(?,?,?)";
        SQLiteStatement insertStmt      =   db.compileStatement(sql);
        insertStmt.clearBindings();
        insertStmt.bindString(1, Constants.KEY_ID);

        insertStmt.bindString(2,imgName);

        byte[] data = getBitmapAsByteArray(img);

        insertStmt.bindBlob(3, data);
        //insertStmt.executeInsert();
        db.close();
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    //class is supposed to retreive database data but specifications only asked for storage, retrieval has been left
    //as it was.
//    public Cursor getCurrentAccount() {
//        SQLiteDatabase db       =   dbhelper.getWritableDatabase();
//        String sql              =   "SELECT * FROM TemplatesTable";
//        Cursor cursor           =   db.rawQuery(sql, new String[] {});
//
//        if(cursor.moveToFirst()){
//            String name             = cursor.getString(0);
//            String img           = cursor.getString(1);
//
//        }
//        if (cursor != null && !cursor.isClosed()) {
//            cursor.close();
//        }
//        db.close();
//        if(cursor.getCount() == 0){
//            return null;
//        } else {
//
//            Cursor c = db.query(Constants.TABLE_NAME,null,null,null,null,null,null);
//
//            return c;
//        }
//    }






}
