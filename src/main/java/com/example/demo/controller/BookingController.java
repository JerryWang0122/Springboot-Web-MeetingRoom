package com.example.demo.controller;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * GET /booking 首頁(預約會議室表單, 列出所有會議室預約情形)
 * POST /booking 預約(預約會議室)
 */

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String index(@ModelAttribute BookingMeetingRoom bookingMeetingRoom, Model model) {
        List<BookingMeetingRoomDto> bookingDtos = bookingService.findAllBookings();
        List<MeetingRoom> rooms = bookingService.findAllRooms();
        model.addAttribute("bookingDtos", bookingDtos);
        model.addAttribute("rooms", rooms);
        return "index";
    }
}
