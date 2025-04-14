import AuthGuard from "@/components/AuthGuard";
import { defineConfig } from "umi";

const { BUILD_ENV } = process.env;
const name = process.env.npm_package_name;
const version = process.env.npm_package_version;
let publicPath = "/";

switch (BUILD_ENV) {
  case "test":
    publicPath = `https://rany-ops.oss-cn-hangzhou.aliyuncs.com/fe-res/test/${name}/${version}/`;
    break;
  case "pre":
    publicPath = `https://rany-ops.oss-cn-hangzhou.aliyuncs.com/fe-res/pre/${name}/${version}/`;
    break;
  case "prod":
    publicPath = `https://rany-ops.oss-cn-hangzhou.aliyuncs.com/fe-res/prod/${name}/${version}/`;
    break;
  default:
    break;
}

export default defineConfig({
  esbuildMinifyIIFE: true, // fatal - [esbuildHelperChecker] Found conflicts in esbuild helpers: Rn (872.async.js, umi.js), please set esbuildMinifyIIFE: true in your config file.
  base: "/",
  publicPath: publicPath,
  plugins: ["@umijs/plugins/dist/react-query", "@umijs/plugins/dist/dva"],
  reactQuery: {},
  extraBabelPlugins:
    process.env.NODE_ENV === "prod" ? ["babel-plugin-dynamic-import-node"] : [],

  // 全部路由配置
  routes: [
    { path: "/", redirect: "/uic/isv" },
    {
      path: "/uic/isv",
      component: "isv/isv-list",
      name: "ISV列表",
      wrappers: [AuthGuard],
    },
    {
      path: "/uic/tenant",
      component: "tenant/tenant-list",
      name: "租户列表",
      wrappers: [AuthGuard],
    },
    {
      path: "/uic/account",
      component: "tenant/account-list",
      name: "账号列表",
      wrappers: [AuthGuard],
    },
    {
      path: "/acl/app",
      component: "app/app-list",
      name: "应用管理",
      wrappers: [AuthGuard],
    },
    {
      path: "/acl/menu-tree",
      component: "app/menu-tree",
      name: "菜单管理",
      wrappers: [AuthGuard],
    },
    {
      path: "/acl/role-list",
      component: "app/role-list",
      name: "角色管理",
      wrappers: [AuthGuard],
    },
    { path: "/404", component: "system/not-found" }, // 显式声明 /404 路由
    { path: "/403", component: "system/not-authorized" }, // 显式声明 /403 路由
    { path: "/*", redirect: "/404" }, // 通配符路由，重定向到 /404 路由
  ],
  npmClient: "pnpm",
  dva: {},
  proxy: {
    "/api": {
      target: "http://127.0.0.1:6322",
      changeOrigin: true,
      pathRewrite: { "^/api": "/api" },
      logLevel: "debug",
      onError(err, req, res) {
        console.error("Proxy error:", err);
      },
    },
  },
  mock: false,
});
