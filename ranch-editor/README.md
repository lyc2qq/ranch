# 编辑器

属性

|属性|类型|说明|
|---|---|---|
|template|int|模板：0-否；1-是。|
|type|char(100)|类型。|
|sort|int|顺序，默认0，降序。|
|name|char(100)|名称。|
|keyword|char(100)|关键词。|
|width|int|宽度。|
|height|int|高度。|
|image|char(100)|预览图。|
|screenshot|char(100)|主截图。|
|state|int|状态：0-待审核；1-审核通过；2-审核拒绝；3-已上架；4-已下架；5-已删除。|
|source|char(100)|来源。|
|used|int|被使用次数。|
|create|datetime|创建时间。|
|modify|long|修改时间。|

> 支持扩展自定义属性。

[检索编辑器信息集](doc/query.md)

[检索当前用户编辑器信息集](doc/query-user.md)

[查找](doc/find.md)

[保存](doc/save.md)

[修改](doc/modify.md)

[生成预览图](doc/image.md)

[审核通过](doc/pass.md)

[审核不通过](doc/reject.md)

[上架](doc/sale.md)

[下架](doc/nonsale.md)

[导出PDF](doc/pdf.md)

[复制](doc/copy.md)

[搜索模板信息集](doc/search.md)

[重建搜索索引](doc/reset-search-index.md)

## 角色

属性

|属性|类型|说明|
|---|---|---|
|user|char(36)|用户，分享用户ID不包含`-`字符。|
|editor|char(36)|编辑器。|
|type|int|类型：0-所有者；1-可编辑；2-仅浏览。|
|password|char(100)|访问密码。|
|template|int|编辑器模板。|
|etype|char(100)|编辑器类型。|
|state|int|编辑器状态。|
|modify|datetime|编辑器修改时间。|
|create|datetime|创建时间。|

[检索](doc/role/query.md)

[分享](doc/role/share.md)

[设置访问密码](doc/role/password.md)

[删除](doc/role/delete.md)

## 用户媒体

属性

|属性|类型|说明|
|---|---|---|
|user|char(36)|用户。|
|editor|char(36)|编辑器。|
|type|int|类型：0-背景；1-图片；2-音频；3-视频。|
|url|char(100)|URL地址。|
|name|char(100)|文件名。|
|size|long|文件大小。|
|width|int|图片宽。|
|height|int|图片高。|
|time|datetime|时间。|

[检索](doc/media/query.md)

[上传](doc/media/upload.md)

[修改文件名](doc/media/name.md)

[删除](doc/media/delete.md)

[删除全部](doc/media/deletes.md)

## 元素

属性

|属性|类型|说明|
|---|---|---|
|editor|char(36)|编辑器。|
|parent|char(36)|父元素。|
|sort|int|顺序。|
|text|string|文本，用于全文检索。|
|create|datetime|创建时间。|
|modify|long|修改时间。|

> 支持扩展自定义属性。

[检索](doc/element/query.md)

[查找](doc/element/find.md)

[保存](doc/element/save.md)

[排序](doc/element/sort.md)

[删除](doc/element/delete.md)

[删除全部](doc/element/deletes.md)

[批量操作](doc/element/batch.md)

## 日志

属性

|属性|类型|说明|
|---|---|---|
|editor|char(36)|编辑器。|
|parent|char(36)|父元素。|
|element|char(36)|元素。|
|sort|int|顺序。|
|type|char(100)|类型。|
|x|int|X位置。|
|y|int|Y位置。|
|width|int|宽度。|
|height|int|高度。|
|create|datetime|创建时间。|
|modify|long|修改时间。|
|operation|int|操作：0-新增；1-修改；2-删除。|
|time|datetime|时间。|

## 截图

属性

|属性|类型|说明|
|---|---|---|
|editor|char(36)|编辑器。|
|page|char(100)|页面ID，主页面为空`""`。|
|uri|char(100)|URI地址。|

[检索截图集](doc/screenshot/query.md)

[查找截图](doc/screenshot/find.md)

[截图](doc/screenshot/capture.md)
