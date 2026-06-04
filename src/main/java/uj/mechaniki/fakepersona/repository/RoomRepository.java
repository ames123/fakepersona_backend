package uj.mechaniki.fakepersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uj.mechaniki.fakepersona.model.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room getRoomByRoomCode(String roomCode);
}
