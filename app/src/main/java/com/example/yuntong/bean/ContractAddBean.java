package com.example.yuntong.bean;

import java.util.List;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-19 16:08
 */

public class ContractAddBean {

    /**
     * contract : {"contractAmount":10000.99,"contractName":"测试合同","createTime":"2019-04-16 18:49:47","createUser":{"avatar":"http://www.baidu.com/default-avatar.png","id":3,"nickname":"杨建","sex":0,"state":1,"structureType":"user","username":"amen"},"firstPartyName":"测试甲方","id":200,"regionList":["河北"," 北京"],"secondPartyName":"测试乙","structureType":"contract"}
     */

    private ContractBean contract;

    public ContractBean getContract() {
        return contract;
    }

    public void setContract(ContractBean contract) {
        this.contract = contract;
    }

    public static class ContractBean {
        /**
         * contractAmount : 10000.99
         * contractName : 测试合同
         * createTime : 2019-04-16 18:49:47
         * createUser : {"avatar":"http://www.baidu.com/default-avatar.png","id":3,"nickname":"杨建","sex":0,"state":1,"structureType":"user","username":"amen"}
         * firstPartyName : 测试甲方
         * id : 200
         * regionList : ["河北"," 北京"]
         * secondPartyName : 测试乙
         * structureType : contract
         */

        private double contractAmount;
        private String contractName;
        private String createTime;
        private CreateUserBean createUser;
        private String firstPartyName;
        private int id;
        private String secondPartyName;
        private String structureType;
        private List<String> regionList;

        public double getContractAmount() {
            return contractAmount;
        }

        public void setContractAmount(double contractAmount) {
            this.contractAmount = contractAmount;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSecondPartyName() {
            return secondPartyName;
        }

        public void setSecondPartyName(String secondPartyName) {
            this.secondPartyName = secondPartyName;
        }

        public String getStructureType() {
            return structureType;
        }

        public void setStructureType(String structureType) {
            this.structureType = structureType;
        }

        public List<String> getRegionList() {
            return regionList;
        }

        public void setRegionList(List<String> regionList) {
            this.regionList = regionList;
        }

        public static class CreateUserBean {
            /**
             * avatar : http://www.baidu.com/default-avatar.png
             * id : 3
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
