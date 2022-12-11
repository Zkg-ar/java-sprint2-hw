import java.util.HashMap;

public class Checker {
    YearlyReport yearlyReport;
    MonthlyReport monthlyReport;

    public Checker(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        this.yearlyReport = yearlyReport;
        this.monthlyReport = monthlyReport;
    }

    void checkReports(){
        if(yearlyReport.isReaded() && monthlyReport.isReaded()){
            checkExpenses();
            checkIncome();
        }else{
            printErrorMessage();
        }
    }
    private void checkExpenses(){
        HashMap<String,Integer>yearReportExpenses = yearlyReport.getExpenses();
        HashMap<String,Integer>monthReportExpenses = monthlyReport.getExpenses();

        for (String key : yearReportExpenses.keySet()) {
            if(yearReportExpenses.get(key).equals(monthReportExpenses.get(key))){
                System.out.println("Расходы за " + key + " совпадают в годовом и месячном отчетах");
            }else{
                System.out.println("Ошибка! Расходы за " + key + " не совпадают в отчетах");
            }
        }
    }
    private void checkIncome(){
        HashMap<String,Integer>yearReportIncome = yearlyReport.getIncome();
        HashMap<String,Integer>monthReportIncome = monthlyReport.getIncome();

        for (String key : yearReportIncome.keySet()) {
            if(yearReportIncome.get(key).equals(monthReportIncome.get(key))){
                System.out.println("Доходы за " + key + " совпадают в годовом и месячном отчетах");
            }else{
                System.out.println("Ошибка! Доходы за " + key + " не совпадают в отчетах");
            }
        }
    }
    void printErrorMessage(){
        System.out.println("Все требуемые для сверки отчеты не считаны. Воспользуйтесь пунтками 1 и 2, чтобы считать их.");
    }
}
