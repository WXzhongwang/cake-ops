create table if not exists account
(
    id              bigint auto_increment
        primary key,
    account_name    varchar(255)                       not null,
    phone           varchar(255)                       null,
    email           varchar(255)                       null,
    tenant_id       bigint                             not null,
    is_admin        char     default '0'               not null,
    account_type    varchar(20)                        not null,
    status          char     default '0'               not null,
    is_deleted      char     default '0'               not null,
    last_login_ip   varchar(255)                       null,
    last_login_time datetime                           null,
    feature         varchar(2000)                      null,
    gmt_create      datetime default CURRENT_TIMESTAMP not null,
    gmt_modified    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    head_image      varchar(512)                       null,
    dingding        varchar(255)                       null,
    qq              varchar(255)                       null,
    wechat          varchar(255)                       null,
    birthday        date                               null,
    tags            varchar(255)                       null,
    ding_union_id   varchar(255)                       null,
    ding_user_id    varchar(255)                       null,
    work_no         varchar(64)                        null,
    creator         varchar(255)                       null,
    modifier        varchar(255)                       null
)
    charset = utf8mb3;

create table if not exists acl_permission
(
    id            bigint                                 not null
        primary key,
    app_code      varchar(255)                           not null,
    tenant_id     bigint                                 null,
    resource_type varchar(30)                            null,
    resource_name varchar(255)                           null,
    resource_path varchar(255)                           null,
    creator       varchar(255) default '0'               null,
    modifier      varchar(255) default '0'               null,
    gmt_create    datetime     default CURRENT_TIMESTAMP not null,
    gmt_modified  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted    char                                   not null,
    ref_menu_id   bigint                                 null,
    status        char                                   null
);

create table if not exists acl_role
(
    id             bigint                             not null
        primary key,
    app_code       varchar(255)                       not null,
    tenant_id      bigint                             null,
    role_name      varchar(255)                       not null,
    role_key       varchar(255)                       not null,
    role_desc      varchar(255)                       not null,
    creator        varchar(255)                       null,
    modifier       varchar(255)                       null,
    gmt_create     datetime default CURRENT_TIMESTAMP not null,
    gmt_modified   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    is_deleted     char                               null,
    parent_role_id bigint                             null,
    status         char                               null
);

create table if not exists application
(
    id           bigint                             not null
        primary key,
    app_name     varchar(255)                       not null,
    app_code     varchar(255)                       not null,
    auth_type    varchar(255)                       not null,
    creator      varchar(255)                       null,
    modifier     varchar(255)                       null,
    gmt_create   datetime default CURRENT_TIMESTAMP not null,
    gmt_modified datetime default CURRENT_TIMESTAMP not null,
    is_deleted   char                               not null,
    status       char                               not null,
    constraint idx_unique_app_code
        unique (app_code) comment '应用编码唯一索引'
);

create table if not exists isv
(
    id           bigint                               not null
        primary key,
    name         varchar(255)                         not null,
    short_name   varchar(255)                         not null,
    status       varchar(4) default '0'               not null,
    phone        varchar(255)                         null,
    email        varchar(255)                         null,
    country      varchar(255)                         null,
    url          varchar(255)                         null,
    register_ip  varchar(255)                         null,
    creator      varchar(255)                         null,
    modifier     varchar(255)                         null,
    gmt_create   datetime   default CURRENT_TIMESTAMP not null,
    gmt_modified datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted   char       default '0'               not null,
    max_tenants  int        default 100               not null,
    address      varchar(512)                         null
)
    charset = utf8mb3;

create table if not exists menu
(
    id           bigint auto_increment
        primary key,
    app_code     varchar(255)       not null,
    tenant_id    bigint             null,
    name         varchar(255)       null,
    path         varchar(255)       not null,
    parent_id    bigint default 0   not null,
    level        int                not null,
    icon         varchar(512)       null,
    hidden       char               not null,
    creator      varchar(64)        null,
    modifier     varchar(64)        null,
    gmt_create   datetime           not null,
    gmt_modified datetime           null,
    is_deleted   char   default '0' not null,
    sort         int                not null,
    status       char               null
)
    collate = utf8mb3_bin;

create table if not exists role_menu
(
    id           bigint                             not null
        primary key,
    app_code     varchar(255)                       not null,
    tenant_id    bigint                             null,
    role_id      bigint                             not null,
    menu_id      bigint                             not null,
    is_deleted   char                               not null,
    creator      varchar(64)                        not null,
    modifier     varchar(64)                        null,
    gmt_create   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '角色菜单绑定关系' collate = utf8mb3_bin;

create table if not exists role_permission
(
    id            bigint                             not null
        primary key,
    app_code      varchar(255)                       null,
    tenant_id     bigint                             null,
    role_id       bigint                             not null,
    permission_id bigint                             not null,
    creator       varchar(255)                       null,
    modifier      varchar(255)                       null,
    gmt_create    datetime default CURRENT_TIMESTAMP not null,
    gmt_modified  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    id_deleted    char     default '0'               not null
);

create table if not exists safe_strategy
(
    id             bigint auto_increment
        primary key,
    account_id     bigint                             not null,
    login_strategy char(20)                           not null,
    auth_code      varchar(255)                       not null,
    auth_value     varchar(255)                       null,
    block_at       datetime                           null,
    expired_at     datetime                           null,
    is_deleted     char     default '0'               not null,
    creator        varchar(255)                       null,
    modifier       varchar(255)                       null,
    gmt_create     datetime default CURRENT_TIMESTAMP not null,
    gmt_modified   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    charset = utf8mb3;

create table if not exists tenant
(
    id           bigint                             not null
        primary key,
    isv_id       bigint                             null,
    name         varchar(255)                       not null,
    short_name   varchar(255)                       not null,
    email        varchar(255)                       not null,
    source       varchar(255)                       not null,
    phone        varchar(32)                        null,
    is_deleted   char     default '0'               not null,
    creator      varchar(255)                       null,
    modifier     varchar(255)                       null,
    gmt_create   datetime default CURRENT_TIMESTAMP not null,
    gmt_modified datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    address      varchar(255)                       null,
    status       char     default '0'               not null
)
    charset = utf8mb3;

create table if not exists user_role
(
    id           bigint                                 not null
        primary key,
    app_code     varchar(255)                           not null,
    tenant_id    bigint                                 null,
    user_id      bigint                                 not null,
    role_id      bigint                                 not null,
    creator      varchar(255) default '0'               null,
    modifier     varchar(255) default '0'               null invisible,
    gmt_create   datetime     default CURRENT_TIMESTAMP not null,
    gmt_modified datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted   char         default '0'               not null,
    status       char         default '0'               null
);

