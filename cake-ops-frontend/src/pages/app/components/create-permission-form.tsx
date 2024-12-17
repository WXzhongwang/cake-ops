// src/pages/app/components/create-permission-form.tsx
import React from "react";
import { Form, Input, Button, Radio, Select } from "antd";
import { PermissionDTO } from "@/models/permission";

interface CreatePermissionFormProps {
  onSave: (values: PermissionDTO) => void;
  onUpdate: (values: PermissionDTO) => void;
  onCancel: () => void;
  initialValues?: Partial<PermissionDTO>;
}

const CreatePermissionForm: React.FC<CreatePermissionFormProps> = ({
  onSave,
  onUpdate,
  onCancel,
  initialValues,
}) => {
  const [form] = Form.useForm();

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      if (initialValues) {
        onUpdate(values);
      } else {
        onSave(values);
      }
    } catch (error) {
      console.error("表单验证失败:", error);
    }
  };

  return (
    <Form
      form={form}
      initialValues={initialValues}
      layout="vertical"
      onFinish={handleSubmit}
    >
      <Form.Item
        label="资源类型"
        name="resourceType"
        rules={[{ required: true, message: "请输入资源类型" }]}
      >
        <Select>
          <Select.Option value="query">查询权限</Select.Option>
          <Select.Option value="operation">操作权限</Select.Option>
        </Select>
      </Form.Item>
      <Form.Item
        label="资源名称"
        name="resourceName"
        rules={[{ required: true, message: "请输入资源名称" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="资源路径"
        name="resourcePath"
        rules={[{ required: true, message: "请输入资源路径" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit">
          提交
        </Button>
        <Button type="default" onClick={onCancel} style={{ marginLeft: 8 }}>
          取消
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CreatePermissionForm;
