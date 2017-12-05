package top.cuggis.geotools.grv;

import java.util.StringTokenizer;

public class MyStringTokenizer extends StringTokenizer {
    private String oldStr, str;
    private int m_currPosition = 0, m_beginPosition = 0;

    MyStringTokenizer(String str) {
        super(str, " ");
        this.oldStr = str;
        this.str = str;
    }

    public String nextToken() {
        try {
            String s = super.nextToken();
            if (oldStr.equals(s)) {
                return s;
            }
            String sval = " ";
            int pos = str.indexOf(s + sval);
            if (pos == -1) {
                pos = str.indexOf(sval + s);
                if (pos == -1)
                    return null;
                else
                    pos += 1;
            }
            int xBegin = pos + s.length();
            str = str.substring(xBegin);
            m_currPosition = m_beginPosition + pos;
            m_beginPosition = m_beginPosition + xBegin;
            return s;
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // 返回token在字符串中的位置
    public int getCurrPosition() {
        return m_currPosition;
    }
}

