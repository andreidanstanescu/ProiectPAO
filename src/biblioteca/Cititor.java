package biblioteca;

import java.util.*;

public class Cititor extends Person{
    private static long idCounter = 0;
    private long id;
    private int varsta;
    private int an_inscriere;
    //tip abonament: normal sau pro
    private String abonament;
    private HashSet<Carte> citite = new HashSet<>();

    Cititor(String nume, String prenume, int varsta){
        super(nume, prenume);
        this.id = generateID();
        this.abonament = "normal";
        this.varsta = varsta;
    }

    public void setVarsta(int varsta){
        this.varsta = varsta;
    }

    public static synchronized long generateID()
    {
        return (idCounter++);
    }

    public int getVarsta(){
        return this.varsta;
    }

    public boolean isPro(){
        return abonament.equals("pro");
    }

    public int getAn_inscriere(){
        return an_inscriere;
    }

    public HashSet<Carte> getCitite(){
        return this.citite;
    }

    public void addCarte(Carte nou){
        citite.add(nou);
    }

    @Override
    public String toString() {
        return "Cititor{" +
                "nume='" + getNume()+ '\'' +
                ", prenume='" + getPrenume() + '\'' +
                ", tip abomanet='" + abonament + '\'' +
                ", " + Arrays.toString(citite.toArray()) +
                '}';
    }

}
