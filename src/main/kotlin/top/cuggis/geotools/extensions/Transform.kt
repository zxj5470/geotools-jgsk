package top.cuggis.geotools.extensions

import java.awt.Dimension
import java.awt.Image
import java.io.File
import javax.swing.ImageIcon
import javax.swing.JButton

/*
 * @date: 2017/11/25
 */
fun JButton.adaptImage(w: Int = 32, h: Int = 32) {
    val imgIcon = this.icon
    if (imgIcon is ImageIcon) {
        val temp = imgIcon.image.getScaledInstance(w, h, Image.SCALE_SMOOTH)
        this.icon = ImageIcon(temp)
        this.preferredSize = Dimension(w, h)
    }
}

fun adaptImage(self: ImageIcon, w: Int = 32, h: Int = 32): ImageIcon {
    return ImageIcon(self.image.getScaledInstance(w, h, Image.SCALE_SMOOTH))
}

//inline operator fun <T, R> T.invoke(block: (T) -> R): R = block(this)
//
//fun main(args: Array<String>) {
//    val reader=File(".gitignore").inputStream()
//    var len=0
//    while ( (reader.read()){len=it;len!=-1} ){
//        println(len.toChar())
//    }
//}