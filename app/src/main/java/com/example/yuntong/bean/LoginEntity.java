package com.example.yuntong.bean;

import java.util.List;

/**
 * 说明：
 * Author: ${杨晓峰}
 * Email: m15201161256@163.com
 * Date: 2019-04-12 14:19
 */

public class LoginEntity {


    /**
     * item : {"avatar":"","id":1,"nickname":"管理员","roleList":[{"id":1,"name":"系统管理员","structureType":"role"}],"sex":0,"state":0,"structureType":"user","username":"root"}
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
         * avatar :
         * id : 1
         * nickname : 管理员
         * roleList : [{"id":1,"name":"系统管理员","structureType":"role"}]
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
        private List<RoleListBean> roleList;

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

        public List<RoleListBean> getRoleList() {
            return roleList;
        }

        public void setRoleList(List<RoleListBean> roleList) {
            this.roleList = roleList;
        }

        public static class RoleListBean {
            /**
             * id : 1
             * name : 系统管理员
             * structureType : role
             */

            private int id;
            private String name;
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

            public String getStructureType() {
                return structureType;
            }

            public void setStructureType(String structureType) {
                this.structureType = structureType;
            }
        }
    }
}
