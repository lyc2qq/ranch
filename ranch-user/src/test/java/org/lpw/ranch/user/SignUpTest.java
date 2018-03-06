package org.lpw.ranch.user;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.lpw.tephra.ctrl.validate.Validators;
import org.lpw.tephra.test.GeneratorAspect;
import org.lpw.tephra.util.TimeUnit;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lpw
 */
public class SignUpTest extends TestSupport {
    @Inject
    private GeneratorAspect generatorAspect;

    @Test
    public void signUp() {
        mockHelper.reset();
        mockHelper.mock("/user/sign-up");
        JSONObject object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1501, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "empty", message.get(UserModel.NAME + ".uid")), object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", generator.random(101));
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1502, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "over-max-length", message.get(UserModel.NAME + ".uid"), 100), object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", "sign up uid 1");
        mockHelper.getRequest().addParameter("type", "1");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1503, object.getIntValue("code"));
        Assert.assertEquals(message.get(UserModel.NAME + ".password.empty"), object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", "sign up uid 1");
        mockHelper.getRequest().addParameter("type", "-1");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1527, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "not-between", message.get(UserModel.NAME + ".type"), 0, 2), object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", "sign up uid 1");
        mockHelper.getRequest().addParameter("type", "3");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1527, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "not-between", message.get(UserModel.NAME + ".type"), 0, 2), object.getString("message"));

        List<String> list = new ArrayList<>();
        list.add("mock session id 1");
        list.add("code 0");
        list.add("mock session id 2");
        list.add("mock session id 3");
        list.add("code 0");
        list.add("code 1");
        list.add("mock session id 4");
        list.add("code 2");
        generatorAspect.randomString(list);

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", "sign up uid 1");
        mockHelper.getRequest().addParameter("password", "password 1");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        equalsSignUp(object.getJSONObject("data"), "sign up uid 1", 0, null, "code 0");

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", "sign up uid 1");
        mockHelper.getRequest().addParameter("password", "password 1");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1504, object.getIntValue("code"));
        Assert.assertEquals(message.get("ranch.user.auth.uid.exists"), object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("uid", "sign up uid 2");
        mockHelper.getRequest().addParameter("password", "password 2");
        mockHelper.getRequest().addParameter("type", "1");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        equalsSignUp(object.getJSONObject("data"), "sign up uid 2", 1, "password 2", "code 1");

        UserModel user = new UserModel();
        user.setRegister(new Timestamp(System.currentTimeMillis() - TimeUnit.Hour.getTime()));
        user.setCode("code 3");
        liteOrm.save(user);

        mockHelper.reset();
        session.set(UserModel.NAME + ".service.session", user);
        mockHelper.getRequest().addParameter("uid", "sign up uid 3");
        mockHelper.getRequest().addParameter("password", "password 3");
        mockHelper.getRequest().addParameter("type", "1");
        mockHelper.mock("/user/sign-up");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        equalsSignUp(object.getJSONObject("data"), "sign up uid 3", 1, "password 3",
                System.currentTimeMillis() - TimeUnit.Hour.getTime(), "code 3");

        generatorAspect.randomString(null);
    }
}
