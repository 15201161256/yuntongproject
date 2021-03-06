package com.example.yuntong.bean;

import com.example.yuntong.base.BaseEntry;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-17 09:51
 */

public class ContractListEntity extends BaseEntry implements Serializable {


    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * contractAmount : 802825.25
         * contractName : 测试合同199
         * contractNo : 000000199
         * createTime : 2019-04-17 02:09:42
         * createUser : {"avatar":"","id":1,"nickname":"管理员","sex":0,"state":0,"structureType":"user","username":"root"}
         * firstPartyName : 测试甲方
         * id : 199
         * region : "上海"
         * secondPartyName : 测试乙方
         * structureType : contract
         */

        private String contractAmount;
        private String contractName;
        private String contractNo;
        private String createTime;
        private CreateUserBean createUser;
        private String firstPartyName;
        private String id;
        private int status;
        private String secondPartyName;
        private String structureType;
        private String region;
        private String signTime;
        private String startTime;

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

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public static class CreateUserBean implements Serializable {
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
