import React,{PureComponent} from 'react';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import {Table,Card,Button} from 'antd';
import {connect} from 'dva';
import ModalForm from '@/components/ModalForm';
import columns from './_table.js';
import addItem from './_add';

@connect(({ loading, user }) => ({
    loading,
    userPage: user.userPage
  }))
class User extends PureComponent {

    state = {
        data: '',
        visible: false
    }

    componentDidMount() {
        const {dispatch} = this.props;
        dispatch({
            type: 'user/querySystemUser',
        })
    }

    onChange = page => {
        console.log(page)
    }

    showAddModal = () => {
        this.setState({visible:true})
    }

    onCancel = () => {
        this.setState({visible:false})
    }

    onAdd = values => {
        const {dispatch} = this.props;
        dispatch({
            type: 'user/addSystemUser',
            payload: values
        })
    }

    onDelete = id => {
        const {dispatch} = this.props;
        dispatch({
            type: 'user/deleteSystemUser',
            payload:{id}
        })
    }

    render() {
        const {userPage:{data,pagination},loading} = this.props;
        const {visible} = this.state;
        return (
            <PageHeaderWrapper
            title="用户管理"
            >
                <Card>
                    <div style={{marginBottom: '5px'}}>
                            <Button type="primary" onClick={this.showAddModal}>新增</Button>
                    </div>   
                    <Table
                    rowKey={(record=>record.id)}
                    columns={columns(this)} 
                    dataSource={data}
                    pagination={{
                        ...pagination,
                        onChange: this.onChange,
                        showTotal: total => `共有${total}条记录`,
                    
                    }}
                    loading={loading.effects['user/querySystemUser']}
                    />
                </Card>

               <ModalForm
               titie='新增用户'
               addItem={addItem}
               visible={visible}
               onCancel={this.onCancel}
               onOk={this.onAdd}
               />
              
                   
            </PageHeaderWrapper>
        )
    }
}

export default User;