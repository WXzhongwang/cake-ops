// src/pages/app/components/RoleAddDrawer.tsx
import React from "react";
import { Drawer, Form, Input, Button, TreeSelect, message } from "antd";
import { RoleTreeDTO } from "@/models/role";
import { initial } from "lodash";
import { MenuDTO } from "@/models/menu";
import { RoleDTO } from "@/models/user";

interface CreateRoleDrawerProps {
  visible: boolean;
  onClose: () => void;
  treeData: RoleTreeDTO[];
  selectedAppCode: string | null;
  saveRole: (values: any) => void;
  finalParentRoleId: string | undefined;
}

const CreateRoleDrawer: React.FC<CreateRoleDrawerProps> = ({
  visible,
  onClose,
  treeData,
  selectedAppCode,
  saveRole,
  finalParentRoleId,
}) => {
  const [addForm] = Form.useForm<RoleDTO>();
  // 设置上级角色

  const saveRoleFunc = (values: any) => {
    saveRole({
      ...values,
      appCode: selectedAppCode,
    });
  };

  const closeDrawer = () => {
    addForm.resetFields();
    onClose();
  };

  return (
    <Drawer
      title="添加角色"
      placement="right"
      onClose={closeDrawer}
      open={visible}
      width={400}
    >
      <Form
        form={addForm}
        layout="vertical"
        onFinish={saveRoleFunc}
        initialValues={{
          parentId: finalParentRoleId || undefined,
        }}
      >
        <Form.Item label="角色名称" name="roleName">
          <Input />
        </Form.Item>
        <Form.Item label="角色Key" name="roleKey">
          <Input />
        </Form.Item>
        <Form.Item label="角色描述" name="roleDesc">
          <Input />
        </Form.Item>
        <Form.Item label="上级角色" name="parentId">
          <TreeSelect
            showSearch
            style={{ width: "100%" }}
            dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
            placeholder="请选择上级角色"
            allowClear
            treeDefaultExpandAll
            treeData={treeData}
            treeNodeFilterProp="title"
          />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default CreateRoleDrawer;
