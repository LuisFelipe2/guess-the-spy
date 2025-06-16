package luis_auth.guess_the_spy.repository;

import luis_auth.guess_the_spy.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository {

	private final List<Room> rooms = new ArrayList<>();

	public List<Room> getAll() {
		return rooms;
	}

	public Room findRoomByName(String name)  {
		return rooms.stream().filter(room -> room.getName().equals(name))
			.findFirst().orElseThrow(() -> new RuntimeException("Sala não existe"));
	}

	public void create(Room room) {
		if(rooms.contains(room)) {
			throw new RuntimeException("Sala já existe");
		}
		rooms.add(room);
	}
}
