package top.cuggis.geotools.extensions

import java.awt.Dimension
import java.awt.Image
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