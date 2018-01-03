package top.cuggis.geotools;


import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.map.MapContent;
import top.cuggis.geotools.ui.ZhJMapFrame;
import top.cuggis.geotools.ui.ZhMapLoader;
import top.cuggis.geotools.utils.ZhMapMouseWheelEvent;

import java.awt.*;


public class Main {

    //    public int a = 0;
    public static ZhJMapFrame frame;
    public static MapContent map;

    public static void main(String[] args) throws Exception {
        map = new MapContent();
        frame = new ZhJMapFrame(map);

        ZhMapLoader.initMap();
        InitKt.adaptImageIcons();

        //鼠标滚动事件
        frame.getMapPane().addMouseListener((ZhMapMouseWheelEvent)me->{
            System.out.println(me.getWheelAmount());
            Rectangle paneArea = frame.getMapPane().getVisibleRect();
            DirectPosition2D mapPos = me.getWorldPos();
            double scale = frame.getMapPane().getWorldToScreenTransform().getScaleX();
            Envelope2D newMapArea;
            double leftTopX;
            double rightBottomY;
            switch (me.getWheelAmount()) {
                case 1://缩小
                    double newScale;
                    newScale = scale / 1.1;
                    leftTopX=mapPos.getX() - 0.5 * paneArea.getWidth() / newScale;
                    rightBottomY=mapPos.getY() + 0.5 * paneArea.getHeight() / newScale;
                    DirectPosition2D corner = new DirectPosition2D(leftTopX, rightBottomY);
                    newMapArea = new Envelope2D();
                    newMapArea.setFrameFromCenter(mapPos, corner);
                    frame.getMapPane().setDisplayArea(newMapArea);
                    break;
                case -1://放大
                    newScale = scale * 1.1;
                    leftTopX=mapPos.getX() - 0.5 * paneArea.getWidth() / newScale;
                    rightBottomY=mapPos.getY() + 0.5 * paneArea.getHeight() / newScale;
                    corner = new DirectPosition2D(leftTopX, rightBottomY);
                    newMapArea = new Envelope2D();
                    newMapArea.setFrameFromCenter(mapPos, corner);
                    frame.getMapPane().setDisplayArea(newMapArea);
                    break;
                default:
                    break;
            }
        });

        frame.setVisible(true);
    }
}

