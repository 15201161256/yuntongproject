package com.example.yuntong.bean;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-25 16:26
 */

public class MinorTermDetailBean {

    /**
     * item : {"contractOrderId":3,"cooperator":"施工方x","id":5,"startDate":"1991-01-01","structureType":"small-item"}
     */

    private ItemBean item;

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public static class ItemBean {

        private int contractOrderId;
        private String name;
        private String cooperator;
        private String startDate;
        private String endDate;
        private String checkAndAcceptDate;
        private String amount;
        private String firstPartyMaterialCost;
        private String secondPartyMaterialCost;
        private String secondPartySafeProductionCost;
        private String stipulatedFee;
        private String amountCashed;
        private String secondPartyAuditFee;
        private String secondPartyDeductedAmount;
        private String secondPartyConstructionCost;
        private String taxAmount;
        private String managementFee;
        private String actualPayment;
        private String totalPaid;
        private String invoiceAmount;
        private String balance;
        private String accountPayable;

        public String getInvoiceAmount() {
            return invoiceAmount;
        }

        public void setInvoiceAmount(String invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getAccountPayable() {
            return accountPayable;
        }

        public void setAccountPayable(String accountPayable) {
            this.accountPayable = accountPayable;
        }

        public String getTotalPaid() {
            return totalPaid;
        }

        public void setTotalPaid(String totalPaid) {
            this.totalPaid = totalPaid;
        }

        public String getSecondPartyConstructionCost() {
            return secondPartyConstructionCost;
        }

        public void setSecondPartyConstructionCost(String secondPartyConstructionCost) {
            this.secondPartyConstructionCost = secondPartyConstructionCost;
        }

        public int getContractOrderId() {
            return contractOrderId;
        }

        public void setContractOrderId(int contractOrderId) {
            this.contractOrderId = contractOrderId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCooperator() {
            return cooperator;
        }

        public void setCooperator(String cooperator) {
            this.cooperator = cooperator;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCheckAndAcceptDate() {
            return checkAndAcceptDate;
        }

        public void setCheckAndAcceptDate(String checkAndAcceptDate) {
            this.checkAndAcceptDate = checkAndAcceptDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFirstPartyMaterialCost() {
            return firstPartyMaterialCost;
        }

        public void setFirstPartyMaterialCost(String firstPartyMaterialCost) {
            this.firstPartyMaterialCost = firstPartyMaterialCost;
        }

        public String getSecondPartyMaterialCost() {
            return secondPartyMaterialCost;
        }

        public void setSecondPartyMaterialCost(String secondPartyMaterialCost) {
            this.secondPartyMaterialCost = secondPartyMaterialCost;
        }

        public String getSecondPartySafeProductionCost() {
            return secondPartySafeProductionCost;
        }

        public void setSecondPartySafeProductionCost(String secondPartySafeProductionCost) {
            this.secondPartySafeProductionCost = secondPartySafeProductionCost;
        }

        public String getStipulatedFee() {
            return stipulatedFee;
        }

        public void setStipulatedFee(String stipulatedFee) {
            this.stipulatedFee = stipulatedFee;
        }

        public String getAmountCashed() {
            return amountCashed;
        }

        public void setAmountCashed(String amountCashed) {
            this.amountCashed = amountCashed;
        }

        public String getSecondPartyAuditFee() {
            return secondPartyAuditFee;
        }

        public void setSecondPartyAuditFee(String secondPartyAuditFee) {
            this.secondPartyAuditFee = secondPartyAuditFee;
        }

        public String getSecondPartyDeductedAmount() {
            return secondPartyDeductedAmount;
        }

        public void setSecondPartyDeductedAmount(String secondPartyDeductedAmount) {
            this.secondPartyDeductedAmount = secondPartyDeductedAmount;
        }

        public String getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(String taxAmount) {
            this.taxAmount = taxAmount;
        }

        public String getManagementFee() {
            return managementFee;
        }

        public void setManagementFee(String managementFee) {
            this.managementFee = managementFee;
        }

        public String getActualPayment() {
            return actualPayment;
        }

        public void setActualPayment(String actualPayment) {
            this.actualPayment = actualPayment;
        }
    }
}
