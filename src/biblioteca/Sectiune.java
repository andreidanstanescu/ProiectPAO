package biblioteca;

import java.util.*;

public class Sectiune implements Comparable<Sectiune>{
    private String nume;
    private TreeMap<Autor,TreeSet<String> > carti = new TreeMap<Autor,TreeSet<String> >();
    private HashMap<String,Integer> gen = new HashMap<String,Integer>();
    //private HashSet<Carte> cc = new HashSet<Carte>();

    public void setNume(String nume){
        this.nume = nume;
    }

    public String getNume(){
        return nume;
    }

    //cum le tin pe rafturi
    public TreeMap<Autor,TreeSet<String> > getCarti(){
        return carti;
    }

    public HashMap<String,Integer> getGen(){
        return gen;
    }


    public void sterge() {
        carti.clear();
        gen.clear();
    }

    public void add(Carte nou) {

        if(!carti.containsKey(nou.getAutor())){
            carti.put(nou.getAutor(),new TreeSet<String>());
            carti.get(nou.getAutor()).add(nou.getTitlu());
        }
        else{
            carti.get(nou.getAutor()).add(nou.getTitlu());
        }

        Integer freq = 0;
        if(gen.containsKey(nou.getType()))
            freq = gen.get(nou.getType());
        gen.put(nou.getType(), freq + 1);

    }

    public void remove(Carte sters) {
        carti.get(sters.getAutor()).remove(sters.getTitlu());
        gen.put(sters.getType(), gen.get(sters.getType()) - 1);
    }

    public Integer getTotalCarti() {
        Integer sum = 0;
        for(Autor a: carti.keySet())
            sum += carti.get(a).size();
        return sum;
    }

    public boolean contineCarte(Carte waldo) {
        if(!carti.containsKey(waldo.getAutor()))
            return false;
        else
            return carti.get(waldo.getAutor()).contains(waldo.getTitlu());
        //worst case O(log^2(n))
    }

    @Override
    public int compareTo(Sectiune other){
        if(getTotalCarti() == other.getTotalCarti())
            return 0;
        if(getTotalCarti() > other.getTotalCarti())
            return 1;
        else
            return -1;

    }
}
