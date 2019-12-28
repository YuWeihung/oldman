package com.tongji.oldman.service;

import com.tongji.oldman.entity.Calendar;
import com.tongji.oldman.entity.CalendarExample;
import com.tongji.oldman.mapper.CalendarMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {
    private final CalendarMapper calendarMapper;

    public CalendarService(CalendarMapper calendarMapper) {
        this.calendarMapper = calendarMapper;
    }

    public List<Calendar> getCalendars(CalendarExample calendarExample) {
        return calendarMapper.selectByExample(calendarExample);
    }

    public long countCalendars(CalendarExample calendarExample) {
        return calendarMapper.countByExample(calendarExample);
    }

    public int newCalendar(Calendar calendar) {
        return calendarMapper.insertSelective(calendar);
    }

    public int deleteCalendar(CalendarExample calendarExample) {
        return calendarMapper.deleteByExample(calendarExample);
    }

    public int updateCalendar(Calendar calendar) {
        return calendarMapper.updateByPrimaryKeySelective(calendar);
    }
}
