# 检索编辑器信息集

请求
- Service Key - ranch.editor.query
- URI - /editor/query

参数

|名称|类型|说明|
|---|---|---|
|mobile|string|用户手机号，为空表示不限制。|
|email|string|用户Email，为空表示不限制。|
|nick|string|用户昵称，为空表示不限制。|
|type|string|类型，为空表示不限制。|
|name|string|名称，模糊匹配，为空表示不限制。|
|keyword|string|关键词，模糊匹配，为空表示不限制。|
|createStart|string|创建开始日期，格式：yyyy-MM-dd，为空表示不限制。|
|createEnd|string|创建结束日期，格式：yyyy-MM-dd，为空表示不限制。|
|modifyStart|string|编辑开始日期，格式：yyyy-MM-dd，为空表示不限制。|
|modifyEnd|string|编辑结束日期，格式：yyyy-MM-dd，为空表示不限制。|
|pageSize|int|每页显示记录数，默认20。|
|pageNum|int|当前显示页数。|

返回值
```json
{
    "count": "总记录数",
    "size": "每页显示记录数",
    "number": "当前显示页数",
    "list": [
        {
            "type": "类型",
            "sort": "顺序",
            "name": "名称",
            "keyword": "关键词",
            "width": "宽度",
            "height": "高度",
            "image": "预览图",
            "create": "创建时间",
            "modify": "修改时间",
            "role": "类型：0-所有者；1-可编辑；2-仅浏览"
        }
    ]
}
```