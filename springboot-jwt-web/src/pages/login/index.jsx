import React, { PureComponent } from 'react';
import { Form, Input,Button } from 'antd';
import {connect} from 'dva';
import { UserOutlined,LockOutlined } from '@ant-design/icons';
import style from './index.less';

@connect(login => ({
  login,
}))
class Login extends PureComponent {

  onFinish = values => {
    const {dispatch} = this.props;
    dispatch({
      type: 'login/login',
      payload: values
    })
  }

  render() {
    const formItemLayout = {
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 24 },
      },
    };
    return (<div>
      <Form {...formItemLayout} onFinish={this.onFinish} className={style.login}>
        <Form.Item  name="username"  rules={[
              {
                required: true,
                message: '请输入用户名!',
              },
            ]} >
          <Input prefix={<UserOutlined/>  }  />
        </Form.Item>
        <Form.Item name="password" rules={[
              {
                required: true,
                message: '请输入密码!',
              },
            ]} >
          <Input.Password prefix={<LockOutlined/>} />
        </Form.Item>
        <Form.Item>
        <Button type="primary" size="large"  htmlType="submit" block>
           登录
          </Button>
        </Form.Item>
      </Form>
      </div>);
  }
}

export default Login;