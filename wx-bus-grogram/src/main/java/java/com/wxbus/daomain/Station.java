package java.com.wxbus.daomain;

public class Station {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column station.station_id
     *
     * @mbggenerated
     */
    private Integer stationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column station.station_name
     *
     * @mbggenerated
     */
    private String stationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column station.station_coord
     *
     * @mbggenerated
     */
    private String stationCoord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column station.station_status
     *
     * @mbggenerated
     */
    private Integer stationStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column station.station_describe
     *
     * @mbggenerated
     */
    private String stationDescribe;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column station.station_id
     *
     * @return the value of station.station_id
     *
     * @mbggenerated
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column station.station_id
     *
     * @param stationId the value for station.station_id
     *
     * @mbggenerated
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column station.station_name
     *
     * @return the value of station.station_name
     *
     * @mbggenerated
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column station.station_name
     *
     * @param stationName the value for station.station_name
     *
     * @mbggenerated
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column station.station_coord
     *
     * @return the value of station.station_coord
     *
     * @mbggenerated
     */
    public String getStationCoord() {
        return stationCoord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column station.station_coord
     *
     * @param stationCoord the value for station.station_coord
     *
     * @mbggenerated
     */
    public void setStationCoord(String stationCoord) {
        this.stationCoord = stationCoord == null ? null : stationCoord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column station.station_status
     *
     * @return the value of station.station_status
     *
     * @mbggenerated
     */
    public Integer getStationStatus() {
        return stationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column station.station_status
     *
     * @param stationStatus the value for station.station_status
     *
     * @mbggenerated
     */
    public void setStationStatus(Integer stationStatus) {
        this.stationStatus = stationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column station.station_describe
     *
     * @return the value of station.station_describe
     *
     * @mbggenerated
     */
    public String getStationDescribe() {
        return stationDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column station.station_describe
     *
     * @param stationDescribe the value for station.station_describe
     *
     * @mbggenerated
     */
    public void setStationDescribe(String stationDescribe) {
        this.stationDescribe = stationDescribe == null ? null : stationDescribe.trim();
    }
}