package accesscode.c4q.nyc.memeifyme;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SaveMeme {
    Bitmap bm;
    public void saveMeme(ImageView imageView, String imgName, ContentResolver c) {
        imageView.buildDrawingCache();
        Bitmap bm = imageView.getDrawingCache();

        OutputStream fOut = null;
        String strDirectory = Environment.getExternalStorageDirectory().toString();

        File f = new File(strDirectory, imgName);
        try {
            fOut = new FileOutputStream(f);

            // Compress image
            bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();

            // Update image to gallery
            MediaStore.Images.Media.insertImage(c,
                    f.getAbsolutePath(), f.getName(), f.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}