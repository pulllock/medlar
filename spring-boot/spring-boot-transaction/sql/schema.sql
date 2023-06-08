CREATE DATABASE IF NOT EXISTS spring_boot_transaction DEFAULT CHARSET utf8mb4;

CREATE TABLE user_badge(
  id                  bigint unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time         datetime                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time         datetime                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  version             int unsigned                   NOT NULL DEFAULT 1 COMMENT '版本号',
  user_id             bigint unsigned                NOT NULL COMMENT '用户ID',
  badge_id            bigint unsigned                NOT NULL COMMENT '徽章ID',
  count               int unsigned                   NOT NULL COMMENT '数量',
  latest_acquire_time datetime                       NOT NULL COMMENT '最近一次获得的时间',
  PRIMARY KEY (id),
  KEY idx_user_badge_id (user_id, badge_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户徽章表';

CREATE TABLE user_badge_log(
  id           bigint unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time  datetime                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time  datetime                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  version      int unsigned                   NOT NULL DEFAULT 1 COMMENT '版本号',
  user_id      bigint unsigned                NOT NULL COMMENT '用户ID',
  badge_id     bigint unsigned                NOT NULL COMMENT '徽章ID',
  acquire_time datetime                       NOT NULL COMMENT '获得的时间',
  PRIMARY KEY (id),
  KEY idx_user_badge_id (user_id, badge_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户徽章记录表';