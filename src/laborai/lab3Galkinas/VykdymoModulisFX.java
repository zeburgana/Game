package laborai.lab3Galkinas;

import javafx.application.Application;
import javafx.stage.Stage;
import laborai.gui.fx.Lab3WindowFX;

import java.util.Locale;

/**
 * Created by Pug b0iiiii on 2017-11-30.
 */
public class VykdymoModulisFX extends Application {

    public static void main(String [] args) {
        laborai.lab3Galkinas.VykdymoModulisFX.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        VaizduskiuTestai.aibėsTestas();
        Lab3WindowFX.createAndShowFXGUI(primaryStage);
    }
}