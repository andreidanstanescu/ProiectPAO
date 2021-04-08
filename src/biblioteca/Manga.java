package biblioteca;

public class Manga extends Carte{
    @Override
    public String getType(){
        return "Manga";
    }

    @Override
    int getValoare(){
        return 100;
    }

}
