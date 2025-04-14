import React from 'react';
import { history } from 'umi';
import { useSelector } from 'umi';

// 假设从环境变量中获取 SSO 登录地址
const SSO_LOGIN_URL = process.env.SSO_LOGIN_URL;

const AuthGuard: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { isLogin, menu } = useSelector((state: any) => state.user);

  // 修改为使用 history.location.pathname 来获取当前路径
  const currentPath = history.location.pathname;

  const hasPermission = (path: string, menuTree: any[]): boolean => {
    return menuTree.some((item) => {
      if (item.path === path) {
        return true;
      }
      if (item.children) {
        return hasPermission(path, item.children);
      }
      return false;
    });
  };

  if (!isLogin) {
    // 未登录，将当前页面 URL 编码后拼接到 SSO 登录地址后面
    const encodedCurrentPath = encodeURIComponent(currentPath);
    const redirectUrl = `${SSO_LOGIN_URL}?webapp=${encodedCurrentPath}`;
    window.location.href = redirectUrl;
    return null;
  }

  if (!hasPermission(history.location.pathname, menu.menuTree)) {
    // 没有权限，重定向到无权限页面
    history.push('/403');
    return null;
  }

  return children;
};

export default AuthGuard;