package top.cuggis.geotools

import top.cuggis.geotools.component.OpenFileAction
import top.cuggis.geotools.extensions.adaptImage
import top.cuggis.geotools.utils.MapLoader
import javax.swing.JButton

/*
 * @date: 2017/11/25
 */
fun main(args: Array<String>) {
    Main.main(null)
    val f = Main.frame

//    val buttons = f.toolBar.components.filter { it is JButton }.map { it as JButton }

    val btn = JButton(OpenFileAction(f.mapPane))
    btn.name = "ToolbarPointerButton"
    f.toolBar.add(btn)
    val buttons = f.toolBar.components.filter { it is JButton }.map { it as JButton }
    f.toolBar.components.forEach { println(it) }
    buttons.forEach { it.adaptImage() }

    f.isVisible = true
//    Thread {
//        while (true) {
//            println(f.mapPane.cursor is Cursor)
//            Thread.sleep(100)
//        }
//    }.start()
//    f.toolBar.add()

//    MapLoader.openAndLoadMap("shp")


}