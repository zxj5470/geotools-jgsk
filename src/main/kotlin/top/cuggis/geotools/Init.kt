package top.cuggis.geotools

/*
 * @date: 2017/11/25
 */

import top.cuggis.geotools.extensions.adaptImage
import javax.swing.JButton
import groovy.lang.GroovyShell

val groovyShell = GroovyShell()

fun adaptImageIcons() {
    val frame = Main.frame
    val buttons = frame.toolBar.components.filter { it is JButton }.map { it as JButton }
    buttons.forEach { it.adaptImage() }
}