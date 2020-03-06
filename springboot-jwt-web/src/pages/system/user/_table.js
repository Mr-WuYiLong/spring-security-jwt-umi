import React from 'react';
import {Button,Popconfirm,Row} from 'antd';

export default self =>[
  {
    title: '姓名',
    dataIndex: 'username',
  },
  {
    title: '密码',
    dataIndex: 'password',

  },
  {
    title: '操作',
    dataIndex: 'option',
    render: (_, record) => (<Row>

      {/* <Button style={{ marginRight: '5px' }} type="primary" icon="edit" onClick={() => self.showEditModal(record, index)}>编辑</Button> */}
      <Popconfirm
        title="你确定要删除?"
        onConfirm={() => self.onDelete(record.id)}
        okText="确定"
        cancelText="取消"
      >
        <Button type="primary">删除</Button>

      </Popconfirm>
    </Row>),
  },

];