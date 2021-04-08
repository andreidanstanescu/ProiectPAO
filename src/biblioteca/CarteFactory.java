package biblioteca;

public class CarteFactory extends AbstractFactory{
    //idk daca il mai folosesc
    private static int _id = 0;

    @Override
    public Carte getCarte(String tip){
        if(tip.equalsIgnoreCase("manga")){
            return new Manga();
        }else if(tip.equalsIgnoreCase("roman")){
            return new Roman();
        }
        else if(tip.equalsIgnoreCase("schite")) {
            return new Schite();
        }
        else if(tip.equalsIgnoreCase("teatru")) {
            return new Teatru();
        }
        else if(tip.equalsIgnoreCase("enciclopedie")){
            return new Enciclopedie();
        }

        System.out.println("Optiune invalida !");

        return null;
    }

    //un demo
    /*public static void main(String[] args) {
        CarteFactory demo = new CarteFactory();
        Carte test = demo.getCarte("manga");
        System.out.println(test.getType());
    }*/
}
