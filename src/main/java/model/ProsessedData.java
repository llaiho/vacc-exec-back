package model;

public class ProsessedData {

    private String id;              // bottle id
    private String vaccine;         // vaccine manufacturer
    private String[] usedOnDate;    // dates when used
    private String arrived;         // arrival date and time
    private String district;        // health care district

    public ProsessedData() {}


    public ProsessedData(String id, String vaccine, String[] usedOnDate, String arrived, String district ) {
        this.id             = id;
        this.vaccine        = vaccine;
        this.usedOnDate     = usedOnDate;
        this.arrived        = arrived;
        this.district       = district;
    }




    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String[] getUsedOnDate() {
        return usedOnDate;
    }

    public void setUsedOnDate(String[] usedOnDate) {
        this.usedOnDate = usedOnDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }


    public String getArrived() {
        return arrived;
    }

    public void setArrived(String arrived) {
        this.arrived = arrived;
    }
}
