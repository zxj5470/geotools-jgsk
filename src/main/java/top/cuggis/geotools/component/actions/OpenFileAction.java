package top.cuggis.geotools.component.actions;

import org.geotools.swing.MapPane;
import org.geotools.swing.locale.LocaleUtils;
import top.cuggis.geotools.utils.MapLoader;

import java.awt.event.ActionEvent;

public class OpenFileAction extends ZhMapAction {

    public static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "Open");
    public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "OpenTooltip");
    public static final String ICON_IMAGE = "/top/cuggis/geotools/icons/fileopen.png";

    public OpenFileAction(MapPane mapPane) {
        this(mapPane, false);
    }

    public OpenFileAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? TOOL_NAME : null;
        super.init(mapPane, toolName, TOOL_TIP, ICON_IMAGE);
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(null);
        MapLoader.openAndLoadMap("shp");
    }
}