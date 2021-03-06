package com.example.yuntong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-03-27 10:54
 */

public class HomeBean extends BaseBean{


    /**
     * contract : {"contractName":"测试名称","contractNo":"测试编号","contractOrderCount":1,"createTime":"2019-05-21 01:01:27","createUser":{"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"},"endTime":"2019-05-21","firstPartyName":"测试名称","id":240,"region":"测试合同","secondPartyName":"测试单位","signTime":"2019-05-06","startTime":"2019-05-23","status":2,"structureType":"contract"}
     * instrumentList : [{"borrower":"鸭子","createTime":"2019-05-27 09:47:33","id":9,"name":"测试结束","purchaseTime":"2019-05-26","remark":"测试结束时间","startTime":"2019-05-24","structureType":"instrument","type":"哦哦"},{"borrower":"lala啊","createTime":"2019-05-27 09:33:09","id":8,"name":"锤子","purchaseTime":"2019-04-30","remark":"阿斯顿","startTime":"2019-04-30","structureType":"instrument","type":"把"}]
     * materialList : [{"id":7,"name":"钢管","remainCount":80,"remark":"","structureType":"material","type":"7.0直径","unit":"米"},{"id":6,"name":"测试材料","remainCount":10,"remark":"测试备注","structureType":"material","type":"测试型号","unit":"测试单位"}]
     */

    private ContractBean contract;
    private List<InstrumentListBean> instrumentList;
    private List<MaterialListBean> materialList;

    public ContractBean getContract() {
        return contract;
    }

    public void setContract(ContractBean contract) {
        this.contract = contract;
    }

    public List<InstrumentListBean> getInstrumentList() {
        return instrumentList;
    }

    public void setInstrumentList(List<InstrumentListBean> instrumentList) {
        this.instrumentList = instrumentList;
    }

    public List<MaterialListBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialListBean> materialList) {
        this.materialList = materialList;
    }

    public static class ContractBean implements Serializable{
        /**
         * contractName : 测试名称
         * contractNo : 测试编号
         * contractOrderCount : 1
         * createTime : 2019-05-21 01:01:27
         * createUser : {"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"}
         * endTime : 2019-05-21
         * firstPartyName : 测试名称
         * id : 240
         * region : 测试合同
         * secondPartyName : 测试单位
         * signTime : 2019-05-06
         * startTime : 2019-05-23
         * status : 2
         * structureType : contract
         */

        private String contractName;
        private String contractNo;
        private int contractOrderCount;
        private String createTime;
        private CreateUserBean createUser;
        private String endTime;
        private String firstPartyName;
        private int id;
        private String region;
        private String secondPartyName;
        private String signTime;
        private String startTime;
        private int status;
        private String structureType;

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public int getContractOrderCount() {
            return contractOrderCount;
        }

        public void setContractOrderCount(int contractOrderCount) {
            this.contractOrderCount = contractOrderCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public CreateUserBean getCreateUser() {
            return createUser;
        }

        public void setCreateUser(CreateUserBean createUser) {
            this.createUser = createUser;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFirstPartyName() {
            return firstPartyName;
        }

        public void setFirstPartyName(String firstPartyName) {
            this.firstPartyName = firstPartyName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getSecondPartyName() {
            return secondPartyName;
        }

        public void setSecondPartyName(String secondPartyName) {
            this.secondPartyName = secondPartyName;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
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

        public static class CreateUserBean {
            /**
             * avatar :
             * id : 1
             * nickname : 管理员
             * sex : 0
             * state : 0
             * structureType : user
             * username : root
             */

            private String avatar;
            private int id;
            private String nickname;
            private int sex;
            private int state;
            private String structureType;
            private String username;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getStructureType() {
                return structureType;
            }

            public void setStructureType(String structureType) {
                this.structureType = structureType;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }

    public static class InstrumentListBean implements Serializable{
        /**
         * borrower : 鸭子
         * createTime : 2019-05-27 09:47:33
         * id : 9
         * name : 测试结束
         * purchaseTime : 2019-05-26
         * remark : 测试结束时间
         * startTime : 2019-05-24
         * structureType : instrument
         * type : 哦哦
         */

        private String borrower;
        private String createTime;
        private int id;
        private String name;
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

    public static class MaterialListBean implements Serializable{
        /**
         * id : 7
         * name : 钢管
         * remainCount : 80
         * remark :
         * structureType : material
         * type : 7.0直径
         * unit : 米
         */

        private int id;
        private String name;
        private int remainCount;
        private String remark;
        private String structureType;
        private String type;
        private String unit;

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

        public int getRemainCount() {
            return remainCount;
        }

        public void setRemainCount(int remainCount) {
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
    }
}
