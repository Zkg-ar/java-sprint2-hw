import java.util.HashMap;

public interface Report {
    Map<Integer,String> months = new HashMap<Integer,String>();
    public void fillTheMonthMap(); 
    public String readFileContents(String path);
    public Map<String,Integer> getExpenses();
    public Map<String,Integer> getIncome();

}
