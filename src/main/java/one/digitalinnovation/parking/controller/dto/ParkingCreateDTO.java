package one.digitalinnovation.parking.controller.dto;

public class ParkingCreateDTO {

    private String license;
    private String state;
    private String model;
    private String color;

    private String parkingSpot;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String createParkingSpot(int lenLetters, int lenNumbers) {
        String Letter_Part = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Number_Part = "0123456789";

        StringBuilder s1 = new StringBuilder(lenLetters);
        StringBuilder s2 = new StringBuilder(lenNumbers);
        for (int i=0; i<lenLetters; i++) {
            int ch = (int)(Letter_Part.length()*Math.random());
            s1.append(Letter_Part.charAt(ch));
        }

        for (int i=0; i<lenNumbers; i++){
            int no = (int)(Number_Part.length()*Math.random());
            s2.append(Number_Part.charAt(no));
        }
        String parkingSpot = (s1 + "-" + s2).toString();
        System.out.println(parkingSpot);

        return parkingSpot;
    }

    public String getParkingSpot() {
        createParkingSpot(2, 3);
        return parkingSpot;
    }


}
