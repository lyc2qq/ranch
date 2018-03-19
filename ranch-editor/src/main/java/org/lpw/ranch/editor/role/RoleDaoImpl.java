package org.lpw.ranch.editor.role;

import org.lpw.tephra.dao.orm.lite.LiteOrm;
import org.lpw.tephra.dao.orm.lite.LiteQuery;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * @author lpw
 */
@Repository(RoleModel.NAME + ".dao")
class RoleDaoImpl implements RoleDao {
    @Inject
    private LiteOrm liteOrm;

    @Override
    public RoleModel find(String user, String editor) {
        return liteOrm.findOne(new LiteQuery(RoleModel.class).where("c_editor=? and c_user=?"), new Object[]{editor, user});
    }

    @Override
    public void save(RoleModel role) {
        liteOrm.save(role);
    }
}
