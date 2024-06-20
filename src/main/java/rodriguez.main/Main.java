package rodriguez.main;

import rodriguez.framework.Start;

public class Main {
    public static void main(String[]args) {
        String path = "C:\\Users\\elrod\\OneDrive\\Escritorio\\Cosas\\Uni\\OO2\\TRABAJO FINAL FRAMEWORK\\" +
                "TrabajoFinal-FrameWorks\\src\\main\\resources\\config.json";

        Start m = new Start(path);
        m.init();
    }
}
