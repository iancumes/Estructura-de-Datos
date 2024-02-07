<<<<<<< HEAD
import java.util.ArrayList;

public interface ICalculator {
    
    public int add(int n1, int n2);// +
    public int substraction(int n1, int n2);// -
    public int multiplication(int n1, int n2);// *
    public int division(int n1, int n2) throws Exception;// /
    public int residue(int n1, int n2) throws Exception;// %
    public ArrayList<String> read(String charactersString) throws Exception;
    public int solve(ArrayList<String> elements) throws Exception;
=======
import java.util.ArrayList;

public interface ICalculator {
    
    public int add(int n1, int n2);// +
    public int substraction(int n1, int n2);// -
    public int multiplication(int n1, int n2);// *
    public int division(int n1, int n2) throws Exception;// /
    public int residue(int n1, int n2) throws Exception;// %
    public ArrayList<Character> read(String charactersString) throws Exception;
    public int solve(ArrayList<Character> elements) throws Exception;
>>>>>>> 2f4f928bd2827837d67b7508d64cee8d6f86d7c6
}