package top.cuggis.geotools.ui.component.actions;

import org.geotools.swing.MapPane;
import org.geotools.swing.tool.ZoomInTool;
import top.cuggis.geotools.ui.component.tools.ZhZoomOutTool;

import java.awt.event.ActionEvent;

public class ZhZoomOutAction extends ZhMapAction {
    public ZhZoomOutAction(MapPane mapPane) {
        this(mapPane, false);
    }

    public ZhZoomOutAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? ZoomInTool.TOOL_NAME : null;
        super.init(mapPane, toolName, ZoomInTool.TOOL_TIP, "/org/geotools/swing/icons/mActionZoomOut.png");
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(new ZhZoomOutTool());
    }
}
