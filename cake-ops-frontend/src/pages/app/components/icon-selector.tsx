// src/components/IconSelector.tsx
import React from 'react';
import {Empty, Modal,} from 'antd';
import * as AllIcons from '@ant-design/icons';

interface IconSelectorProps {
    visible: boolean;
    onCancel: () => void;
    onSelect: (icon: React.ReactNode) => void;
}

const IconSelector: React.FC<IconSelectorProps> = ({visible, onCancel, onSelect}) => {
    const icons = Object.entries(AllIcons).map(([key, IconComponent]) => ({
        key,
        component: IconComponent,
    }));

    const handleSelect = (icon: React.ReactNode) => {
        onSelect(icon);
        onCancel();
    };

    return (
        <Modal
            title="选择图标"
            visible={visible}
            onCancel={onCancel}
            footer={null}
            width={800}
        >
            <div style={{display: 'flex', flexWrap: 'wrap'}}>
                {icons.length > 0 ? (
                    icons.map(({key, component: IconComponent}) => (
                        <div
                            key={key}
                            onClick={() => handleSelect(<IconComponent/>)}
                            style={{
                                display: 'inline-block',
                                padding: 16,
                                cursor: 'pointer',
                                border: '1px solid #f0f0f0',
                                borderRadius: 4,
                                margin: 4,
                                textAlign: 'center',
                            }}
                        >
                            <IconComponent style={{fontSize: 24}}/>
                            <div>{key}</div>
                        </div>
                    ))
                ) : (
                    <Empty description="没有找到图标"/>
                )}
            </div>
        </Modal>
    );
};

export default IconSelector;
