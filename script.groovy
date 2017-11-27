import javax.swing.JFrame
import javax.swing.JTextArea
import javax.swing.JButton
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

import top.cuggis.geotools.*


class groovy {
    public static void main(String[] args) {
        def a = new JFrame("Groovy脚本，Ctrl+Enter执行")
        a.setSize(500,250)
        def text=new JTextArea()
        def font=text.getFont()
        def size=font.getSize()+5

        text.setFont(new Font(font.name,font.style,size))
        def button=new JButton()
        button.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                println 23333
            }
        })
        a.add(text)
        a.iconImage=InitKt.getIcon()
        a.visible=true
        text.addKeyListener(new KeyListener() {
            @Override
            void keyTyped(KeyEvent e) {
            }

            @Override
            void keyPressed(KeyEvent e) {
                if (e.isControlDown()
                        && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                    GroovyShell groovyShell= InitKt.getGroovyShell()
                    try {
                        groovyShell.evaluate(text.text)
                    }catch (Exception ex){
                        ex.printStackTrace()
                    }
                }
            }

            @Override
            void keyReleased(KeyEvent e) {
            }
        })
    }
}
