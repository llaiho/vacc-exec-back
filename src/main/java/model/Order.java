package model;

public class Order {

    private String id;
    private int orderNumber;
    private String responsiblePerson;
    private String vaccine;
    private int injections;
    private String arrived;

    public Order() {}


    public Order(String id, int orderNumber, String responsiblePerson, String vaccine, int injections, String arrived) {
        this.id                 = id;
        this.orderNumber        = orderNumber;
        this.responsiblePerson  = responsiblePerson;
        this.vaccine            = vaccine;
        this.injections         = injections;
        this.arrived            = arrived;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public int getInjections() {
        return injections;
    }

    public void setInjections(int injections) {
        this.injections = injections;
    }

    public String getArrived() {
        return arrived;
    }

    public void setArrived(String arrived) {
        this.arrived = arrived;
    }
}













