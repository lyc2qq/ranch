# 搜索模板信息集

请求
- Service Key - ranch.editor.search
- URI - /editor/search

参数

|名称|类型|必须|说明|
|---|---|---|---|
|type|string|是|类型。|
|labels|string|否|标签，多个标签以逗号分隔，空表示搜索关键词。|
|words|string|否|关键词，多个关键词以逗号分隔，空表示所有。|
|order|string|否|排序规则：hot-热门（默认）；newest-最新；none-不排序。|
|pageSize|int|否|每页显示记录数，默认20。|
|pageNum|int|否|当前显示页数。|

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
            "label": "标签",
            "width": "宽度",
            "height": "高度",
            "image": "预览图",
            "screenshot": "主截图",
            "state": "状态：0-待审核；1-审核通过；2-审核拒绝；3-已上架；4-已下架",
            "source": "来源",
            "used": "被使用次数",
            "create": "创建时间",
            "modify": "修改时间"
        }
    ]
}
```

> 如果未检索到数据,将返回空JSON`{}`。
