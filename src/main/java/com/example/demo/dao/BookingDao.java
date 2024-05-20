package com.example.demo.dao;

import com.example.demo.model.po.BookingMeetingRoom;

import java.util.List;

public interface BookingDao extends RoomDao, UserDao {

    // 預定會議室
    Integer addBooking(BookingMeetingRoom bookingMeetingRoom);

    // 取消預訂
    Integer cancelBooking(Integer bookingId);

    // 查看所有預定
    List<BookingMeetingRoom> findAllBookingRooms();

    // 修改預約人
    Integer updateBookingUserId(Integer bookingId, Integer userId);
}
