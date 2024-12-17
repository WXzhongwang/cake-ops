import React from "react";
import { Form, Input, Button, Select } from "antd";
import { AccountDTO } from "@/models/account";
import {
  validateAccountName,
  validateEmail,
  validatePhoneNumber,
} from "@/utils";
import { TenantOption } from "../account-list";

const { Option } = Select;

interface CreateAccountFormProps {
  initialValues?: AccountDTO;
  onSave: (values: AccountDTO) => void;
  onCancel: () => void;
  onUpdate: (values: AccountDTO) => void;
  tenantOptions: TenantOption[];
}

const CreateAccountForm: React.FC<CreateAccountFormProps> = ({
  initialValues,
  onSave,
  onCancel,
  onUpdate,
  tenantOptions,
}) => {
  const [form] = Form.useForm();

  // 设置表单的初始值
  React.useEffect(() => {
    form.setFieldsValue(initialValues);
  }, [form, initialValues]);

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      if (initialValues) {
        // 如果存在初始值，则触发更新操作
        onUpdate(values);
      } else {
        onSave(values);
      }
    } catch (error) {
      console.error("表单验证失败:", error);
    }
  };

  return (
    <Form form={form} layout="vertical" onFinish={handleSubmit}>
      <Form.Item
        name="accountName"
        label="账号名称"
        rules={[
          { required: true, message: "请输入账号名称" },
          { validator: validateAccountName },
        ]}
      >
        <Input placeholder="请输入账号名称" />
      </Form.Item>

      <Form.Item
        name="phone"
        label="手机号"
        rules={[
          { required: true, message: "请输入手机号" },
          { validator: validatePhoneNumber },
        ]}
      >
        <Input placeholder="请输入手机号" />
      </Form.Item>

      <Form.Item
        name="email"
        label="邮箱地址"
        rules={[
          { required: true, message: "请输入邮箱地址" },
          { validator: validateEmail },
        ]}
      >
        <Input placeholder="请输入邮箱地址" />
      </Form.Item>

      <Form.Item
        name="tenantId"
        label="所属租户"
        rules={[{ required: true, message: "请选择所属租户" }]}
      >
        <Select placeholder="请选择所属租户" allowClear>
          {tenantOptions.map((option) => (
            <Option key={option.value} value={option.value}>
              {option.label}
            </Option>
          ))}
        </Select>
      </Form.Item>

      <Form.Item
        name="isAdmin"
        label="是否管理员"
        rules={[{ required: true, message: "请选择是否管理员" }]}
      >
        <Select placeholder="请选择是否管理员">
          <Option value={true}>是</Option>
          <Option value={false}>否</Option>
        </Select>
      </Form.Item>

      <Form.Item
        name="accountType"
        label="账号类型"
        rules={[{ required: true, message: "请输入账号类型" }]}
      >
        <Select placeholder="账号类型">
          <Option value="BASIC">基础账号</Option>
          <Option value="DEVELOPER">开发者账号</Option>
          <Option value="VIP">VIP</Option>
        </Select>
      </Form.Item>

      <Form.Item>
        <Button type="primary" htmlType="submit">
          {initialValues ? "更新" : "保存"}{" "}
        </Button>
        <Button onClick={onCancel} style={{ marginLeft: 8 }}>
          取消
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CreateAccountForm;
