package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-05-21 09:29
 */

public class EditInstrumentBean extends BaseBean{

    /**
     * item : {"borrower":{"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"},"createTime":"2019-05-20 12:39:44","endTime":"1993-10-10","id":1,"name":"测试工具1","purchaseTime":"1991-10-10","remark":"借出去了一年","startTime":"1992-10-10","structureType":"instrument","type":"model-x"}
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
         * borrower : {"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"}
         * createTime : 2019-05-20 12:39:44
         * endTime : 1993-10-10
         * id : 1
         * name : 测试工具1
         * purchaseTime : 1991-10-10
         * remark : 借出去了一年
         * startTime : 1992-10-10
         * structureType : instrument
         * type : model-x
         */

        private String createTime;
        private String endTime;
        private String id;
        private String name;
        private String borrower;
        private String purchaseTime;
        private String remark;
        private String startTime;
        private String structureType;
        private String type;

        public String getBorrower() {
            return borrower;
        }

        public void setBorrower(String borrower) {
            this.borrower = borrower;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPurchaseTime() {
            return purchaseTime;
        }

        public void setPurchaseTime(String purchaseTime) {
            this.purchaseTime = purchaseTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}
