package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-05-09 09:30
 */

public class EditOrderBean implements Serializable {


    /**
     * item : {"id":6,"contractId":200,"orderNo":"0000001","orderName":"测试订单0000001","bigItemNo":"大项编号0000001","orderAmount":10000,"structureType":"contract-order"}
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
         * id : 6
         * contractId : 200
         * orderNo : 0000001
         * orderName : 测试订单0000001
         * bigItemNo : 大项编号0000001
         * orderAmount : 10000
         * structureType : contract-order
         */

        private int id;
        private int contractId;
        private String orderNo;
        private String orderName;
        private String bigItemNo;
        private String startTime;
        private String orderAmount;
        private String structureType;
        private int status;
        private String taxRate;
        private String missNo;

        public String getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(String taxRate) {
            this.taxRate = taxRate;
        }

        public String getMissNo() {
            return missNo;
        }

        public void setMissNo(String missNo) {
            this.missNo = missNo;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getContractId() {
            return contractId;
        }

        public void setContractId(int contractId) {
            this.contractId = contractId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public String getBigItemNo() {
            return bigItemNo;
        }

        public void setBigItemNo(String bigItemNo) {
            this.bigItemNo = bigItemNo;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }
    }
}
