package biblioteca;

public class Person {
    private String nume,prenume;

    public Person(){

    }

    public Person(String nume, String prenume){
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getNume() {
        return this.nume;
    }

    public String getPrenume() {
        return this.prenume;
    }

    @Override
    public String toString() {
        return  "Nume " + nume +
                " prenume " + prenume;
    }

}
