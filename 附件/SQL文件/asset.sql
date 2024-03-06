DROP DATABASE IF EXISTS asset;
CREATE DATABASE asset CHARACTER SET utf8mb4;
use asset;

# 用户表，根据身份判断用户是管理员还是审核员
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       bigint primary key auto_increment COMMENT '主键',
    username varchar(30) not null unique COMMENT '用户名',
    password varchar(30) not null COMMENT '密码' DEFAULT '2309',
    nickname varchar(30) not null COMMENT '昵称' DEFAULT '操作员',
    identity varchar(30) not null COMMENT '身份',
    tel      varchar(30)                         DEFAULT '' COMMENT '联系电话',
    email    varchar(30)                         DEFAULT '' COMMENT '联系邮箱',
    dept     varchar(30)                         DEFAULT '' COMMENT '所属部门',
    unit     varchar(30)                         DEFAULT '' COMMENT '所属单位'
) COMMENT '用户' CHARSET = utf8mb4;

# 部门表
DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id   bigint primary key auto_increment COMMENT '主键',
    code varchar(50) unique not null COMMENT '部门编码',
    name varchar(50)        not null COMMENT '部门名称',
    tel  varchar(30) DEFAULT '' COMMENT '联系电话',
    unit varchar(30) DEFAULT '' COMMENT '所属单位'
) COMMENT '部门' CHARSET = utf8mb4;

# 资产分类表
DROP TABLE IF EXISTS asset_category;
CREATE TABLE asset_category
(
    id        int primary key auto_increment COMMENT '主键',
    name      varchar(50) not null COMMENT '类别名称',
    parent_id int unsigned DEFAULT 0 COMMENT '父组类别ID，如果无父级，则为0',
    level     int unsigned DEFAULT 1 COMMENT '级别，最顶级为1，次级为2，以此类推',
    is_parent int unsigned DEFAULT 0 COMMENT '是否包含子级，1=父级，0=不是父级'
) COMMENT '资产分类' CHARSET = utf8mb4;

# 资产信息表
DROP TABLE IF EXISTS asset_info;
CREATE TABLE asset_info
(
    id            bigint primary key auto_increment COMMENT '主键',
    code          varchar(30) unique not null COMMENT '资产编码',
    name          varchar(50)        not null COMMENT '资产名',
    type          varchar(50)        not null COMMENT '资产类型',
    max_type      varchar(50)        not null COMMENT '所属的一级分类',
    dept          varchar(30)    DEFAULT '' COMMENT '所属部门',
    unit          varchar(30)    DEFAULT '' COMMENT '所属单位',
    life          int unsigned   DEFAULT 0 COMMENT '使用年限',
    amount        decimal(10, 2) DEFAULT 0.00 COMMENT '资产价值',
    use_status    varchar(30)    DEFAULT '在用' COMMENT '使用状态，包括在用，闲置，在修，报废',
    review_status varchar(30)    DEFAULT '审核中' COMMENT '审核状态，包括审核中，已通过',
    approval_date datetime       DEFAULT NULL COMMENT '审核通过日期'
) COMMENT '资产变更信息' CHARSET = utf8mb4;

# 资产数据变更存储表
DROP TABLE IF EXISTS asset_change_info;
CREATE TABLE asset_change_info
(
    id            bigint primary key auto_increment COMMENT '主键',
    code          varchar(30) unique not null COMMENT '资产编码',
    name          varchar(50)        not null COMMENT '资产名',
    type          varchar(50)        not null COMMENT '资产类型',
    max_type      varchar(50)        not null COMMENT '所属的一级分类',
    dept          varchar(30)    DEFAULT '' COMMENT '所属部门',
    unit          varchar(30)    DEFAULT '' COMMENT '所属单位',
    life          int unsigned   DEFAULT 0 COMMENT '使用年限',
    amount        decimal(10, 2) DEFAULT 0.00 COMMENT '资产价值',
    use_status    varchar(30)    DEFAULT '在用' COMMENT '使用状态，包括在用，闲置，在修，报废',
    review_status varchar(30) COMMENT '审核状态，包括审核中，已通过，未通过',
    submit_date   datetime       DEFAULT NULL COMMENT '提交日期日期',
    approval_date datetime       DEFAULT NULL COMMENT '审核通过日期'
) COMMENT '资产信息' CHARSET = utf8mb4;


