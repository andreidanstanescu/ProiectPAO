package biblioteca;

public final class Autor implements Comparable<Autor>{
    private String nume,prenume;

    public Autor(){

    }

    public Autor(String nume, String prenume){
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
    public int compareTo(Autor other){
        if(this.nume.equals(other.nume))
            return this.prenume.compareTo(other.prenume);
        else
            return this.nume.compareTo(other.nume);
    }
}
