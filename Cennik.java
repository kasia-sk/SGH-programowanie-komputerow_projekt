import java.util.Scanner;

class Cennik {
    private static int cenaStudent = 20;
    private static int cenaDorosly = 32;
    private static int cenaDziecko = 15;
    private static int cenaKartaSportowa = 10;
    private static int cenaButow = 8;
    private static int cenaUprzaz = 10;
    
    static int getCenaStudent() {
        return cenaStudent;
    }

    static int getCenaDorosly() {
        return cenaDorosly;
    }

    static int getCenaDziecko() {
        return cenaDziecko;
    }

    static int getCenaKartaSportowa() {
        return cenaKartaSportowa;
    }

    static int getCenaButow() {
        return cenaButow;
    }

    static int getCenaUprzaz() {
        return cenaUprzaz;
    }

    static void setCenaStudent(int cenaStudent) {
        Cennik.cenaStudent = cenaStudent;
    }

    static void setCenaDorosly(int cenaDorosly) {
        Cennik.cenaDorosly = cenaDorosly;
    }

    static void setCenaDziecko(int cenaDziecko) {
        Cennik.cenaDziecko = cenaDziecko;
    }

    static void setCenaKartaSportowa(int cenaKartaSportowa) {
        Cennik.cenaKartaSportowa = cenaKartaSportowa;
    }

    static void setCenaButow(int cenaButow) {
        Cennik.cenaButow = cenaButow;
    }

    static void setCenaUprzaz(int cenaUprzaz) {
        Cennik.cenaUprzaz = cenaUprzaz;
    }

    static void printCennik(){
        System.out.println("Cennik:");
        System.out.println("Student " + cenaStudent);
        System.out.println("Dorosły " + cenaDorosly);
        System.out.println("Dziecko " + cenaDziecko);
        System.out.println("Karta sportowa " + cenaKartaSportowa);
        System.out.println("Wypożyczenie butów " + cenaButow);
        System.out.println("Wypożyczenie uprzęży " + cenaUprzaz + "\n");
    }

    static void zmienCennik(Scanner skaner){
        System.out.println("Zmień cenę dla:\n" +
            "1 - Wejściówka dla studenta\n" +
            "2 - Wejściówka dla dorosłego\n" +
            "3 - Wejściówka dla dziecka\n" +
            "4 - Wejściówka dla osoby z kartą sportową\n" +
            "5 - Wypożyczenie butów\n" +
            "6 - Wypożyczenie uprzęży");
        int zmianaCeny = Scianka.wczytajLiczbe(skaner, 1, 6);
        if (zmianaCeny == 1){
            System.out.println("Jaką nową cenę wejścia dla studenta chcesz ustawić?");
            Cennik.setCenaStudent(Scianka.pobierzLiczbe(skaner));
            System.out.println("Nowa cena wejścia dla studenta to: " + Cennik.getCenaStudent() + "\n");
        } else if (zmianaCeny == 2){
            System.out.println("Jaką nową cenę wejścia dla dorosłego chcesz ustawić?");
            Cennik.setCenaDorosly(Scianka.pobierzLiczbe(skaner));
            System.out.println("Nowa cena wejścia dla studenta to: " + Cennik.getCenaDorosly() + "\n");
        } else if (zmianaCeny == 3){
            System.out.println("Jaką nową cenę wejścia dla dziecka chcesz ustawić?");
            Cennik.setCenaDziecko(Scianka.pobierzLiczbe(skaner));
            System.out.println("Nowa cena wejścia dla studenta to: " + Cennik.getCenaDziecko() + "\n");
        } else if (zmianaCeny == 4){
            System.out.println("Jaką nową cenę wejścia dla osoby z kartą sportową chcesz ustawić?");
            Cennik.setCenaKartaSportowa(Scianka.pobierzLiczbe(skaner));
            System.out.println("Nowa cena wejścia dla osoby z kartą sportową to: " + Cennik.getCenaKartaSportowa() + "\n");
        } else if (zmianaCeny == 5){
            System.out.println("Jaką nową cenę wypożyczenia butów chcesz ustawić?");
            Cennik.setCenaButow(Scianka.pobierzLiczbe(skaner));
            System.out.println("Nowa cena wypożyczenia butów to: " + Cennik.getCenaButow() + "\n");
        } else if (zmianaCeny == 6){
            System.out.println("Jaką nową cenę wypożyczenia uprzęży chcesz ustawić?");
            Cennik.setCenaUprzaz(Scianka.pobierzLiczbe(skaner));
            System.out.println("Nowa cena wypożyczenia butów to: " + Cennik.getCenaUprzaz() + "\n");
        }
    }
    
}
