package org.lpw.ranch.gps;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.lpw.tephra.ctrl.validate.Validators;
import org.lpw.tephra.test.MockHelper;
import org.lpw.tephra.test.TephraTestSupport;
import org.lpw.tephra.util.HttpImpl;
import org.lpw.tephra.util.Message;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author lpw
 */
public class AddressTest extends TephraTestSupport {
    @Inject
    private Message message;
    @Inject
    private MockHelper mockHelper;
    @Inject
    private GpsService gpsService;

    @Test
    public void address() throws Exception {
        mockHelper.reset();
        mockHelper.mock("/gps/address");
        JSONObject object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1101, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "illegal-latitude", message.get(GpsModel.NAME + ".lat")),
                object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "91");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1101, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "illegal-latitude", message.get(GpsModel.NAME + ".lat")),
                object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "-91");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1101, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "illegal-latitude", message.get(GpsModel.NAME + ".lat")),
                object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1102, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "illegal-longitude", message.get(GpsModel.NAME + ".lng")),
                object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.getRequest().addParameter("lng", "181");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1102, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "illegal-longitude", message.get(GpsModel.NAME + ".lng")),
                object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.getRequest().addParameter("lng", "-39.397140");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(1102, object.getIntValue("code"));
        Assert.assertEquals(message.get(Validators.PREFIX + "illegal-longitude", message.get(GpsModel.NAME + ".lng")),
                object.getString("message"));

        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.getRequest().addParameter("lng", "116.307490");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        JSONObject data = object.getJSONObject("data");
        Assert.assertTrue(data.getString("address").contains("北京市海淀区"));
        JSONObject component = data.getJSONObject("component");
        Assert.assertEquals("中国", component.getString("nation"));
        Assert.assertEquals("北京市", component.getString("province"));
        Assert.assertEquals("北京市", component.getString("city"));
        Assert.assertEquals("海淀区", component.getString("district"));
        Assert.assertTrue(component.containsKey("street"));
        Assert.assertTrue(component.containsKey("street_number"));

        Field field = GpsServiceImpl.class.getDeclaredField("qqlbsKey");
        field.setAccessible(true);
        field.set(gpsService, "");
        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.getRequest().addParameter("lng", "116.307490");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        Assert.assertTrue(object.getJSONObject("data").isEmpty());

        field.set(gpsService, "qqlbs-key");
        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.getRequest().addParameter("lng", "116.307490");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        Assert.assertTrue(object.getJSONObject("data").isEmpty());

        field = GpsServiceImpl.class.getDeclaredField("http");
        field.setAccessible(true);
        field.set(gpsService, new HttpImpl() {
            @Override
            public String get(String url, Map<String, String> headers, String parameters) {
                return null;
            }
        });
        mockHelper.reset();
        mockHelper.getRequest().addParameter("lat", "39.984154");
        mockHelper.getRequest().addParameter("lng", "116.307490");
        mockHelper.mock("/gps/address");
        object = mockHelper.getResponse().asJson();
        Assert.assertEquals(0, object.getIntValue("code"));
        Assert.assertTrue(object.getJSONObject("data").isEmpty());
    }
}
