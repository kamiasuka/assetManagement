DROP DATABASE IF EXISTS asset;
CREATE DATABASE asset CHARACTER SET utf8mb4;
use asset;

# 用户表，根据身份判断用户是管理员还是审核员
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    username varchar(30) primary key COMMENT '用户名',
    password varchar(30) not null COMMENT '密码',
    identity varchar(30) not null COMMENT '身份',
    tel      varchar(30) DEFAULT '' COMMENT '联系电话',
    email    varchar(30) DEFAULT '' COMMENT '联系邮箱',
    dept     varchar(30) DEFAULT '' COMMENT '所属部门',
    unit     varchar(30) DEFAULT '' COMMENT '所属单位'
) COMMENT '登录用户' CHARSET = utf8mb4;

# 部门表
DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id   varchar(50) primary key COMMENT '部门编码',
    name varchar(50) not null COMMENT '部门名称',
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
    level     int unsigned DEFAULT 0 COMMENT '级别，最顶级为1，次级为2，以此类推',
    type      varchar(50)  DEFAULT '' COMMENT '资产类型',
    is_parent int unsigned DEFAULT 0 COMMENT '是否包含子级，1=父级，0=不是父级'
) COMMENT '资产分类' CHARSET = utf8mb4;

# 资产信息表
DROP TABLE IF EXISTS asset_info;
CREATE TABLE asset_info
(
    id            varchar(30) primary key COMMENT '资产编码',
    name          varchar(50) not null COMMENT '资产名',
    type          varchar(50) not null COMMENT '资产类型',
    dept          varchar(30)    DEFAULT '' COMMENT '所属部门',
    unit          varchar(30)    DEFAULT '' COMMENT '所属单位',
    life          int unsigned   DEFAULT 0 COMMENT '使用年限',
    amount        decimal(10, 2) DEFAULT 0.00 COMMENT '资产价值',
    use_status    varchar(30)    DEFAULT '闲置' COMMENT '使用状态，包括在用，闲置，在修，报废',
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
INSERT INTO asset.user(username, password, identity, tel, email, dept, unit) VALUES ('admin','admin','管理员','12345678901','1232@aa.com','资产部','宝相公司'),
                                                                                    ('auditor','auditor','审核员','11111111555','4542@aa.com','资产部','宝相公司');