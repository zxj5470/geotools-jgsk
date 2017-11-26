import top.cuggis.geotools.Main

class groovy {
    public static void main(String[] args) {

        def layer=Main.map.layers()[0]
        println layer.metaPropertyValues.forEach{
            println it.name+'\t|\t'+it.value
        }
        def a=layer.getProperties()['featureSource']
        println a
//        def reader=a.featureReader
//        while(reader.hasNext()){
//            println reader.next()
//        }

    }
}
