// src/wrappers/auth.tsx
import React, { useEffect, useState } from "react";
import { connect, useLocation } from "umi";
import { history } from "umi";
import { UserRoleMenuDTO } from "@/models/user";
import { checkPermission } from "@/utils/permission";

interface AuthProps {
  children: React.ReactNode;
  menu: UserRoleMenuDTO;
}

const AuthWrapper: React.FC<Omit<AuthProps, "location">> = ({
  children,
  menu,
}) => {
  const location = useLocation();
  const [hasPermission, setHasPermission] = useState(false);

  console.log("url", location.pathname);
  console.log("menu", menu.menuTree);

  // useEffect(() => {
  //   const isPermitted = checkPermission(location.pathname, menu);
  //   console.log("isPermitted", isPermitted);
  //   setHasPermission(isPermitted);
  //   if (!isPermitted) {
  //     history.push("/403");
  //   }
  // }, [location.pathname, menu]);

  // return hasPermission ? <>{children}</> : null;
  return <>{children}</>;
};

export default connect(
  ({
    user,
  }: {
    user: {
      menu: UserRoleMenuDTO;
    };
  }) => ({
    menu: user.menu,
  })
)(AuthWrapper);
