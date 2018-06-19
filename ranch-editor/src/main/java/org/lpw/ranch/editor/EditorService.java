package org.lpw.ranch.editor;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author lpw
 */
public interface EditorService {
    /**
     * 编辑器信息是否存在验证器Bean名称。
     */
    String VALIDATOR_EXISTS = EditorModel.NAME + ".validator.exists";
    /**
     * 编辑器状态是否可编辑验证器Bean名称。
     */
    String VALIDATOR_EDITABLE = EditorModel.NAME + ".validator.editable";

    /**
     * 检索编辑器信息集。
     *
     * @param mobile      用户手机号。
     * @param email       用户Email。
     * @param nick        用户昵称。
     * @param template    模板。
     * @param type        类型。
     * @param name        名称。
     * @param keyword     关键词。
     * @param state       状态。
     * @param createStart 创建开始日期，格式：yyyy-MM-dd。
     * @param createEnd   创建结束日期，格式：yyyy-MM-dd。
     * @param modifyStart 编辑开始日期，格式：yyyy-MM-dd。
     * @param modifyEnd   编辑结束日期，格式：yyyy-MM-dd。
     * @return 编辑器信息集。
     */
    JSONObject query(String mobile, String email, String nick, int template, String type, String name, String keyword,
                     int state, String createStart, String createEnd, String modifyStart, String modifyEnd);

    /**
     * 检索当前用户编辑器信息集。
     *
     * @return 编辑器信息集。
     */
    JSONObject queryUser();

    /**
     * 查找编辑器信息。
     *
     * @param id ID值。
     * @return 编辑器信息，不存在则返回null。
     */
    EditorModel findById(String id);

    /**
     * 查找编辑器信息。
     *
     * @param id ID值。
     * @return 编辑器信息。
     */
    JSONObject find(String id);

    /**
     * 保存编辑器信息。
     *
     * @param editor 编辑器信息。
     * @return 保存后的JSON信息。
     */
    JSONObject save(EditorModel editor);

    /**
     * 生成预览图。
     *
     * @param id 编辑器ID。
     * @return 异步ID。
     */
    String image(String id);

    /**
     * 设置状态。
     *
     * @param id    ID值。
     * @param state 状态。
     * @return 编辑器信息。
     */
    JSONObject state(String id, int state);

    /**
     * 异步导出PDF。
     *
     * @param id 编辑器ID。
     * @return 异步ID。
     */
    String pdf(String id);

    /**
     * 复制。
     *
     * @param id   源ID值。
     * @param type 目标类型。
     * @return 新编辑器信息。
     */
    JSONObject copy(String id, String type);

    /**
     * 修改编辑器修改时间。
     *
     * @param map 数据集，id-modify对。
     */
    void modify(Map<String, Long> map);

    /**
     * 修改编辑器修改时间。
     *
     * @param id ID值。
     */
    void modify(String id);

    /**
     * 删除。
     *
     * @param id ID值。
     */
    void delete(String id);

    /**
     * 搜索模板信息集。
     *
     * @param type  类型。
     * @param words 关键词集。
     * @return 编辑器信息集。
     */
    JSONObject searchTemplate(String type, String[] words);

    /**
     * 重建搜索索引。
     *
     * @param type 类型。
     * @return 异步ID。
     */
    String resetSearchIndex(String type);
}
