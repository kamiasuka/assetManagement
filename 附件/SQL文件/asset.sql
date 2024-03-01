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
) COMMENT '资产信息' CHARSET = utf8mb4;

# 修改记录表
DROP TABLE IF EXISTS modify_record;
CREATE TABLE modify_record
(
    id          bigint primary key auto_increment COMMENT '主键',
    operator    varchar(50)  not null COMMENT '操作者',
    obj         varchar(255) not null COMMENT '被修改的对象',
    content     varchar(255) not null COMMENT '修改的内容',
    update_time datetime     not null COMMENT '修改时间',
    reviewer    varchar(50)  not null COMMENT '审核者',
    past_time   datetime     not null COMMENT '审核通过时间'
) COMMENT '修改记录' CHARSET = utf8mb4;


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
       ('文物和陈列品', DEFAULT, DEFAULT, DEFAULT),
       ('家具用具', DEFAULT, DEFAULT, DEFAULT),
       ('图书档案', DEFAULT, DEFAULT, DEFAULT),
       ('动植物', DEFAULT, DEFAULT, DEFAULT),
       ('无形资产', DEFAULT, DEFAULT, DEFAULT),
       ('在建工程', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO asset.asset_category(name, parent_id, level, is_parent)
VALUES ('电子设备', 8, 2, 0);
UPDATE asset.asset_category
set is_parent=1
where id = 8;

# 资产信息表操作
INSERT INTO asset.asset_info(code, name, type,max_type, dept, unit, life, amount, use_status, review_status, approval_date)
VALUES ('1', '土地测试', '土地','土地', '', '宝相公司', 50, 1000000.00, DEFAULT, DEFAULT, DEFAULT),
       ('2', '电脑a', '电脑', '通用设备','', '宝相公司', 5, 3000.00, DEFAULT, DEFAULT, DEFAULT),
       ('3', '办公桌a', '家具用具','家具用具', '', '宝相公司', 20, 10000.00, DEFAULT, DEFAULT, DEFAULT),
       ('4', '办公桌b', '家具用具','家具用具', '', '宝相公司', 20, 7000.00, DEFAULT, DEFAULT, DEFAULT);

# 部门管理数据库表
CREATE TABLE departments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             unit VARCHAR(255) NOT NULL,
                             code VARCHAR(255) NOT NULL UNIQUE,
                             phone VARCHAR(20),
                             fax VARCHAR(20)