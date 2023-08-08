
	import java.util.*;

	class Reservation {
	    private String pnrNumber;
	    private String trainNumber;
	    private String trainName;
	    private String classType;
	    private Date dateOfJourney;
	    private String from;
	    private String to;

	    public Reservation(String pnrNumber, String trainNumber, String trainName, String classType, Date dateOfJourney, String from, String to) {
	        this.pnrNumber = pnrNumber;
	        this.trainNumber = trainNumber;
	        this.trainName = trainName;
	        this.classType = classType;
	        this.dateOfJourney = dateOfJourney;
	        this.from = from;
	        this.to = to;
	    }

	    // Getters
	}

	class User {
	    private String username;
	    private String password;

	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    public boolean checkPassword(String password) {
	        return this.password.equals(password);
	    }
	}

	public class OnlineReservationSystem {
	    private Map<String, User> users;
	    private List<Reservation> reservations;
	    private int pnrCounter;

	    public OnlineReservationSystem() {
	        users = new HashMap<>();
	        reservations = new ArrayList<>();
	        pnrCounter = 1;
	    }

	    public void addUser(String username, String password) {
	        users.put(username, new User(username, password));
	    }

	    public boolean isValidUser(String username, String password) {
	        User user = users.get(username);
	        return user != null && user.checkPassword(password);
	    }

	    public String makeReservation(String trainNumber, String trainName, String classType, Date dateOfJourney, String from, String to) {
	        String pnrNumber = "PNR" + pnrCounter++;
	        Reservation reservation = new Reservation(pnrNumber, trainNumber, trainName, classType, dateOfJourney, from, to);
	        reservations.add(reservation);
	        return pnrNumber;
	    }

	    public Reservation findReservationByPNR(String pnrNumber) {
	        for (Reservation reservation : reservations) {
	            if (reservation.getPnrNumber().equals(pnrNumber)) {
	                return reservation;
	            }
	        }
	        return null;
	    }

	    public boolean cancelReservation(String pnrNumber) {
	        Reservation reservation = findReservationByPNR(pnrNumber);
	        if (reservation != null) {
	            reservations.remove(reservation);
	            return true;
	        }
	        return false;
	    }

	    public static void main(String[] args) {
	        OnlineReservationSystem reservationSystem = new OnlineReservationSystem();

	        reservationSystem.addUser("user1", "password1");
	        reservationSystem.addUser("user2", "password2");

	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter username: ");
	        String username = scanner.nextLine();

	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        if (reservationSystem.isValidUser(username, password)) {
	            System.out.println("Login successful!");

	            // Simulate reservation
	            String pnrNumber = reservationSystem.makeReservation("123", "Sample Express", "First Class", new Date(), "CityA", "CityB");
	            System.out.println("Reservation made. Your PNR number: " + pnrNumber);

	            // Simulate cancellation
	            System.out.print("Enter PNR number to cancel: ");
	            String cancelPNR = scanner.nextLine();
	            if (reservationSystem.cancelReservation(cancelPNR)) {
	                System.out.println("Reservation with PNR " + cancelPNR + " has been canceled.");
	            } else {
	                System.out.println("Reservation not found for PNR " + cancelPNR);
	            }
	        } else {
	            System.out.println("Invalid credentials. Please try again.");
	        }
	    }
	}



