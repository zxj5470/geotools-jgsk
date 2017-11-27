package top.cuggis.geotools;


import org.geotools.map.MapContent;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import top.cuggis.geotools.ui.ZhJMapFrame;
import top.cuggis.geotools.utils.MapLoader;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class Main {

    public static ZhJMapFrame frame;
    public static MapContent map;

    public static void main(String[] args) throws Exception {
        //选择系统UI
        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
        UIManager.put("RootPane.setupButtonVisible", false);
        BeautyEyeLNFHelper.launchBeautyEyeLNF();
        map = new MapContent();
        map.setTitle("GeoTools 地图");
        frame = new ZhJMapFrame(map);
        frame.enableLayerTable(true);
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        frame.initComponents();
        frame.setSize(800, 600);
//        debug jar Icon
//        release jar Icon
        InitKt.adaptImageIcons();

        final int MOUSE_UP=-1;
        final int MOUSE_DOWN=1;
        frame.getMapPane().addMouseWheelListener(e->{
            switch (e.getWheelRotation()){
                case MOUSE_UP:

                    break;
                case MOUSE_DOWN:

                    break;
                default:
                    break;
            }
            System.out.println("x="+e.getX()+" ,y="+e.getY());
        });

        frame.setVisible(true);
    }
}

