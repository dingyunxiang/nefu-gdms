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