package top.cuggis.geotools.grv;

import top.cuggis.geotools.utils.SystemChooser;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ZhTextPane extends JTextPane {
    private DefaultStyledDocument document;
    private MutableAttributeSet keyAttr, normalAttr, someAttr;
    private MutableAttributeSet bracketAttr;
    private MutableAttributeSet inputAttributes = new RTFEditorKit().getInputAttributes();
    private static final String[] keyWords = new String[]{"int", "static", "void", "public", "private", "double", "import", "def"};
    private static final String[] someWords = new String[]{"println", "out", "String"};
    private static final char[] characters = new char[]{'(', ')', ',', '.',
            ':', '\t', '\n', '+', '-', '*', '/'};

    public ZhTextPane() {
        super();
        StyleContext context = new StyleContext();
        document = new DefaultStyledDocument(context);
        this.setDocument(document);
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                dealSingleRow();
            }
        });
        keyAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(keyAttr, Color.decode("0x00007f"));
        String fontFamily;
        if(SystemChooser.isLinux()){
            fontFamily="YaHei Consolas Hybrid";
        }else{
            fontFamily="Consolas";
        }
        someAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(someAttr, Color.decode("233333"));

        normalAttr = new SimpleAttributeSet();
        StyleConstants.setFontFamily(normalAttr, fontFamily);
        StyleConstants.setBold(normalAttr, false);
        StyleConstants.setForeground(normalAttr, Color.black);


        bracketAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(bracketAttr, Color.RED);
        StyleConstants.setFontFamily(bracketAttr, fontFamily);
        StyleConstants.setBold(bracketAttr, true);
        dealSingleRow();
    }

    private void setBracketColor(String text) {
        int len = text.length();
        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            if (ch == '{' || ch == '}') {
                document.setCharacterAttributes(i, 1, bracketAttr, false);
            }
        }
    }

    private boolean isCharacter(char c) {
        for (char each : characters) {
            if (c == each) {
                return true;
            }
        }
        return false;
    }

    private void setKeyColor(String key, int start, String[] typeOfKey, MutableAttributeSet attr) {
        for (String k : typeOfKey) {
            int index = key.indexOf(k);
            if (index < 0) {
                continue;
            }
            int length = index + k.length();
            if (length == key.length()) {
                if (index == 0) {//处理单独一个关键字的情况，例如：if else 等
                    document.setCharacterAttributes(start, k.length(),
                            attr, false);
                } else {//处理关键字前面还有字符的情况，例如：)if else 等
                    char chTemp = key.charAt(index - 1);
                    if (isCharacter(chTemp)) {
                        document.setCharacterAttributes(start + index,
                                k.length(), attr, false);
                    }
                }
            } else {
                if (index == 0) {
                    char chTemp = key.charAt(k.length());
                    if (isCharacter(chTemp)) {
                        document.setCharacterAttributes(start, k.length(),
                                attr, false);
                    }
                } else {
                    char chTemp = key.charAt(index - 1);
                    char chTemp2 = key.charAt(length);
                    if (isCharacter(chTemp) && isCharacter(chTemp2)) {
                        document.setCharacterAttributes(start + index,
                                k.length(), attr, false);
                    }
                }
            }
        }
    }

    public void dealText(int start, int end) {
        String text = "";
        try {
            text = document.getText(start, end - start).toUpperCase();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        if (text.equals("")) {
            return;
        }
        int xStart;
        document.setCharacterAttributes(start, text.length(), normalAttr, false);
        ZhStringTokenizer st = new ZhStringTokenizer(text);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s == null)
                return;
            xStart = st.getCurrPosition();
            setKeyColor(s.toLowerCase(), start + xStart, keyWords, keyAttr);
            setKeyColor(s.toLowerCase(), start + xStart, someWords, someAttr);
        }
        setBracketColor(text);
        inputAttributes.addAttributes(normalAttr);
    }

    private void dealSingleRow() {
        Element root = document.getDefaultRootElement();
        int cursorPos = this.getCaretPosition();
        int line = root.getElementIndex(cursorPos);
        Element para = root.getElement(line);
        int start = para.getStartOffset();
        int end = para.getEndOffset() - 1;
        dealText(start, end);
    }
}
