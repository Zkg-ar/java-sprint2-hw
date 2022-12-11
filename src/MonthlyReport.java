import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthlyReport implements Report{

    ArrayList<MonthlyReportObject>info;
    HashMap<Integer,String> months;

    public boolean isReaded() {
        return isReaded;
    }

    private boolean isReaded = false;

    public MonthlyReport(){
        months = new HashMap<>();
        info = new ArrayList<>();
        fillTheMonthMap();
    }
    void loadReports(int monthNumber){
        String path = "resources\\m.20210"+monthNumber + ".csv";
        String contents =  readFileContents(path);
        if(contents!=null) {
            String[] lines = contents.split("\r\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");
                String itemName = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sumOfOne = Integer.parseInt(parts[3]);

                MonthlyReportObject object = new MonthlyReportObject(itemName, isExpense, quantity, sumOfOne, monthNumber);
                info.add(object);
            }
        }else{
            System.out.println("��������� ������������ ��������� ���� � ���������� �����");
        }
    }

    public String readFileContents(String path) {
        try {
            isReaded = true;
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("���������� ��������� ����  " + path +". �������� ���� �� ��������� � ������ ����������.");
            return null;
        }
    }

    public void  printInfoAboutMonthlyReport(int monthNumber){
        if(isReaded){
            System.out.println("����� �� " + getMonthName(monthNumber) + ":");
            System.out.println("����� ���������� ����� �� ���� �����:" + getTheMostProfitableProduct(monthNumber));
            System.out.println("����� ������� ����� �� ���� �����:" + getTheBiggetWaste(monthNumber));
        }else{
            System.out.println("����� �� " + months.get(monthNumber)  + " ��� �� ��� ������. �������� ����� 1 - ������� ��� �������� �����");
        }
    }

    private String getTheMostProfitableProduct(int monthNumber){
        String product = "";
        int maxCost = 0;
        for (int i = 0; i < info.size(); i++){
            if(info.get(i).getMonthNumber() == monthNumber && !info.get(i).isExpense()){
                int currentCost = info.get(i).getQuantity()*info.get(i).getSumOfOne();
                if(maxCost < currentCost){
                    maxCost = currentCost;
                    product = info.get(i).getItemName();
                }
            }
        }
        return product + " - " + Integer.toString(maxCost) ;
    }

    private String getTheBiggetWaste(int monthNumber){
        String product = "";
        int maxWaste = 0;
        for (int i = 0; i < info.size(); i++){
            if(info.get(i).getMonthNumber() == monthNumber && info.get(i).isExpense()){
                int currentWaste = info.get(i).getQuantity()*info.get(i).getSumOfOne();
                if(maxWaste < currentWaste){
                    maxWaste = currentWaste;
                    product = info.get(i).getItemName();
                }
            }
        }
        return product + " - " + Integer.toString(maxWaste) ;
    }

    @Override
    public HashMap<String,Integer> getIncome(){
        int sum = 0;
        HashMap<String,Integer> income = new HashMap<>();

        for(int j = 1; j<=getMonthCount();j++){
            for(int i = 0; i < info.size();i++) {
                if (!info.get(i).isExpense() && info.get(i).getMonthNumber() == j) {
                    sum += info.get(i).getQuantity() * info.get(i).getSumOfOne();
                }
            }
            income.put(months.get(j),sum);
            sum = 0;
        }
        return income;
    }
    @Override
    public HashMap<String,Integer> getExpenses(){
        int sum = 0;
        HashMap<String,Integer> expenses = new HashMap<>();

        for(int j = 1; j<=getMonthCount();j++){
            for(int i = 0; i < info.size();i++) {
                if (info.get(i).isExpense() && info.get(i).getMonthNumber() == j) {
                    sum += info.get(i).getQuantity() * info.get(i).getSumOfOne();
                }
            }
            expenses.put(months.get(j),sum);
            sum = 0;
        }
        return expenses;
    }

    private int getMonthCount(){

        Set<Integer> unique = new HashSet<>();
        for(int i = 0; i < info.size();i++){
            unique.add(info.get(i).getMonthNumber());
        }

        return unique.size();
    }
    private String getMonthName(int monthNumber){
        return months.get(monthNumber);
    }



     public void  fillTheMonthMap(){
        months.put(1,"������");
        months.put(2,"�������");
        months.put(3,"����");
        months.put(4,"������");
        months.put(5,"���");
        months.put(6,"����");
        months.put(7,"����");
        months.put(8,"������");
        months.put(9,"��������");
        months.put(10,"�������");
        months.put(11,"������");
        months.put(12,"�������");

    }
}
