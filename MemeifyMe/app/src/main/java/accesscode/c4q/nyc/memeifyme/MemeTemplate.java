package accesscode.c4q.nyc.memeifyme;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by s3a on 7/16/15.
 */
public class MemeTemplate {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private Integer res_ID;

    @DatabaseField
    private byte [] data;

    public MemeTemplate(){

    }

    public MemeTemplate (String title, Integer res_ID){
        this.title =title;
        this.res_ID =res_ID;

    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte [] data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRes_ID() {
        return res_ID;
    }

    public void setRes_ID(Integer res_ID) {
        this.res_ID = res_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString () {
        return id + " | " + title + " | " + res_ID;
    }
}