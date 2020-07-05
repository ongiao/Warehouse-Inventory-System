create table if not EXISTS tb_product (
  `id` bigint(20) not null auto_increment primary key comment 'product id',
  `name` varchar(50) not null comment 'product name',
  `code` varchar(50) not null unique comment 'product code',
  `weight` double(7,2) not null default 0.00 comment 'product weight',
	`status` int(1) not null default 1 comment 'status of the data, 1 is valid, 0 is invalid',
	`create_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment 'create time',
	`update_time` TIMESTAMP null comment 'update time',
	`delete_time` TIMESTAMP null comment 'delete time'
)engine=InnoDB default charset=utf8 comment='This is product table, which includes attributes such as name, code, weight';

insert into tb_product(name, code, weight) values('face mask', 'FM-HKTV01', 100); 
insert into tb_product(name, code, weight) values('Cola', 'FM-HKTV02', 45);

# --------------------------------------------------------------------------------------------------------------------------------------------

create table if not EXISTS tb_warehouse (
	`id` bigint(20) not null auto_increment primary key comment 'warehouse id',
	`location_name` varchar(10) not null comment 'locatioon name'
)engine=InnoDB default charset=utf8 comment='This is warehouse table, which includes the location name of every warehouse in Hong Kong';

insert into tb_warehouse(location_name) values('TKO');
insert into tb_warehouse(location_name) values('CWB');
insert into tb_warehouse(location_name) values('NP');
insert into tb_warehouse(location_name) values('NTK');
insert into tb_warehouse(location_name) values('PTC');
insert into tb_warehouse(location_name) values('SKM');
insert into tb_warehouse(location_name) values('SO');
insert into tb_warehouse(location_name) values('HMT');
insert into tb_warehouse(location_name) values('ST');
insert into tb_warehouse(location_name) values('STK');
insert into tb_warehouse(location_name) values('ETST');
insert into tb_warehouse(location_name) values('SYP');
insert into tb_warehouse(location_name) values('MK');
insert into tb_warehouse(location_name) values('YTM');

# --------------------------------------------------------------------------------------------------------------------------------------------

create table if not EXISTS tb_warehouse_inventory (
	`id` bigint(20) not null auto_increment UNIQUE comment 'warehouse inventory id',
	`product_name` varchar(50) not null comment 'product name',
	`product_code` varchar(50) not null comment 'product code',
	`location_name` varchar(10) not null comment 'locatioon name',
	`quanty` int(10) not null default 0 comment 'the quanty of the specific product',
	`per_weight` double(7,2) not null default 0.00 comment 'product weight',
	primary key (`product_code`, `location_name`)
)engine=InnoDB default charset=utf8 comment='This is warehouse table, which includes the location name of every warehouse in Hong Kong';

insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('face mask', 'FM-HKTV01', 'TKU', 50, 100.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('face mask', 'FM-HKTV01', 'CWB', 10, 100.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('bottle', 'FM-HKTV05', 'TKU', 20, 20.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('bottle', 'FM-HKTV05', 'CWB', 20, 20.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('iphone XR', 'FM-HKTV06', 'TKU', 0, 500.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('iphone XR', 'FM-HKTV06', 'CWB', 30, 500.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('MacBook Pro', 'FM-HKTV08', 'TKU', 20, 2000.00);
insert into tb_warehouse_inventory(product_name, product_code, location_name, quanty, per_weight) values('MacBook Pro', 'FM-HKTV08', 'CWB', 0, 2000.00);