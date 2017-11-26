package top.cuggis.geotools.extensions

import javax.swing.ImageIcon
import javax.swing.JButton
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver
import org.geotools.legend.Glyph.image
import top.cuggis.geotools.Main
import org.geotools.legend.Glyph.image
import java.awt.*
import java.awt.Color.white
import org.geotools.legend.Glyph.image




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

fun adaptImage(self: Image, w: Int = 32, h: Int = 32): Image {
    return self.getScaledInstance(w, h, Image.SCALE_SMOOTH)
}

fun cursorAdaptImage(self: ImageIcon, w: Int = 32, h: Int = 32): ImageIcon {
    return ImageIcon(self.image.getScaledInstance(w, h, Image.SCALE_SMOOTH))
}
