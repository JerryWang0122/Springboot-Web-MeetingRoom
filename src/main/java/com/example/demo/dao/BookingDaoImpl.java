package com.example.demo.dao;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingDaoImpl implements BookingDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer addBooking(BookingMeetingRoom bookingMeetingRoom) {
        String sql = "insert into BookingMeetingRoom(roomId, userId, bookingDate) values (?, ?, ?)";
        int rowcount = jdbcTemplate.update(sql, bookingMeetingRoom.getRoomId(),
                                                bookingMeetingRoom.getUserId(),
                                                bookingMeetingRoom.getBookingDate());
        return rowcount;
    }

    @Override
    public Integer cancelBooking(Integer bookingId) {
        String sql = "delete from BookingMeetingRoom where bookingId = ?";
        int rowcount = jdbcTemplate.update(sql, bookingId);
        return rowcount;
    }

    @Override
    public List<BookingMeetingRoomDto> findAllBookingRooms() {
        return null;
    }

    @Override
    public Integer updateBookingUserId(Integer bookingId, Integer userId) {
        String sql = "update BookingMeetingRoom set userId = ? where bookingId = ?";
        int rowcount = jdbcTemplate.update(sql, userId, bookingId);
        return rowcount;
    }

    @Override
    public List<MeetingRoom> findAllRooms() {
        String sql = "select roomId, roomName, roomSize from meetingroom";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MeetingRoom.class));
    }

    @Override
    public Optional<MeetingRoom> getRoom(Integer roomId) {
        String sql = "select roomId, roomName, roomSize from meetingroom where roomId = ?";
        try {
            MeetingRoom room = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(MeetingRoom.class), roomId);
            return Optional.of(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(null);
    }

    @Override
    public Integer addRoom(MeetingRoom room) {
        String sql = "insert into MeetingRoom(roomId, roomName, roomSize) values (?, ?, ?)";
        int rowcount = jdbcTemplate.update(sql, room.getRoomId(), room.getRoomName(), room.getRoomSize());
        return rowcount;
    }

}
