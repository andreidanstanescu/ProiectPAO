package biblioteca;

public class Schite extends Carte{
    private String stil;

    Schite(){
        super();
        stil = "umoristic";
    }

    @Override
    public String getType(){
        return "Schite";
    }

    //pretul daca un cititor pierde/nu aduce/are amenda
    @Override
    int getValoare() {
        return 50;
    }
}
