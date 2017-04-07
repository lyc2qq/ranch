DROP TABLE IF EXISTS t_address;
CREATE TABLE t_address
(
  c_id CHAR(36) NOT NULL COMMENT '主键',
  c_user CHAR(36) NOT NULL COMMENT '用户ID',
  c_region CHAR(36) DEFAULT NULL COMMENT '行政区字典ID',
  c_detail VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
  c_postcode VARCHAR(255) DEFAULT NULL COMMENT '邮政编码',
  c_latitude VARCHAR(255) DEFAULT NULL COMMENT 'GPS纬度',
  c_longitude VARCHAR(255) DEFAULT NULL COMMENT 'GPS经度',
  c_label VARCHAR(255) DEFAULT NULL COMMENT '标签',
  c_major INT DEFAULT 0 COMMENT '默认地址：0-否；1-是',
  c_time DATETIME DEFAULT NULL COMMENT '时间',

  PRIMARY KEY pk(c_id) USING HASH,
  KEY k_user(c_user) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;