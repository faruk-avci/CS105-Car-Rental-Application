public class SUV extends Car{
    private double towingCapacity;

    public SUV(String make, String model, int year, String licensePlateNumber, String transmission, int seatCount, double towingCapacity) {
        super(make, model, year, licensePlateNumber, transmission, seatCount);
        this.towingCapacity = towingCapacity;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
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
                ", seatCount=" + getSeatCount() +
                ", towingCapacity=" + towingCapacity +
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
        SUV suv = (SUV) obj;
        return getTowingCapacity() == suv.getTowingCapacity();
    }

    @Override
    public double getDailyPrice() {
        return 2500.0;
    }
    @Override
    public boolean checkRentalRestrictions(Driver driver) {
        return driver.getAge() >= 21 && driver.getLicenseYears() >= 3;
    }

}
