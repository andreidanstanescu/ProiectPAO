package biblioteca;

import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class CSV {

    private static CSV instanta = null;

    private CSV() {
    }

    public static CSV getInstanta()
    {
        if (instanta == null)
        {
            instanta = new CSV();
        }
        return instanta;
    }

    public static ArrayList<Autor> citireAutor() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(".idea/autor.csv"));
        String linie;
        ArrayList<Autor> rez = new ArrayList<Autor>();
        while((linie = br.readLine()) != null){
            String[] sc = linie.split(",");
            String nume = sc[0];
            String prenume = sc[1];
            Autor a = new Autor(nume, prenume);
            rez.add(a);
            //System.out.println(sc.next());
        }
        br.close();
        return rez;
    }

    public static ArrayList<Cititor> citireCititor() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(".idea/cititor.csv"));
        String linie;
        ArrayList<Cititor> rez = new ArrayList<Cititor>();
        while((linie = br.readLine()) != null){
            String[] sc = linie.split(",");
            String nume = sc[0];
            String prenume = sc[1];
            String varsta = sc[2];
            Cititor c = new Cititor(nume, prenume, Integer.parseInt(varsta));
            rez.add(c);
        }
        br.close();
        return rez;
    }

    public static ArrayList<Carte> citireRoman() throws Exception{
        //Scanner sc = new Scanner(new FileReader(".idea/roman.csv"));
        BufferedReader br = new BufferedReader(new FileReader(".idea/roman.csv"));
        String linie;
        ArrayList<Carte> rez = new ArrayList<Carte>();
        while((linie = br.readLine()) != null){
            String[] sc = linie.split(",");
            Roman c = new Roman();
            String titlu = sc[0];
            //System.out.println(titlu);
            c.setTitlu(titlu);
            String sectiune = sc[1];
            c.setSectiune(sectiune);
            String[] nume = sc[2].split(" ");
            //System.out.println(nume[1]);
            Autor aux = new Autor(nume[0], nume[1]);
            c.setAutor(aux);
            int nr = Integer.parseInt(sc[3]);
            c.setNrPagini(nr);
            String adapt = sc[4];
            //System.out.println(adapt);
            switch (adapt){
                case "true" -> {
                    c.setAdaptare();
                }
            }
            rez.add(c);
            //System.out.println(sc.next());
        }
        br.close();
        return rez;
    }

    public static ArrayList<Carte> citireManga() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(".idea/manga.csv"));
        String linie;
        ArrayList<Carte> rez = new ArrayList<Carte>();
        while((linie = br.readLine()) != null){
            String[] sc = linie.split(",");
            Manga c = new Manga();
            String titlu = sc[0];
            String sectiune = sc[1];
            String[] nume = sc[2].split(" ");
            Autor aux = new Autor(nume[0], nume[1]);
            c.setAutor(aux);
            c.setTitlu(titlu);
            c.setSectiune(sectiune);
            Boolean ad = Boolean.getBoolean(sc[3]);
            if(ad)
                c.setareAnime();
            rez.add(c);
        }
        br.close();
        return rez;
    }

    public static void scrieAutor(Autor a) throws Exception{
        FileWriter writer = new FileWriter(".idea/autor.csv", true);
        String s = new String();
        s += a.getNume();
        s += ',';
        s += a.getPrenume();
        s += '\n';
        //System.out.println(s);
        writer.append(s);
        writer.close();
    }

    public static void scrieCititor(Cititor c) throws Exception{
        FileWriter writer = new FileWriter(".idea/cititor.csv", true);
        String s = new String();
        s += c.getNume();
        s += ',';
        s += c.getPrenume();
        s += ',';
        s += c.getVarsta();
        s += '\n';
        //System.out.println(s);
        writer.append(s);
        writer.close();
    }

    public static void scrieRoman(Roman r) throws Exception{
        FileWriter writer = new FileWriter(".idea/roman.csv", true);
        String s = new String();
        s += r.getTitlu();
        s += ',';
        s += r.getSectiune();
        s += ',';
        s += r.getAutor();
        s += ',';
        s += r.getNrPagini();
        s += ',';
        s += r.getAdaptare();
        s += '\n';
        //System.out.println(s);
        writer.append(s);
        writer.close();
    }

    public static void scrieManga(Manga m) {
        try{
            FileWriter writer = new FileWriter(".idea/manga.csv", true);
            String s = new String();
            s += m.getTitlu();
            s += ',';
            s += m.getSectiune();
            s += ',';
            s += m.getAutor();
            s += ',';
            s += m.getareAnime();
            s += '\n';
            //System.out.println(s);
            writer.append(s);
            writer.close();}
        catch(Exception e){
            System.out.println("Nu exista fisierul");
        }
    }

    public static void scrie(String tip) throws Exception{
        FileWriter writer = new FileWriter("audit.csv", true);
        String s = new String();
        s += tip;
        s += ',';
        s += ' ';
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //System.out.println(formatter.format(date));
        s += formatter.format(date);
        s += '\n';
        //System.out.println(s);
        writer.append(s);
        writer.close();
    }

}
