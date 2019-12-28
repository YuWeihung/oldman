package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.sun.imageio.plugins.common.I18N;
import com.tongji.oldman.entity.Calendar;
import com.tongji.oldman.entity.CalendarExample;
import com.tongji.oldman.response.CalendarListResponse;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.CalendarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/getcalendar")
    public String getCalendar() {
        CalendarExample calendarExample = new CalendarExample();
        List<Calendar> calendars = calendarService.getCalendars(calendarExample);
        CalendarListResponse calendarListResponse = new CalendarListResponse(calendars);
        return JSON.toJSONString(calendarListResponse);
    }

    @PostMapping("/putcalendarinfo")
    public String putCalendarInfo(Integer uid, String context, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = new Calendar();
        calendar.setContext(context);
        calendar.setDate(date1);
        int success = 0;
        int insert = calendarService.newCalendar(calendar);
        if (insert == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

}
