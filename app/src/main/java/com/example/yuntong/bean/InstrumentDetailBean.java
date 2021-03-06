package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-05-21 10:20
 */

public class InstrumentDetailBean extends BaseBean {

    /**
     * item : {"id":1,"name":"测试工具1","type":"model-x","purchaseTime":"1991-10-10","borrower":{"id":1,"username":"root","nickname":"管理员","avatar":"","state":0,"sex":0,"structureType":"user"},"startTime":"1992-10-10","endTime":"1993-10-10","remark":"借出去了一年","createTime":"2019-05-20 12:39:44","structureType":"instrument"}
     */

    private ItemBean item;

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public static class ItemBean implements Serializable{
        /**
         * id : 1
         * name : 测试工具1
         * type : model-x
         * purchaseTime : 1991-10-10
         * borrower : {"id":1,"username":"root","nickname":"管理员","avatar":"","state":0,"sex":0,"structureType":"user"}
         * startTime : 1992-10-10
         * endTime : 1993-10-10
         * remark : 借出去了一年
         * createTime : 2019-05-20 12:39:44
         * structureType : instrument
         */

        private int id;
        private String name;
        private String type;
        private String purchaseTime;
        private String startTime;
        private String endTime;
        private String remark;
        private String createTime;
        private String borrower;
        private String structureType;

        public String getBorrower() {
            return borrower;
        }

        public void setBorrower(String borrower) {
            this.borrower = borrower;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPurchaseTime() {
            return purchaseTime;
        }

        public void setPurchaseTime(String purchaseTime) {
            this.purchaseTime = purchaseTime;
        }


        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }

    }
}
