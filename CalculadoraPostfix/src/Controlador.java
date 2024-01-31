import java.util.ArrayList;

public class Controlador implements ICalculator{

    public Controlador() {
    }

    @Override
    public int add(int n1, int n2) {
        int n5 = n1 + n2;
        return n5;
    }

    @Override
    public int substraction(int n1, int n2) {
        int n5 = n1 - n2;
        return n5;
    }

    @Override
    public int multiplication(int n1, int n2) {
        int n5 = n1 * n2;
        return n5;
    }

    @Override
    public int division(int n1, int n2) throws Exception {
        int n5 = n1 / n2;
        return n5;
    }

    @Override
    public int residue(int n1, int n2) throws Exception {
        return 0;
    }

    @Override
    public ArrayList<Character> read(String charactersString) throws Exception {
        return null;
    }

    @Override
    public int solve(ArrayList<Character> elements) throws Exception {
        return 0;
    }
}
