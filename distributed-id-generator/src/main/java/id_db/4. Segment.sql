-- MySQL ID号段表: 存储号段计数信息，获取时返回区间ID
CREATE TABLE t_id_segment (
    id int(10) not null,
    current_max_id bigint(20) not null,
    increment_step int(10) not null,
    primary key (id)
) COLLATE='utf8_general_ci';

-- TinyID表设计: 从DB中获取TinyIdInfo转换成SegmentId返回
CREATE TABLE `tiny_id_info` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `biz_type` varchar(63) NOT NULL DEFAULT '' COMMENT '业务类型，唯一',
    `begin_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '开始id，仅记录初始值，无其他含义。初始化时begin_id和max_id应相同',
    `max_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '当前最大id',
    `step` int(11) DEFAULT '0' COMMENT '步长',
    `delta` int(11) NOT NULL DEFAULT '1' COMMENT '每次id增量',
    `remainder` int(11) NOT NULL DEFAULT '0' COMMENT '余数',
    `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
    `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_biz_type` (`biz_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT 'id信息表';

CREATE TABLE `tiny_id_token` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `token` varchar(255) NOT NULL DEFAULT '' COMMENT 'token',
    `biz_type` varchar(63) NOT NULL DEFAULT '' COMMENT '此token可访问的业务类型标识',
    `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
    `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT 'token信息表';
