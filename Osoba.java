class Osoba{
	private String imie;
	private String nazwisko;
	private String telefon;
	private String email;
	
	Osoba(String imie, String nazwisko, String telefon, String email){
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.telefon = telefon;
		this.email = email;
	}

	String getImie(){
		return imie;
	}

	String getNazwisko(){
		return nazwisko;
	}

	String getTelefon(){
		return telefon;
	}

	String getEmail(){
		return email;
	}

	void setImie(String imie){
		this.imie = imie;
	}

	void setNazwisko(String nazwisko){
		this.nazwisko = nazwisko;
	}

	void setTelefon(String telefon){
		this.telefon = telefon;
	}

	void setEmail(String email){
		this.email = email;
	}
}