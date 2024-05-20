package com.example.demo.controller;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import com.example.demo.model.po.User;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(@ModelAttribute BookingMeetingRoom bookingMeetingRoom, Model model) {
        List<BookingMeetingRoomDto> bookingDtos = bookingService.findAllBookings();
        List<MeetingRoom> rooms = bookingService.findAllRooms();
        List<User> users = userService.findAllUsers();

        model.addAttribute("bookingDtos", bookingDtos);     // 給別表用
        model.addAttribute("rooms", rooms);     // 給表單下拉選單用
        model.addAttribute("users", users);     // 給表單下拉選單用
        return "index";
    }

    @PostMapping
    public String add(@ModelAttribute BookingMeetingRoom bookingMeetingRoom, Model model) {

        try {
            Integer rowcount = bookingService.addBooking(bookingMeetingRoom.getRoomId(),
                                      bookingMeetingRoom.getUserId(),
                                      bookingMeetingRoom.getBookingDate());
            String message = "新增 " + (rowcount == 1 ? "success" : "fail");
            model.addAttribute("message", message);
        } catch (Exception e) {
            String message = "新增錯誤：" + e.getMessage();
            model.addAttribute("message", message);
        }
        return "result";
    }
}
