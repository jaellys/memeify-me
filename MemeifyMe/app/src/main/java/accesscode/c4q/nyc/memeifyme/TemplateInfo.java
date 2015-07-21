package accesscode.c4q.nyc.memeifyme;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by kadeemmaragh on 7/17/15.
 */
@DatabaseTable(tableName = "Template Memes")
public class TemplateInfo {


    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private Integer resourceID;

    @DatabaseField
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }


    public TemplateInfo(int resourceID, String description) {
        this.resourceID = resourceID;
        this.description = description;
    }

    public TemplateInfo(){

    }


    @Override
    public String toString() {
        return description;
    }
}
