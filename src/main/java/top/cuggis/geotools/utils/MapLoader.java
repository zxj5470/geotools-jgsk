package top.cuggis.geotools.utils;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.data.JFileDataStoreChooser;
import top.cuggis.geotools.Main;

import java.io.File;
import java.io.IOException;

public class MapLoader {

    private static Layer openFile(String fileType) {
        Layer layer = null;
        File file = JFileDataStoreChooser.showOpenFile(fileType, null);
        if (file == null) {
            return null;
        }
        try {
            FileDataStore store;
            store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            Style style = SLD.createSimpleStyle(featureSource.getSchema());
            layer = new FeatureLayer(featureSource, style);
            System.out.println(layer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return layer;
    }

    public static void openAndLoadMap(MapContent map, String fileType) {
        Layer l = openFile(fileType);
        if (l != null) {
            map.addLayer(l);
        }
    }
}
