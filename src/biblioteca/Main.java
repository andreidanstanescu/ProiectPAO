package biblioteca;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MyException {
        Serviciu test = new Serviciu();

        //un test de mana
        test.addSectiune("comedie");
        test.addSectiune("drama");
        //test.stergeSectiune("slice of life");
        test.addCarte("comedie");
        Carte c = new Roman();
        c.setTitlu("Razboi si pace");
        c.setSectiune("comedie");
        Autor a1 = new Autor("Tolstoi","Lev");
        c.setAutor(a1);
        Cititor p1 = new Cititor("Azusagawa","Sakuta",18);
        test.imprumuta("comedie",c,p1);
        //test.aduceInapoi("comedie",c);
        Cititor p2 = new Cititor("Azusagawa","Kaede",11);
        test.imprumuta("comedie",c,p2);
        test.afiseazaSectiuni();
        System.out.println(test.mostBooks());
        System.out.println(test.searchSectiune("comedie").getGen());
        System.out.println(test.topType("comedie"));

        //meniul
        Scanner fin = new Scanner(System.in);
        boolean op = true;
        while(op){
            System.out.println("Introdu tipul actiunii:");
            String action = fin.nextLine().toLowerCase(Locale.ROOT);
            switch (action){
                case "adaugare" -> {
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.addSectiune(nume);
                }
                case "adauga carte" -> {
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.addCarte(nume);
                }
                case "total" -> {
                    System.out.println(test.totalCarti());
                }
                case "cele mai multe carti" -> {
                    System.out.println(test.mostBooks());
                }
                case "cei mai multi cititori" -> {
                    System.out.println(test.coolest());
                }
                case "contine cartea" -> {
                    CarteFactory demo = new CarteFactory();

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

                    System.out.println("Introdu numele sectiunii: ");
                    str = fin.nextLine();
                    carte.setSectiune(str);

                    System.out.println(test.contineCarte(carte));
                }
                case "imprumuta cartea" -> {
                    CarteFactory demo = new CarteFactory();

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

                    System.out.println("Introdu numele sectiunii: ");
                    str = fin.nextLine();
                    carte.setSectiune(str);

                    System.out.println("Introdu numele cititorului: ");
                    String nume = fin.nextLine();
                    System.out.println("Introdu prenumele cititorului: ");
                    String prenume = fin.nextLine();
                    System.out.println("Introdu varsta cititorului: ");
                    int varsta = Integer.parseInt(fin.nextLine());
                    //System.out.println(varsta);
                    Cititor p = new Cititor(nume, prenume, varsta);

                    test.imprumuta(str, carte, p);
                }
                case "afiseaza carti" -> {
                    System.out.println("Introdu numele sectiunii: ");
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.afisareCarti(nume);
                }
                case "afiseaza cititori" -> {
                    System.out.println("Introdu numele sectiunii: ");
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.afisareCititori(nume);
                }
                case "afiseaza" -> {
                    test.afiseazaSectiuni();
                }
                case "top titlu" -> {
                    System.out.println("Introdu sectiune\n");
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    System.out.println(test.topType(nume));
                }
                case "exit" -> {
                    op = false;
                }
                default -> System.out.print("\nComanda invalida!.\n");
            }
        }

    }
}
