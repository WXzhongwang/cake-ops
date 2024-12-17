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
  Popconfirm,
} from "antd";
import { connect, Dispatch } from "umi";
import { IsvDTO } from "@/models/isv";
import { create, update } from "@/services/isv";
import CreateIsvForm from "./components/create-isv-form";
import { API } from "typings";
import { UserRoleMenuDTO } from "@/models/user";
import dayjs from "dayjs";
import { ColumnsType } from "antd/lib/table";
import Paragraph from "antd/lib/typography/Paragraph";

interface IsvListProps {
  dispatch: Dispatch;
}

const IsvList: React.FC<IsvListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [isvList, setIsvList] = useState<IsvDTO[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [filters, setFilters] = useState({
    name: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingIsv, setEditingIsv] = useState<IsvDTO | undefined>(undefined);
  const [form] = Form.useForm();

  useEffect(() => {
    fetchIsvList();
  }, [pagination, filters]);

  const fetchIsvList = () => {
    dispatch({
      type: "isv/fetchIsvList",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<IsvDTO>) => {
        setIsvList(res.items);
        setTotal(res.total);
      },
    });
  };

  const handleEdit = (webhook: IsvDTO) => {
    setEditingIsv(webhook);
    setDrawerVisible(true);
  };

  const handleDelete = (isvId: string) => {
    dispatch({
      type: "isv/delete",
      payload: { id: isvId },
      callback: (success: boolean) => {
        if (success) {
          message.success("删除成功");
          fetchIsvList();
        }
      },
    });
  };

  const handleDisabled = (isvId: string) => {
    dispatch({
      type: "isv/disable",
      payload: { id: isvId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
          fetchIsvList();
        }
      },
    });
  };

  const handleEnable = (isvId: string) => {
    dispatch({
      type: "isv/enable",
      payload: { id: isvId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
          fetchIsvList();
        }
      },
    });
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddIsv = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingIsv(undefined);
  };

  const handleSaveIsv = async (values: IsvDTO) => {
    dispatch({
      type: "isv/create",
      payload: { ...values },
      callback: (success: boolean) => {
        if (success) {
          message.success("创建成功");
        }
      },
    });
  };

  const handleUpdateIsv = async (values: IsvDTO) => {
    if (editingIsv) {
      dispatch({
        type: "isv/update",
        payload: { ...values, id: editingIsv.id },
        callback: (success: boolean) => {
          if (success) {
            message.success("更新成功");
            fetchIsvList();
            setDrawerVisible(false);
            form.resetFields();
          }
        },
      });
    }
  };

  const columns: ColumnsType<IsvDTO> = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
      fixed: "left",
      render: (text: any, record: IsvDTO) => {
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
      dataIndex: "name",
      key: "name",
    },
    {
      title: "简称",
      dataIndex: "shortName",
      key: "shortName",
    },
    {
      title: "租户数量",
      dataIndex: "tenantCount",
      key: "tenantCount",
      render: (text: any, record: IsvDTO) => {
        return (
          <>
            {record.tenantsCount} / {record.maxTenants}
          </>
        );
      },
    },
    {
      title: "邮箱",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "国家",
      dataIndex: "country",
      key: "country",
    },
    {
      title: "网址",
      dataIndex: "url",
      key: "url",
    },
    {
      title: "联系方式",
      dataIndex: "phone",
      key: "phone",
    },
    {
      title: "地址",
      dataIndex: "address",
      key: "address",
      render: (text: any, record: IsvDTO) => {
        return (
          <>
            {record.address === undefined || record.address === null
              ? "未知"
              : record.address}
          </>
        );
      },
    },
    {
      title: "创建时间",
      dataIndex: "gmtCreate",
      key: "gmtCreate",
      render: (text: any, record: IsvDTO) => {
        return (
          <div>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "修改时间",
      dataIndex: "gmtModified",
      key: "gmtModified",
      render: (text: any, record: IsvDTO) => {
        return (
          <div>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "类型",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: IsvDTO) => {
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
      render: (text: any, record: IsvDTO) => (
        <>
          {record.status === "0" ? (
            <Popconfirm
              title="确定要禁用该ISV吗？"
              onConfirm={() => handleDisabled(record.id)}
              okText="确定"
              cancelText="取消"
            >
              <a type="link">禁用</a>
            </Popconfirm>
          ) : (
            <Popconfirm
              title="确定要启用该ISV吗？"
              onConfirm={() => handleEnable(record.id)}
              okText="确定"
              cancelText="取消"
            >
              <a type="link">启用</a>
            </Popconfirm>
          )}
          <Button type="link" onClick={() => handleEdit(record)}>
            编辑
          </Button>
          <Popconfirm
            title="确定要删除该ISV吗？"
            onConfirm={() => handleDelete(record.id)}
            okText="确定"
            cancelText="取消"
          >
            <a type="link">删除</a>
          </Popconfirm>
        </>
      ),
    },
  ];

  return (
    <PageContainer title="ISV管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              setFilters(values);
            }}
          >
            <Form.Item name="name" label="名称">
              <Input placeholder="请输入名称" allowClear />
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
          <Button type="primary" onClick={handleAddIsv}>
            新增ISV
          </Button>
          <Table
            scroll={{ x: "max-content" }}
            columns={columns}
            dataSource={isvList}
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
        title={editingIsv ? "编辑Isv" : "新增Isv"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateIsvForm
          initialValues={editingIsv}
          onSave={handleSaveIsv}
          onUpdate={handleUpdateIsv}
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
)(IsvList);
