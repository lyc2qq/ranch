# 查找绑定信息

请求
- Service Key - ranch.device.find
- URI - /device/find

参数

|名称|类型|说明|
|---|---|---|
|user|char(36)|用户ID，空使用当前用户。|
|appCode|char(100)|APP编码。|
|type|char(100)|类型：android、ios。|

返回值
```json
{
  "id": "ID值",
  "user": "用户",
  "appCode": "APP编码",
  "type": "类型：android、ios",
  "macId": "Mac ID",
  "version": "版本号",
  "time": "时间"
}
```

> 后台管理接口，需验证[请求参数签名](https://github.com/heisedebaise/tephra/blob/master/tephra-ctrl/doc/sign.md)。