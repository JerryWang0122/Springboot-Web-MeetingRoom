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
import org.springframework.web.bind.annotation.*;

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
    public String index(@ModelAttribute BookingMeetingRoom bookingMeetingRoom,
                        @ModelAttribute MeetingRoom meetingRoom,
                        Model model) {
        List<BookingMeetingRoomDto> bookingDtos = bookingService.findAllBookings();
        List<MeetingRoom> rooms = bookingService.findAllRooms();
        List<User> users = userService.findAllUsers();

        model.addAttribute("bookingDtos", bookingDtos);     // 給別表用
        model.addAttribute("rooms", rooms);     // 給表單下拉選單用
        model.addAttribute("users", users);     // 給表單下拉選單用
        return "index";
    }

    @PostMapping("/room")
    public String addRoom(@ModelAttribute MeetingRoom meetingRoom, Model model) {
        try {
            Integer rowcount = bookingService.addRoom(meetingRoom.getRoomId(), meetingRoom.getRoomName(), meetingRoom.getRoomSize());
            String message = "新增會議室 " + (rowcount == 1 ? "success" : "fail");
            model.addAttribute("message", message);
        } catch (Exception e) {
            String message = "新增會議室錯誤：";
            if (e.getMessage().contains("Duplicate")) {
                message += "該會議室ID已重複";
            } else {
                message += e.getMessage();
            }
            model.addAttribute("message", message);
        }
        return "result";
    }

    @PostMapping
    public String add(@ModelAttribute BookingMeetingRoom bookingMeetingRoom, Model model) {

        try {
            Integer rowcount = bookingService.addBooking(bookingMeetingRoom.getRoomId(),
                                      bookingMeetingRoom.getUserId(),
                                      bookingMeetingRoom.getBookingDate());
            String message = "新增預約 " + (rowcount == 1 ? "success" : "fail");
            model.addAttribute("message", message);
        } catch (Exception e) {
            String message = "新增預約錯誤：";
            // unique_roomId_and_bookingDate 是在建立資料表時的表單約束條件
            if (e.getMessage().contains("unique_roomId_and_bookingDate")) {
                message += "該會議室當日已經有人預約";
            } else {
                message += e.getMessage();
            }
            model.addAttribute("message", message);
        }
        return "result";
    }

    @DeleteMapping
    public String cancel(@RequestParam("bookingId") Integer bookingId, Model model) {
        int rowcount = bookingService.cancelBooking(bookingId);
        String message = "取消 " + (rowcount == 1 ? "success" : "fail");
        model.addAttribute("message", message);
        return "result";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<BookingMeetingRoomDto> bookingDtos = bookingService.findAllBookings();
        model.addAttribute("bookingDtos", bookingDtos);
        return "list_booking";
    }
}
