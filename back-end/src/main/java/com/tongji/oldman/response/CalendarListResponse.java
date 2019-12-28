package com.tongji.oldman.response;

import com.tongji.oldman.entity.Calendar;

import java.util.List;

public class CalendarListResponse {
    private List<Calendar> calendars;

    public CalendarListResponse(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    public List<Calendar> getCalendars() {
        return calendars;
    }
}
