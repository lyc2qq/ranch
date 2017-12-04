package org.lpw.ranch.user;

import org.lpw.tephra.ctrl.http.upload.UploadListener;
import org.lpw.tephra.util.Image;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

/**
 * @author lpw
 */
@Controller(UserModel.NAME + ".upload-listener.portrait")
public class PortraitUploadListenerImpl implements UploadListener {
    @Inject
    private Image image;
    @Inject
    private UserService userService;

    @Override
    public String getKey() {
        return UserModel.NAME + ".portrait";
    }

    @Override
    public boolean isUploadEnable(String key, String contentType, String name) {
        return image.is(contentType, name) && !userService.sign().isEmpty();
    }

    @Override
    public String upload(String key, String name, String size, String uri) {
        userService.portrait(uri);

        return uri;
    }
}
