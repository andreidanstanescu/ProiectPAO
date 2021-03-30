package biblioteca;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Carte {
    private int id;
    private String titlu;
    private Autor autor;
    protected Sectiune sectiune;
    private final ArrayList<Cititor> cititori;
    private boolean imprumutata;

    abstract String getType();

    Carte() {
        cititori = new ArrayList<Cititor>();
        imprumutata = false;
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

    public void setSectiune(Sectiune s) {
        this.sectiune = s;
    }

    public void addCititor(Cititor c) {
        try{
            if(!imprumutata)
                this.cititori.add(c);
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
    public String toString() {
        return String.format("titlu: " + titlu + "autor: " + autor);
    }

}
