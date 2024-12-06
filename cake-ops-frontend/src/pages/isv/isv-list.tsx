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
} from "antd";
import { connect, Dispatch } from "umi";
import { IsvDTO } from "@/models/isv";
import {
  create,
  update,
} from "@/services/isv";
import CreateIsvForm from "./components/create-isv-form";
import { API } from "typings";

interface IsvListProps {
  dispatch: Dispatch;
}

const IsvList: React.FC<IsvListProps> = ({
  dispatch,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [isvList, setIsvList] = useState<IsvDTO[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [filters, setFilters] = useState({
    name: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingIsv, setEditingIsv] = useState<
    IsvDTO | undefined
  >(undefined);
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
      }
    });
  };

  const handleEdit = (webhook: IsvDTO) => {
    setEditingIsv(webhook);
    setDrawerVisible(true);
  };

  const handleDelete = (isvId: number) => {
    dispatch({
      type: "isv/delete",
      payload: { id: isvId },
    });
    fetchIsvList();
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
    try {
      if (editingIsv) {
        await update({ ...values, id: editingIsv.id });
      } else {
        await create(values);
      }
      fetchIsvList();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("保存Isv失败:", error);
    }
  };

  const columns = [
    {
      title: "id",
      dataIndex: "id",
      key: "id",
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
      title: "类型",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: IsvDTO) => {
        return <Tag color={record.status === "0" ? "success" : "error"}>
        {record.status === "0" ? "已启用" : "已停用"}
      </Tag>;
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: IsvDTO) => (
        <Space size="middle">
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="Isv列表">
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
          <Button type="primary" onClick={handleAddIsv}>
            新增WebHook
          </Button>
          <Table
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
          onSave={handleAddIsv}
          onUpdate={handleAddIsv} 
          onCancel={handleCloseDrawer}
        />
      </Drawer>
    </PageContainer>
  );
};

export default connect(() => {})(IsvList);
