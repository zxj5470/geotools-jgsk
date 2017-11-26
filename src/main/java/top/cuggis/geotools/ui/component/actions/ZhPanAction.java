package top.cuggis.geotools.ui.component.actions;

import org.geotools.swing.MapPane;
import org.geotools.swing.tool.PanTool;
import top.cuggis.geotools.ui.component.tools.ZhPanTool;

import java.awt.event.ActionEvent;

public class ZhPanAction extends ZhMapAction {
    public ZhPanAction(MapPane mapPane) {
        this(mapPane, false);
    }

    public ZhPanAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? PanTool.TOOL_NAME : null;
        super.init(mapPane, toolName, PanTool.TOOL_TIP, "/org/geotools/swing/icons/mActionPan.png");
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(new ZhPanTool());
    }
}
