import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver {
    private String name;
    private int age;
    private String driverLicenseNumber;
    private int driverLicenseYear;

    public Driver(String name, int age, String driverLicenseNumber, int driverLicenseYear) {
        this.name = name;
        this.age = age;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverLicenseYear = driverLicenseYear;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }
    public int getDriverLicenseYear() {
        return driverLicenseYear;
    }
    public double getLicenseYears() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        int currentYear = Integer.parseInt(dateFormat.format(new Date()));
        double diffInYears = currentYear - getDriverLicenseYear();
        return diffInYears;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }
    public void setDriverLicenseYear(int driverLicenseYear) {
        this.driverLicenseYear = driverLicenseYear;
    }

    // Overrides
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Driver driver = (Driver) obj;
        return name.equals(driver.name) && age == driver.age && driverLicenseNumber.equals(driver.driverLicenseNumber) && driverLicenseYear == driver.driverLicenseYear;
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", driverLicenseYear=" + driverLicenseYear +
                "]";
    }

}
