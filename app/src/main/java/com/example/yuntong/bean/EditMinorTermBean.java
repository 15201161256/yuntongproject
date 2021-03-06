package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-05-09 15:07
 */

public class EditMinorTermBean implements Serializable {

    /**
     * item : {"id":5,"contractOrderId":3,"cooperator":"施工方x","startDate":"1991-01-01","structureType":"small-item"}
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
         * id : 5
         * contractOrderId : 3
         * cooperator : 施工方x
         * startDate : 1991-01-01
         * structureType : small-item
         */


        private int id;
        private int contractOrderId;
        private String name;
        private String cooperator;
        private String startDate;
        private String checkAndAcceptDate;
        private String amount;
        private String secondPartyConstructionCost;
        private String firstPartyMaterialCost;
        private String secondPartyMaterialCost;
        private String secondPartySafeProductionCost;
        private String stipulatedFee;
        private String amountCashed;
        private String secondPartyAuditFee;
        private String secondPartyDeductedAmount;
        private String taxAmount;
        private String managementFee;
        private int status;
        private String structureType;
        private String endDate;


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

        public String getSecondPartyConstructionCost() {
            return secondPartyConstructionCost;
        }

        public void setSecondPartyConstructionCost(String secondPartyConstructionCost) {
            this.secondPartyConstructionCost = secondPartyConstructionCost;
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

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getContractOrderId() {
            return contractOrderId;
        }

        public void setContractOrderId(int contractOrderId) {
            this.contractOrderId = contractOrderId;
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

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }
    }
}
