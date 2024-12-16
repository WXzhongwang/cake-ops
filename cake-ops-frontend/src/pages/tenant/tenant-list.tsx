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
import { TenantDTO } from "@/models/tenant";
import { IsvDTO } from "@/models/isv";
import { create, update } from "@/services/tenant";
import { API } from "typings";
import { UserRoleMenuDTO } from "@/models/user";
import dayjs from "dayjs";
import { ColumnsType } from "antd/lib/table";
import CreateTenantForm from "./components/create-tenant-form";
import Paragraph from "antd/lib/typography/Paragraph";

interface TenantListProps {
  dispatch: Dispatch;
}

interface ISVOption {
  label: string;
  value: string;
}

const TenantList: React.FC<TenantListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [tenantList, setTenantList] = useState<TenantDTO[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [filters, setFilters] = useState({
    name: "",
    isvId: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingTenant, setEditingTenant] = useState<TenantDTO | undefined>(
    undefined
  );
  const [form] = Form.useForm();
  // 假设我们有一个获取ISV数据的方法
  const [isvOptions, setIsvOptions] = useState<ISVOption[]>([]);

  useEffect(() => {
    // 获取ISV数据
    fetchISVData("");
  }, []);

  useEffect(() => {
    fetchTenantList();
  }, [pagination, filters]);

  const fetchISVData = (name: string) => {
    dispatch({
      type: "isv/listIsv",
      payload: { name: name },
      callback: (res: IsvDTO[]) => {
        const data = res.map((isv: IsvDTO) => ({
          value: isv.id,
          label: isv.name,
        }));
        setIsvOptions(data);
      },
    });
  };

  const fetchTenantList = () => {
    dispatch({
      type: "tenant/fetchTenantList",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<TenantDTO>) => {
        setTenantList(res.items);
        setTotal(res.total);
      },
    });
  };

  const handleEdit = (tenant: TenantDTO) => {
    setEditingTenant(tenant);
    setDrawerVisible(true);
  };

  const handleDelete = (tenantId: number) => {
    dispatch({
      type: "tenant/delete",
      payload: { id: tenantId },
    });
    fetchTenantList();
  };

  const handleDisabled = (tenantId: number) => {
    dispatch({
      type: "tenant/disable",
      payload: { id: tenantId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
        }
      },
    });
    fetchTenantList();
  };

  const handleEnable = (tenantId: number) => {
    dispatch({
      type: "tenant/enable",
      payload: { id: tenantId },
      callback: (success: boolean) => {
        if (success) {
          message.success("处理成功");
        }
      },
    });
    fetchTenantList();
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddTenant = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingTenant(undefined);
  };

  const handleSaveTenant = async (values: TenantDTO) => {
    try {
      if (editingTenant) {
        await update({ ...values, id: editingTenant.id });
      } else {
        await create(values);
      }
      fetchTenantList();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("保存Tenant失败:", error);
    }
  };

  const columns: ColumnsType<TenantDTO> = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
      fixed: "left",
      render: (text: any, record: TenantDTO) => {
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
      title: "ISV",
      dataIndex: "isvName",
      key: "isvName",
    },
    {
      title: "邮箱",
      dataIndex: "email",
      key: "email",
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
      render: (text: any, record: TenantDTO) => {
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
      render: (text: any, record: TenantDTO) => {
        return (
          <div>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "修改时间",
      dataIndex: "gmtModified",
      key: "gmtModified",
      render: (text: any, record: TenantDTO) => {
        return (
          <div>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "类型",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: TenantDTO) => {
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
      render: (text: any, record: TenantDTO) => (
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
    <PageContainer title="租户管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              setFilters(values);
            }}
          >
            <Form.Item name="isvId" label="ISV">
              <Select
                showSearch
                style={{ width: 200 }}
                placeholder="请选择或输入ISV"
                optionFilterProp="children"
                options={isvOptions}
              />
            </Form.Item>

            <Form.Item name="name" label="租户名称">
              <Input placeholder="请输入租户名称" />
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
                    isvId: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAddTenant}>
            新增租户
          </Button>
          <Table
            scroll={{ x: "max-content" }}
            columns={columns}
            dataSource={tenantList}
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
        title={editingTenant ? "编辑租户" : "新增租户"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateTenantForm
          initialValues={editingTenant}
          onSave={handleSaveTenant}
          onUpdate={handleSaveTenant}
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
)(TenantList);
