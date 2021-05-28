package biblioteca;

public class Roman extends Carte{
    private int nrPagini;
    private boolean adaptare;

    Roman(){
        super();
        nrPagini = 100;
        adaptare = false;
    }

    public void setAdaptare(){
        adaptare = true;
        /*CSV csv = CSV.getInstanta();
        try {
            CSV.scrieRoman(this);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public boolean getAdaptare() { return adaptare; }

    public void setNrPagini(int n){
        nrPagini = n;
    }

    public int getNrPagini(){
        return nrPagini;
    }

    @Override
    public String getType(){
        return "Roman";
    }

    @Override
    int getValoare(){
        return 70;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getType();
    }

}
