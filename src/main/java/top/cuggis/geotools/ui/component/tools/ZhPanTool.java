package top.cuggis.geotools.ui.component.tools;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.awt.*;
import javax.swing.ImageIcon;
import org.geotools.swing.JMapPane;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.locale.LocaleUtils;
import org.geotools.swing.tool.CursorTool;
import top.cuggis.geotools.constants.ZhMouseConstants;
import top.cuggis.geotools.extensions.TransformKt;

public class ZhPanTool extends CursorTool {
    private static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "Pan");
    public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "PanTooltip");
    public static final String CURSOR_IMAGE = "/org/geotools/swing/icons/mActionPan.png";
    private static final Point CURSOR_HOTSPOT = ZhMouseConstants.CURSOR_HOTSPOT;
    public static final String ICON_IMAGE = "/org/geotools/swing/icons/mActionPan.png";
    private Cursor cursor;
    private Point panePos;
    private boolean panning;

    public ZhPanTool() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/org/geotools/swing/icons/mActionPan.png"));
        Image img=TransformKt.adaptImage(imgIcon.getImage(),32,32);
        this.cursor = tk.createCustomCursor(img, CURSOR_HOTSPOT, TOOL_NAME);
        this.panning = false;
    }

    public void onMousePressed(MapMouseEvent ev) {
        this.panePos = ev.getPoint();
        this.panning = true;
    }

    public void onMouseDragged(MapMouseEvent ev) {
        if (this.panning) {
            Point pos = ev.getPoint();
            if (!pos.equals(this.panePos)) {
                (this.getMapPane()).moveImage(pos.x - this.panePos.x, pos.y - this.panePos.y);
                this.panePos = pos;
            }
        }

    }

    public void onMouseReleased(MapMouseEvent ev) {
        this.panning = false;
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public boolean drawDragBox() {
        return false;
    }
}

