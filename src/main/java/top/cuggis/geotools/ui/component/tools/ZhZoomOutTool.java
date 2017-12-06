package top.cuggis.geotools.ui.component.tools;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.locale.LocaleUtils;
import org.geotools.swing.tool.AbstractZoomTool;
import top.cuggis.geotools.extensions.TransformKt;

import javax.swing.*;
import java.awt.*;

public class ZhZoomOutTool  extends AbstractZoomTool {
    public static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "ZoomOut");
    public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "ZoomOutTooltip");
    public static final String CURSOR_IMAGE = "/org/geotools/swing/icons/mActionZoomOut.png";
    public static final Point CURSOR_HOTSPOT = new Point(14, 9);
    public static final String ICON_IMAGE = "/org/geotools/swing/icons/mActionZoomOut.png";
    private Cursor cursor;

    public ZhZoomOutTool() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/org/geotools/swing/icons/mActionZoomOut.png"));
        ImageIcon image= TransformKt.cursorAdaptImage(imgIcon,32,32);
        this.cursor = tk.createCustomCursor(image.getImage(), CURSOR_HOTSPOT, TOOL_NAME);
    }

    public void onMouseClicked(MapMouseEvent ev) {
        Rectangle paneArea = ((JComponent)this.getMapPane()).getVisibleRect();
        DirectPosition2D mapPos = ev.getWorldPos();
        double scale = this.getMapPane().getWorldToScreenTransform().getScaleX();
        double newScale = scale / this.zoom;
        DirectPosition2D corner = new DirectPosition2D(mapPos.getX() - 0.5D * paneArea.getWidth() / newScale, mapPos.getY() + 0.5D * paneArea.getHeight() / newScale);
        Envelope2D newMapArea = new Envelope2D();
        newMapArea.setFrameFromCenter(mapPos, corner);
        this.getMapPane().setDisplayArea(newMapArea);
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public boolean drawDragBox() {
        return false;
    }
}
