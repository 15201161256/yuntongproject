package com.example.yuntong.bean;

import java.io.Serializable;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-05-08 15:55
 */

public class EditContractBean implements Serializable{

    /**
     * item : {"contractAmount":6666666,"contractName":"哦哟哦我去","contractNo":"王智伟","contractOrderCount":0,"createTime":"2019-05-08 03:09:56","createUser":{"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"},"firstPartyName":"移动","id":216,"region":"铁塔","secondPartyName":"多多魔女","status":1,"structureType":"contract"}
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
         * contractAmount : 6666666.0
         * contractName : 哦哟哦我去
         * contractNo : 王智伟
         * contractOrderCount : 0
         * createTime : 2019-05-08 03:09:56
         * createUser : {"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"}
         * firstPartyName : 移动
         * id : 216
         * region : 铁塔
         * secondPartyName : 多多魔女
         * status : 1
         * structureType : contract
         */

        private String contractAmount;
        private String contractName;
        private String contractNo;
        private int contractOrderCount;
        private String createTime;
        private String signTime;
        private String startTime;
        private CreateUserBean createUser;
        private String firstPartyName;
        private String id;
        private String region;
        private String secondPartyName;
        private int status;
        private String structureType;

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

        public String getContractAmount() {
            return contractAmount;
        }

        public void setContractAmount(String contractAmount) {
            this.contractAmount = contractAmount;
        }

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

        public String getFirstPartyName() {
            return firstPartyName;
        }

        public void setFirstPartyName(String firstPartyName) {
            this.firstPartyName = firstPartyName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public static class CreateUserBean implements Serializable{
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
}
