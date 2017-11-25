package top.cuggis.geotools;

import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;

import javax.swing.*;

public class Main {

    public static JMapFrame frame;
    public static MapContent map;

    public static MapContent getMap(){
        return map;
    }
    public static void main(String[] args) throws Exception {
        //选择系统UI
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // Create a map content and add our shapefile to it
        map = new MapContent();
        map.setTitle("geo tools 开发");

        frame = new JMapFrame(map);
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        frame.initComponents();
        frame.setSize(800, 600);

    }
}

