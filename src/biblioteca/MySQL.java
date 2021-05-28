package biblioteca;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {

    private static Connection dbConnection = null;
    private static MySQL dbInstance = null;

    private MySQL(){
        String url = "jdbc:mysql://127.0.0.1:3306/proiectpao";
        String user = "root";
        String password = "";
        try {
            dbConnection = DriverManager.getConnection(url, user, password);
            //String query = "insert into cititor values ('Sarbu', 'Ioan', 20)";
            //statement.executeUpdate(query);

            //statement.close();
            //connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static MySQL getInstanta()
    {
        if (dbInstance == null)
        {
            dbInstance = new MySQL();
        }
        return dbInstance;
    }


    /*
        Operatii pe tabelul cititor
     */

    public void addCititor(Cititor cc) throws SQLException{
        String query
                = "insert into cititor VALUES (?, ?, ?)";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ps.setString(1, cc.getNume());
        ps.setString(2, cc.getPrenume());
        ps.setInt(3, cc.getVarsta());
        try{
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Cititorul exista deja");
        }
    }

    public void delete(Cititor cc) throws SQLException {
        String query
                = "delete from cititor where nume =? and prenume =?";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ps.setString(1, cc.getNume());
        ps.setString(2, cc.getPrenume());
        try{
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Nu exista cititorul");
        }
    }

    public ArrayList<Cititor> citireCititori()
            throws SQLException
    {
        String query = "select * from cititor";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Cititor> lc = new ArrayList<Cititor>();

        while (rs.next()) {
            String stepNume = rs.getString("nume");
            String stepPrenume = rs.getString("prenume");
            int stepVarsta = rs.getInt("varsta");
            lc.add(new Cititor(stepNume, stepPrenume, stepVarsta));
        }
        return lc;
    }

    public void update(Cititor cc)
            throws SQLException
    {

        String query
                = "update cititor set varsta=varsta+1 where nume=? and prenume=?";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ps.setString(1, cc.getNume());
        ps.setString(2, cc.getPrenume());
        ps.executeUpdate();
    }


    /*
        Sfarsit cititor
     */

    public void addAutor(Autor aa) throws SQLException{
        String query
                = "insert into autor VALUES (?, ?)";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ps.setString(1, aa.getNume());
        ps.setString(2, aa.getPrenume());
        try{
            ps.executeUpdate();
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("se afla deja autorul");
        }
    }

    public ArrayList<Autor> citireAutori()
            throws SQLException
    {
        String query = "select * from autor";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Autor> lc = new ArrayList<Autor>();

        while (rs.next()) {
            String stepNume = rs.getString("nume");
            String stepPrenume = rs.getString("prenume");
            lc.add(new Autor(stepNume, stepPrenume));
        }
        return lc;
    }

    /*
    Sfarsit Autor
     */

    public void addRoman(Carte cc) throws SQLException{
        String query
                = "insert into roman VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ps.setString(1, cc.getTitlu());
        ps.setString(2, cc.getSectiune());
        Autor aux = cc.getAutor();
        ps.setString(3, aux.getNume());
        ps.setString(4, aux.getPrenume());
        ps.setInt(5, ((Roman)cc).getNrPagini());
        ps.setBoolean(6, ((Roman)cc).getAdaptare());
        try{
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Cartea se afla deja in biblioteca");
        }
    }

    public ArrayList<Carte> citireRoman()
            throws SQLException
    {
        String query = "select * from roman";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Carte> lc = new ArrayList<Carte>();

        while (rs.next()) {
            Roman m = new Roman();
            String stepTitlu = rs.getString("titlu");
            m.setTitlu(stepTitlu);
            String stepSectiune = rs.getString("sectiune");
            m.setSectiune(stepSectiune);
            String stepNume= rs.getString("nume");
            String stepPrenume= rs.getString("prenume");
            Autor aux = new Autor(stepNume, stepPrenume);
            m.setAutor(aux);
            int nrPagini = rs.getInt("nrpagini");
            m.setNrPagini(nrPagini);
            boolean stepFilm= rs.getBoolean("film");
            if(stepFilm)
                m.setAdaptare();
            lc.add(m);
        }
        return lc;
    }

    /*
        Sfarsit Roman
     */

    public ArrayList<Carte> citireManga()
            throws SQLException
    {
        String query = "select * from manga";
        PreparedStatement ps
                = dbConnection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Carte> lc = new ArrayList<Carte>();

        while (rs.next()) {
            Manga m = new Manga();
            String stepTitlu = rs.getString("titlu");
            m.setTitlu(stepTitlu);
            String stepSectiune = rs.getString("sectiune");
            m.setSectiune(stepSectiune);
            String stepNume= rs.getString("nume autor");
            String stepPrenume= rs.getString("prenume autor");
            Autor aux = new Autor(stepNume, stepPrenume);
            m.setAutor(aux);
            boolean stepAnime= rs.getBoolean("anime");
            if(stepAnime)
                m.setareAnime();
            lc.add(m);
        }
        return lc;
    }

    /*
        Sfarsit Manga
     */


    /*public static void main(String[] args) throws SQLException {
        MySQL db = MySQL.getInstanta();
        //Cititor p1 = new Cititor("Azusagawa","Sakuta",19);
        //db.addCititor(p1);
        //db.update(p1);
        //db.delete(p1);
        //Autor a = new Autor("Tolstoi", "Lev");
        //db.addAutor(a);
        //ArrayList<Carte> romane = db.citireRoman();
        //for(Carte c: romane)
        //    System.out.println(c.getTitlu());
        ArrayList<Carte> manga = db.citireManga();
        for(Carte c: manga)
            System.out.println(c.getTitlu());
    }*/
}

