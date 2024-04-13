import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Reservation {
    public static double LATE_RETURN_DAILY_PENALTY_AMOUNT = 1500.0;
    public static String STATUS_COMPLETED = "completed";
    public static String STATUS_ACTIVE = "active";
    public static double DIFFERENT_RETURN_LOCATION_FEE = 2000.0;
    private static final String SCHOOL_NUMBER = "034113";

    private String reservationNumber;
    private String pickupLocation;
    private String returnLocation;
    private Date returnDate;
    private Date pickUpDate;
    private Date actualReturnDate;
    private double dayCount;
    private double totalAmount;
    private double extraPayment;
    private Driver driver;
    private Vehicle vehicle;
    private Promotion promotion;
    private String status = STATUS_ACTIVE;
    private static Random random = new Random();

    public Reservation(Vehicle vehicle, Driver driver,  Date pickupDate, Date returnDate, String pickupLocation, String returnLocation) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
        this.pickUpDate = pickupDate;
        this.returnDate = returnDate;
        this.reservationNumber = generateReservationNumber();
        this.dayCount = calculateDayCount(pickupDate, returnDate);
        this.extraPayment = calculateExtraPayment();
        this.totalAmount = calculateTotalAmount(vehicle, dayCount, promotion, extraPayment );
        this.promotion = null;
        this.actualReturnDate = null;
    }
    public Reservation(Vehicle vehicle, Driver driver,  Date pickupDate, Date returnDate, String pickupLocation, String returnLocation, Promotion promotion) {

        this.vehicle = vehicle;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
        this.pickUpDate = pickupDate;
        this.returnDate = returnDate;
        this.reservationNumber = generateReservationNumber();
        this.dayCount = calculateDayCount(pickupDate, returnDate);
        this.extraPayment = calculateExtraPayment();
        this.totalAmount = calculateTotalAmount(vehicle, dayCount, promotion,extraPayment);
        this.promotion = promotion;
    }
    // Getters
    public String getReservationNumber() {
        return reservationNumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
    public Date getActualReturnDate() {
        return actualReturnDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public Date getPickUpDate() {
        return pickUpDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public double getDayCount() {
        return dayCount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    private static String generateReservationNumber() {
        int randomNumberPart1 = 10000 + random.nextInt(90000);
        return "R-" + SCHOOL_NUMBER + "-" + randomNumberPart1;
    }
    private static double calculateDayCount(Date pickupDate, Date returnDate) {
        long differenceInMillis = returnDate.getTime() - pickupDate.getTime();
        double dayCount = differenceInMillis / (1000.0 * 60 * 60 * 24);
        return dayCount;
    }

    private double calculateTotalAmount(Vehicle vehicle, double dayCount, Promotion promotion, double extraPayment) {
        double totalAmount = vehicle.getDailyPrice() * dayCount + extraPayment;
        if (promotion != null) {
            if(promotion.isUsed()){
                System.out.println("Promotion has already been used. Standart prices applied");
            } else {
                System.out.println("Promotion applied successfully");
                double discount = totalAmount * promotion.getDiscountRate() / 100;
                totalAmount -= discount;
                promotion.setUsed(true);
            }
        }
        return totalAmount;
    }
    private double calculateExtraPayment() {
        double extraPayment = 0.0;
        if (!pickupLocation.equals(returnLocation)) {
            extraPayment = DIFFERENT_RETURN_LOCATION_FEE;
            System.out.println("Different return location fee applied: " + "(" + DIFFERENT_RETURN_LOCATION_FEE + ")");
        }
        return extraPayment;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return "RESERVATION SUMMARY:\n" +
                "reservationNumber='" + reservationNumber + "'\n" +
                "pickupLocation='" + pickupLocation + "'\n" +
                "returnLocation='" + returnLocation + "'\n" +
                "returnDate=" + dateFormat.format(returnDate) + "\n" +
                "pickUpDate=" + dateFormat.format(pickUpDate) + "\n" +
                "dayCount=" + dayCount + "\n" +
                "totalAmount=" + totalAmount + "\n" +
                "driver=" + driver + "\n" +
                "vehicle=" + vehicle + "\n";
    }

}
