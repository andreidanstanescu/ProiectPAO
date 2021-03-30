package biblioteca;

public class Main {
    public static void main(String[] args) throws MyException {
        Serviciu test = new Serviciu();
        test.addSectiune("drama");
        test.addSectiune("slice of life");
        test.golesteSectiune("slice of life");
        System.out.println(test.s.size());
    }
}
