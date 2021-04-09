package biblioteca;

public final class Autor extends Person implements Comparable<Autor> {

    public Autor(){
        super();
    }

    public Autor(String nume, String prenume){
        super(nume, prenume);
    }

    @Override
    public boolean equals(Object other1){
        if (other1.getClass() != this.getClass()) {
            return false;
        }
        final Autor other = (Autor)other1;
        return this.getNume().equals(other.getNume()) && this.getPrenume().equals(other.getPrenume()) ;
        //return true;
    }

    @Override
    public int compareTo(Autor other){
        if(this.getNume().equals(other.getNume()))
            return this.getPrenume().compareTo(other.getPrenume());
        else
            return this.getNume().compareTo(other.getNume());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
