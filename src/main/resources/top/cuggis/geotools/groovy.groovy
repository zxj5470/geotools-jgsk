package top.cuggis.geotools

import org.geotools.swing.data.JFileDataStoreChooser
import org.jb2011.lnf.beautyeye.ch20_filechooser.BEFileChooserUICross

import javax.swing.JFileChooser

class groovy {
    public static void main(String[] args) {
        JFileDataStoreChooser f=new JFileDataStoreChooser("shp");
        BEFileChooserUICross.createUI(f)
        f.setVisible(true)
        f.setDialogType(JFileChooser.OPEN_DIALOG)
    }
}
