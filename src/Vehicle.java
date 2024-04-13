public abstract class Vehicle {
    public static String  STATUS_AVAILABLE="Available";
    public static String STATUS_RENTED="Rented";
    private String licensePlateNumber;
    private String transmission;
    private String make;
    private String model;
    private int year;
    private String status = STATUS_AVAILABLE;

    public Vehicle(String make, String model, int year, String licensePlateNumber, String transmission) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlateNumber = licensePlateNumber;
        this.transmission = transmission;
    }

    // Getters
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
    public String getTransmission() {
        return transmission;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public String getStatus() {
        return status;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setYear(int year) {
        this.year = year;
    }

    // Overrides
    @Override
    public String toString() {
        return  "["+
                "licensePlateNumber='" + licensePlateNumber + '\'' +
                ", transmission='" + transmission + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", status='" + status + '\'' +
                "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;
        return licensePlateNumber.equals(vehicle.licensePlateNumber) && transmission.equals(vehicle.transmission) && make.equals(vehicle.make) && model.equals(vehicle.model) && year == vehicle.year && status.equals(vehicle.status);
    }

    // Abstract methods
    public abstract double getDailyPrice();
    public abstract boolean checkRentalRestrictions(Driver driver);
}
