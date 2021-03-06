package com.tongji.oldman.mapper;

import com.tongji.oldman.entity.Calendar;
import com.tongji.oldman.entity.CalendarExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CalendarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    long countByExample(CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int deleteByExample(CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int insert(Calendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int insertSelective(Calendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    List<Calendar> selectByExample(CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    Calendar selectByPrimaryKey(Integer cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Calendar record, @Param("example") CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Calendar record, @Param("example") CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Calendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Calendar record);
}