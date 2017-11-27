package top.cuggis.geotools;


import org.geotools.map.MapContent;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import top.cuggis.geotools.ui.ZhJMapFrame;
import top.cuggis.geotools.utils.MapLoader;

import javax.swing.*;


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
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        frame.initComponents();
        frame.setSize(800, 600);
        InitKt.adaptImageIcons();
        frame.setVisible(true);

        MapLoader.openAndLoadMap(map,"shp");

    }
}

