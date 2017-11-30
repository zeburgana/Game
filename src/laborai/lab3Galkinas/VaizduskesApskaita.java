package laborai.lab3Galkinas;

import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

/**
 * Created by Pug b0iiiii on 2017-11-30.
 */
public class VaizduskesApskaita {
    public static SetADT<String> VaizduskiuModeliai(VaizdoKortos[] kortos) {
        SetADT<VaizdoKortos> uni = new BstSetKTU<>(VaizdoKortos.pagalModeli);
        SetADT<String> kart = new BstSetKTU<>();
        for (VaizdoKortos k : kortos) {
            int sizeBefore = uni.size();
            uni.add(k);

            if (sizeBefore == uni.size()) {
                kart.add(k.getModelis());
            }
        }
        return kart;
    }
}
