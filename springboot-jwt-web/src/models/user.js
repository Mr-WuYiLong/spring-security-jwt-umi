/* eslint-disable no-plusplus */
/* eslint-disable no-param-reassign */
import { querySystemUser,addSystemUser,deleteSystemUser } from '@/services/user';

export default {
  namespace: 'user',
  state: {
    userPage: {
      data: [],
      pagination: {
        current: 1,
        pageSize: 10,
        total: 0,
      },  
    },
  },
  effects: {
    *querySystemUser(_,{call,put}) {
      const res = yield call(querySystemUser);
      
      if( res.code === 200) {
        yield put({
          type: 'querySystemUserSuccess',
          payload: res.data
        })
      }
    },
    *addSystemUser({payload},{call,put}) {
      const res = yield call(addSystemUser,payload);
      if(res.code === 200) {
        yield put({
          type: 'querySystemUser',
        })
      }
    },
    *deleteSystemUser({payload},{call,put}) {
      const res = yield call(deleteSystemUser,payload);
      if(res.code === 200) {
        yield put({
          type: 'querySystemUser'
        })
      }
    }
  },
  reducers: {
    querySystemUserSuccess(state,{payload}) {
      return {
        ...state,
        userPage: payload
      }
    },
  }
};
