import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class RentalCompany {
    private String name;
    private ArrayList<Vehicle> VehicleList;
    private ArrayList<Reservation> ReservationList;

    // Constructor
    public RentalCompany(String name) {
        this.name = name;
        this.VehicleList = new ArrayList<>();
        this.ReservationList = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void addVehicle(Vehicle vehicle) {
        VehicleList.add(vehicle);
    }

    public void printActiveReservations(){
        System.out.println("Active Reservations:");
        for (Reservation reservation : ReservationList) {
            if(reservation.getStatus().equals(Reservation.STATUS_ACTIVE)){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");// assume this is your reservation object
                System.out.println(reservation.getReservationNumber() + " " +
                        reservation.getVehicle().getMake() + "/" +
                        reservation.getVehicle().getModel() + " " +
                        "return date:" + dateFormat.format(reservation.getReturnDate()));
            }
        }
    }

    // Method to print completed reservations
    public void printCompletedReservations(){
        System.out.println("Completed Reservations:");
        for (Reservation reservation : ReservationList) {
            if(reservation.getStatus().equals(Reservation.STATUS_COMPLETED)){
                System.out.println(reservation.getReservationNumber());
            }
        }
    }

    // Method to print rented vehicles
    public void printRentedVehicles(){
        System.out.println("Rented Vehicles:");
        for (Vehicle vehicle : VehicleList) {
            if(vehicle.getStatus().equals(Vehicle.STATUS_RENTED)){
                System.out.println(vehicle);
            }
        }
    }

    public Reservation makeReservation(Vehicle vehicle, Driver driver, Date pickUpDate, Date returnDate, String pickUpLocation, String returnLocation, Promotion promotion) {
        if(vehicle.checkRentalRestrictions(driver)) {
            if (vehicle.getStatus().equals(vehicle.STATUS_AVAILABLE)) {
                System.out.println("Reservation prerequisites have been successfully passed");
                Reservation reservation;
                if (promotion != null) {
                    reservation = new Reservation(vehicle, driver, pickUpDate, returnDate, pickUpLocation, returnLocation,  promotion);
                }else{
                    reservation = new Reservation(vehicle, driver, pickUpDate, returnDate, pickUpLocation, returnLocation);
                }
                System.out.println("Reservation created successfully");
                vehicle.setStatus(vehicle.STATUS_RENTED);
                ReservationList.add(reservation);
                System.out.println(reservation);
                return reservation;

            } else {
                System.out.println("Reservation request rejected. This Car has already been rented!\n");
                return null;
            }
        }else{
            System.out.println("The driver does not meet the rental restrictions for this " + vehicle.getClass().getSimpleName());
            return null;
        }
    }

    public boolean returnVehicle(String reservationNumber) {
        Reservation reservation = null;
        for (Reservation res : ReservationList) {
            if (res.getReservationNumber().equals(reservationNumber)) {
                reservation = res;
                break;
            }
        }

        if (reservation == null) {
            System.out.println("RESERVATION NOT FOUND");
            return false;
        }

        if (reservation.getStatus().equals(Reservation.STATUS_COMPLETED)) {
            System.out.println("RESERVATION ALREADY COMPLETED");
            return false;
        }

        Date systemDate = new Date();
        reservation.setActualReturnDate(systemDate);
        long diffMillis = systemDate.getTime() - reservation.getPickUpDate().getTime();
        int actualDayCount = (int) (diffMillis / (1000 * 60 * 60 * 24));

        double extraPayment = 0.0;
        if (actualDayCount > reservation.getDayCount()) {
            int extraDays = actualDayCount - (int) reservation.getDayCount();
            System.out.println(extraDays);
            extraPayment = extraDays * Reservation.LATE_RETURN_DAILY_PENALTY_AMOUNT;
            reservation.setTotalAmount(reservation.getTotalAmount() + extraPayment);
        }

        reservation.setStatus(Reservation.STATUS_COMPLETED);

        Vehicle vehicle = reservation.getVehicle();
        vehicle.setStatus(Vehicle.STATUS_AVAILABLE);

        if (extraPayment > 0) {
            System.out.println("Additional payment of " + extraPayment + " required");
        }
            System.out.println("Return process completed successfully");

        return true;
    }
}
