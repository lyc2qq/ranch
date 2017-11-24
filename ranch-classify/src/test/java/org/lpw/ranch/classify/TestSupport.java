package org.lpw.ranch.classify;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.lpw.ranch.recycle.Recycle;
import org.lpw.tephra.cache.Cache;
import org.lpw.tephra.crypto.Sign;
import org.lpw.tephra.dao.orm.lite.LiteOrm;
import org.lpw.tephra.test.MockHelper;
import org.lpw.tephra.test.PageTester;
import org.lpw.tephra.test.SchedulerAspect;
import org.lpw.tephra.test.TephraTestSupport;
import org.lpw.tephra.util.Converter;
import org.lpw.tephra.util.Generator;
import org.lpw.tephra.util.Message;
import org.lpw.tephra.util.Numeric;

import javax.inject.Inject;

/**
 * @author lpw
 */
public class TestSupport extends TephraTestSupport {
    @Inject
    Message message;
    @Inject
    Generator generator;
    @Inject
    Numeric numeric;
    @Inject
    Cache cache;
    @Inject
    LiteOrm liteOrm;
    @Inject
    Sign sign;
    @Inject
    PageTester pageTester;
    @Inject
    MockHelper mockHelper;
    @Inject
    SchedulerAspect schedulerAspect;
    @Inject
    ClassifyService classifyService;

    void equals(JSONObject object, int i) {
        for (String name : new String[]{"code", "key", "value", "name"})
            Assert.assertEquals(name + " " + i, object.get(name));
    }

    void equals(JSONObject object, String code, String key, String value, String name) {
        Assert.assertEquals(code, object.getString("code"));
        if (key == null)
            Assert.assertFalse(object.containsKey("key"));
        else
            Assert.assertEquals(key, object.getString("key"));
        Assert.assertEquals(value, object.getString("value"));
        Assert.assertEquals(name, object.getString("name"));
    }

    ClassifyModel create(int code, boolean recycle) {
        return create(code, "{\"json\":" + code + "}", recycle);
    }

    ClassifyModel create(int code, String json, boolean recycle) {
        return create("code " + code, code, json, recycle);
    }

    ClassifyModel create(String code, int i, String json, boolean recycle) {
        ClassifyModel classify = new ClassifyModel();
        classify.setCode(code);
        classify.setKey("key " + i);
        classify.setValue("value " + i);
        classify.setName("name " + i);
        classify.setJson(json);
        classify.setRecycle((recycle ? Recycle.Yes : Recycle.No).getValue());
        liteOrm.save(classify);

        return classify;
    }
}
