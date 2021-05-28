package biblioteca;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        CSV csv = CSV.getInstanta();
        Serviciu test = new Serviciu();
        CSV.scrie("creat clasa serviciu");

        //citesc datele din fisierele csv si le adaug in clasa serviciu(care gestioneaza direct toata biblioteca)

        /*
        ArrayList<Cititor> cititori = CSV.citireCititor();
        ArrayList<Autor> autori = CSV.citireAutor();
         */
        MySQL db = MySQL.getInstanta();
        ArrayList<Cititor> cititori = db.citireCititori();
        ArrayList<Autor> autori = db.citireAutori();


        //ArrayList<Carte> romane = CSV.citireRoman();
        ArrayList<Carte> romane = db.citireRoman();
        for(Carte c: romane){
            c.addCititor(cititori.get(0));
            test.addSectiune(c.sectiune);
            test.addCarte(c.sectiune, c);
        }

        //ArrayList<Carte> manga = CSV.citireManga();
        ArrayList<Carte> manga = db.citireManga();
        for(Carte c: manga) {
            c.addCititor(cititori.get(1));
            test.addSectiune(c.sectiune);
            test.addCarte(c.sectiune, c);
        }

        Cititor c1 = cititori.get(0);
        Carte rp = romane.get(1);
        //System.out.println(rp);
        test.imprumuta("drama", rp, c1);
        //il afiseaza pe c1 si cartile citite
        //(adica cea imprumutata acum + cea adaugata de la inceput din csv)
        test.afisareCititori("drama");
        CSV.scrie("afisat cititorii adaugati din csv");

        test.afiseazaSectiuni();
        CSV.scrie("afisat sectiuni din csv");

        Roman r = new Roman();
        r.setTitlu("IT");
        r.setSectiune("horror");
        Autor a2 = new Autor("King","Stephen");
        //CSV.scrieAutor(a2);
        r.setAutor(a2);
        //CSV.scrieRoman(r);
        //object slicing(in caz ca o apelez)
        db.addRoman(r);
        Carte c2 = r;

        //un test de mana
        test.addSectiune("comedie");
        test.addSectiune("drama");
        //test.stergeSectiune("slice of life");
        test.addCarte("drama");
        Carte c = new Roman();
        c.setTitlu("Anna Karenina");
        c.setSectiune("drama");
        Autor a1 = new Autor("Tolstoi","Lev");
        c.setAutor(a1);
        CSV.scrie("creat carte cu autor");

        Cititor p1 = new Cititor("Azusagawa","Sakuta",18);
        test.imprumuta("drama",c,p1);
        //test.aduceInapoi("drama",c);
        Cititor p2 = new Cititor("Azusagawa","Kaede",11);
        db.addCititor(p2);
        test.imprumuta("drama",c,p2);
        test.afiseazaSectiuni();
        System.out.println(test.mostBooks());
        System.out.println(test.cautaSectiune("drama").getGen());
        System.out.println(test.topType("drama"));

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
                    CSV.scrie("am adaugat o sectiune");
                }
                case "adauga carte" -> {
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.addCarte(nume);
                    CSV.scrie("am adaugat o carte");
                }
                case "total" -> {
                    System.out.println(test.totalCarti());
                    CSV.scrie("am cerut totalul de carti din biblioteca");
                }
                case "cele mai multe carti" -> {
                    System.out.println(test.mostBooks());
                    CSV.scrie("am afisat sectiunea cu cele mai multe carti");
                }
                case "cei mai multi cititori" -> {
                    System.out.println(test.coolest());
                    CSV.scrie("am afisat sectiunea cu cei mai multi cititori");
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
                    CSV.scrie("am verificat daca contine o carte");
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
                    CSV.scrie("am incercat sa imprumut o carte, daca exista");
                }
                case "afiseaza carti" -> {
                    System.out.println("Introdu numele sectiunii: ");
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.afisareCarti(nume);
                    CSV.scrie("am afisat cartile");
                }
                case "afiseaza cititori" -> {
                    System.out.println("Introdu numele sectiunii: ");
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    test.afisareCititori(nume);
                    CSV.scrie("am afisat cititorii");
                }
                case "afiseaza" -> {
                    test.afiseazaSectiuni();
                    CSV.scrie("am afisat sectiunile");
                }
                case "top titlu" -> {
                    System.out.println("Introdu sectiune\n");
                    String nume = fin.nextLine().toLowerCase(Locale.ROOT);
                    System.out.println(test.topType(nume));
                    CSV.scrie("am afisat cel mai cerut titlu");
                }
                case "exit" -> {
                    op = false;
                }
                default -> System.out.print("\nComanda invalida!.\n");
            }
        }

    }
}
