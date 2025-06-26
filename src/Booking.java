import java.util.Date;
import java.text.SimpleDateFormat;

public class Booking {
    private static int nextBookingId = 1;
    private int bookingId;
    private int userId;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;
    private int totalNights;
    private int totalCost;
    
    private User user;
    private Room room;
    
    public Booking(int userId, int roomNumber, Date checkIn, Date checkOut, int totalNights, int totalCost, User user, Room room) {
        this.bookingId = nextBookingId++;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.checkIn = new Date(checkIn.getTime());
        this.checkOut = new Date(checkOut.getTime());
        this.totalNights = totalNights;
        this.totalCost = totalCost;
        this.user = new User(user.getUserId(), user.getBalance());
        this.room = new Room(room.getRoomNumber(), room.getRoomType(), room.getPricePerNight());
    }
    
    // Getters
    public int getBookingId(){ 
        return bookingId; 
    }
    public int getUserId() {
        return userId; 
    }
    public int getRoomNumber() {
        return roomNumber; 
    }
    public Date getCheckIn() {
        return new Date(checkIn.getTime()); 
    }
    public Date getCheckOut() {
        return new Date(checkOut.getTime()); 
    }
    public int getTotalNights() {
        return totalNights; 
    }
    public int getTotalCost() {
        return totalCost; 
    }
    public User getuser() {
        return user; 
    }
    public Room getroom() {
        return room; 
    }
    
    public boolean checkDate(Date otherCheckIn, Date otherCheckOut) {
        return checkIn.before(otherCheckOut) && checkOut.after(otherCheckIn);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Booking{ id = " + bookingId + 
                ", userId = " + userId + 
                ", roomNumber = " + roomNumber + 
                ", checkIn = " + sdf.format(checkIn) + 
                ", checkOut = " + sdf.format(checkOut) + 
                ", totalCost = " + totalCost + 
                ", totalNights = " + totalNights + 
                ".\n userAtBooking = " + user + 
                ".\n roomAtBooking = " + room + "\n}";
    }
}