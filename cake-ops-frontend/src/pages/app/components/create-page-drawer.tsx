import React from "react";
import {
  Drawer,
  Form,
  Input,
  Select,
  Radio,
  TreeSelect,
  Button,
  message,
} from "antd";
import { FormInstance } from "antd/lib/form";
import { MenuTreeDTO } from "@/models/user";
import * as AllIcons from "@ant-design/icons";
import { MenuDTO } from "@/models/menu";

interface PageDrawerProps {
  isDrawerVisible: boolean;
  onClose: () => void;
  addForm: FormInstance<MenuDTO>;
  treeData: any[];
  parentMenuId: string | undefined;
  setParentMenuId: (value: string | undefined) => void;
  onAddFormSubmit: (values: any) => void;
}

// 自定义过滤函数
const filterIconOptions = (
  input: string,
  option?: { label: React.ReactNode; value: string }
) => {
  if (!option) {
    return false;
  }
  return option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};

const PageDrawer: React.FC<PageDrawerProps> = ({
  isDrawerVisible,
  onClose,
  addForm,
  treeData,
  parentMenuId,
  setParentMenuId,
  onAddFormSubmit,
}) => {
  const icons = Object.entries(AllIcons).map(([key, IconComponent]) => {
    const ValidIconComponent = IconComponent as unknown as React.ComponentType<{
      style?: React.CSSProperties;
    }>;
    return {
      label: (
        <div style={{ display: "flex", alignItems: "center" }}>
          <ValidIconComponent style={{ marginRight: 8 }} />
          {key}
        </div>
      ),
      value: key,
    };
  });

  return (
    <Drawer
      title="添加页面"
      placement="right"
      onClose={onClose}
      open={isDrawerVisible}
      width={400}
    >
      <Form form={addForm} layout="vertical" onFinish={onAddFormSubmit}>
        <Form.Item
          label="页面名称"
          name="name"
          rules={[{ required: true, message: "请输入页面名称" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="路径"
          name="path"
          rules={[{ required: true, message: "请输入页面路径" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item label="图标" name="icon">
          <Select
            placeholder="选择图标"
            options={icons}
            style={{ width: "100%" }}
            onChange={(value: any) => {
              addForm.setFieldsValue({ icon: value });
            }}
            showSearch
            filterOption={filterIconOptions}
          />
        </Form.Item>
        <Form.Item label="是否隐藏" name="hidden">
          <Radio.Group>
            <Radio value="false"> 否 </Radio>
            <Radio value="true"> 是 </Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item label="排序" name="sort">
          <Input type="number" />
        </Form.Item>
        <Form.Item label="上级菜单" name="parentId">
          <TreeSelect
            showSearch
            style={{ width: "100%" }}
            dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
            placeholder="请选择上级菜单"
            allowClear
            treeDefaultExpandAll
            treeData={treeData}
            treeNodeFilterProp="title"
            value={parentMenuId}
            onChange={(value) => {
              console.log("parent menu id:", value);
              setParentMenuId(value);
            }}
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

export default PageDrawer;
