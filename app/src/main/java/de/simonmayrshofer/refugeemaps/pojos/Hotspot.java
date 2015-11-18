
package de.simonmayrshofer.refugeemaps.pojos;

import java.util.ArrayList;
import java.util.List;

public class Hotspot {

    private String category;
    private String name;
    private String openingHours;
    private String address;
    private Position position;
    private List<Translation> translations = new ArrayList<Translation>();



    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The openingHours
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * 
     * @param openingHours
     *     The openingHours
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The translations
     */
    public List<Translation> getTranslations() {
        return translations;
    }

    /**
     * 
     * @param translations
     *     The translations
     */
    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

}
