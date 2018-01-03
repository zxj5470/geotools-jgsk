package top.cuggis.geotools.utils;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.event.MapMouseListener;
import top.cuggis.geotools.Main;

import java.io.File;

public interface ZhMapMouseWheelEvent extends MapMouseListener {
    default void onMouseClicked(MapMouseEvent mapMouseEvent) {
        System.out.println(mapMouseEvent.getWorldPos().x + "," + mapMouseEvent.getWorldPos().y);
        if (mapMouseEvent.getButton() == 2) {
            try {
                FileDataStore store = FileDataStoreFinder.getDataStore(new File("G:\\x555\\CountryResourceTutorial\\实习一\\2\\counties.shp"));
                SimpleFeatureSource featureSource = store.getFeatureSource();
                Style style = SLD.createSimpleStyle(featureSource.getSchema());
                Layer layer = new FeatureLayer(featureSource, style);
                Main.map.addLayer(layer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    default void onMouseDragged(MapMouseEvent mapMouseEvent) {
    }

    default void onMouseEntered(MapMouseEvent mapMouseEvent) {
    }

    default void onMouseExited(MapMouseEvent mapMouseEvent) {
    }

    default void onMouseMoved(MapMouseEvent mapMouseEvent) {
    }

    default void onMousePressed(MapMouseEvent mapMouseEvent) {
    }

    default void onMouseReleased(MapMouseEvent mapMouseEvent) {
    }

    @Override
    void onMouseWheelMoved(MapMouseEvent mapMouseEvent);
}