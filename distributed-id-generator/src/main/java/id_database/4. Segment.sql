-- MySQL ID号段表，存储号段计数信息，获取时返回区间ID
CREATE TABLE t_id_segment (
    id int(10) not null,
    current_max_id bigint(20) not null,
    increment_step int(10) not null,
    primary key (id)
) COLLATE='utf8_general_ci';