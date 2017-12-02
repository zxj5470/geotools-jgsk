import javax.swing.text.*
import javax.swing.*
import java.awt.*
import java.awt.event.*
import javax.swing.*
import javax.swing.text.rtf.RTFEditorKit

import top.cuggis.geotools.*
import java.awt.event.*

class groovy {
    static JFrame a;
    public static void main(String[] args) {
        try {
            a = new JFrame("Groovy脚本，Ctrl+Enter执行")
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
            console.setPreferredSize(new Dimension(0, 150));

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
            MyPrintStream mps = new MyPrintStream(System.out, console);
            System.setOut(mps);
            System.setErr(mps);
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
        }catch (Exception e){
            e.printStackTrace()
        }
    }
}

class MyTextPane extends JTextPane {
    protected StyleContext m_context
    protected DefaultStyledDocument m_doc
    private MutableAttributeSet keyAttr, normalAttr,someAttr
    private MutableAttributeSet bracketAttr
    private MutableAttributeSet inputAttributes = new RTFEditorKit()
            .getInputAttributes()
    private final static String[] _keys = ["int", "static", "void", "public", "private", "double", "import", "def"]
    public final static String [] _someWords = ["println","out","String"]
    private final static char[] _character = ['(', ')', ',', ';','.',
                                              ':', '\t', '\n', '+', '-', '*', '/'] as char[]

    public MyTextPane() {
        super()
        m_context = new StyleContext()
        m_doc = new DefaultStyledDocument(m_context)
        this.setDocument(m_doc)
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                dealSingleRow()
            }
        })
        keyAttr = new SimpleAttributeSet()
        StyleConstants.setForeground(keyAttr, Color.decode("7A9EC2"))

        someAttr = new SimpleAttributeSet()
        StyleConstants.setForeground(someAttr, Color.decode("233333"))
        // 义一般文本显示属性
        normalAttr = new SimpleAttributeSet()
        StyleConstants.setFontFamily(normalAttr, "Consolas")
        StyleConstants.setBold(normalAttr, false)
        StyleConstants.setForeground(normalAttr, Color.black)
        bracketAttr = new SimpleAttributeSet()
        StyleConstants.setForeground(bracketAttr, Color.RED)
        StyleConstants.setFontFamily(bracketAttr, "Consolas")
        StyleConstants.setBold(bracketAttr, true)
        dealSingleRow()
    }

    private void setBracketColor(String _text) {
        int len = _text.length()
        for (int i = 0; i < len; i++) {
            char ch = _text.charAt(i)
            if (ch == '{' || ch == '}') {
                m_doc.setCharacterAttributes(i, 1, bracketAttr, false)
            }
        }
    }

    private boolean isCharacter(char _ch) {
        for (int i = 0; i < _character.length; i++) {
            if (_ch == _character[i]) {
                return true
            }
        }
        return false
    }

    private int setKeyColor(String _key, int _start, int _length) {
        for (int i = 0; i < _keys.length; i++) {
            int li_index = _key.indexOf(_keys[i])
            if (li_index < 0) {
                continue
            }
            int li_legnth = li_index + _keys[i].length()
            if (li_legnth == _key.length()) {
                if (li_index == 0) {//处理单独一个关键字的情况，例如：if else 等
                    m_doc.setCharacterAttributes(_start, _keys[i].length(),
                            keyAttr, false)
                } else {//处理关键字前面还有字符的情况，例如：)if ;else 等
                    char ch_temp = _key.charAt(li_index - 1)
                    if (isCharacter(ch_temp)) {
                        m_doc.setCharacterAttributes(_start + li_index,
                                _keys[i].length(), keyAttr, false)
                    }
                }
            } else {
                if (li_index == 0) {//处理关键字后面还有字符的情况，例如：if(  end;等
                    char ch_temp = _key.charAt(_keys[i].length())
                    if (isCharacter(ch_temp)) {
                        m_doc.setCharacterAttributes(_start, _keys[i].length(),
                                keyAttr, false)
                    }
                } else {//处理关键字前面和后面都有字符的情况，例如：)if( 等
                    char ch_temp = _key.charAt(li_index - 1)
                    char ch_temp_2 = _key.charAt(li_legnth)
                    if (isCharacter(ch_temp) && isCharacter(ch_temp_2)) {
                        m_doc.setCharacterAttributes(_start + li_index,
                                _keys[i].length(), keyAttr, false)
                    }
                }
            }
        }
        return _length + 1
    }

    private int setWordColor(String _key, int _start, int _length) {
        for (int i = 0; i < _someWords.length; i++) {
            int li_index = _key.indexOf(_someWords[i])
            if (li_index < 0) {
                continue
            }
            int li_legnth = li_index + _someWords[i].length()
            if (li_legnth == _key.length()) {
                if (li_index == 0) {//处理单独一个关键字的情况，例如：if else 等
                    m_doc.setCharacterAttributes(_start, _someWords[i].length(),
                            someAttr, false)
                } else {//处理关键字前面还有字符的情况，例如：)if ;else 等
                    char ch_temp = _key.charAt(li_index - 1)
                    if (isCharacter(ch_temp)) {
                        m_doc.setCharacterAttributes(_start + li_index,
                                _someWords[i].length(), someAttr, false)
                    }
                }
            } else {
                if (li_index == 0) {//处理关键字后面还有字符的情况，例如：if(  end;等
                    char ch_temp = _key.charAt(_someWords[i].length())
                    if (isCharacter(ch_temp)) {
                        m_doc.setCharacterAttributes(_start, _someWords[i].length(),
                                someAttr, false)
                    }
                } else {//处理关键字前面和后面都有字符的情况，例如：)if( 等
                    char ch_temp = _key.charAt(li_index - 1)
                    char ch_temp_2 = _key.charAt(li_legnth)
                    if (isCharacter(ch_temp) && isCharacter(ch_temp_2)) {
                        m_doc.setCharacterAttributes(_start + li_index,
                                _someWords[i].length(), someAttr, false)
                    }
                }
            }
        }
        return _length + 1
    }

    /**
     * 处理一行的数据
     * @param _start
     * @param _end
     */
    public void dealText(int _start, int _end) {
        String text = ""
        try {
            text = m_doc.getText(_start, _end - _start).toUpperCase()
        } catch (BadLocationException e) {
            e.printStackTrace()
        }
        if (text == null || text.equals("")) {
            return
        }
        int xStart = 0
        // 析关键字---
        m_doc.setCharacterAttributes(_start, text.length(), normalAttr, false)
        MyStringTokenizer st = new MyStringTokenizer(text)
        while (st.hasMoreTokens()) {
            String s = st.nextToken()
            if (s == null)
                return
            xStart = st.getCurrPosition()
            setKeyColor(s.toLowerCase(), _start + xStart, s.length())
            setWordColor(s.toLowerCase(), _start + xStart, s.length())
        }
        setBracketColor(text)
        inputAttributes.addAttributes(normalAttr)
    }
    /**
     * 在进行文本修改的时候
     * 获得光标所在行，只对该行进行处理
     */
    private void dealSingleRow() {
        Element root = m_doc.getDefaultRootElement()
        // 光标当前行
        int cursorPos = this.getCaretPosition(); // 前光标的位置
        int line = root.getElementIndex(cursorPos);// 当前行
        Element para = root.getElement(line)
        int start = para.getStartOffset()
        int end = para.getEndOffset() - 1;// 除\r字符
        dealText(start, end)
    }
    /**
     * 在初始化面板的时候调用该方法，
     * 查找整个篇幅的关键字
     */
}

