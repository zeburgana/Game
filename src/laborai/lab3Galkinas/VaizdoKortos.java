package laborai.lab3Galkinas;


import laborai.studijosktu.KTUable;
import laborai.studijosktu.Ks;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Pug b0iiiii on 2017-11-29.
 */
public class VaizdoKortos implements KTUable<VaizdoKortos> {

    // bendri duomenys visiems automobiliams (visai klasei)
    private static final int priimtinųMetųRiba = 2013;
    private static final int esamiMetai = LocalDate.now().getYear();
    private static final double minKaina = 150.0;
    private static final double maxKaina = 37500.0;
    private static final String idCode = "TA";   //  ***** nauja
    private static int serNr = 100;               //  ***** nauja
    private final String autoRegNr;
    private int CUDABranduoliai = -1;
    private String modelis = "";
    private int gamMetai = -1;
    private int Daznis = -1;
    private double kaina = -1.0;

    public VaizdoKortos() {
        autoRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
    }

    public VaizdoKortos(int cuda, String modelis,
                       int gamMetai, int daznis, double kaina) {
        autoRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.CUDABranduoliai = cuda;
        this.modelis = modelis;
        this.gamMetai = gamMetai;
        this.Daznis = daznis;
        this.kaina = kaina;
        validate();
    }

    public VaizdoKortos(String dataString) {
        autoRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.parse(dataString);
    }

    public VaizdoKortos(VaizdoKortos.Builder builder) {
        autoRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.CUDABranduoliai = builder.CUDABranduoliai;
        this.modelis = builder.modelis;
        this.gamMetai = builder.gamMetai;
        this.Daznis = builder.Daznis;
        this.kaina = builder.kaina;
        validate();
    }

    @Override
    public VaizdoKortos create(String dataString) {
        return new VaizdoKortos(dataString);
    }

    @Override
    public String validate() {
        String klaidosTipas = "";
        if (gamMetai < priimtinųMetųRiba || gamMetai > esamiMetai) {
            klaidosTipas = "Netinkami gamybos metai, turi būti ["
                    + priimtinųMetųRiba + ":" + esamiMetai + "]";
        }
        if (kaina < minKaina || kaina > maxKaina) {
            klaidosTipas += " Kaina už leistinų ribų [" + minKaina
                    + ":" + maxKaina + "]";
        }
        return klaidosTipas;
    }

    @Override
    public void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);

            setCUDABranduoliai(ed.nextInt());
            modelis = ed.next();
            gamMetai = ed.nextInt();
            setDaznis(ed.nextInt());
            setKaina(ed.nextDouble());
            validate();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie korta -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie korta -> " + dataString);
        }
    }

    @Override
    public String toString() {  // papildyta su autoRegNr
        return getAutoRegNr() + "=" + CUDABranduoliai + "_" + modelis + ":" + gamMetai + " " + getDaznis() + " " + String.format("%4.1f", kaina);
    }

    public int getCUDABranduoliai() {
        return CUDABranduoliai;
    }
    public void setCUDABranduoliai(int cuda){
        this.CUDABranduoliai = cuda;
    }

    public String getModelis() {
        return modelis;
    }

    public int getGamMetai() {
        return gamMetai;
    }

    public int getDaznis() {
        return Daznis;
    }

    public void setDaznis(int Daznis) {
        this.Daznis = Daznis;
    }

    public double getKaina() {
        return kaina;
    }

    public void setKaina(double kaina) {
        this.kaina = kaina;
    }

    public String getAutoRegNr() {  //** nauja.
        return autoRegNr;
    }

    @Override
    public int compareTo(VaizdoKortos a) {
        return getAutoRegNr().compareTo(a.getAutoRegNr());
    }

    public static Comparator<VaizdoKortos> pagalModeli = (VaizdoKortos a1, VaizdoKortos a2) -> a1.modelis.compareTo(a2.modelis);

    public static Comparator<VaizdoKortos> pagalKaina = (VaizdoKortos a1, VaizdoKortos a2) -> {
        // didėjanti tvarka, pradedant nuo mažiausios
        if (a1.kaina < a2.kaina) {
            return -1;
        }
        if (a1.kaina > a2.kaina) {
            return +1;
        }
        return 0;
    };

    public static Comparator<VaizdoKortos> pagalMetusKainą = (VaizdoKortos a1, VaizdoKortos a2) -> {
        // metai mažėjančia tvarka, esant vienodiems lyginama kaina
        if (a1.gamMetai > a2.gamMetai) {
            return +1;
        }
        if (a1.gamMetai < a2.gamMetai) {
            return -1;
        }
        if (a1.kaina > a2.kaina) {
            return +1;
        }
        if (a1.kaina < a2.kaina) {
            return -1;
        }
        return 0;
    };

    // VaizdoKortos klases objektų gamintojas
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[] MODELIAI = { // galimų automobilių markių ir jų modelių masyvas
                "Titan-XP", "Titan-X", "GTX-1080", "GTX-1080Ti", "GTX-1070Ti", "GTX-1070", "GTX-1060", "GTX-1050",
                "GTX-1050Ti", "GTX-980Ti", "GTX-980", "GTX-970", "GTX-960", "GTX-950"
        };

        private String modelis = "";
        private int CUDABranduoliai = -1;
        private int gamMetai = -1;
        private int Daznis = -1;
        private double kaina = -1.0;

        public VaizdoKortos build() {
            return new VaizdoKortos(this);
        }

        public VaizdoKortos buildRandom() {
            int ma = RANDOM.nextInt(MODELIAI.length);
            return new VaizdoKortos(300+RANDOM.nextInt(1000),
                     MODELIAI[ma],
                    2000 + RANDOM.nextInt(17),// metai tarp 2000 ir 2017
                    6000 + RANDOM.nextInt(222000),// Daznis tarp 6000 ir 228000
                    800 + RANDOM.nextDouble() * 3000);// kaina tarp 800 ir 88800
        }

        public VaizdoKortos.Builder gamMetai(int gamMetai) {
            this.gamMetai = gamMetai;
            return this;
        }

        public VaizdoKortos.Builder CUDABranduoliai(int CUDABranduoliai) {
            this.CUDABranduoliai = CUDABranduoliai;
            return this;
        }

        public VaizdoKortos.Builder modelis(String modelis) {
            this.modelis = modelis;
            return this;
        }

        public VaizdoKortos.Builder Daznis(int Daznis) {
            this.Daznis = Daznis;
            return this;
        }

        public VaizdoKortos.Builder kaina(double kaina) {
            this.kaina = kaina;
            return this;
        }
    }
}

