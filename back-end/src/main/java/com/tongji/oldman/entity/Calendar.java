package com.tongji.oldman.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table calendar
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Calendar implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column calendar.cid
     *
     * @mbg.generated
     */
    private Integer cid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column calendar.uid
     *
     * @mbg.generated
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column calendar.uname
     *
     * @mbg.generated
     */
    private String uname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column calendar.context
     *
     * @mbg.generated
     */
    private String context;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column calendar.date
     *
     * @mbg.generated
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table calendar
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column calendar.cid
     *
     * @return the value of calendar.cid
     *
     * @mbg.generated
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column calendar.cid
     *
     * @param cid the value for calendar.cid
     *
     * @mbg.generated
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column calendar.uid
     *
     * @return the value of calendar.uid
     *
     * @mbg.generated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column calendar.uid
     *
     * @param uid the value for calendar.uid
     *
     * @mbg.generated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column calendar.uname
     *
     * @return the value of calendar.uname
     *
     * @mbg.generated
     */
    public String getUname() {
        return uname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column calendar.uname
     *
     * @param uname the value for calendar.uname
     *
     * @mbg.generated
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column calendar.context
     *
     * @return the value of calendar.context
     *
     * @mbg.generated
     */
    public String getContext() {
        return context;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column calendar.context
     *
     * @param context the value for calendar.context
     *
     * @mbg.generated
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column calendar.date
     *
     * @return the value of calendar.date
     *
     * @mbg.generated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column calendar.date
     *
     * @param date the value for calendar.date
     *
     * @mbg.generated
     */
    public void setDate(Date date) {
        this.date = date;
    }
}