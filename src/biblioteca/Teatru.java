package biblioteca;

public class Teatru extends Carte{

    @Override
    public String getType(){
        return "Teatru";
    }

    @Override
    int getValoare(){
        return 40;
    }
}
