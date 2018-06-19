package org.lpw.ranch.editor;

import org.lpw.ranch.util.DaoHelper;
import org.lpw.ranch.util.DaoOperation;
import org.lpw.tephra.dao.orm.PageList;
import org.lpw.tephra.dao.orm.lite.LiteOrm;
import org.lpw.tephra.dao.orm.lite.LiteQuery;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lpw
 */
@Repository(EditorModel.NAME + ".dao")
class EditorDaoImpl implements EditorDao {
    @Inject
    private LiteOrm liteOrm;
    @Inject
    private DaoHelper daoHelper;

    @Override
    public PageList<EditorModel> query(Set<String> ids, int template, String type, String name, String keyword, int state,
                                       Timestamp createStart, Timestamp createEnd, Timestamp modifyStart, Timestamp modifyEnd,
                                       int pageSize, int pageNum) {
        StringBuilder where = new StringBuilder();
        List<Object> args = new ArrayList<>();
        daoHelper.in(where, args, "c_id", ids.toArray());
        daoHelper.where(where, args, "c_template", DaoOperation.Equals, template);
        daoHelper.where(where, args, "c_type", DaoOperation.Equals, type);
        daoHelper.where(where, args, "c_state", DaoOperation.Equals, state);
        daoHelper.where(where, args, "c_create", DaoOperation.GreaterEquals, createStart);
        daoHelper.where(where, args, "c_create", DaoOperation.LessEquals, createEnd);
        daoHelper.where(where, args, "c_modify", DaoOperation.GreaterEquals, createStart);
        daoHelper.where(where, args, "c_modify", DaoOperation.LessEquals, createEnd);
        daoHelper.like(null, where, args, "c_name", name);
        daoHelper.like(null, where, args, "c_keyword", keyword);

        return liteOrm.query(new LiteQuery(EditorModel.class).where(where.toString()).order("c_sort,c_modify desc")
                .size(pageSize).page(pageNum), args.toArray());
    }

    @Override
    public EditorModel findById(String id) {
        return liteOrm.findById(EditorModel.class, id);
    }

    @Override
    public void save(EditorModel editor) {
        liteOrm.save(editor);
    }
}
