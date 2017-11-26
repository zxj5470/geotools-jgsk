package top.cuggis.geotools;


import org.geotools.map.MapContent;

import top.cuggis.geotools.ui.ZhJMapFrame;

import javax.swing.*;

public class Main {

    public static ZhJMapFrame frame;
    public static MapContent map;

    public static MapContent getMap(){
        return map;
    }
    public static void main(String[] args) throws Exception {
        //选择系统UI
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        map = new MapContent();
        map.setTitle("geo tools dev");
        frame = new ZhJMapFrame(map);
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        frame.initComponents();
        frame.setSize(800, 600);
        InitKt.adaptImageIcons();
        frame.setVisible(true);
    }
}

