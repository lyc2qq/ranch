# 修改当前用户信息

请求
- Service Key - ranch.user.modify
- URI - /user/modify

参数

|名称|类型|必须|说明|
|---|---|---|---|
|name|char(100)|否|姓名。|
|nick|char(100)|否|昵称。|
|mobile|char(100)|否|手机号。|
|email|char(100)|否|Email地址。|
|gender|int|否|性别：0-未知；1-男；2-女。|
|address|char(100)|否|详细地址。|
|birthday|date|否|出生日期，格式：yyyy-MM-dd。|

> 参数未提供（is null）则表示不修改，保留原值。

返回值
```json
{
    "id": "ID值",
    "name": "姓名",
    "nick": "昵称",
    "mobile": "手机号",
    "email": "Email地址",
    "portrait": "头像",
    "gender": "性别：0-未知；1-男；2-女",
    "address": "详细地址",
    "birthday": "出生日期",
    "code": "唯一编码",
    "register": "注册时间",
    "grade": "等级：<50为用户；>=50为管理员；99为超级管理员",
    "state": "状态：0-正常；1-禁用"
}
```
