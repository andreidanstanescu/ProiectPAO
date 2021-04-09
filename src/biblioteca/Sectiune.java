package biblioteca;

import java.util.*;

import static java.lang.Math.max;

public class Sectiune implements Comparable<Sectiune>{
    private String nume;
    private TreeMap<Autor,TreeSet<String> > carti = new TreeMap<Autor,TreeSet<String> >();
    private HashMap<String,Integer> gen = new HashMap<String,Integer>();
    private HashSet<Carte> cc = new HashSet<Carte>();
    private HashSet<Cititor> popularitate= new HashSet<Cititor>();

    public HashSet<Carte> getTotCarti(){
        return cc;
    }

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

    public HashSet<Cititor> getPopularitate(){
        return popularitate;
    }

    public void sterge() {
        carti.clear();
        gen.clear();
        cc.clear();
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

        //System.out.println(nou);
        cc.add(nou);
    }

    public void remove(Carte sters) {
        carti.get(sters.getAutor()).remove(sters.getTitlu());
        gen.put(sters.getType(), gen.get(sters.getType()) - 1);
        cc.remove(sters);
    }

    //thread-safe
    public synchronized void imprumuta(Carte c, Cititor p){
        System.out.println("Contine cartea: " + contineCarte(c));
        for(Carte c1: cc)
            if(c1.equals(c)) {
                if (!c1.getImprumutata() && contineCarte(c)) {
                    c1.setImprumutata(true);
                    popularitate.add(p);
                    c1.getCititori().add(p);
                } else
                    System.out.println("Cartea este deja imprumutata de catre altcineva!");
            }
    }

    //thread-safe
    public synchronized void aduceInapoi(Carte c){
        for(Carte c1: cc)
            if(c1.equals(c))
                c1.setImprumutata(false);
    }

    public int getTotalCititori(){
        return popularitate.size();
    }

    public Integer getTotalCarti() {
        Integer sum = 0;
        for(Autor a: carti.keySet())
            sum += carti.get(a).size();
        return sum;
    }

    public boolean contineAutor(Autor waldo) {
        return carti.containsKey(waldo);
    }

    public String mostAutor(){
        Integer count = -1;
        Autor popular = new Autor();
        for(Autor a: carti.keySet()) {
            if(count < carti.get(a).size()){
                count = carti.get(a).size();
                popular = a;
            }
        }
        return popular.toString();
    }

    public boolean contineCarte(Carte waldo) {
        if(!carti.containsKey(waldo.getAutor()))
            return false;
        else
            return carti.get(waldo.getAutor()).contains(waldo.getTitlu());
        //worst case O(log^2(n))
    }

    public String topType(){
        String ans ="Sectiune goala !";
        int count = -1;
        for(String tip: gen.keySet())
            if(count < gen.get(tip))
                ans = tip;
        return ans;
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
