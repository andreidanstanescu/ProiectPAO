package biblioteca;

public class Main {
    public static void main(String[] args) throws MyException {
        Serviciu test = new Serviciu();
        test.addSectiune("drama");
        test.addSectiune("slice of life");
        //test.stergeSectiune("slice of life");
        test.addCarte("slice of life");
        test.afiseazaSectiuni();
        System.out.println(test.mostBooks());
    }
}
