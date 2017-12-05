package top.cuggis.geotools;


import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.map.MapContent;
import org.geotools.swing.MapPane;
import top.cuggis.geotools.ui.ZhJMapFrame;
import top.cuggis.geotools.ui.ZhMapLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;


public class Main {

    public int a = 0;
    public static ZhJMapFrame frame;
    public static MapContent map;

    public static void main(String[] args) throws Exception {
        //选择系统UI
        map = new MapContent();
        frame = new ZhJMapFrame(map);

        ZhMapLoader.initMap();
        InitKt.adaptImageIcons();
        final int MOUSE_UP = -1;
        final int MOUSE_DOWN = 1;
        frame.getMapPane().addMouseWheelListener(e -> {
            switch (e.getWheelRotation()) {
                case MOUSE_UP://放大
                    Rectangle paneArea = frame.getMapPane().getVisibleRect();
                    calculateWorldPos(frame.getMapPane(), e);
                    DirectPosition2D mapPos = calculateWorldPos(frame.getMapPane(), e);
                    double scale = frame.getMapPane().getWorldToScreenTransform().getScaleX();
                    double newScale = scale * 1.1;
                    Envelope2D newMapArea = new Envelope2D();
//                    newMapArea.setFrameFromCenter(mapPos, corner);
                    double x = mapPos.x - 0.5D * paneArea.getWidth() / newScale;
                    double y = mapPos.y + 0.5D * paneArea.getHeight() / newScale;
                    newMapArea.setFrame(
                            x,
                            y,
                            1,
                            1
                    );
                    frame.getMapPane().setDisplayArea(newMapArea);
                    break;
                case MOUSE_DOWN://缩小
                    break;
                default:
                    break;
            }
        });

        frame.setVisible(true);
    }

    private static DirectPosition2D calculateWorldPos(MapPane pane, MouseEvent event) {
        AffineTransform tr = pane.getScreenToWorldTransform();
        DirectPosition2D pos = new DirectPosition2D((double) event.getX(), (double) event.getY());
        tr.transform(pos, pos);
        pos.setCoordinateReferenceSystem(pane.getMapContent().getCoordinateReferenceSystem());
        return new DirectPosition2D(pos.getCoordinateReferenceSystem(), pos.x, pos.y);
    }
}

