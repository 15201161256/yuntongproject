package com.example.yuntong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-23 08:49
 */

public class OrderListbean implements Serializable{


    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * bigItemNo : jnxnn
         * contractId : 214
         * id : 1
         * orderAmount : 685000.0
         * orderName : 迪莫
         * orderNo : jnmc
         * smallItemCount : 1
         * startTime : 2019-04-29
         * status : 1
         * structureType : contract-order
         */

        private String bigItemNo;
        private int contractId;
        private int id;
        private String orderAmount;
        private String orderName;
        private String orderNo;
        private int smallItemCount;
        private String startTime;
        private int status;
        private String structureType;
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

        public String getBigItemNo() {
            return bigItemNo;
        }

        public void setBigItemNo(String bigItemNo) {
            this.bigItemNo = bigItemNo;
        }

        public int getContractId() {
            return contractId;
        }

        public void setContractId(int contractId) {
            this.contractId = contractId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getSmallItemCount() {
            return smallItemCount;
        }

        public void setSmallItemCount(int smallItemCount) {
            this.smallItemCount = smallItemCount;
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

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }
    }
}
