package Game;

public class RoomDoor {
	
	int sideOfRoom;
	int room;
	
	public RoomDoor(int sideOfRoom, int room) {
		this.sideOfRoom = sideOfRoom;
		this.room = room;
	}
	
	public int update(int x, int y) {
		return y;
		
	}

}
