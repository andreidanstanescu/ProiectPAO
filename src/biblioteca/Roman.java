package biblioteca;

public class Roman extends Carte{
    private int nr_pagini;
    private boolean has_adaptation;

    Roman(){
        super();
        nr_pagini = 100;
        has_adaptation = false;
    }

    public void setHas_adaptation(){
        has_adaptation = true;
    }

    public void setNr_pagini(int n){
        nr_pagini = n;
    }

    public int getNr_pagini(){
        return nr_pagini;
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
        return super.toString() + getType();
    }

}
