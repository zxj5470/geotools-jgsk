package top.cuggis.geotools.ui.component.tools;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.locale.LocaleUtils;
import org.geotools.swing.tool.AbstractZoomTool;
import top.cuggis.geotools.constants.ZhMouseConstants;
import top.cuggis.geotools.extensions.TransformKt;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class ZhZoomInTool extends AbstractZoomTool {
    private static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "ZoomIn");
    public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "ZoomInTooltip");
    public static final String CURSOR_IMAGE = "/org/geotools/swing/icons/mActionZoomIn.png";
    private static final Point CURSOR_HOTSPOT = ZhMouseConstants.CURSOR_HOTSPOT;
    public static final String ICON_IMAGE = "/org/geotools/swing/icons/mActionZoomIn.png";
    private Cursor cursor;
    private final Point startPosDevice;
    private final Point2D startPosWorld;
    private boolean dragged;

    public ZhZoomInTool() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/org/geotools/swing/icons/mActionZoomIn.png"));
        ImageIcon image=TransformKt.cursorAdaptImage(imgIcon,32,32);
        this.cursor = tk.createCustomCursor(image.getImage(), CURSOR_HOTSPOT, TOOL_NAME);
        this.startPosDevice = new Point();
        this.startPosWorld = new DirectPosition2D();
        this.dragged = false;
    }

    public void onMouseClicked(MapMouseEvent e) {
        Rectangle paneArea = ((JComponent)this.getMapPane()).getVisibleRect();
        DirectPosition2D mapPos = e.getWorldPos();
        double scale = this.getMapPane().getWorldToScreenTransform().getScaleX();
        double newScale = scale * this.zoom;
        DirectPosition2D corner = new DirectPosition2D(mapPos.getX() - 0.5D * paneArea.getWidth() / newScale, mapPos.getY() + 0.5D * paneArea.getHeight() / newScale);
        Envelope2D newMapArea = new Envelope2D();
        newMapArea.setFrameFromCenter(mapPos, corner);
        this.getMapPane().setDisplayArea(newMapArea);
    }

    public void onMousePressed(MapMouseEvent ev) {
        this.startPosDevice.setLocation(ev.getPoint());
        this.startPosWorld.setLocation(ev.getWorldPos());
    }

    public void onMouseDragged(MapMouseEvent ev) {
        this.dragged = true;
    }

    public void onMouseReleased(MapMouseEvent ev) {
        if (this.dragged && !ev.getPoint().equals(this.startPosDevice)) {
            Envelope2D env = new Envelope2D();
            env.setFrameFromDiagonal(this.startPosWorld, ev.getWorldPos());
            this.dragged = false;
            this.getMapPane().setDisplayArea(env);
        }

    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public boolean drawDragBox() {
        return true;
    }
}
