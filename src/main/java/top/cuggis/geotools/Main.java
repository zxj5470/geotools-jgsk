package top.cuggis.geotools;


import org.geotools.map.MapContent;
import org.geotools.swing.locale.LocaleUtils;

import top.cuggis.geotools.ui.ZhJMapFrame;

import javax.swing.*;
import java.util.Locale;

public class Main {

    public static ZhJMapFrame frame;
    public static MapContent map;

    public static MapContent getMap(){
        return map;
    }
    public static void main(String[] args) throws Exception {
        //选择系统UI
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        LocaleUtils.isSupportedLocale(Locale.SIMPLIFIED_CHINESE);
        LocaleUtils.setLocale(Locale.SIMPLIFIED_CHINESE);
        // Create a map content and add our shapefile to it
        map = new MapContent();
        map.setTitle("geo tools 开发");
        frame = new ZhJMapFrame(map);
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        frame.initComponents();
        frame.setSize(800, 600);

    }
}

