import React from "react";
import {PageContainer} from "@ant-design/pro-components";
import {Form,} from "antd";
import {connect, Dispatch} from "umi";
import {API} from "typings";
import {UserRoleMenuDTO} from "@/models/user";

interface MenuTreeProps {
    dispatch: Dispatch;
}

const MenuPage: React.FC<MenuTreeProps> = ({dispatch}) => {
    const [form] = Form.useForm();

    return (
        <PageContainer title="应用菜单">

        </PageContainer>
    );
};

export default connect(
    ({
         user,
     }: {
        user: {
            isLogin: boolean;
            userData: API.UserInfo;
            menu: UserRoleMenuDTO;
        };
    }) => ({
        isLogin: user.isLogin,
        userData: user.userData,
        menu: user.menu,
    })
)(MenuPage);
