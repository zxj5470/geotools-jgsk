package top.cuggis.geotools.utils;

public class SystemChooser {
    public static boolean isLinux(){
        return "linux".equals(getOS());
    }
    private static String getOS(){
        return System.getProperty("os.name").toLowerCase();
    }
}
