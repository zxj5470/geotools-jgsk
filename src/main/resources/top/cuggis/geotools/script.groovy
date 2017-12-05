package top.cuggis.geotools

import java.awt.*
import java.awt.Font
import java.awt.event.*

import javax.swing.*
import top.cuggis.geotools.grv.ZhTextPane
import top.cuggis.geotools.grv.ZhPrintStream

class script{
    static void main(String[] args) {
        try {
            def ide = new JFrame("Groovy脚本，Ctrl+Enter执行")
            println "打开控制台"
            ide.setLayout(new BorderLayout())
            ide.setSize(800, 400)
            ide.setLocation(100,300)
            ide.visible = true
            JTextPane text = new ZhTextPane() as JTextPane
            JScrollPane scroll = new JScrollPane(text)
            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)
            scroll.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED)
            text.text =
                    """import top.cuggis.geotools.Main
def frame=Main.frame
int layersNumber=Main.map.layers().size()
println frame.getMapPane()
println layersNumber"""
            text.dealText(0, text.text.length())

            Font font = text.getFont()
            def size = font.getSize() + 5
            text.setFont(new Font(font.name, font.style, size))
            def console = new JTextPane()
            console.editable = false
            console.setPreferredSize(new Dimension(0, 150))

            JScrollPane consoleScroll = new JScrollPane(console)
            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)
            scroll.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED)

            ide.add(scroll, BorderLayout.CENTER)
            ide.add(consoleScroll, BorderLayout.SOUTH)

            ide.iconImage = InitKt.getIcon()
            ide.setAlwaysOnTop(true)
            ide.addWindowListener(new WindowAdapter() {
                @Override
                void windowClosing(WindowEvent e) {
                    System.setOut(System.out)
                    System.setErr(System.err)
                }
            })
            ZhPrintStream mps = new ZhPrintStream(System.out, console)
            System.setOut(mps as PrintStream)
            System.setErr(mps as PrintStream)
            text.addKeyListener(new KeyListener() {
                @Override
                void keyTyped(KeyEvent e) {
                }

                @Override
                void keyPressed(KeyEvent e) {
                    if (e.isControlDown()
                            && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                        GroovyShell groovyShell = InitKt.getGroovyShell()
                        try {
                            groovyShell.evaluate(text.text)
                        } catch (Exception ex) {
                            ex.printStackTrace()
                            JOptionPane.showMessageDialog(ide, "语法错误", "alert", JOptionPane.ERROR_MESSAGE)
                        }
                    }
                }

                @Override
                void keyReleased(KeyEvent e) {
                }
            })
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
