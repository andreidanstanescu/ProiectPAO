package biblioteca;

import java.util.*;

public class Serviciu {
    //de obicei dorim sectiunile din biblioteca sortate alfabetic
    public TreeSet<Sectiune> s = new TreeSet<Sectiune>(new LexComp());

    public void addSectiune(String nume){
        Sectiune aux = new Sectiune();
        aux.setNume(nume);
        s.add(aux);
    }

    public Sectiune searchSectiune(String nume) throws MyException{
        Sectiune lower_bound = new Sectiune();
        lower_bound.setNume(nume);
        Sectiune ceil = s.ceiling(lower_bound);
        if(ceil.getNume().equals(nume))
            return ceil;
        throw new MyException("nu exista sectiunea");
    }

    public void golesteSectiune(String nume) throws MyException{
        //searchSectiune(nume).sterge();
        s.remove(searchSectiune(nume));
    }




}

class LexComp implements Comparator<Sectiune>{

    @Override
    public int compare(Sectiune s1, Sectiune s2) {
        return s1.getNume().compareTo(s2.getNume());
    }

}
