import java.awt.*
import javax.swing.*
import top.cuggis.geotools.grv.*
import top.cuggis.geotools.*

import java.awt.event.*

static void main(String[] args) {
    try {
        def a = new JFrame("Groovy脚本，Ctrl+Enter执行")
        a.setLayout(new BorderLayout())
        a.setSize(800, 400)
        def text = new MyTextPane()
        JScrollPane scroll = new JScrollPane(text)
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED)
        text.text = """import top.cuggis.geotools.Main
def frame=Main.frame
println frame.getMapPane()"""
        text.dealText(0, text.text.length())
        def font = text.getFont()
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

        a.add(scroll, BorderLayout.CENTER)
        a.add(consoleScroll, BorderLayout.SOUTH)

        a.iconImage = InitKt.getIcon()
        a.setAlwaysOnTop(true)
        a.visible = true
        MyPrintStream mps = new MyPrintStream(System.out, console)
        System.setOut(mps)
        System.setErr(mps)
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
                        JOptionPane.showMessageDialog(a, "语法错误", "alert", JOptionPane.ERROR_MESSAGE)
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