package com.example.yuntong.bean;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-06-06 09:09
 */

public class MateralOutBean extends BaseBean{

    private String outUser;
    private String outTime;
    private int id;
    private String outNum;

    public MateralOutBean(String outUser, String outTime, int id, String outNum) {
        this.outUser = outUser;
        this.outTime = outTime;
        this.id = id;
        this.outNum = outNum;
    }

    public String getoutUser() {
        return outUser;
    }

    public void setoutUser(String outUser) {
        this.outUser = outUser;
    }

    public String getoutTime() {
        return outTime;
    }

    public void setoutTime(String outTime) {
        this.outTime = outTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getoutNum() {
        return outNum;
    }

    public void setoutNum(String outNum) {
        this.outNum = outNum;
    }
}
