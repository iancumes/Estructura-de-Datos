import java.util.ArrayList;

public interface ICalculator {
    
    public int add(int n1, int n2);// +
    public int substraction(int n1, int n2);// -
    public int multiplication(int n1, int n2);// *
    public int division(int n1, int n2) throws Exception;// /
    public int residue(int n1, int n2) throws Exception;// %
    public ArrayList<String> read(String charactersString) throws Exception;
    public int solve(ArrayList<String> elements) throws Exception;
}