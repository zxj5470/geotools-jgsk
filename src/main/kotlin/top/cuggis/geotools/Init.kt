package top.cuggis.geotools

/*
 * @date: 2017/11/25
 */

import top.cuggis.geotools.extensions.adaptImage
import javax.swing.JButton
import groovy.lang.GroovyShell
import java.awt.Image
import javax.swing.ImageIcon

val groovyShell = GroovyShell()
var icon: Image?=null

fun adaptImageIcons() {
    val frame = Main.frame
    val buttons = frame.toolBar.components.filter { it is JButton }.map { it as JButton }
    buttons.forEach { it.adaptImage() }

    /**
     * debug version path
     */
    icon=ImageIcon("src/main/resources/top/cuggis/geotools/icons/GIS.png").image

    /**
     * debug version path
     */

    /**
     * release version path
     */
//        frame.iconImage = ImageIcon("top/cuggis/geotools/icons/GIS.png").image
    /**
     * release version path
     */
    frame.iconImage=icon
}