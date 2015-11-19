
package de.simonmayrshofer.refugeemaps.pojos;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "Translations")
public class Translation extends Model {

    @Expose
    @Column(name = "Language")
    public String language;

    @Expose
    @Column(name = "Category")
    public String text;

    @Column(name = "Hotspot", onDelete = Column.ForeignKeyAction.CASCADE)
    public Hotspot hotspot;


    public Translation() {
        super();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * @return The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

}
