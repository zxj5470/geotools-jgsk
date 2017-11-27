package top.cuggis.geotools;


import org.geotools.map.MapContent;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import top.cuggis.geotools.ui.ZhJMapFrame;
import top.cuggis.geotools.utils.ZhMapLoader;

import javax.swing.*;


public class Main {

    public static ZhJMapFrame frame;
    public static MapContent map;

    public static void main(String[] args) throws Exception {
        //选择系统UI
        map = new MapContent();
        frame = new ZhJMapFrame(map);
        ZhMapLoader.initMap();
        InitKt.adaptImageIcons();
        final int MOUSE_UP=-1;
        final int MOUSE_DOWN=1;
        frame.getMapPane().addMouseWheelListener(e->{
            switch (e.getWheelRotation()){
                case MOUSE_UP://放大
                    break;
                case MOUSE_DOWN://缩小

                    break;
                default:
                    break;
            }
            System.out.println("x="+e.getX()+" ,y="+e.getY());
            System.out.println(map.getViewport().getScreenArea().x);

        });

        frame.setVisible(true);
    }
}

