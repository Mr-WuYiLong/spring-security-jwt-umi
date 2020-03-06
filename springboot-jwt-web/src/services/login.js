import request from '@/utils/request';

/**
 * 成功登录获得token
 * @param {*} params 
 */
export async function fakeAccountLogin(params) {
  return request('/login', {
    method: 'POST',
    data: params,
    requestType: 'form' // 后端请求token需要表单的形式
  });
}
export async function getFakeCaptcha(mobile) {
  return request(`/api/login/captcha?mobile=${mobile}`);
}
