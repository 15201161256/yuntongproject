package com.example.yuntong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-19 15:27
 */

public class MinorTermBean implements Serializable{

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{


        /**
         * id : 20
         * contractOrderId : 8
         * name : 图
         * cooperator : 投诉
         * startDate : 2019-05-16
         * checkAndAcceptDate : 2019-05-23
         * amount : 6679799.0
         * secondPartyConstructionCost : 99799.0
         * firstPartyMaterialCost : 9997.0
         * secondPartyMaterialCost : 999.0
         * secondPartySafeProductionCost : 888.0
         * stipulatedFee : 8878.0
         * amountCashed : 88898.0
         * secondPartyAuditFee : 88778.0
         * secondPartyDeductedAmount : 500.0
         * taxAmount : 999.0
         * managementFee : 180.0
         * status : 1
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

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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
