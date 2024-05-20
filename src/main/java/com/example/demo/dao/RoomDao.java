package com.example.demo.dao;

import com.example.demo.model.po.MeetingRoom;

import java.util.List;
import java.util.Optional;

public interface RoomDao {
    List<MeetingRoom> findAllRooms();
    Optional<MeetingRoom> getRoom(Integer roomId);
    Integer addRoom(MeetingRoom room);
}
