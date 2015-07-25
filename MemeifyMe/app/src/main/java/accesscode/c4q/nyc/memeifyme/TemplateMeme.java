package accesscode.c4q.nyc.memeifyme;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * Created by c4q-joshelynvivas on 7/23/15.
 *
 * Create a table for the TemplateMeme using ORMLite
 *
 */

@DatabaseTable
public class TemplateMeme {

    @DatabaseField(generatedId = true) //Id is automatically generated for us
    private int id;

    @DatabaseField
    private int picture;

    public TemplateMeme() {
    }

    public TemplateMeme(int picture) {
        this.picture = picture;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

}
