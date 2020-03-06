import request from '@/utils/request';

/**
 * 查询系统用户
 * @param {} params 
 */
export async function querySystemUser(params) {
  return request('/user/page', {
    params,
  });
}

/**
 * 新增系统用户
 * @param {*} params 
 */
export async function addSystemUser(params){
  return request('/user/add', {
    method: 'post',
    data: params
  });
}

/**
 * 根据id删除系统用户
 * @param {*} params 
 */
export async function deleteSystemUser(params) {
  return request('/user/deleteById',{
    method: 'delete',
    data: params,
    requestType: 'form'
  })
}