package top.cuggis.geotools


import top.cuggis.geotools.component.actions.OpenFileAction
import top.cuggis.geotools.extensions.adaptImage
import javax.swing.JButton
import groovy.lang.GroovyShell
import top.cuggis.geotools.component.actions.RunSctiptAction

val groovyShell = GroovyShell()

/*
 * @date: 2017/11/25
 */
fun main(args: Array<String>) {

    Main.main(null)
    val f = Main.frame
    var btn = JButton(OpenFileAction(f.mapPane))
    btn.name = "OpenFile"
    f.toolBar.add(btn)
    btn = JButton(RunSctiptAction(f.mapPane))
    btn.name = "RunScript"
    f.toolBar.add(btn)

    val buttons = f.toolBar.components.filter { it is JButton }.map { it as JButton }
    buttons.forEach { it.adaptImage() }
    f.isVisible = true

}