package web.models;

public class Car {
    private String colour;
    private int plateNumber;
    private String model;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car(String colour, int plateNumber, String model) {
        this.colour = colour;
        this.plateNumber = plateNumber;
        this.model = model;
    }
    public Car() {

    }
}
