package laborai.lab3Galkinas;

import laborai.gui.MyException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Created by Pug b0iiiii on 2017-11-30.
 */
public class VaizduskiuGamyba {
    private static VaizdoKortos[] kortos;
    private static int pradinisIndeksas = 0, galinisIndeksas = 0;
    private static boolean arPradzia = true;

    public static VaizdoKortos[] generuoti(int kiekis) {
        kortos = new VaizdoKortos[kiekis];
        for (int i = 0; i < kiekis; i++) {
            kortos[i] = new VaizdoKortos.Builder().buildRandom();
        }
        return kortos;
    }

    public static VaizdoKortos[] generuotiIrIsmaisyti(int aibesDydis,
                                                     double isbarstymoKoeficientas) throws MyException {
        return generuotiIrIsmaisyti(aibesDydis, aibesDydis, isbarstymoKoeficientas);
    }

    /**
     *
     * @param aibesDydis
     * @param aibesImtis
     * @param isbarstymoKoeficientas
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws MyException
     */
    public static VaizdoKortos[] generuotiIrIsmaisyti(int aibesDydis,
                                                     int aibesImtis, double isbarstymoKoeficientas) throws MyException {
        kortos = generuoti(aibesDydis);
        return ismaisyti(kortos, aibesImtis, isbarstymoKoeficientas);
    }

    // Galima paduoti masyvą išmaišymui iš išorės
    public static VaizdoKortos[] ismaisyti(VaizdoKortos[] autoBaze,
                                          int kiekis, double isbarstKoef) throws MyException {
        if (autoBaze == null) {
            throw new IllegalArgumentException("AutoBaze yra null");
        }
        if (kiekis <= 0) {
            throw new MyException(String.valueOf(kiekis), 1);
        }
        if (autoBaze.length < kiekis) {
            throw new MyException(autoBaze.length + " >= " + kiekis, 2);
        }
        if ((isbarstKoef < 0) || (isbarstKoef > 1)) {
            throw new MyException(String.valueOf(isbarstKoef), 3);
        }

        int likusiuKiekis = autoBaze.length - kiekis;
        int pradziosIndeksas = (int) (likusiuKiekis * isbarstKoef / 2);

        VaizdoKortos[] pradineAutomobiliuImtis = Arrays.copyOfRange(autoBaze, pradziosIndeksas, pradziosIndeksas + kiekis);
        VaizdoKortos[] likusiAutomobiliuImtis = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(autoBaze, 0, pradziosIndeksas)),
                        Arrays.stream(Arrays.copyOfRange(autoBaze, pradziosIndeksas + kiekis, autoBaze.length)))
                .toArray(VaizdoKortos[]::new);

        Collections.shuffle(Arrays.asList(pradineAutomobiliuImtis)
                .subList(0, (int) (pradineAutomobiliuImtis.length * isbarstKoef)));
        Collections.shuffle(Arrays.asList(likusiAutomobiliuImtis)
                .subList(0, (int) (likusiAutomobiliuImtis.length * isbarstKoef)));

        VaizduskiuGamyba.pradinisIndeksas = 0;
        galinisIndeksas = likusiAutomobiliuImtis.length - 1;
        VaizduskiuGamyba.kortos = likusiAutomobiliuImtis;
        return pradineAutomobiliuImtis;
    }

    public static VaizdoKortos gautiIsBazes() throws MyException {
        if ((galinisIndeksas - pradinisIndeksas) < 0) {
            throw new MyException(String.valueOf(galinisIndeksas - pradinisIndeksas), 4);
        }
        // Vieną kartą VaizdoKortos imamas iš masyvo pradžios, kitą kartą - iš galo.     
        arPradzia = !arPradzia;
        return kortos[arPradzia ? pradinisIndeksas++ : galinisIndeksas--];
    }
}
