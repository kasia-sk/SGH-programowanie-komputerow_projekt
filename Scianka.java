import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scianka {
    static List<Klient> listaKlientow = new ArrayList<>();

    static int wczytajLiczbe(Scanner skaner, int min, int max){
        while(true){
            System.out.println("Podaj liczbę od " + min + " do " + max + ":");
            while(!skaner.hasNextInt()){
                System.out.println("Podaj liczbę:");
                skaner.next();
            }

            int wybor = skaner.nextInt();
    
            if (wybor >= min && wybor <= max)
                return wybor;
        }
    }

    static int pobierzLiczbe(Scanner skaner){
        int pobranaLiczba = 0;
        while(pobranaLiczba <= 0){
            System.out.println("Podaj liczbę:");
            while(!skaner.hasNextInt()){
                System.out.println("Podaj poprawną liczbę:");
                skaner.next();
            }
            pobranaLiczba = skaner.nextInt();
            if (pobranaLiczba <= 0)
                System.out.println("Liczba musi być większa od 0.");
        }
        return pobranaLiczba;
    }

    static Klient wyszukajKlienta(String szukaneImie, String szukaneNazwisko){
        for(Klient klient: listaKlientow){
            String aktualneImie = klient.getImie();
            String aktualneNazwisko = klient.getNazwisko();
            if (aktualneImie.equals(szukaneImie) && aktualneNazwisko.equals(szukaneNazwisko))
                return klient;
        }
        return null;
    }

        static void dodawanieKlienta(Scanner skaner){
        System.out.println("Ile osób chcesz dodać?");
        int ileOsob = pobierzLiczbe(skaner);
        
        try{
            BufferedWriter bufer = new BufferedWriter(new FileWriter("klienciLista.txt", true));
            for(int i = 0; i < ileOsob; i++){
                System.out.println("Podaj imię:");
                String noweImie = skaner.next();
                System.out.println("Podaj nazwisko:");
                String noweNazwisko = skaner.next();

                System.out.println("Podaj numer telefonu:");
                String nowyNumer = skaner.next();

                while(nowyNumer.length() < 9 || !nowyNumer.matches("^\\d{9,}$")){
                    System.out.println("Podaj poprawny numer telefonu (np. 123456789):");
                    nowyNumer = skaner.next();
                }
                
                System.out.println("Podaj adres e-mail:");
                String nowyEmail = skaner.next();

                while(!nowyEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
                    System.out.println("Podaj poprawny adres email (np. jan.kowalski@gmail.com):");
                    nowyEmail = skaner.next();
                }

                System.out.println("Podaj typ klienta - wybierz odpowiedni numer\n" +
                    " 1 - Dziecko (<12 r.ż.)\n" +
                    " 2 - Student (<26 r.ż.)\n" +
                    " 3 - Dorosły \n" +
                    " 4 - Klient posiadający kartę sportową (Multisport, Medicover, BeActive)");
                
                int typWejsciowki = wczytajLiczbe(skaner, 1, 4);
                String typWejscia = "Dorosły";
                if (typWejsciowki == 1)
                    typWejscia = "Dziecko";
                else if (typWejsciowki == 2)
                    typWejscia = "Student";
                else if (typWejsciowki == 4)
                    typWejscia = "Karta_sportowa";

                Klient nowyKlient = new Klient(noweImie, noweNazwisko, nowyNumer, nowyEmail, typWejscia);
                listaKlientow.add(nowyKlient);
                String liniaDoPliku = "\r\n" + noweImie + " " + noweNazwisko + " " + nowyNumer + " " + nowyEmail + " " + typWejscia;
                bufer.write(liniaDoPliku);
            }
            bufer.close();
        }catch(IOException e){
            System.out.println("Nie można zapisać do pliku klienciLista.txt!");
        }
    }

    static void pokazanieListyKlientow(){
        for(Klient klient : listaKlientow){
            System.out.println(klient.getImie() + " " + klient.getNazwisko() +
                " " + klient.getTelefon() + " " + klient.getEmail() +
                " " + klient.getTypWejscia() + 
                " cena wejścia: " + klient.getCenaWejscia() + " zł");
        }
    }

    static void sprzedazWejscia(Scanner skaner){
        System.out.println("Ilu klientom chcesz sprzedać wejściówkę?");

        try{
            BufferedWriter bufer = new BufferedWriter(new FileWriter("historiaSprzedazy.txt", true));
            int liczbaKlientow = pobierzLiczbe(skaner);
            int sumaKwotySprzedazy = 0;
            String liniaDoPliku = "";
            for(int j = 1; j <= liczbaKlientow; j++)
            {
                System.out.println("Podaj imię klienta nr " + j + ":");
                String szukaneImieKlienta = skaner.next();
                System.out.println("Podaj nazwisko klienta nr " + j + ":");
                String szukaneNazwiskoKlienta = skaner.next();
                Klient wyszukanyKlient = wyszukajKlienta(szukaneImieKlienta, szukaneNazwiskoKlienta);

                while(wyszukanyKlient == null){
                    System.out.println("Nie ma takiej osoby");
                    System.out.println("Podaj imię klienta nr " + j + ":");
                    szukaneImieKlienta = skaner.next();
                    System.out.println("Podaj nazwisko klienta nr " + j + ":");
                    szukaneNazwiskoKlienta = skaner.next();
                    wyszukanyKlient = wyszukajKlienta(szukaneImieKlienta, szukaneNazwiskoKlienta);
                }

                liniaDoPliku += szukaneImieKlienta + " " + szukaneNazwiskoKlienta;
                if(j != liczbaKlientow)
                    liniaDoPliku += ", ";
                sumaKwotySprzedazy += wyszukanyKlient.getCenaWejscia();
            }
            liniaDoPliku += ": ";

            System.out.println("Czy wypożyczenie sprzętu?\n" +
                "1 - tak\n" +
                "2 - nie");
            int opcja = wczytajLiczbe(skaner, 1, 2);
            if(opcja == 1){
                System.out.println("Ile par butów?");
                int liczbaParButow = wczytajLiczbe(skaner, 0, 100);
                if(liczbaParButow > 0)
                    liniaDoPliku += liczbaParButow + " x buty, ";
                
                System.out.println("Ile uprzęży?");

                int liczbaUprzezy = wczytajLiczbe(skaner, 0, 100);
                if(liczbaUprzezy > 0)
                    liniaDoPliku += liczbaUprzezy + " x uprzaz, ";

                if(liczbaParButow != 0 && liczbaUprzezy != 0){
                    sumaKwotySprzedazy += liczbaUprzezy * Cennik.getCenaUprzaz() + liczbaParButow * Cennik.getCenaButow();
                    System.out.println("Kwota do zapłaty przez klienta to: " + sumaKwotySprzedazy + " zł\n");
                } else if(liczbaParButow == 0){
                    sumaKwotySprzedazy += liczbaUprzezy * Cennik.getCenaUprzaz();
                    System.out.println("Kwota do zapłaty przez klienta to: " + sumaKwotySprzedazy + " zł\n");
                } else if(liczbaUprzezy == 0){
                    sumaKwotySprzedazy += liczbaParButow * Cennik.getCenaButow();
                    System.out.println("Kwota do zapłaty przez klienta to: " + sumaKwotySprzedazy + " zł\n");
                }
            } else {
                System.out.println("Kwota do zapłaty przez klienta to: " + sumaKwotySprzedazy + " zł\n");
            }
            liniaDoPliku += "suma: " + sumaKwotySprzedazy + "PLN\r\n";
            bufer.write(liniaDoPliku);
            bufer.close();
        }catch(IOException e){
            System.out.println("Nie można zapisać do pliku historiaSprzedazy.txt!");
        }
    }

    static void zmianaDanychKlienta(Scanner skaner){
        System.out.println("Podaj imię klienta, dla którego chcesz zmienić dane:");
        String szukaneImieKlienta = skaner.next();
        System.out.println("Podaj nazwisko klienta, dla którego chcesz zmienić dane:");
        String szukaneNazwiskoKlienta = skaner.next();
        Klient wyszukiwanyKlient = wyszukajKlienta(szukaneImieKlienta, szukaneNazwiskoKlienta);

        if(wyszukiwanyKlient == null){
            System.out.println("Nie ma takiego klienta");
        } else {
            System.out.println("Co chcesz zmienić dla klienta: " + wyszukiwanyKlient.getImie() + " " + wyszukiwanyKlient.getNazwisko());
            int zmianaDanychKlienta = 0;
            while(zmianaDanychKlienta != 6){
                System.out.println("Wybierz jedną z następujących opcji:\n" +
                    "1 - Zmiana imienia\n" +
                    "2 - Zmiana nazwiska\n" +
                    "3 - Zmiana numeru telefonu\n" +
                    "4 - Zmiana adresu e-mail\n" +
                    "5 - Zmiana typu wejścia\n" +
                    "6 - Zakończ proces zmian");
                zmianaDanychKlienta = wczytajLiczbe(skaner, 1, 6);

                if (zmianaDanychKlienta == 1){
                    System.out.println("Podaj nowe imię:");
                    wyszukiwanyKlient.setImie(skaner.next());
                } else if (zmianaDanychKlienta == 2){
                    System.out.println("Podaj nowe nazwisko:");
                    wyszukiwanyKlient.setNazwisko(skaner.next());
                } else if (zmianaDanychKlienta == 3){
                    System.out.println("Podaj nowy numer telefonu:");

                    String nowyNumer = skaner.next();

                    while(nowyNumer.length() < 9 || !nowyNumer.matches("^\\d{9,}$")){
                        System.out.println("Podaj poprawny numer telefonu (np. 123456789)");
                        nowyNumer = skaner.next();     
                    }

                    wyszukiwanyKlient.setTelefon(nowyNumer);

                } else if (zmianaDanychKlienta == 4){
                    System.out.println("Podaj nowy adres e-mail:");
                    String nowyEmail = skaner.next();

                    while(!nowyEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
                        System.out.println("Podaj poprawny adres email (np. jan.kowalski@gmail.com)");
                        nowyEmail = skaner.next();     
                    }

                    wyszukiwanyKlient.setEmail(nowyEmail);

                } else if (zmianaDanychKlienta == 5){
                    System.out.println("Podaj nowy typ wejścia (wybierz opcję wpisując odpowiedni numer\n" +
                        "1 - Student\n" +
                        "2 - Dorosły\n" +
                        "3 - Karta sportowa\n" +
                        "4 - Dziecko");
                    int typKlienta = wczytajLiczbe(skaner, 1, 4);
                    switch(typKlienta){
                        case 1: 
                            wyszukiwanyKlient.setTypWejscia("Student");
                            break;
                        case 2:
                            wyszukiwanyKlient.setTypWejscia("Dorosły");
                            break;
                        case 3:
                            wyszukiwanyKlient.setTypWejscia("Karta_sportowa");
                            break;
                        case 4: 
                            wyszukiwanyKlient.setTypWejscia("Dziecko");
                            break;
                    }
                }
            }
        }
    }

    static void obslugaKlienta(Scanner skaner){
        System.out.println("Co chcesz zrobić?\n" +
            "1 - Dodaj klienta\n" +
            "2 - Pokaż klientów\n" +
            "3 - Sprzedaj wejściówkę\n" +
            "4 - Zmień dane klienta\n" +
            "5 - Wróć do menu głównego");
                
        int wyborPodkategorii = wczytajLiczbe(skaner, 1, 5);
                   
        if (wyborPodkategorii == 1)
            dodawanieKlienta(skaner);
        else if (wyborPodkategorii == 2)
            pokazanieListyKlientow();
        else if(wyborPodkategorii == 3)
            sprzedazWejscia(skaner);  
        else if (wyborPodkategorii == 4)
            zmianaDanychKlienta(skaner);
    }

    public static void main(String[] args) {
        if (args.length > 0){
            String nazwaPliku = args[0];
            try {
                Path sciezka = Paths.get(nazwaPliku);
                BufferedReader bufor = Files.newBufferedReader(sciezka, StandardCharsets.UTF_8);
                String linia;

                while ((linia = bufor.readLine()) != null) {
                    String[] pola = linia.split(" ");

                    if (pola.length == 5) {
                        String imie = pola[0];
                        String nazwisko = pola[1];
                        String telefon = pola[2];
                        String email = pola[3];
                        String typWejscia = pola[4];
                        Klient klient = new Klient(imie, nazwisko, telefon, email, typWejscia);
                        listaKlientow.add(klient);
                    } else {
                        System.out.println("Nieprawidłowy format danych w linii: " + linia);
                    }
                }
            } catch (NoSuchFileException e) {
                System.out.println("Nie znaleziono pliku \"" + nazwaPliku + "\"");
                return;
            } catch (IOException e) {
                System.out.println("Błąd przy wczytywaniu pliku \"" + nazwaPliku + "\"");
                return;
            }
        }
        
        Scanner skaner = new Scanner(System.in, "cp852"); // cp852 - standardowe kodowanie konsoli Windows               
        int wybor = 0;
        while(wybor != 3){
            System.out.println("Co chcesz zrobić? - wybierz odpowiedni numer \n" +
                "1 - Obsługa klienta\n" +
                "2 - Cennik\n" +
                "3 - Zakończ działanie programu");
            
            wybor = wczytajLiczbe(skaner, 1, 3);
            
            if (wybor == 1){
                obslugaKlienta(skaner);
            } else if(wybor == 2){          
                System.out.println("Co chcesz zrobić? \n" +
                    "1 - Pokaż cennik\n" +
                    "2 - Zmień cennik\n" +
                    "3 - Wróć do menu głównego");   
                int wyborPodkategorii = wczytajLiczbe(skaner, 1, 3);
                if (wyborPodkategorii == 1)
                    Cennik.printCennik();
                else if (wyborPodkategorii == 2)
                    Cennik.zmienCennik(skaner);
            }
        }
        skaner.close();
    }
}
