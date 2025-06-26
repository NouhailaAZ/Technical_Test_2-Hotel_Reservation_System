public class Room {
    private int roomNumber;
    private RoomType roomType;
    private int pricePerNight;
    
    public Room(int roomNumber, RoomType roomType, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }
    
    // Getters et Setters
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public RoomType getRoomType() {
        return roomType;
    }
    
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    
    public int getPricePerNight() {
        return pricePerNight;
    }
    
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
    @Override
    public String toString() {
        return "Room{ roomNumber = " + roomNumber + ", type = " + roomType + ", pricePerNight = " + pricePerNight + " }";
    }
}