class MyStringTokenizer extends StringTokenizer {
    String sval = " "
    String oldStr, str
    int m_currPosition = 0, m_beginPosition = 0

    MyStringTokenizer(String str) {
        super(str, " ")
        this.oldStr = str
        this.str = str
    }

    public String nextToken() {
        try {
            String s = super.nextToken()
            int pos = -1
            if (oldStr.equals(s)) {
                return s
            }
            pos = str.indexOf(s + sval)
            if (pos == -1) {
                pos = str.indexOf(sval + s)
                if (pos == -1)
                    return null
                else
                    pos += 1
            }
            int xBegin = pos + s.length()
            str = str.substring(xBegin)
            m_currPosition = m_beginPosition + pos
            m_beginPosition = m_beginPosition + xBegin
            return s
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace()
            return null
        }
    }
    // 返回token在字符串中的位置
    public int getCurrPosition() {
        return m_currPosition
    }
}

public class MyPrintStream extends PrintStream {

    private JTextPane text;
    private StringBuffer sb = new StringBuffer();

    public MyPrintStream(OutputStream out, JTextPane text) {
        super(out);
        this.text = text;
    }

    /**
     * 在这里重截,所有的打印方法都要调用的方法
     */
    public void write(byte[] buf, int off, int len) {
        final String message = new String(buf, off, len);
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                sb.append(message);
                text.setText(sb.toString());
            }
        });
    }
}

