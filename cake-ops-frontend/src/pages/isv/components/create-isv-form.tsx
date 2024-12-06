import React from "react";
import { Form, Input, Button, Select } from "antd";
import { IsvDTO } from "@/models/isv"; 


const { Option } = Select;

interface CreateIsvFormProps {
  initialValues?: IsvDTO; 
  onSave: (values: IsvDTO) => void;
  onCancel: () => void;
  onUpdate: (values: IsvDTO) => void; 
}

const CreateIsvForm: React.FC<CreateIsvFormProps> = ({
  initialValues,
  onSave,
  onCancel,
  onUpdate,
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
        label="Isv名称"
        rules={[{ required: true, message: "请输入Isv名称" }]}
      >
        <Input placeholder="请输入Isv名称" />
      </Form.Item>

      <Form.Item
        name="shortName"
        label="Isv 简称"
        rules={[{ required: true, message: "请输入Isv 简称" }]}
      >
        <Input placeholder="请输入Isv简称" />
      </Form.Item>

      
      <Form.Item
        name="邮箱地址"
        label="email"
        rules={[{ required: true, message: "请输入邮箱地址" }]}
      >
        <Input placeholder="请输入邮箱地址" />
      </Form.Item>

      <Form.Item
        name="国家"
        label="country"
        rules={[{ required: true, message: "请输入国家" }]}
      >
        <Input placeholder="请输入国家" />
      </Form.Item>

      <Form.Item
        name="地址"
        label="address"
        rules={[{ required: true, message: "请输入地址" }]}
      >
        <Input placeholder="请输入地址" />
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

export default CreateIsvForm;
