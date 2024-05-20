package com.example.demo.dao;

import com.example.demo.model.po.MeetingRoom;

import java.util.List;

public interface RoomDao {
    List<MeetingRoom> findAllRooms();
    MeetingRoom getRoom(Integer roomId);
    Integer addRoom(MeetingRoom room);
}
