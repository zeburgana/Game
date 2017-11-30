package laborai.lab3Galkinas;

import laborai.gui.swing.Lab3Window;
import java.util.Locale;

/**
 * Created by Pug b0iiiii on 2017-11-30.
 */
public class VykdymoModulis {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        VaizduskiuTestai.aibėsTestas();
        Lab3Window.createAndShowGUI();
    }
}