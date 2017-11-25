package top.cuggis.geotools.component.actions;

import org.geotools.swing.MapPane;
import org.geotools.swing.tool.ZoomInTool;

import java.awt.event.ActionEvent;

public class ZhZoomInAction extends ZhMapAction {
    public ZhZoomInAction(MapPane mapPane) {
        this(mapPane, false);
    }

    public ZhZoomInAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? ZoomInTool.TOOL_NAME : null;
        super.init(mapPane, toolName, ZoomInTool.TOOL_TIP, "/org/geotools/swing/icons/mActionZoomIn.png");
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(new ZoomInTool());
    }
}
