package biblioteca;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Carte {
    private static long id;
    private String titlu;
    private Autor autor;
    protected String sectiune;
    private ArrayList<Cititor> cititori;
    private boolean imprumutata;
    private boolean restricted = false;

    abstract String getType();

    Carte() {
        cititori = new ArrayList<Cititor>();
        imprumutata = false;
        this.id = generateID();
    }
    
    abstract int getValoare();

    public static synchronized long generateID()
    {
        return (id++);
    }

    public void setRestrictie(){
        restricted = true;
    }

    public boolean getRestricted(){
        return restricted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutor(Autor a) {
        this.autor = a;
    }

    public void setTitlu(String t) {
        this.titlu = t;
    }

    public void setSectiune(String s) {
        this.sectiune = s;
    }

    public void addCititor(Cititor c) {
        try{
            if(!imprumutata) {
                this.cititori.add(c);
                c.addCarte(this);
            }
            else throw new MyException("Este deja imprumutata !");
        }
        catch (MyException e){
            e.printStackTrace();
        }

    }

    //ma asigur oricum cand o caut daca e sau nu in sectiune
    public void setImprumutata(boolean borrowed) {
        imprumutata = borrowed;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getSectiune() {
        return sectiune;
    }

    public String getTitlu() {
        return titlu;
    }

    public ArrayList<Cititor> getCititori () {
        return cititori;
    }

    public boolean getImprumutata() {
        return imprumutata;
    }

    public void sortbyNume () {
        Collections.sort(cititori, new Comparator<Cititor>() {
            @Override
            public int compare(Cititor c1, Cititor c2)
            {
                return c1.getNume().compareTo(c2.getNume());
            }
        });
    }

    @Override
    public boolean equals(Object other1){
        if (other1.getClass() != this.getClass()) {
            return false;
        }
        final Carte other = (Carte)other1;
        /*System.out.println(this.titlu);
        System.out.println(this.autor);
        System.out.println(this.sectiune);
        System.out.println(other.titlu);
        System.out.println(other.autor);
        System.out.println(other.sectiune);
         */
        return (this.titlu.equals(other.titlu) && this.autor.equals(other.autor) && this.sectiune.equals(other.sectiune));
        //return true;
    }

    @Override
    public String toString() {
        return String.format("titlu: " + titlu + " autor: " + autor.toString());
    }

}
