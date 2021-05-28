package biblioteca;

import java.sql.SQLException;
import java.util.*;

public class Serviciu {
    //de obicei dorim sectiunile din biblioteca sortate alfabetic
    private TreeSet<Sectiune> s = new TreeSet<Sectiune>(new LexComp());
    MySQL db = MySQL.getInstanta();

    public void addSectiune(String nume){
        if(existaSectiune(nume))
            return;
        Sectiune aux = new Sectiune();
        aux.setNume(nume);
        s.add(aux);
    }

    public void redenumesteSectiune(String nume, String alt_nume){
        try{
            cautaSectiune(nume).setNume(alt_nume);
        }
        catch(MyException e){
            addSectiune(alt_nume);
        }
    }

    //adaug o carte noua intr-o anumita sectiune, al carei nume este dat ca parametru
    public void addCarte(String nume) throws MyException {
        CarteFactory demo = new CarteFactory();
        Scanner fin = new Scanner(System.in);
        System.out.println("Introdu tipul cartii: ");
        String str = fin.nextLine();
        Carte carte = demo.getCarte(str);

        System.out.println("Introdu titlul cartii: ");
        str = fin.nextLine();
        carte.setTitlu(str);

        System.out.println("Introdu numele autorului: ");
        str = fin.nextLine();
        System.out.println("Introdu prenumele autorului: ");
        String str1 = fin.nextLine();
        Autor a = new Autor(str,str1);
        carte.setAutor(a);

        carte.setSectiune(nume);

        if(carte.getType() == "Roman") {
            try {
                db.addRoman(carte);
            } catch (SQLException e){
                System.out.println("exista deja cartea");
            }
        }
        cautaSectiune(nume).add(carte);
        System.out.println("Carte adaugata cu succes");
    }

    public void addCarte(String nume, Carte c) throws MyException {
        cautaSectiune(nume).add(c);
    }

    //sectiunea cu cele mai multe carti
    public String mostBooks(){
        Sectiune ans = s.stream().max(Comparator.comparing(i -> i.getTotalCarti())).get();
        return ans.getNume();
    }

    public Integer totalCarti(){
        Integer sum = 0;
        for(Sectiune i: s)
            sum += i.getTotalCarti();
        return sum;
    }

    //sectiunea cu cele mai multi cititori
    public String coolest(){
        int aux = -1;
        String ans = "";
        for(Sectiune i: s){
            if(aux < i.getTotalCititori()){
                ans = i.getNume();
                aux = i.getTotalCititori();
            }
        }
        return ans;
    }

    public Sectiune cautaSectiune(String nume) throws MyException{
        Sectiune lower_bound = new Sectiune();
        lower_bound.setNume(nume);
        Sectiune ceil = s.ceiling(lower_bound);
        if(ceil.getNume().equals(nume))
            return ceil;
        throw new MyException("nu exista sectiunea");
    }

    public boolean existaSectiune(String nume) {
        Sectiune lower_bound = new Sectiune();
        lower_bound.setNume(nume);
        Sectiune ceil = s.ceiling(lower_bound);
        try {
            if (ceil.getNume().equals(nume))
                return true;
        }
        catch(NullPointerException e)
        {
            return false;
        }
        return false;
    }

    public boolean contineCarte(Carte c){
        try{
            Sectiune caut = cautaSectiune(c.sectiune);
            return caut.contineCarte(c);
        }
        catch(MyException e){
            return false;
        }
    }

    public void golesteSectiune(String nume) throws MyException{
        cautaSectiune(nume).sterge();
    }

    public void stergeSectiune(String nume) throws MyException{
        s.remove(cautaSectiune(nume));
    }

    public void afiseazaSectiuni(){
        //sunt deja sortate dupa nume
        for(Sectiune i: s)
            System.out.println(i.getNume());
    }

    //media varstei de pe un anumit raft
    public float medie(String nume) throws MyException {
        float ans = 0;
        for(Cititor cc: cautaSectiune(nume).getPopularitate())
            ans += cc.getVarsta();
        return ans/cautaSectiune(nume).getPopularitate().size();
    }

    //cel mai comun tip de carte dintr-o sectiune
    //cu nume dat ca parametru
    public String topType(String nume) throws MyException {
        return cautaSectiune(nume).topType();
    }

    public synchronized void imprumuta(String nume, Carte c, Cititor p) throws MyException {
        Sectiune aux = cautaSectiune(nume);

        /*Carte asta = new Manga();
        for(Carte c1: aux.getTotCarti()) {
            System.out.println(c1.equals(c));
        }*/

        boolean flag = false;
        for(Carte c1: aux.getTotCarti())
            if(c1.equals(c)) {
                flag = true;
                //System.out.println(c1);
                //System.out.println(aux.contineCarte(c));
                if(!c1.getImprumutata() && aux.contineCarte(c) ) {
                    c1.setImprumutata(true);
                    aux.getPopularitate().add(p);
                    c1.getCititori().add(p);
                    System.out.println("Cartea a fost imprumutata!");
                }
                else
                    System.out.println("Cartea este deja imprumutata de catre altcineva!");
            }
        if(!flag)
            System.out.println("Nu avem cartea!");
    }

    public synchronized void aduceInapoi(String nume, Carte c) throws MyException {
        Sectiune aux = cautaSectiune(nume);
        for(Carte c1: aux.getTotCarti())
            if(c1.equals(c))
                c1.setImprumutata(false);
    }

    public void afisareCarti(String nume) throws MyException {
        Sectiune aux = cautaSectiune(nume);
        for(Carte c1: aux.getTotCarti())
            System.out.println(c1.toString());
    }

    public void afisareCititori(String nume) throws MyException {
        Sectiune aux = cautaSectiune(nume);
        for(Cititor c: aux.getPopularitate())
            System.out.println(c.toString());
    }

    //total: 17 operatii

}

class LexComp implements Comparator<Sectiune>{

    @Override
    public int compare(Sectiune s1, Sectiune s2) {
        return s1.getNume().compareTo(s2.getNume());
    }

}
