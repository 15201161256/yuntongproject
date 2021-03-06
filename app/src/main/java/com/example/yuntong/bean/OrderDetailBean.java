package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-25 11:11
 */

public class OrderDetailBean implements Serializable{


    /**
     * item : {"amountCashed":200.9,"bigItemNo":"大项编号0000001","contractId":200,"id":3,"invoiceAmount":1000,"missNo":"missNo-10001","orderAmount":10000,"orderName":"测试订单0000001","orderNo":"0000001","smallItemCount":5,"structureType":"contract-order","taxRate":0.25}
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
         * amountCashed : 6880.0
         * bigItemNo : 舞台妆
         * contractId : 227
         * endTime : 2019-05-09
         * id : 4
         * invoiceAmount : 85000.0
         * missNo : 99988
         * orderAmount : 9895.0
         * orderName : 舞台妆
         * orderNo : 哦咯咯
         * smallItemCount : 4
         * startTime : 2019-05-09
         * status : 2
         * structureType : contract-order
         * taxRate : 3.0
         */

        private String amountCashed;
        private String bigItemNo;
        private int contractId;
        private String endTime;
        private int id;
        private String invoiceAmount;
        private String orderAmount;
        private String orderName;
        private String orderNo;
        private String smallItemCount;
        private String startTime;
        private int status;
        private String structureType;
        private String taxRate;
        private String missNo;
        private String amountBalance;
        private String amountCashedPercent;



        private String amount1;
        private String paidTime1;
        private String amount2;
        private String paidTime2;
        private String amount3;
        private String paidTime3;

        public String getAmount1() {
            return amount1;
        }

        public void setAmount1(String amount1) {
            this.amount1 = amount1;
        }

        public String getPaidTime1() {
            return paidTime1;
        }

        public void setPaidTime1(String paidTime1) {
            this.paidTime1 = paidTime1;
        }

        public String getAmount2() {
            return amount2;
        }

        public void setAmount2(String amount2) {
            this.amount2 = amount2;
        }

        public String getPaidTime2() {
            return paidTime2;
        }

        public void setPaidTime2(String paidTime2) {
            this.paidTime2 = paidTime2;
        }

        public String getAmount3() {
            return amount3;
        }

        public void setAmount3(String amount3) {
            this.amount3 = amount3;
        }

        public String getPaidTime3() {
            return paidTime3;
        }

        public void setPaidTime3(String paidTime3) {
            this.paidTime3 = paidTime3;
        }

        public String getAmountCashed() {
            return amountCashed;
        }

        public void setAmountCashed(String amountCashed) {
            this.amountCashed = amountCashed;
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInvoiceAmount() {
            return invoiceAmount;
        }

        public void setInvoiceAmount(String invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
        }

        public String getMissNo() {
            return missNo;
        }

        public void setMissNo(String missNo) {
            this.missNo = missNo;
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

        public String getSmallItemCount() {
            return smallItemCount;
        }

        public void setSmallItemCount(String smallItemCount) {
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

        public String getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(String taxRate) {
            this.taxRate = taxRate;
        }

        public String getAmountBalance() {
            return amountBalance;
        }

        public void setAmountBalance(String amountBalance) {
            this.amountBalance = amountBalance;
        }

        public String getAmountCashedPercent() {
            return amountCashedPercent;
        }

        public void setAmountCashedPercent(String amountCashedPercent) {
            this.amountCashedPercent = amountCashedPercent;
        }
    }
}
