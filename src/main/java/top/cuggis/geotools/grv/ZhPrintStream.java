package top.cuggis.geotools.grv;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import java.io.*;

public class ZhPrintStream extends PrintStream {

    private JTextPane text;
    private StringBuffer sb = new StringBuffer();

    public ZhPrintStream(OutputStream out, JTextPane text) {
        super(out);
        this.text = text;
    }

    /**
     * 在这里重截,所有的打印方法都要调用的方法
     */
    public void write(byte[] buf, int off, int len) {
        final String message = new String(buf, off, len);
        SwingUtilities.invokeLater(() -> {
            sb.append(message);
            text.setText(sb.toString());
        });
    }
}

