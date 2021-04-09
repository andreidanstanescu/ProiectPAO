package biblioteca;

public class Teatru extends Carte{
    private final String status;
    private boolean has_adaptation;
    private int nr_scene;

    Teatru(){
        super();
        status = "old-school";
        has_adaptation = false;
    }

    public void setHas_adaptation(){
        has_adaptation = true;
    }

    public void setNr_scene(int n){
        nr_scene = n;
    }

    public int getNr_scene(){
       return nr_scene;
    }

    @Override
    public String getType(){
        return "Teatru";
    }

    @Override
    int getValoare(){
        return 40;
    }
}
