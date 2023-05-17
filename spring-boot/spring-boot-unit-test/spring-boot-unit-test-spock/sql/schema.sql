CREATE DATABASE IF NOT EXISTS spring_boot_unit_test DEFAULT CHARSET utf8mb4;

CREATE TABLE user (
    id            bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '主键id',
    create_time   datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    version       smallint(6)                        NOT NULL DEFAULT 1 COMMENT '版本号',
    name          varchar(255)                       NOT NULL COMMENT '姓名',
    sex           smallint(6)                        NOT NULL COMMENT '性别：1-男 2-女',
    PRIMARY KEY pk_id(id),
    INDEX idx_name(name)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息表';