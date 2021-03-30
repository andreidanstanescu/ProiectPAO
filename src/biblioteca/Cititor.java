package biblioteca;

public class Cititor {
    private int id;
    private String nume,prenume;

    Cititor(int id, String nume, String prenume){
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getNume() {
        return this.nume;
    }
}
