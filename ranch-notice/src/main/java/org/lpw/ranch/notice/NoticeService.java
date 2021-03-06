package org.lpw.ranch.notice;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lpw
 */
public interface NoticeService {
    /**
     * 检索通知集。
     *
     * @param type 类型，为空则表示全部。
     * @param read 已读：-1-全部；0-否；1-是。
     * @return 通知集。
     */
    JSONObject query(String type, int read);

    /**
     * 发送通知。
     *
     * @param user    用户。
     * @param type    类型。
     * @param subject 标题。
     * @param content 内容。
     */
    void send(String user, String type, String subject, String content);

    /**
     * 发送通知。
     *
     * @param users   用户集。
     * @param type    类型。
     * @param subject 标题。
     * @param content 内容。
     */
    void send(String[] users, String type, String subject, String content);

    /**
     * 标记已读。
     *
     * @param id ID值。
     */
    void read(String id);

    /**
     * 全部标记已读。
     *
     * @param type 类型，为空则表示全部。
     */
    void reads(String type);
}
