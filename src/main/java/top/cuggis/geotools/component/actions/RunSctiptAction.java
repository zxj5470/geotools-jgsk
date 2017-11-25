package top.cuggis.geotools.component.actions;

import groovy.lang.GroovyShell;
import org.geotools.swing.MapPane;
import org.geotools.swing.locale.LocaleUtils;
import top.cuggis.geotools.KtMainKt;

import java.awt.event.ActionEvent;
import java.io.File;

public class RunSctiptAction extends ZhMapAction {

    public static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "None");
    public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "NoneTooltip");
    public static final String ICON_IMAGE = "/top/cuggis/geotools/icons/runscript.png";

    public RunSctiptAction(MapPane mapPane) {
        this(mapPane, false);
    }

    public RunSctiptAction(MapPane mapPane, boolean showToolName) {
        String toolName = showToolName ? TOOL_NAME : null;
        super.init(mapPane, toolName, TOOL_TIP, ICON_IMAGE);
    }

    public void actionPerformed(ActionEvent ev) {
        this.getMapPane().setCursorTool(null);

        GroovyShell groovyShell=KtMainKt.getGroovyShell();
        try {
            groovyShell.evaluate(new File("src/main/resources/top/cuggis/geotools/groovy.groovy"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}