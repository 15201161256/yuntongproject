package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-05-24 09:02
 */

public class ContractToatalBean extends BaseBean{


    /**
     * item : {"cashedAmount":1000,"payedAmount":12,"amount":988,"count":4}
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
         * cashedAmount : 1000
         * payedAmount : 12
         * amount : 988
         * count : 4
         */

        private String cashedAmount;
        private String payedAmount;
        private String realTimeAmount;
        private String balanceAmount;
        private String actualAmount;
        private int count;

        public String getRealTimeAmount() {
            return realTimeAmount;
        }

        public void setRealTimeAmount(String realTimeAmount) {
            this.realTimeAmount = realTimeAmount;
        }

        public String getBalanceAmount() {
            return balanceAmount;
        }

        public void setBalanceAmount(String balanceAmount) {
            this.balanceAmount = balanceAmount;
        }

        public String getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(String actualAmount) {
            this.actualAmount = actualAmount;
        }

        public String getCashedAmount() {
            return cashedAmount;
        }

        public void setCashedAmount(String cashedAmount) {
            this.cashedAmount = cashedAmount;
        }

        public String getPayedAmount() {
            return payedAmount;
        }

        public void setPayedAmount(String payedAmount) {
            this.payedAmount = payedAmount;
        }


        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
