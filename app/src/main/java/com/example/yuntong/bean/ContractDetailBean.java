package com.example.yuntong.bean;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-25 08:44
 */

public class ContractDetailBean {

    /**
     * item : {"contractAmount":20000.99,"contractName":"测试合同","contractNo":"000000001","contractOrderCount":4,"createTime":"2019-04-22 09:04:32","createUser":{"avatar":"http://www.baidu.com/default-avatar.png","id":2,"nickname":"杨建","sex":0,"state":1,"structureType":"user","username":"amen"},"endTime":"1992-10-10","firstPartyName":"测试甲方","id":200,"region":"beijing,shanghai","secondPartyName":"测试乙方","signTime":"1992-10-10","startTime":"1992-10-11","structureType":"contract"}
     */

    private ItemBean item;

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * contractAmount : 20000.99
         * contractName : 测试合同
         * contractNo : 000000001
         * contractOrderCount : 4
         * createTime : 2019-04-22 09:04:32
         * createUser : {"avatar":"http://www.baidu.com/default-avatar.png","id":2,"nickname":"杨建","sex":0,"state":1,"structureType":"user","username":"amen"}
         * endTime : 1992-10-10
         * firstPartyName : 测试甲方
         * id : 200
         * region : beijing,shanghai
         * secondPartyName : 测试乙方
         * signTime : 1992-10-10
         * startTime : 1992-10-11
         * structureType : contract
         */

        private String contractAmount;
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
        private String structureType;

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

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }

        public static class CreateUserBean {
            /**
             * avatar : http://www.baidu.com/default-avatar.png
             * id : 2
             * nickname : 杨建
             * sex : 0
             * state : 1
             * structureType : user
             * username : amen
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
