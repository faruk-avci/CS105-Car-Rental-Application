public class Camper extends Vehicle{
    public static String CAMPER_TYPE_TRAVEL_TRAILER = "Travel Trailer";
    public static String CAMPER_TYPE_VAN = "Van";
    public static String CAMPER_TYPE_MOTORHOME = "Motorhome";

    private String type;
    private int numberOfBeds;

    public Camper(String make, String model, int year, String licensePlateNumber, String transmission, int numberOfBeds, String type) {
        super(make, model, year, licensePlateNumber, transmission);
        this.type = type;
        this.numberOfBeds = numberOfBeds;
    }

    public String getType() {
        return type;
    }
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }


    @Override
    public double getDailyPrice() {
        return 10000.0;
    }

    @Override
    public boolean checkRentalRestrictions(Driver driver) {
        return driver.getAge() >= 25 && driver.getLicenseYears() >= 5;
    }

    @Override
    public String toString() {
        return "[" +
                "licensePlateNumber='" + getLicensePlateNumber() + '\'' +
                ", transmission='" + getTransmission() + '\'' +
                ", make='" + getMake() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", status='" + getStatus() + '\'' +
                ", type='" + type + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Camper camper = (Camper) obj;
        return getType().equals(camper.getType());
    }



}
