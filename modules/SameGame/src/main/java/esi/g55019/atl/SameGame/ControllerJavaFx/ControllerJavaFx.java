package esi.g55019.atl.SameGame.ControllerJavaFx;

import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import esi.g55019.atl.SameGame.ViewJavaFx.ViewJavaFx;

public class ControllerJavaFx {
    private ModelJavaFx model;
    private ViewJavaFx view;

    public ControllerJavaFx(ModelJavaFx model) {
        this.model = model;
        this.view = new ViewJavaFx(this, model);
    }

}
