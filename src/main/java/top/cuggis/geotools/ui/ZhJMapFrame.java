//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package top.cuggis.geotools.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapPane;
import org.geotools.swing.MapLayerTable;
import org.geotools.swing.action.InfoAction;
import org.geotools.swing.action.NoToolAction;
import org.geotools.swing.action.PanAction;
import org.geotools.swing.action.ResetAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swing.action.ZoomOutAction;
import org.geotools.swing.control.JMapStatusBar;
import top.cuggis.geotools.component.actions.ZhZoomInAction;

public class ZhJMapFrame extends JFrame {
    public static final String TOOLBAR_INFO_BUTTON_NAME = "ToolbarInfoButton";
    public static final String TOOLBAR_PAN_BUTTON_NAME = "ToolbarPanButton";
    public static final String TOOLBAR_POINTER_BUTTON_NAME = "ToolbarPointerButton";
    public static final String TOOLBAR_RESET_BUTTON_NAME = "ToolbarResetButton";
    public static final String TOOLBAR_ZOOMIN_BUTTON_NAME = "ToolbarZoomInButton";
    public static final String TOOLBAR_ZOOMOUT_BUTTON_NAME = "ToolbarZoomOutButton";
    private boolean showToolBar;
    private Set<ZhJMapFrame.Tool> toolSet;
    private JMapPane mapPane;
    private MapLayerTable mapLayerTable;
    private JToolBar toolBar;
    private boolean showStatusBar;
    private boolean showLayerTable;
    private boolean uiSet;

    public static void showMap(final MapContent content) {
        if (SwingUtilities.isEventDispatchThread()) {
            doShowMap(content);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ZhJMapFrame.doShowMap(content);
                }
            });
        }

    }

    private static void doShowMap(MapContent content) {
        ZhJMapFrame frame = new ZhJMapFrame(content);
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        frame.initComponents();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public ZhJMapFrame() {
        this((MapContent)null);
    }

    public ZhJMapFrame(MapContent content) {
        super(content == null ? "" : content.getTitle());
        this.setDefaultCloseOperation(3);
        this.setLocale(Locale.SIMPLIFIED_CHINESE);
        this.showLayerTable = false;
        this.showStatusBar = false;
        this.showToolBar = false;
        this.toolSet = EnumSet.noneOf(ZhJMapFrame.Tool.class);
        this.mapPane = new JMapPane(content);
        this.mapPane.setBackground(Color.WHITE);
        this.mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                ZhJMapFrame.this.mapPane.requestFocusInWindow();
            }
        });
        this.mapPane.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                ZhJMapFrame.this.mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

            public void focusLost(FocusEvent e) {
                ZhJMapFrame.this.mapPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        this.mapPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ZhJMapFrame.this.mapPane.requestFocusInWindow();
            }
        });
    }

    public void enableToolBar(boolean enabled) {
        if (enabled) {
            this.toolSet = EnumSet.allOf(ZhJMapFrame.Tool.class);
        } else {
            this.toolSet.clear();
        }

        this.showToolBar = enabled;
    }

    public void enableTool(ZhJMapFrame.Tool... tool) {
        if (tool != null && tool.length != 0) {
            this.toolSet = EnumSet.copyOf(Arrays.asList(tool));
            this.showToolBar = true;
        } else {
            this.enableToolBar(false);
        }

    }

    public void enableStatusBar(boolean enabled) {
        this.showStatusBar = enabled;
    }

    public void enableLayerTable(boolean enabled) {
        this.showLayerTable = enabled;
    }

    public void setVisible(boolean state) {
        if (state && !this.uiSet) {
            this.initComponents();
        }

        super.setVisible(state);
    }

    public void initComponents() {
        if (!this.uiSet) {
            StringBuilder sb = new StringBuilder();
            if (!this.toolSet.isEmpty()) {
                sb.append("[]");
            }

            sb.append("[grow]");
            if (this.showStatusBar) {
                sb.append("[min!]");
            }

            JPanel panel = new JPanel(new MigLayout("wrap 1, insets 0", "[grow]", sb.toString()));
            if (this.showToolBar) {
                this.toolBar = new JToolBar();
                this.toolBar.setOrientation(0);
                this.toolBar.setFloatable(false);
                ButtonGroup cursorToolGrp = new ButtonGroup();
                JButton btn;
                if (this.toolSet.contains(ZhJMapFrame.Tool.POINTER)) {
                    btn = new JButton(new NoToolAction(this.mapPane));
                    btn.setName("ToolbarPointerButton");
                    this.toolBar.add(btn);
                    cursorToolGrp.add(btn);
                }

                if (this.toolSet.contains(ZhJMapFrame.Tool.ZOOM)) {
                    btn = new JButton(new ZhZoomInAction(this.mapPane));
                    btn.setName("ToolbarZoomInButton");
                    this.toolBar.add(btn);
                    cursorToolGrp.add(btn);
                    btn = new JButton(new ZoomOutAction(this.mapPane));
                    btn.setName("ToolbarZoomOutButton");
                    this.toolBar.add(btn);
                    cursorToolGrp.add(btn);
                    this.toolBar.addSeparator();
                }

                if (this.toolSet.contains(ZhJMapFrame.Tool.PAN)) {
                    btn = new JButton(new PanAction(this.mapPane));
                    btn.setName("ToolbarPanButton");
                    this.toolBar.add(btn);
                    cursorToolGrp.add(btn);
                    this.toolBar.addSeparator();
                }

                if (this.toolSet.contains(ZhJMapFrame.Tool.INFO)) {
                    btn = new JButton(new InfoAction(this.mapPane));
                    btn.setName("ToolbarInfoButton");
                    this.toolBar.add(btn);
                    this.toolBar.addSeparator();
                }

                if (this.toolSet.contains(ZhJMapFrame.Tool.RESET)) {
                    btn = new JButton(new ResetAction(this.mapPane));
                    btn.setName("ToolbarResetButton");
                    this.toolBar.add(btn);
                }

                panel.add(this.toolBar, "grow");
            }

            if (this.showLayerTable) {
                this.mapLayerTable = new MapLayerTable(this.mapPane);
                this.mapLayerTable.setPreferredSize(new Dimension(200, -1));
                JSplitPane splitPane = new JSplitPane(1, false, this.mapLayerTable, this.mapPane);
                panel.add(splitPane, "grow");
            } else {
                panel.add(this.mapPane, "grow");
            }

            if (this.showStatusBar) {
                panel.add(JMapStatusBar.createDefaultStatusBar(this.mapPane), "grow");
            }

            this.getContentPane().add(panel);
            this.uiSet = true;
        }
    }

    public MapContent getMapContent() {
        return this.mapPane.getMapContent();
    }

    public void setMapContent(MapContent content) {
        if (content == null) {
            throw new IllegalArgumentException("map content must not be null");
        } else {
            this.mapPane.setMapContent(content);
        }
    }

    public JMapPane getMapPane() {
        return this.mapPane;
    }

    public JToolBar getToolBar() {
        if (!this.uiSet) {
            this.initComponents();
        }

        return this.toolBar;
    }

    public static enum Tool {
        POINTER,
        INFO,
        PAN,
        RESET,
        ZOOM;

        private Tool() {
        }
    }
}
