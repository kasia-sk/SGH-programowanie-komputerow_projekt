class Klient extends Osoba {
    private String typWejscia;
    
    Klient(String imie, String nazwisko, String telefon, String email, String typWejscia){
        super(imie, nazwisko, telefon, email);
        this.typWejscia = typWejscia;
	}

    String getTypWejscia(){
        return typWejscia;
    }

    void setTypWejscia(String typWejscia){
        this.typWejscia = typWejscia;
    }

    int getCenaWejscia(){
        if(this.typWejscia.equals("Student")) 
            return Cennik.getCenaStudent();
        else if (this.typWejscia.equals("Dziecko"))
            return Cennik.getCenaDziecko();
        else if (this.typWejscia.equals("Karta_sportowa"))
            return Cennik.getCenaKartaSportowa();
        return Cennik.getCenaDorosly();
    }
}
