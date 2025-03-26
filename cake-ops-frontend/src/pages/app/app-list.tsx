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
} from "antd";
import { connect, Dispatch } from "umi";
import { AppDTO } from "@/models/app";
import { create, update } from "@/services/app";
import CreateAppForm from "./components/create-app-form";
import { API } from "typings";
import { UserRoleMenuDTO } from "@/models/user";
import dayjs from "dayjs";
import { ColumnsType } from "antd/lib/table";
import Paragraph from "antd/lib/typography/Paragraph";

interface AppListProps {
  dispatch: Dispatch;
}

const AppList: React.FC<AppListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [appList, setAppList] = useState<AppDTO[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [filters, setFilters] = useState({
    name: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingApp, setEditingApp] = useState<AppDTO | undefined>(undefined);
  const [form] = Form.useForm();

  useEffect(() => {
    fetchAppList();
  }, [pagination, filters]);

  const fetchAppList = () => {
    dispatch({
      type: "app/fetchAppList",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<AppDTO>) => {
        setAppList(res.items);
        setTotal(res.total);
      },
    });
  };

  const handleEdit = (app: AppDTO) => {
    setEditingApp(app);
    setDrawerVisible(true);
  };

  const handleDelete = (appId: string) => {
    dispatch({
      type: "app/delete",
      payload: { id: appId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
          fetchAppList();
        }
      },
    });
  };

  const handleDisabled = (appId: string) => {
    dispatch({
      type: "app/disable",
      payload: { id: appId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
          fetchAppList();
        }
      },
    });
  };

  const handleEnable = (appId: string) => {
    dispatch({
      type: "app/enable",
      payload: { id: appId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
          fetchAppList();
        }
      },
    });
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddApp = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingApp(undefined);
  };

  const handleSaveApp = async (values: AppDTO) => {
    try {
      if (editingApp) {
        await update({ ...values, id: editingApp.id });
      } else {
        await create(values);
      }
      fetchAppList();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("保存App失败:", error);
    }
  };

  const columns: ColumnsType<AppDTO> = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
      fixed: "left",
      render: (text: any, record: AppDTO) => {
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
      title: "名称",
      dataIndex: "appName",
      key: "name",
    },
    {
      title: "应用编码",
      dataIndex: "appCode",
      key: "appCode",
      render: (text: any, record: AppDTO) => {
        return (
          <Paragraph
            copyable={{ tooltips: ["点击复制", "复制成功"] }}
            style={{ display: "inline" }}
          >
            {record.appCode}
          </Paragraph>
        );
      },
    },
    {
      title: "创建时间",
      dataIndex: "gmtCreate",
      key: "gmtCreate",
      render: (text: any, record: AppDTO) => {
        return (
          <div>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "修改时间",
      dataIndex: "gmtModified",
      key: "gmtModified",
      render: (text: any, record: AppDTO) => {
        return (
          <div>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: AppDTO) => {
        return (
          <Tag color={record.status === "0" ? "success" : "error"}>
            {record.status === "0" ? "已启用" : "已停用"}
          </Tag>
        );
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: AppDTO) => (
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
    <PageContainer title="应用管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              setFilters(values);
            }}
          >
            <Form.Item name="name" label="名称">
              <Input placeholder="请输入名称" />
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
                    name: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAddApp}>
            新增应用
          </Button>
          <Table
            scroll={{ x: "max-content" }}
            columns={columns}
            dataSource={appList}
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
        title={editingApp ? "编辑应用" : "新增应用"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateAppForm
          initialValues={editingApp}
          onSave={handleSaveApp}
          onUpdate={handleSaveApp}
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
)(AppList);
