package org.lpw.ranch.editor;

import org.lpw.ranch.editor.role.RoleService;
import org.lpw.ranch.user.helper.UserHelper;
import org.lpw.tephra.ctrl.context.Request;
import org.lpw.tephra.ctrl.execute.Execute;
import org.lpw.tephra.ctrl.validate.Validate;
import org.lpw.tephra.ctrl.validate.Validators;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

/**
 * @author lpw
 */
@Controller(EditorModel.NAME + ".ctrl")
@Execute(name = "/editor/", key = EditorModel.NAME, code = "32")
public class EditorCtrl {
    @Inject
    private Request request;
    @Inject
    private EditorService editorService;

    @Execute(name = "query", validates = {
            @Validate(validator = Validators.SIGN)
    })
    public Object query() {
        return editorService.query(request.get("mobile"), request.get("email"), request.get("nick"),
                request.getAsInt("template", -1), request.get("type"), request.get("name"),
                request.get("label"), request.getAsArray("states"), request.get("createStart"),
                request.get("createEnd"), request.get("modifyStart"), request.get("modifyEnd"),
                Order.find(request.get("order"), Order.Newest));
    }

    @Execute(name = "query-user", validates = {
            @Validate(validator = UserHelper.VALIDATOR_SIGN_IN)
    })
    public Object queryUser() {
        return editorService.queryUser(request.getAsInt("template", -1), request.get("type"),
                request.getAsArray("states"));
    }

    @Execute(name = "find", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2),
            @Validate(validator = RoleService.VALIDATOR_VIEWABLE, parameters = {"user", "id"}, failureCode = 41)
    })
    public Object find() {
        return editorService.find(request.get("id"));
    }

    @Execute(name = "save", validates = {
            @Validate(validator = Validators.ID, emptyable = true, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.NOT_EMPTY, parameter = "type", failureCode = 3),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "type", failureCode = 4),
            @Validate(validator = Validators.NOT_EMPTY, parameter = "name", failureCode = 5),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "name", failureCode = 6),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "label", failureCode = 7),
            @Validate(validator = Validators.GREATER_THAN, number = {0}, parameter = "width", failureCode = 8),
            @Validate(validator = Validators.GREATER_THAN, number = {0}, parameter = "height", failureCode = 9),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "image", failureCode = 10),
            @Validate(validator = UserHelper.VALIDATOR_SIGN_IN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, emptyable = true, parameter = "id", failureCode = 2),
            @Validate(validator = EditorService.VALIDATOR_EDITABLE, emptyable = true, parameter = "id", failureCode = 11),
            @Validate(validator = RoleService.VALIDATOR_OWNER, emptyable = true, parameter = "id", failureCode = 41)
    })
    public Object save() {
        return editorService.save(request.setToModel(EditorModel.class));
    }

    @Execute(name = "modify", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "type", failureCode = 4),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "name", failureCode = 6),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "label", failureCode = 7),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "image", failureCode = 10),
            @Validate(validator = UserHelper.VALIDATOR_SIGN_IN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2),
            @Validate(validator = EditorService.VALIDATOR_EDITABLE, parameter = "id", failureCode = 11),
            @Validate(validator = RoleService.VALIDATOR_OWNER, parameter = "id", failureCode = 41)
    })
    public Object modify() {
        return editorService.modify(request.setToModel(EditorModel.class), request.getAsInt("template", -1));
    }

    @Execute(name = "image", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = UserHelper.VALIDATOR_SIGN_IN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2),
            @Validate(validator = EditorService.VALIDATOR_EDITABLE, parameter = "id", failureCode = 11),
            @Validate(validator = RoleService.VALIDATOR_EDITABLE, parameter = "id", failureCode = 41)
    })
    public Object image() {
        return editorService.image(request.get("id"));
    }

    @Execute(name = "pass", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.SIGN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2)
    })
    public Object pass() {
        return editorService.state(request.get("id"), 1);
    }

    @Execute(name = "reject", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.SIGN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2)
    })
    public Object reject() {
        return editorService.state(request.get("id"), 2);
    }

    @Execute(name = "sale", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.SIGN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2)
    })
    public Object sale() {
        return editorService.state(request.get("id"), 3);
    }

    @Execute(name = "nonsale", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.SIGN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2)
    })
    public Object nonsale() {
        return editorService.state(request.get("id"), 4);
    }

    @Execute(name = "pdf", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = UserHelper.VALIDATOR_SIGN_IN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2),
            @Validate(validator = RoleService.VALIDATOR_VIEWABLE, parameter = "id", failureCode = 41)
    })
    public Object pdf() {
        return editorService.pdf(request.get("id"));
    }

    @Execute(name = "copy", validates = {
            @Validate(validator = Validators.ID, parameter = "id", failureCode = 1),
            @Validate(validator = Validators.MAX_LENGTH, number = {100}, parameter = "type", failureCode = 4),
            @Validate(validator = UserHelper.VALIDATOR_SIGN_IN),
            @Validate(validator = EditorService.VALIDATOR_EXISTS, parameter = "id", failureCode = 2)
    })
    public Object copy() {
        return editorService.copy(request.get("id"), request.get("type"));
    }

    @Execute(name = "search", validates = {
            @Validate(validator = Validators.NOT_EMPTY, parameter = "type", failureCode = 3)
    })
    public Object search() {
        return editorService.searchTemplate(request.get("type"), request.getAsArray("labels"),
                request.getAsArray("words"), Order.find(request.get("order"), Order.Hot));
    }

    @Execute(name = "reset-search-index", validates = {
            @Validate(validator = Validators.NOT_EMPTY, parameter = "type", failureCode = 3),
            @Validate(validator = Validators.SIGN)
    })
    public Object resetSearchIndex() {
        return editorService.resetSearchIndex(request.get("type"));
    }
}
