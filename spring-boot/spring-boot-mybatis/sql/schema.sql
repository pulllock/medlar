CREATE DATABASE IF NOT EXISTS mybatis_test DEFAULT CHARSET utf8mb4;

CREATE TABLE mt_api (
    id            bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '主键id',
    created_time  datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modified_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    version       smallint(6)                        NOT NULL DEFAULT 1 COMMENT '版本号',
    code          varchar(255)                       NOT NULL COMMENT 'api唯一标识',
    name          varchar(255)                       NOT NULL COMMENT 'api接口名',
    method        varchar(255)                       NOT NULL COMMENT 'api方法名',
    alias         varchar(255)                           NULL COMMENT 'api方法别名',
    sys_id        bigint(20)                         NOT NULL COMMENT '所属业务系统id',
    timeout       smallint(6)                        NOT NULL DEFAULT 1000 COMMENT '超时时间，毫秒',
    PRIMARY KEY pk_id(id),
    INDEX idx_code(code),
    INDEX idx_sys_id(sys_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '接口信息';

CREATE TABLE mt_api_param (
    id            bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '主键id',
    created_time  datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modified_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    version       smallint(6)                        NOT NULL DEFAULT 1 COMMENT '版本号',
    api_id        bigint(20)                         NOT NULL COMMENT 'api id',
    name          varchar(255)                       NOT NULL COMMENT '参数名',
    type          varchar(255)                       NOT NULL COMMENT '参数类型',
    `sequence`    smallint(6)                        NOT NULL COMMENT '参数顺序',
    PRIMARY KEY pk_id(id),
    INDEX idx_api_id(api_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '接口对应的参数信息';

















