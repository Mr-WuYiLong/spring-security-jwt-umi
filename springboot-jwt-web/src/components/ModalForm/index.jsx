import React,{PureComponent} from 'react';
import {Modal} from 'antd';
import WrapForm from '@/components/WrapForm';

/**
 * modal和form组合的组件
 */
class ModalForm extends PureComponent {
    formRef = React.createRef(); // 获得FormInstance

    render() {
        const {addItem,visible,onCancel,onOk,titie} = this.props;
        return (
            <Modal
            title={titie}
            visible={visible}
            okText="确定"
            cancelText="取消"
            onCancel={onCancel}
            onOk={()=>{
                this.formRef.current.validateFields()
                .then(values => {
                    this.formRef.current.resetFields();
                    onCancel();
                    onOk(values);
                })
                .catch(() => {
                    // console.log('Validate Failed:', info);
                  });
            }}
           >
               <WrapForm formItem={addItem} formRef2={this.formRef} />
           </Modal>
        )
    }
}

export default ModalForm;