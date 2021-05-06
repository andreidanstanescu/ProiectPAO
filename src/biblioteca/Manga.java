package biblioteca;

public class Manga extends Carte{
    private final String status;
    private Autor artist;
    private boolean areAnime;

    Manga(){
        super();
        status = "weeb";
        artist = new Autor();
        areAnime = false;
    }

    public Autor getArtist(){
        return artist;
    }

    public void setareAnime(){
        CSV csv = CSV.getInstanta();
        CSV.scrieManga(this);
        areAnime = true;
    }

    public boolean getareAnime() { return areAnime; }

    public void setArtist(Autor a){
        artist = a;
    }

    @Override
    public String getType(){
        return "Manga";
    }

    @Override
    int getValoare(){
        return 100;
    }

    @Override
    public String toString() {
        return super.toString() + getType();
    }

}
