package top.cuggis.geotools.component.actions;

import org.geotools.swing.MapPane;
import org.geotools.swing.action.MapAction;
import top.cuggis.geotools.extensions.TransformKt;

import javax.swing.*;

public abstract class ZhMapAction extends MapAction {
    private static final long serialVersionUID = 2400755645451641127L;
    private MapPane mapPane;

    protected void init(MapPane mapPane, String toolName, String toolTip, String iconImage) {
        this.mapPane = mapPane;
        if (toolName != null) {
            this.putValue("Name", toolName);
        }

        this.putValue("ShortDescription", toolTip);
        if (iconImage != null) {
            ImageIcon tempIcon=new ImageIcon(ZhMapAction.class.getResource(iconImage));
            this.putValue("SmallIcon",
                    TransformKt.adaptImage(tempIcon,128,128));
        }
    }

    public MapPane getMapPane() {
        return this.mapPane;
    }
}
