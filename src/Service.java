import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Service {
    ArrayList<Room> rooms;
    ArrayList<User> users;
    ArrayList<Booking> bookings;
    
    public Service() {
        rooms = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    void setUser(int userId, int balance) {
        try {
            if (balance < 0) {
                throw new IllegalArgumentException("Balance cannot be negative ( User : " + userId + " )");
            }    
            User existingUser = findUserById(userId);
            if (existingUser != null) {
                // modifier le solde
                existingUser.setBalance(balance);
            }else {
                if (balance < 0) {
                    throw new IllegalArgumentException("Balance cannot be negative");
                }else{
                    User newUser = new User(userId, balance);
                    users.add(newUser);
                }  
            }
        } catch (Exception e) {
            System.err.println("Error setting user: " + e.getMessage());
        }
    }
    
    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        try {          
            if (roomPricePerNight < 0) {
                throw new IllegalArgumentException("Price per night cannot be negative ( Room : " + roomNumber + " )");
            }
            
            Room existingRoom = findRoomByNumber(roomNumber);
            if (existingRoom != null) {
                existingRoom.setRoomType(roomType);
                existingRoom.setPricePerNight(roomPricePerNight);
            }else {
                 if (roomPricePerNight < 0) {
                    throw new IllegalArgumentException("Price per night cannot be negative");
                }else{
                    // Créer une nouvelle chambre
                    Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
                    rooms.add(newRoom);
                }       
            }
        } catch (Exception e) {
            System.err.println("Error setting room: " + e.getMessage());
        }
    }
    
    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        try {
            // Validations
            if (checkIn == null || checkOut == null) {
                throw new IllegalArgumentException("Check-in and check-out dates cannot be null");
            }
            
            if (checkIn.after(checkOut) || checkIn.equals(checkOut)) {
                throw new IllegalArgumentException("Invalid date range: check-in must be before check-out");
            }
            
            // Trouver l'utilisateur et la chambre
            User user = findUserById(userId);
            Room room = findRoomByNumber(roomNumber);
            
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }
            
            if (room == null) {
                throw new IllegalArgumentException("Room not found with number: " + roomNumber);
            }
            
            // Calculer le nmbr de nuits et le coût total
            long diffInMillies = checkOut.getTime() - checkIn.getTime();
            int nights = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            int totalCost = nights * room.getPricePerNight();
            
            // Vérifier si l'utilisateur peut reserver
            if (!user.canBook(totalCost)) {
                throw new IllegalArgumentException("Insufficient balance. Required: " + totalCost + ", Available: " + user.getBalance());
            }
            
            // Vérifier si la chambre est disponible
            if (!isRoomAvailable(roomNumber, checkIn, checkOut)) {
                throw new IllegalArgumentException("Room is not available for the specified period");
            }
            
            // Effectuer la reservation
            user.deductBalance(totalCost);
            Booking booking = new Booking(userId, roomNumber, checkIn, checkOut, nights, totalCost, user, room);
            bookings.add(booking);
            
            System.out.println("Booking successful.");
            
        } catch (Exception e) {
            System.err.println("Booking failed: " + e.getMessage());
        }
    }
    
    
    void printAll() {        
        List<Room> allRooms = new ArrayList<>(rooms);
        Collections.reverse(allRooms);
        
        System.out.println("\n--- ROOMS ---");
        for (Room room : allRooms) {
            System.out.println(room);
        }
        
        List<Booking> allBookings = new ArrayList<>(bookings);
        Collections.reverse(allBookings);
        
        System.out.println("\n--- BOOKINGS ---");
        if( allBookings.isEmpty()){
            System.out.println("Aucune réservation trouvée.");
        }
        for (Booking booking : allBookings) {
            System.out.println(booking);
        }
    }
    
    void printAllUsers() {        
        List<User> allUsers = new ArrayList<>(users);
        Collections.reverse(allUsers);
        
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
    
    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    
    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
    
    private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == roomNumber) {
                if (booking.checkDate(checkIn, checkOut)) {
                    return false;
                }
            }
        }
        return true;
    }
}
