package biblioteca;

public class Enciclopedie extends Carte{

    Enciclopedie(){
        super();
        setRestrictie();
    }

    @Override
    public String getType(){
        return "Enciclopedie";
    }

    @Override
    int getValoare(){
        return 150;
    }
}
