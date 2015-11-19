
package de.simonmayrshofer.refugeemaps.pojos;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


@Table(name = "Hotspots")
public class Hotspot extends Model {

    @Expose
    @Column(name = "Category")
    public String category;

    @Expose
    @Column(name = "Name")
    public String name;

    @Expose
    @Column(name = "OpeningHours")
    public String openingHours;

    @Expose
    @Column(name = "Address")
    public String address;

    @Expose
//    @Column(name = "Position")
    public Position position;

    @Expose
    public List<Translation> translations = new ArrayList<Translation>();

    public Hotspot() {
        super();
    }

    //--- Getters for Database Foreign Keys (Active Android, ORM) ----------------------------------

    public List<Translation> getTranslationList() {
        return getMany(Translation.class, "Hotspot");
    }

    public Position getPositionObject() {
        return load(Position.class, this.getId());
//        return new Select().from(Position.class).where("Hotspot = ?", this.getId()).executeSingle();
    }

    //--- Serializable Getters/Setters (needed for Retrofit) ---------------------------------------


    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The openingHours
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * @param openingHours The openingHours
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return The translations
     */
    public List<Translation> getTranslations() {
        return translations;
    }

    /**
     * @param translations The translations
     */
    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

}
