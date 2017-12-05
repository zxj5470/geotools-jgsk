package top.cuggis.geotools.ui.component.actions;

import org.geotools.swing.MapPane;
import org.geotools.swing.locale.LocaleUtils;
import top.cuggis.geotools.Main;
import top.cuggis.geotools.ui.ZhMapLoader;

import java.awt.event.ActionEvent;

public class ZhOpenFileAction extends ZhMapAction {

    private static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "Open");
    private static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "OpenTooltip");
    private static final String ICON_IMAGE = "/top/cuggis/geotools/icons/fileopen.png";

    public ZhOpenFileAction(MapPane mapPane) {
        this(mapPane, false);
    }

    private ZhOpenFileAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? TOOL_NAME : null;
        super.init(mapPane, toolName, TOOL_TIP, ICON_IMAGE);
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(null);
        ZhMapLoader.openAndLoadMap(Main.map,"shp");
    }
}