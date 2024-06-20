package rodriguez.framework;

public class Start {

    Framework framework;
    String pathConfig;
    Configuracion config;

    public Start(String pathConfig){
        this.pathConfig = pathConfig;
        this.config = new Configuracion();
    }


    public void init(){
        framework = new Framework(config.configToActionsList(pathConfig));
        framework.mostrarMenu();
    }
}