# 资产数据录入存储表
DROP TABLE IF EXISTS asset_add_info;
CREATE TABLE asset_add_info
(
    id            bigint primary key auto_increment COMMENT '主键',
    code          varchar(30) unique not null COMMENT '资产编码',
    name          varchar(50)        not null COMMENT '资产名',
    type          varchar(50)        not null COMMENT '资产类型',
    max_type      varchar(50)        not null COMMENT '所属的一级分类',
    dept          varchar(30)    DEFAULT '' COMMENT '所属部门',
    unit          varchar(30)    DEFAULT '' COMMENT '所属单位',
    life          int unsigned   DEFAULT 0 COMMENT '使用年限',
    amount        decimal(10, 2) DEFAULT 0.00 COMMENT '资产价值',
    use_status    varchar(30)    DEFAULT '在用' COMMENT '使用状态，包括在用，闲置，在修，报废',
    review_status varchar(30)    DEFAULT '审核中' COMMENT '审核状态，包括审核中，已通过',
    submit_date   datetime       DEFAULT NULL COMMENT '提交日期日期',
    approval_date datetime       DEFAULT NULL COMMENT '审核通过日期'
) COMMENT '资产录入信息' CHARSET = utf8mb4;

# 修改日志记录表
DROP TABLE IF EXISTS modify_log;
CREATE TABLE modify_log
(
    id          bigint primary key auto_increment COMMENT '主键id',
    operator    varchar(50)  not null COMMENT '操作者',
    type        varchar(255) not null comment '操作类型',
    description varchar(255) not null comment '操作描述',
    model       varchar(255) not null comment '操作模块',
    date        datetime     not null comment '操作日期',
    reviewer    varchar(50)  not null COMMENT '审核者',
    past_time   datetime     not null COMMENT '审核通过时间'
) COMMENT '修改日志' CHARSET = utf8mb4;

# 用户表操作
INSERT INTO asset.user(username, password, nickname, identity, tel, email, dept, unit)
VALUES ('admin', DEFAULT, 'admin', '管理员', '12345678901', '1232@aa.com', '资产部', '宝相公司'),
       ('admin2', DEFAULT, DEFAULT, '管理员', '12345678901', '1232@aa.com', '资产部', '宝相公司'),
       ('auditor', DEFAULT, 'auditor', '审核员', '11111111555', '4542@aa.com', '资产部', '宝相公司');
# INSERT INTO asset.user(username, password, identity, tel, email, dept, unit) VALUES ('admin','sc','管理员2','121','123ss2@aa.com','资产部','宝相公司');

# 部门表操作
INSERT INTO asset.dept(code, name, tel, unit)
VALUES ('1', '资产部', '12345678900', '宝相公司'),
       ('2', '测试部门', '12345678901', '宝相公司'),
       ('3', '办公室', '12345678910', '宝相公司'),
       ('4', '财务部', '12345678911', '宝相公司');

# 资产分类表操作
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('土地', DEFAULT, DEFAULT, DEFAULT),
       ('房屋', DEFAULT, DEFAULT, DEFAULT),
       ('构筑物', DEFAULT, DEFAULT, DEFAULT),
       ('通用设备', DEFAULT, DEFAULT, DEFAULT),
       ('专用设备', DEFAULT, DEFAULT, DEFAULT),
       ('车辆', DEFAULT, DEFAULT, DEFAULT),
       ('图书档案', DEFAULT, DEFAULT, DEFAULT),
       ('动植物', DEFAULT, DEFAULT, DEFAULT),
       ('无形资产', DEFAULT, DEFAULT, DEFAULT),
       ('在建工程', DEFAULT, DEFAULT, DEFAULT);


INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('商业土地', 1, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('住房土地', 1, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('办公室', 2, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('食堂', 2, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('烟囱', 3, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('围墙', 3, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('家具用具', 4, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('文物和陈列品', 4, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('电子设备', 4, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('专用仪器仪表', 5, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('文艺设备', 5, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('体育设备', 5, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('小轿车', 6, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('货车', 6, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('图书', 7, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('档案', 7, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('动物', 8, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('植物', 8, 2, 1);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('金融资产', 9, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('专利权', 9, 2, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('绿化工程', 10, 2, 0);


INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('办公桌', 17, 3, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('椅子', 17, 3, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('沙发', 17, 3, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('取暖降温设备', 17, 3, 0);

INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('文物', 18, 3, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('陈列品', 18, 3, 0);

INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('电脑', 19, 3, 0);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('盆栽', 28, 3, 0);


UPDATE asset.asset_category
set is_parent=1
where id = 8;

# 资产信息表操作
INSERT INTO asset.asset_info(code, name, type, max_type, dept, unit, life, amount, use_status, review_status,
                             approval_date)
VALUES ('1', '土地测试a', '商业土地', '土地', '', '宝相公司', 50, 1000000.00, DEFAULT, DEFAULT, DEFAULT),
       ('2', '办公室a', '办公室', '房屋', '', '宝相公司', 10, 300000.00, DEFAULT, DEFAULT, DEFAULT),
       ('3', '枫园食堂', '食堂', '房屋', '', '宝相公司', 5, 300000.00, DEFAULT, DEFAULT, DEFAULT),
       ('4', '烟囱a', '烟囱', '构筑物', '', '宝相公司', 5, 33000.00, DEFAULT, DEFAULT, DEFAULT),
       ('5', '围墙a', '围墙', '构筑物', '', '宝相公司', 5, 433000.00, DEFAULT, DEFAULT, DEFAULT),
       ('6', '办公桌a', '办公桌', '通用设备', '', '宝相公司', 10, 5000.00, DEFAULT, DEFAULT, DEFAULT),
       ('7', '椅子a', '椅子', '通用设备', '', '宝相公司', 10, 5000.00, DEFAULT, DEFAULT, DEFAULT),
       ('8', '沙发a', '沙发', '通用设备', '', '宝相公司', 10, 5000.00, DEFAULT, DEFAULT, DEFAULT),
       ('9', '空调a', '取暖降温设备', '通用设备', '', '宝相公司', 10, 5000.00, DEFAULT, DEFAULT, DEFAULT),
       ('10', '电脑a', '电脑', '通用设备', '', '宝相公司', 10, 5000.00, DEFAULT, DEFAULT, DEFAULT),
       ('11', '投影仪', '文艺设备', '专用设备', '', '宝相公司', 10, 5000.00, DEFAULT, DEFAULT, DEFAULT),
       ('12', '乒乓球桌', '体育设备', '专用设备', '', '宝相公司', 15, 8000.00, DEFAULT, DEFAULT, DEFAULT),
       ('13', '小轿车a', '小轿车', '车辆', '', '宝相公司', 20, 200000.00, DEFAULT, DEFAULT, DEFAULT),
       ('14', '货车a', '货车', '车辆', '', '宝相公司', 20, 700000.00, DEFAULT, DEFAULT, DEFAULT),
       ('15', '绿萝盆栽', '盆栽', '动植物', '', '宝相公司', 20, 70000.00, DEFAULT, DEFAULT, DEFAULT),
       ('16', '花坛', '绿化工程', '在建工程', '', '宝相公司', 20, 700000.00, DEFAULT, DEFAULT, DEFAULT);



INSERT INTO asset.asset_info(code, name, type, max_type, dept, unit, life, amount, use_status, review_status,
                             approval_date)
VALUES ('17', '电脑a', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT,  '已通过', DEFAULT),
       ('18', '电脑b', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('19', '电脑c', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('20', '电脑d', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('21', '电脑e', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('22', '电脑f', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('23', '电脑g', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('24', '电脑h', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('25', '电脑i', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('26', '电脑j', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('27', '电脑l', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('29', '电脑m', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('30', '电脑n', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('31', '电脑o', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('32', '电脑p', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('33', '电脑q', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT),
       ('34', '电脑r', '电脑', '通用设备', '开发部门', '宝相公司', 10, 5000.00, DEFAULT, '已通过', DEFAULT);
