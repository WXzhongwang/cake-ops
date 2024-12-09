import React, { useEffect } from 'react';
import { Form, Input, Button, Space, Select } from 'antd';
import { AppDTO } from '@/models/app';

const { Option } = Select;


interface CreateAppFormProps {
  initialValues?: AppDTO;
  onSave: (values: AppDTO) => void;
  onUpdate: (values: AppDTO) => void;
  onCancel: () => void;
}

const CreateAppForm: React.FC<CreateAppFormProps> = ({
  initialValues,
  onSave,
  onUpdate,
  onCancel,
}) => {
  const [form] = Form.useForm();

  useEffect(() => {
    if (initialValues) {
      form.setFieldsValue(initialValues);
    }
  }, [initialValues, form]);

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      if (initialValues) {
        onUpdate(values);
      } else {
        onSave(values);
      }
    } catch (error) {
      console.error('表单验证失败:', error);
    }
  };

  return (
    <Form form={form} layout="vertical" initialValues={initialValues}>
      <Form.Item
        name="appName"
        label="名称"
        rules={[{ required: true, message: '请输入应用名称' }]}
      >
        <Input placeholder="请输入应用名称" />
      </Form.Item>
      <Form.Item
        name="appCode"
        label="应用编码"
        rules={[{ required: true, message: '请输入应用编码' }]}
      >
        <Input placeholder="请输入应用编码" />
      </Form.Item>

      <Form.Item
        name="authType"
        label="应用认证方式"
        rules={[{ required: true, message: '请输入应用认证方式' }]}
      >
        <Select placeholder="认证类型">
          <Option value="BASIC">基础认证</Option>
        </Select>
      </Form.Item>

      <Form.Item>
        <Space>
          <Button type="primary" onClick={handleSubmit}>
            保存
          </Button>
          <Button onClick={onCancel}>取消</Button>
        </Space>
      </Form.Item>
    </Form>
  );
};

export default CreateAppForm;
