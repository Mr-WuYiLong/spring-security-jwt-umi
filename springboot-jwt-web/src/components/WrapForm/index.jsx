import React, { PureComponent } from 'react';
import { Form, Input } from 'antd';

/**
 * 表单组件
 */
class WrapForm extends PureComponent {

    render() {
        const {formItem,formRef2} = this.props; 
        const formItemLayout =
        {
            labelCol: { span: 4 },
            wrapperCol: { span: 14 },
        }
        return (
            <Form ref={formRef2} {...formItemLayout}>
                    {formItem.map(field => (
                        <Form.Item key={field.name} {...field}>
                            <Input />
                        </Form.Item>
                    ))}    
            </Form>
        );
    }
}

export default WrapForm;