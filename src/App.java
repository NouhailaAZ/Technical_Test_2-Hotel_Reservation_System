import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Service service = new Service();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            System.out.println("=== HOTEL RESERVATION SYSTEM TEST ===\n");
            
            // Créer 3 chambres
            System.out.println("Creating rooms...");
            service.setRoom(1, RoomType.STANDARD, 1000);
            service.setRoom(2, RoomType.JUNIOR, 2000);
            service.setRoom(3, RoomType.SUITE, 3000);
            
            // Créer 2 utilisateurs
            System.out.println("Creating users...");
            service.setUser(1, 5000);
            service.setUser(2, 10000);
            
            // Test cases de booking
            System.out.println("\n=== BOOKING TESTS ===");
            
            System.out.println("\n- User 1 tries booking Room 2 from 30/06/2026 to 07/07/2026 (7 nights).");
            Date date1 = sdf.parse("30/06/2026");
            Date date2 = sdf.parse("07/07/2026");
            service.bookRoom(1, 2, date1, date2);
            
            System.out.println("\n- User 1 tries booking Room 2 from 07/07/2026 to 30/06/2026.");
            Date date3 = sdf.parse("07/07/2026");
            Date date4 = sdf.parse("30/06/2026");
            service.bookRoom(1, 2, date3, date4);
            
            System.out.println("\n- User 1 tries booking Room 1 from 07/07/2026 to 08/07/2026 (1 night).");
            Date date5 = sdf.parse("07/07/2026");
            Date date6 = sdf.parse("08/07/2026");
            service.bookRoom(1, 1, date5, date6);
            
            System.out.println("\n- User 2 tries booking Room 1 from 07/07/2026 to 09/07/2026 (2 nights).");
            Date date7 = sdf.parse("07/07/2026");
            Date date8 = sdf.parse("09/07/2026");
            service.bookRoom(2, 1, date7, date8);
            
            System.out.println("\n- User 2 tries booking Room 3 from 07/07/2026 to 08/07/2026 (1 night).");
            Date date9 = sdf.parse("07/07/2026");
            Date date10 = sdf.parse("08/07/2026");
            service.bookRoom(2, 3, date9, date10);
            

            System.out.println("\n- setRoom(1, suite, 10000).");
            service.setRoom(1, RoomType.SUITE, 10000);


            System.out.println("\n=== ALL ROOMS AND BOOKINGS ===");
            service.printAll();
            
            System.out.println("\n=== ALL USERS ===");
            service.printAllUsers();
            
        } catch (ParseException e) {
            System.err.println("Date parsing error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}