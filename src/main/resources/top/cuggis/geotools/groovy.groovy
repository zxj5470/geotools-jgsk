package top.cuggis.geotools

import org.geotools.data.shapefile.ShapefileDataStore

class groovy {
    public static void main(String[] args) {
        def layer=Main.map.layers()[0]
        println layer.metaPropertyValues.forEach{
            println it.name+'\t|\t'+it.value
        }
        def a=layer.getProperties()['featureSource'] as ShapefileDataStore
        def reader=a.featureReader
        while(reader.hasNext()){
            println reader.next()
        }
    }
}
