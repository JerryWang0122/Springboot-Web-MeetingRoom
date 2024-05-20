package com.example.demo.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

// Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingMeetingRoom {
    private Integer bookingId;
    private Integer roomId;
    private Integer userId;
    private String bookingDate;
    private Timestamp createDate;
}
