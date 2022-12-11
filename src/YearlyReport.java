import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class YearlyReport implements Report {
    ArrayList<YearReportObject>info;
    private boolean isReaded = false;
    public YearlyReport(){
        info = new ArrayList<>();
        fillTheMonthMap();
    }
    void loadReport(String path) {

        String contents = readFileContents(path);
        if (contents!=null){
             String[] lines = contents.split("\r\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");
                int month = Integer.parseInt(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                boolean isExpense = Boolean.parseBoolean(parts[2]);

                YearReportObject yearReportInfo = new YearReportObject(month, amount, isExpense);
                info.add(yearReportInfo);
            }
        }else{
            System.out.println("Проверьте правильность введеного пути к требуемому файлу");
        }
    }



    public void  fillTheMonthMap(){
        months.put(1,"Январь");
        months.put(2,"Февраль");
        months.put(3,"Март");
        months.put(4,"Апрель");
        months.put(5,"Май");
        months.put(6,"Июнь");
        months.put(7,"Июль");
        months.put(8,"Август");
        months.put(9,"Сентябрь");
        months.put(10,"Октябрь");
        months.put(11,"Ноябрь");
        months.put(12,"Декабрь");
    }

    @Override
    public String readFileContents(String path) {
        try {
            isReaded = true;
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать годовой отчет. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public void  printInfoAboutYearReport(String fileName){
        if(isReaded){
            System.out.println("Рассматриваемый год:" + getYearOfReport(fileName));
            System.out.println("Прибыль по каждому месяцу:" + getProfit());
            System.out.println("Средний расход за все месяцы в году:" + getAverageExpenses());
            System.out.println("Средний доход за все месяцы в году :" + getAverageIncomes());
        }else{
            System.out.println("Годовой отчет еще не был считан. Выберите пункт 2 - Считать считать годовой отчет");
        }
    }


    private double getAverageIncomes(){
        int counter = 0;
        int sum = 0;
        for(int i = 0; i < info.size();i++){
            if(!info.get(i).isExpense()){
                counter++;
                sum+=info.get(i).getAmount();
            }
        }
        return sum/counter;
    }
    private double getAverageExpenses(){
        int counter = 0;
        int sum = 0;
        for(int i = 0; i < info.size();i++){
            if(info.get(i).isExpense()){
                counter++;
                sum+=info.get(i).getAmount();
            }
        }
        return sum/counter;
    }
    private int getYearOfReport(String fileName){
        String year = "";
         String [] array = fileName.split("\\.");
         for (int i = 0; i < array.length;i++){
            char[]mas = array[i].toCharArray();
            for(int j = 0; j<mas.length;j++){
                if((int)mas[j]>=48 && (int)mas[j]<=57){ //Сравнение по  ASCII
                    year+=mas[j];
                }
            }

         }
         return Integer.parseInt(year);
    }

    public HashMap<String,Integer> getExpenses(){
        HashMap<String,Integer> expenses = new HashMap<>();
        for(int i = 0; i < info.size();i++){
            if(months.containsKey(info.get(i).getMonth())) {
                if (info.get(i).isExpense()) {
                    expenses.put(months.get(info.get(i).getMonth()),info.get(i).getAmount());
                }
            }
        }
        return  expenses;
    }
    public HashMap<String,Integer> getIncome(){
        HashMap<String,Integer> income = new HashMap<>();
        for(int i = 0; i < info.size();i++){
            if(months.containsKey(info.get(i).getMonth())) {
                if (!info.get(i).isExpense()) {
                    income.put(months.get(info.get(i).getMonth()),info.get(i).getAmount());
                }
            }
        }
        return  income;
    }

    private HashMap<String,Integer>getProfit(){
        HashMap<String,Integer>expenses = getExpenses();
        HashMap<String,Integer>income = getIncome();
        HashMap<String,Integer>profit = new HashMap<>();

        for (String key : expenses.keySet()) {
            if(income.containsKey(key)){
                profit.put(key,income.get(key)-expenses.get(key));
            }
        }

        return profit;
    }

    public boolean isReaded() {
        return isReaded;
    }



}