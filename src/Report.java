import java.util.HashMap;

public interface Report {
    HashMap<Integer,String> months = new HashMap<>();
    public void fillTheMonthMap();
    public String readFileContents(String path);
    public HashMap<String,Integer> getExpenses();
    public HashMap<String,Integer> getIncome();

}
