# 截图

请求
- Service Key - ranch.editor.screenshot.capture
- URI - /editor/screenshot/capture

参数

|名称|类型|必须|说明|
|---|---|---|---|
|editor|string|是|编辑器ID。|
|mainWidth|int|是|主页面宽。|
|mainHeight|int|是|主页面高。|
|pageWidth|int|是|页面宽。|
|pageHeight|int|是|页面高。|

返回值
```
"异步ID"
```

> 异步ID参考[异步服务](../../../ranch-base/doc/async.md)。

> 需要配置`ranch.editor.screenshot.capture`参数，系统调用时会拼接`?sid={当前用户Session ID}&editor={编辑器ID}&page={页面ID，主页面为空""}`参数。

> 操作用户等级必须介于`[50-99]`之间（包含）。
