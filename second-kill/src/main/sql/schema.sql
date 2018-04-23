CREATE DATABASE second_kill;

use second_kill;

create table second_kill(
  second_kill_id bigint not null auto_increment comment '商品库存id',
  name varchar(120) not null comment '商品名称',
  number int not null comment '库存数量',
  start_time timestamp not null comment '秒杀开始时间',
  end_time timestamp not null comment '秒杀结束时间',
  create_time timestamp not null default current_timestamp comment '创建时间',
  primary key (second_kill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)

) engine=InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';

insert into second_kill (name, number, start_time, end_time)
    values
      ('1000元秒杀iphone7', 100, '2018-04-02 00:00:00', '2018-04-03 00:00:00'),
      ('1000元秒杀iphone8', 10, '2018-04-02 00:00:00', '2018-04-03 00:00:00'),
      ('1000元秒杀iphone9', 200, '2018-04-02 00:00:00', '2018-04-03 00:00:00'),
      ('1000元秒杀iphonex', 100, '2018-04-02 00:00:00', '2018-04-03 00:00:00'),
      ('1000元秒杀iphone10', 300, '2018-04-02 00:00:00', '2018-04-03 00:00:00');


create table success_killed(
  second_kill_id bigint not null comment '秒杀商品id',
  user_phone bigint not null comment '用户手机号',
  state tinyint not null default -1 comment '状态：-1 无效，0 成功，1 已付款，2 已发货',
  create_time timestamp not null comment '创建时间',
  primary key (second_kill_id, user_phone),
  key idx_create_time(create_time)
) engine=InnoDB default charset=utf8 comment='秒杀成功明细';