package top.cuggis.geotools.component.actions;

import groovy.lang.GroovyShell;
import org.geotools.swing.MapPane;
import org.geotools.swing.locale.LocaleUtils;
import top.cuggis.geotools.InitKt;

import java.awt.event.ActionEvent;
import java.io.File;

public class RunScriptAction extends ZhMapAction {

    public static final String TOOL_NAME = LocaleUtils.getValue("Common", "RunScript");
    public static final String TOOL_TIP = LocaleUtils.getValue("Common", "RunScriptTooltip");
    public static final String ICON_IMAGE = "/top/cuggis/geotools/icons/runscript.png";

    public RunScriptAction(MapPane mapPane) {
        this(mapPane, false);
    }

    public RunScriptAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? TOOL_NAME : null;
        super.init(mapPane, toolName, TOOL_TIP, ICON_IMAGE);
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(null);
        GroovyShell groovyShell= InitKt.getGroovyShell();
        try {
            groovyShell.evaluate(new File("script.groovy"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}