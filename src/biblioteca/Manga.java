package biblioteca;

public class Manga extends Carte{
    private final String status;
    private Autor artist;
    private boolean has_anime;

    Manga(){
        super();
        status = "weeb";
        artist = new Autor();
        has_anime = false;
    }

    public Autor getArtist(){
        return artist;
    }

    public void setHas_anime(){
        has_anime = true;
    }

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
