-- TODO. 通过DB系统自增特性生成ID值

-- MySQL 设置数据库自增属性值(偏移量, 间隔)
CREATE DATABASE seqid;
set @@auto_increment_offset = 1;
set @@auto_increment_increment = 2;

-- 创建带有自增特定的主键
CREATE TABLE seqid.`tb_distributed_id` (
	 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	 `stub` CHAR(10) NOT NULL DEFAULT '',
	 PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci';

-- 替换语句流程: select -> delete -> insert
-- 在更换数据表中字段的值时拿到最新的ID值
BEGIN;
REPLACE INTO seqid.`tb_distributed_id` (stub) VALUES ('anyword');
SELECT LAST_INSERT_ID();
COMMIT;