
package de.simonmayrshofer.refugeemaps.pojos;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "Positions")
public class Position extends Model {

    @Expose
    @Column(name = "Lat")
    public Double lat;

    @Expose
    @Column(name = "Lng")
    public Double lng;

    @Column(name = "Hotspot", onDelete = Column.ForeignKeyAction.CASCADE)
    public Hotspot hotspot;

    public Position() {
        super();
    }

    /**
     * @return The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

}
