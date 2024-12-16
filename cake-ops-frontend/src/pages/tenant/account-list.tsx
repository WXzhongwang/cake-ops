import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import {
  Button,
  Card,
  Form,
  Input,
  Space,
  Table,
  Drawer,
  Tooltip,
  Badge,
  Tag,
  message,
  Select,
} from "antd";
import { connect, Dispatch } from "umi";
import { AccountDTO } from "@/models/account";
import { TenantDTO } from "@/models/tenant";
import { createAccount, updateAccount } from "@/services/account";
import { API } from "typings";
import dayjs from "dayjs";
import { ColumnsType } from "antd/lib/table";
import CreateAccountForm from "./components/create-account-form";
import Paragraph from "antd/lib/typography/Paragraph";
import { UserRoleMenuDTO } from "@/models/user";

interface AccountListProps {
  dispatch: Dispatch;
}

interface TenantOption {
  label: string;
  value: string;
}

const AccountList: React.FC<AccountListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [accountList, setAccountList] = useState<AccountDTO[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [filters, setFilters] = useState({
    accountName: "",
    tenantId: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingAccount, setEditingAccount] = useState<AccountDTO | undefined>(
    undefined
  );
  const [form] = Form.useForm();
  // 假设我们有一个获取Tenant数据的方法
  const [tenantOptions, setTenantOptions] = useState<TenantOption[]>([]);

  useEffect(() => {
    // 获取Tenant数据
    fetchTenantData("");
  }, []);

  useEffect(() => {
    fetchAccountList();
  }, [pagination, filters]);

  const fetchTenantData = (name: string) => {
    dispatch({
      type: "tenant/listTenant",
      payload: { name: name },
      callback: (res: TenantDTO[]) => {
        const data = res.map((tenant: TenantDTO) => ({
          value: tenant.id,
          label: tenant.name,
        }));
        setTenantOptions(data);
      },
    });
  };

  const fetchAccountList = () => {
    dispatch({
      type: "account/fetchAccountList",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<AccountDTO>) => {
        setAccountList(res.items);
        setTotal(res.total);
      },
    });
  };

  const handleEdit = (account: AccountDTO) => {
    setEditingAccount(account);
    setDrawerVisible(true);
  };

  const handleDelete = (accountId: string) => {
    dispatch({
      type: "account/delete",
      payload: { id: accountId },
    });
    fetchAccountList();
  };

  const handleDisabled = (accountId: string) => {
    dispatch({
      type: "account/disable",
      payload: { id: accountId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
        }
      },
    });
    fetchAccountList();
  };

  const handleEnable = (accountId: string) => {
    dispatch({
      type: "account/enable",
      payload: { id: accountId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
        }
      },
    });
    fetchAccountList();
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddAccount = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingAccount(undefined);
  };

  const handleSaveAccount = async (values: AccountDTO) => {
    try {
      if (editingAccount) {
        await updateAccount({ ...values, id: editingAccount.id });
      } else {
        await createAccount(values);
      }
      fetchAccountList();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("保存Account失败:", error);
    }
  };

  const columns: ColumnsType<AccountDTO> = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
      fixed: "left",
      render: (text: any, record: AccountDTO) => {
        return (
          <Paragraph
            copyable={{ tooltips: ["点击复制", "复制成功"] }}
            style={{ display: "inline" }}
          >
            {record.id}
          </Paragraph>
        );
      },
    },
    {
      title: "账号名称",
      dataIndex: "accountName",
      key: "accountName",
    },
    {
      title: "电话",
      dataIndex: "phone",
      key: "phone",
    },
    {
      title: "邮箱",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "租户",
      dataIndex: "tenantName",
      key: "tenantName",
    },
    {
      title: "是否管理员",
      dataIndex: "isAdmin",
      key: "isAdmin",
      render: (text: any, record: AccountDTO) => {
        return (
          <Tag color={record.isAdmin ? "green" : "red"}>
            {record.isAdmin ? "是" : "否"}
          </Tag>
        );
      },
    },
    {
      title: "账号类型",
      dataIndex: "accountType",
      key: "accountType",
    },
    {
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: AccountDTO) => {
        return (
          <Tag color={record.status === "0" ? "success" : "error"}>
            {record.status === "0" ? "已启用" : "已停用"}
          </Tag>
        );
      },
    },
    {
      title: "最后登录IP",
      dataIndex: "lastLoginIp",
      key: "lastLoginIp",
    },
    {
      title: "最后登录时间",
      dataIndex: "lastLoginTime",
      key: "lastLoginTime",
      render: (text: any, record: AccountDTO) => {
        return (
          <div>
            {/* 非时间格式展示未知 */}
            {!dayjs(record?.lastLoginTime).isValid() && "未知"}
          </div>
        );
      },
    },
    {
      title: "创建时间",
      dataIndex: "gmtCreate",
      key: "gmtCreate",
      render: (text: any, record: AccountDTO) => {
        return (
          <div>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "修改时间",
      dataIndex: "gmtModified",
      key: "gmtModified",
      render: (text: any, record: AccountDTO) => {
        return (
          <div>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: AccountDTO) => (
        <Space size="middle">
          {record.status === "0" ? (
            <a onClick={() => handleDisabled(record.id)}>禁用</a>
          ) : (
            <a onClick={() => handleEnable(record.id)}>启用</a>
          )}
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="账号管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              setFilters(values);
            }}
          >
            <Form.Item name="tenantId" label="租户">
              <Select
                showSearch
                style={{ width: 200 }}
                placeholder="请选择或输入租户"
                optionFilterProp="children"
                options={tenantOptions}
              />
            </Form.Item>

            <Form.Item name="accountName" label="账号名称">
              <Input placeholder="请输入账号名称" />
            </Form.Item>

            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button
                onClick={() => {
                  setFilters({
                    accountName: "",
                    tenantId: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAddAccount}>
            新增账号
          </Button>
          <Table
            scroll={{ x: "max-content" }}
            columns={columns}
            dataSource={accountList}
            rowKey="id"
            pagination={{
              total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>

      <Drawer
        title={editingAccount ? "编辑账号" : "新增账号"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateAccountForm
          initialValues={editingAccount}
          onSave={handleSaveAccount}
          onUpdate={handleSaveAccount}
          onCancel={handleCloseDrawer}
        />
      </Drawer>
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
)(AccountList);
