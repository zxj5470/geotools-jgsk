import top.cuggis.geotools.Main

import javax.swing.JFileChooser
import java.awt.GraphicsEnvironment;

import org.geotools.swing.data.JFileDataStoreChooser
import org.jb2011.lnf.beautyeye.ch20_filechooser.BEFileChooserUICross

class groovy {
    public static void main(String[] args) {
        JFileDataStoreChooser f=new JFileDataStoreChooser("shp");
        BEFileChooserUICross.createUI(f)
        f.setVisible(true)
    }
}
