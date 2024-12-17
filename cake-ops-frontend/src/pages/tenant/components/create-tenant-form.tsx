import React from "react";
import { Form, Input, Button, Select } from "antd";
import { TenantDTO } from "@/models/tenant";
import { ISVOption } from "../tenant-list";
import {
  validateEmail,
  validateNameLength,
  validatePhoneNumber,
  validateShortName,
} from "@/utils";

const { Option } = Select;

interface CreateTenantFormProps {
  initialValues?: TenantDTO;
  onSave: (values: TenantDTO) => void;
  onCancel: () => void;
  onUpdate: (values: TenantDTO) => void;
  isvOptions: ISVOption[]; // 新增属性
}

const CreateTenantForm: React.FC<CreateTenantFormProps> = ({
  initialValues,
  onSave,
  onCancel,
  onUpdate,
  isvOptions,
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
        name="name"
        label="租户名称"
        rules={[
          { required: true, message: "请输入租户名称" },
          { validator: validateNameLength },
        ]}
      >
        <Input placeholder="请输入租户名称" allowClear />
      </Form.Item>

      <Form.Item
        name="shortName"
        label="租户简称"
        rules={[
          { required: true, message: "请输入租户简称" },
          { validator: validateShortName },
        ]}
      >
        <Input placeholder="请输入租户简称" allowClear />
      </Form.Item>
      <Form.Item
        name="isvId"
        label="ISV"
        rules={[{ required: true, message: "请选择ISV" }]}
      >
        <Select placeholder="请选择ISV" allowClear>
          {isvOptions.map((option) => (
            <Option key={option.value} value={option.value}>
              {option.label}
            </Option>
          ))}
        </Select>
      </Form.Item>
      <Form.Item
        name="phone"
        label="手机号"
        rules={[
          { required: true, message: "请输入手机号" },
          { validator: validatePhoneNumber },
        ]}
      >
        <Input type="phone" placeholder="请输入手机号" allowClear />
      </Form.Item>

      <Form.Item
        name="email"
        label="邮箱地址"
        rules={[
          { required: true, message: "请输入邮箱地址" },
          { validator: validateEmail },
        ]}
      >
        <Input type="email" placeholder="请输入邮箱地址" allowClear />
      </Form.Item>

      <Form.Item
        name="source"
        label="来源"
        rules={[{ required: false, message: "请输入国家" }]}
      >
        <Select placeholder="请输入来源" allowClear>
          <Option value="self">自建</Option>
          <Option value="open">外拓</Option>
          <Option value="dev">开发</Option>
        </Select>
      </Form.Item>

      <Form.Item
        name="url"
        label="网站"
        rules={[{ required: false, message: "请输入网站地址" }]}
      >
        <Input placeholder="请输入网站地址" allowClear />
      </Form.Item>

      <Form.Item
        name="address"
        label="地址"
        rules={[{ required: false, message: "请输入地址" }]}
      >
        <Input placeholder="请输入地址" allowClear />
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

export default CreateTenantForm;
