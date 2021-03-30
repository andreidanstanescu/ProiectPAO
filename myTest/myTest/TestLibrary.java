package myTest;

import biblioteca.Autor;
import biblioteca.Carte;
import biblioteca.CarteFactory;
import biblioteca.Sectiune;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLibrary {
    @Test
    public void testLibrary() throws Exception{
        Sectiune s = new Sectiune();
        s.setNume("Slice of Life");
        CarteFactory aux= new CarteFactory();
        Carte test1 = aux.getCarte("manga");
        test1.setTitlu("Just Because!");
        Autor a = new Autor("Ishigura","Rei");
        test1.setAutor(a);
        aux= new CarteFactory();
        Carte test2 = aux.getCarte("manga");
        test2.setTitlu("Highschool of the Dead");
        a = new Autor( "Daisuke","Satō");
        test2.setAutor(a);
        aux= new CarteFactory();
        Carte test3 = aux.getCarte("manga");
        test3.setTitlu("The hidden dungeon only I can enter");
        a = new Autor("Meguru","Seto");
        test3.setAutor(a);
        s.add(test1);
        s.add(test2);
        s.add(test3);
        Assertions.assertEquals(3,s.getTotalCarti());
    }

}
