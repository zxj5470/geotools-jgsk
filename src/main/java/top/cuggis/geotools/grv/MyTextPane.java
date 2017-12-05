package top.cuggis.geotools.grv;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyTextPane extends JTextPane {
    private DefaultStyledDocument m_doc;
    private MutableAttributeSet keyAttr, normalAttr, someAttr;
    private MutableAttributeSet bracketAttr;
    private MutableAttributeSet inputAttributes = new RTFEditorKit().getInputAttributes();
    private static final String[] _keys = new String[]{"int", "static", "void", "public", "private", "double", "import", "def"};
    private static final String[] _someWords = new String[]{"println", "out", "String"};
    private static final char[] _character = new char[]{'(', ')', ',', '.',
            ':', '\t', '\n', '+', '-', '*', '/'};

    public MyTextPane() {
        super();
        StyleContext m_context = new StyleContext();
        m_doc = new DefaultStyledDocument(m_context);
        this.setDocument(m_doc);
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                dealSingleRow();
            }
        });
        keyAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(keyAttr, Color.decode("0x00007f"));

        someAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(someAttr, Color.decode("233333"));

        normalAttr = new SimpleAttributeSet();
        StyleConstants.setFontFamily(normalAttr, "Consolas");
        StyleConstants.setBold(normalAttr, false);
        StyleConstants.setForeground(normalAttr, Color.black);


        bracketAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(bracketAttr, Color.RED);
        StyleConstants.setFontFamily(bracketAttr, "Consolas");
        StyleConstants.setBold(bracketAttr, true);
        dealSingleRow();
    }

    private void setBracketColor(String _text) {
        int len = _text.length();
        for (int i = 0; i < len; i++) {
            char ch = _text.charAt(i);
            if (ch == '{' || ch == '}') {
                m_doc.setCharacterAttributes(i, 1, bracketAttr, false);
            }
        }
    }

    private boolean isCharacter(char _ch) {
        for (char a_character : _character) {
            if (_ch == a_character) {
                return true;
            }
        }
        return false;
    }

    private void setKeyColor(String _key, int _start, String[] typeOfKey, MutableAttributeSet attr) {
        for (String k : typeOfKey) {
            int li_index = _key.indexOf(k);
            if (li_index < 0) {
                continue;
            }
            int li_legnth = li_index + k.length();
            if (li_legnth == _key.length()) {
                if (li_index == 0) {//处理单独一个关键字的情况，例如：if else 等
                    m_doc.setCharacterAttributes(_start, k.length(),
                            attr, false);
                } else {//处理关键字前面还有字符的情况，例如：)if else 等
                    char ch_temp = _key.charAt(li_index - 1);
                    if (isCharacter(ch_temp)) {
                        m_doc.setCharacterAttributes(_start + li_index,
                                k.length(), attr, false);
                    }
                }
            } else {
                if (li_index == 0) {
                    char ch_temp = _key.charAt(k.length());
                    if (isCharacter(ch_temp)) {
                        m_doc.setCharacterAttributes(_start, k.length(),
                                attr, false);
                    }
                } else {
                    char ch_temp = _key.charAt(li_index - 1);
                    char ch_temp_2 = _key.charAt(li_legnth);
                    if (isCharacter(ch_temp) && isCharacter(ch_temp_2)) {
                        m_doc.setCharacterAttributes(_start + li_index,
                                k.length(), attr, false);
                    }
                }
            }
        }
    }

    public void dealText(int _start, int _end) {
        String text = "";
        try {
            text = m_doc.getText(_start, _end - _start).toUpperCase();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        if (text.equals("")) {
            return;
        }
        int xStart;
        m_doc.setCharacterAttributes(_start, text.length(), normalAttr, false);
        MyStringTokenizer st = new MyStringTokenizer(text);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s == null)
                return;
            xStart = st.getCurrPosition();
            setKeyColor(s.toLowerCase(), _start + xStart, _keys, keyAttr);
            setKeyColor(s.toLowerCase(), _start + xStart, _someWords, someAttr);
        }
        setBracketColor(text);
        inputAttributes.addAttributes(normalAttr);
    }

    private void dealSingleRow() {
        Element root = m_doc.getDefaultRootElement();
        int cursorPos = this.getCaretPosition();
        int line = root.getElementIndex(cursorPos);
        Element para = root.getElement(line);
        int start = para.getStartOffset();
        int end = para.getEndOffset() - 1;
        dealText(start, end);
    }
}
