package laborai.lab3Galkinas;

import laborai.studijosktu.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Pug b0iiiii on 2017-11-30.
 */
public class VaizduskiuTestai {
    static VaizdoKortos[] autoBaze;
    static SortedSetADTx<VaizdoKortos> aSerija = new BstSetKTUx(new VaizdoKortos(), VaizdoKortos.pagalKaina);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        aibėsTestas();
    }

    static SortedSetADTx generuotiAibe(int kiekis, int generN) {
        autoBaze = new VaizdoKortos[generN];
        for (int i = 0; i < generN; i++) {
            autoBaze[i] = new VaizdoKortos.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(autoBaze));
        aSerija.clear();
        for (int i = 0; i < kiekis; i++) {
            aSerija.add(autoBaze[i]);
        }
        return aSerija;
    }

    public static void aibėsTestas() throws CloneNotSupportedException {
        VaizdoKortos a1 = new VaizdoKortos(768, "GTX-1050Ti", 2015, 1392, 163.49);
        VaizdoKortos a2 = new VaizdoKortos.Builder()
                .CUDABranduoliai(1635)
                .modelis("GTX-1060")
                .gamMetai(2016)
                .Daznis(2154)
                .kaina(363.23)
                .build();
        VaizdoKortos a3 = new VaizdoKortos.Builder().buildRandom();
        VaizdoKortos a4 = new VaizdoKortos("863  GTX-1050   2014 2145 150");
        VaizdoKortos a5 = new VaizdoKortos("563  GTX-960    2009 2045 100");
        VaizdoKortos a6 = new VaizdoKortos("1652 GTX-1080   2016 4521 1260");
        VaizdoKortos a7 = new VaizdoKortos("1763 GTX-1080Ti 2017 5421 2600");
        VaizdoKortos a8 = new VaizdoKortos("1725 GTX-1080TI 2017 5444 2700");
        VaizdoKortos a9 = new VaizdoKortos("834  GTX-970Ti  2009 1934 120.30");

        VaizdoKortos[] vaizdoMasyvas = {a9, a7, a8, a5, a1, a6};

        Ks.oun("Auto Aibė:");
        SortedSetADTx<VaizdoKortos> vaizdoAibe = new BstSetKTUx(new VaizdoKortos());

        for (VaizdoKortos k : vaizdoMasyvas) {
            vaizdoAibe.add(k);
            Ks.oun("Aibė papildoma: " + k + ". Jos dydis: " + vaizdoAibe.size());
        }
        Ks.oun("");
        Ks.oun(vaizdoAibe.toVisualizedString(""));

        SortedSetADTx<VaizdoKortos> vaizdoAibeKopija
                = (SortedSetADTx<VaizdoKortos>) vaizdoAibe.clone();

        vaizdoAibeKopija.add(a2);
        vaizdoAibeKopija.add(a3);
        vaizdoAibeKopija.add(a4);
        Ks.oun("Papildyta vaizdoAibės kopija:");
        Ks.oun(vaizdoAibeKopija.toVisualizedString(""));

        a9.setDaznis(1630);

        Ks.oun("Originalas:");
        Ks.ounn(vaizdoAibe.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (VaizdoKortos a : vaizdoMasyvas) {
            Ks.oun(a + ": " + vaizdoAibe.contains(a));
        }
        Ks.oun(a2 + ": " + vaizdoAibe.contains(a2));
        Ks.oun(a3 + ": " + vaizdoAibe.contains(a3));
        Ks.oun(a4 + ": " + vaizdoAibe.contains(a4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (VaizdoKortos a : vaizdoMasyvas) {
            Ks.oun(a + ": " + vaizdoAibeKopija.contains(a));
        }
        Ks.oun(a2 + ": " + vaizdoAibeKopija.contains(a2));
        Ks.oun(a3 + ": " + vaizdoAibeKopija.contains(a3));
        Ks.oun(a4 + ": " + vaizdoAibeKopija.contains(a4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + vaizdoAibeKopija.size());
        for (VaizdoKortos a : new VaizdoKortos[]{a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9}) {
            vaizdoAibeKopija.remove(a);
            Ks.oun("Iš vaizdoAibes kopijos pašalinama: " + a + ". Jos dydis: " + vaizdoAibeKopija.size());
        }
        Ks.oun("");

        Ks.oun("Vaizdo kortų aibė su iteratoriumi:");
        Ks.oun("");
        for (VaizdoKortos a : vaizdoAibe) {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Vaizdo kortų aibė AVL-medyje:");
        SortedSetADTx<VaizdoKortos> vaizdoAibe3 = new AvlSetKTUx(new VaizdoKortos());
        for (VaizdoKortos a : vaizdoMasyvas) {
            vaizdoAibe3.add(a);
        }
        Ks.ounn(vaizdoAibe3.toVisualizedString(""));

        Ks.oun("Vaizdo kortų aibė su iteratoriumi:");
        Ks.oun("");
        for (VaizdoKortos a : vaizdoAibe3) {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Vaizdo kortų aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = vaizdoAibe3.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Vaizdo kortų aibės toString() metodas:");
        Ks.ounn(vaizdoAibe3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        vaizdoAibe.clear();
        vaizdoAibe3.clear();

        Ks.oun("");
        Ks.oun("Vaizdo kortų aibė DP-medyje:");
        vaizdoAibe.load("Duomenys\\ban.txt");
        Ks.ounn(vaizdoAibe.toVisualizedString(""));

        Ks.oun("");
        Ks.oun("Vaizdo kortų aibė AVL-medyje:");
        vaizdoAibe3.load("Duomenys\\ban.txt");
        Ks.ounn(vaizdoAibe3.toVisualizedString(""));

        SetADT<String> vaizdoAibe4 = VaizduskesApskaita.VaizduskiuModeliai(vaizdoMasyvas);
        Ks.oun("Pasikartojantys Vaizdo kortų modeliai:\n" + vaizdoAibe4.toString());
    }
}
