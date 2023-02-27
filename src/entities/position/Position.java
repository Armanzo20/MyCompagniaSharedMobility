package entities.position;

import java.math.BigDecimal;

public class Position {
    private BigDecimal longitude; //la longitudine varia da -180 a +180 gradi
    private BigDecimal latitude; //la latitudine varia da -90 a +90
    private static int SCALE = 4;
    private static BigDecimal LONGITUDE_MIN = new BigDecimal(-180).setScale(SCALE);
    private static BigDecimal LONGITUDE_MAX = new BigDecimal(180).setScale(SCALE);
    private static BigDecimal LATITUDE_MIN = new BigDecimal(-90).setScale(SCALE);
    private static BigDecimal LATITUDE_MAX = new BigDecimal(90).setScale(SCALE);




    public Position(double longitude, double latitude) throws Exception {
        this.longitude.setScale(SCALE);
        this.latitude.setScale(SCALE);
        setLongitude(longitude);
        setLatitude(latitude);
    }


    //4 cifre decimali e compreso tra -180 e 180
    public void setLongitude(double doubleLongitude) throws Exception {
        BigDecimal longitude = new BigDecimal(doubleLongitude);
        if (longitude.compareTo(LONGITUDE_MAX) > 0  || longitude.compareTo(LONGITUDE_MIN) < 0 ) {
            throw new IllegalArgumentException("The argument is not within the range [" + LONGITUDE_MIN + ", " + LONGITUDE_MAX + "]");
        }
        if (longitude.scale() < SCALE) {
            throw new IllegalArgumentException("The argument doesn't have at least " + SCALE + " decimal places after the comma");
        }
        this.longitude = longitude;
    }


    //almeno 4 cifre decimali, compreso tra 90 e -90
    public void setLatitude(double doubleLatitude) throws Exception {

        if (longitude.compareTo(LATITUDE_MAX) > 0  || longitude.compareTo(LONGITUDE_MIN) < 0  ) {
            throw new IllegalArgumentException("The argument is not within the range [" + LATITUDE_MAX + ", " + LATITUDE_MIN + "]");
        }
        BigDecimal latitude = new BigDecimal(doubleLatitude);
        if (longitude.scale() < SCALE) {
            throw new IllegalArgumentException("The argument doesn't have at least " + SCALE + " decimal places after the comma");
        }
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public static BigDecimal getLongitudeMin() {
        return LONGITUDE_MIN;
    }

    public static BigDecimal getLongitudeMax() {
        return LONGITUDE_MAX;
    }

    public static BigDecimal getLatitudeMin() {
        return LATITUDE_MIN;
    }

    public static BigDecimal getLatitudeMax() {
        return LATITUDE_MAX;
    }

    public static int getSCALE() {
        return SCALE;
    }
}
