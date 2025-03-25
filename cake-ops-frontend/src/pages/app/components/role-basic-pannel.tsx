// src/pages/app/components/RoleBasicInfoTab.tsx
import React from "react";
import { Form, Input, Button, TreeSelect } from "antd";
import { RoleTreeDTO } from "@/models/role";

interface RoleBasicInfoTabProps {
  form: any;
  roleItem: RoleTreeDTO | null;
  treeData: RoleTreeDTO[];
  updateRole: (values: any) => void;
  deleteRole: (roleId: string) => void;
}

const RoleBasicInfoTab: React.FC<RoleBasicInfoTabProps> = ({
  form,
  roleItem,
  treeData,
  updateRole,
  deleteRole,
}) => {
  return (
    <Form form={form} layout="vertical" onFinish={updateRole}>
      <Form.Item label="角色ID" name="roleId">
        <Input disabled />
      </Form.Item>
      <Form.Item
        label="角色名称"
        name="roleName"
        rules={[{ required: true, message: "请输入角色名称" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="角色Key"
        name="roleKey"
        rules={[{ required: true, message: "请输入角色Key" }]}
      >
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
          treeDefaultExpandAll
          treeData={treeData}
          treeNodeFilterProp="title"
          value={roleItem?.parentId}
          disabled
        />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" style={{ marginRight: 8 }}>
          更新
        </Button>
        <Button
          type="primary"
          danger
          onClick={() => deleteRole(roleItem?.roleId || "")}
          style={{ marginLeft: 8 }}
        >
          删除
        </Button>
      </Form.Item>
    </Form>
  );
};

export default RoleBasicInfoTab;
