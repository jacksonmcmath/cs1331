/**
* Coordinates class
*
* This class is used to represent an ordered pair of coordinates composed of
* latitude and longitude.
*
* @author jmcmath3
* @version 1.0
*/
public class Coordinates {
    private final double latitude;
    private final double longitude;
    /**
    * Returns value of latitude
    *
    * @return latitude
    */
    public double getLatitude() {
        return latitude;
    }
    /**
    * Returns value of longitude
    *
    * @return longitude
    */
    public double getLongitude() {
        return longitude;
    }
    /**
    * Constructor for Coordinates
    *
    * @param latitude  double representing latitude component
    * @param longitude double representing longitude component
    */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Coordinates)) {
            return false;
        }
        Coordinates o = (Coordinates) other;
        return latitude == o.latitude && longitude == o.longitude;
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString() {
        return String.format("latitude: %.2f, longitude: %.2f",
                latitude, longitude);
    }
}
