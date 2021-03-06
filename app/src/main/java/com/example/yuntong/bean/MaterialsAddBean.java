package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-06-10 14:03
 */

public class MaterialsAddBean extends BaseBean {

    /**
     * item : {"id":4,"name":"特殊材料","type":"5x5","unit":"块","remainCount":0,"remark":"这是啥","structureType":"material"}
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
         * id : 4
         * name : 特殊材料
         * type : 5x5
         * unit : 块
         * remainCount : 0
         * remark : 这是啥
         * structureType : material
         */

        private int id;
        private String name;
        private String type;
        private String unit;
        private String remainCount;
        private String remark;
        private String structureType;

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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRemainCount() {
            return remainCount;
        }

        public void setRemainCount(String remainCount) {
            this.remainCount = remainCount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }
    }
}
