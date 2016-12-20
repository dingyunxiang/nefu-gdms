use gdms;
CREATE TABLE IF NOT EXISTS `role`(
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL COMMENT '角色名',
    CONSTRAINT `pk_role_id` PRIMARY KEY(`id`),
    UNIQUE KEY `uc_role_name`(`name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '角色表';

CREATE TABLE IF NOT EXISTS `user_role`(
    `user_id` BIGINT,
    `role_id` BIGINT
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '用户-角色关联表';


CREATE TABLE IF NOT EXISTS `resource`(
    `id` BIGINT AUTO_INCREMENT,
    `url` VARCHAR(1024) NOT NULL COMMENT '资源URL',
    CONSTRAINT `pk_resource_id` PRIMARY KEY(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '资源表';


CREATE TABLE IF NOT EXISTS `role_resource`(
    `role_id` BIGINT,
    `resource_id` BIGINT
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '角色-资源关联表';

-- used in tests that use HSQL
create table oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

create table oauth_client_token (
    token_id VARCHAR(256),
    token LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name VARCHAR(256),
    client_id VARCHAR(256)
);

create table oauth_access_token (
    token_id VARCHAR(256),
    token LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name VARCHAR(256),
    client_id VARCHAR(256),
    authentication LONGVARBINARY,
    refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
    token_id VARCHAR(256),
    token LONGVARBINARY,
    authentication LONGVARBINARY
);

create table oauth_code (
    code VARCHAR(256), authentication LONGVARBINARY
);

create table oauth_approvals (
    userId VARCHAR(256),
    clientId VARCHAR(256),
    scope VARCHAR(256),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
    appId VARCHAR(256) PRIMARY KEY,
    resourceIds VARCHAR(256),
    appSecret VARCHAR(256),
    scope VARCHAR(256),
    grantTypes VARCHAR(256),
    redirectUrl VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation VARCHAR(4096),
    autoApproveScopes VARCHAR(256)
);