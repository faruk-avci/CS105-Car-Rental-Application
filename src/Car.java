public class Car extends Vehicle{
    private int seatCount;

    public Car(String make, String model, int year, String licensePlateNumber, String transmission, int seatCount) {
        super(make, model, year, licensePlateNumber, transmission);
        this.seatCount = seatCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    @Override
    public double getDailyPrice() {
        return 1500.0;
    }

    @Override
    public boolean checkRentalRestrictions(Driver driver) {
        return driver.getAge() >= 18 && driver.getLicenseYears() >= 2;
    }

    @Override
    public String toString() {
        return "["+
                "licensePlateNumber='" + getLicensePlateNumber() + '\'' +
                ", transmission='" + getTransmission() + '\'' +
                ", make='" + getMake() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", status='" + getStatus() + '\'' +
                ", seatCount=" + seatCount +
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
        Car car = (Car) obj;
        return getSeatCount() == car.getSeatCount();
    }

}
