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
                System.out.println("������� �� " + key + " ��������� � ������� � �������� �������");
            }else{
                System.out.println("������! ������� �� " + key + " �� ��������� � �������");
            }
        }
    }
    private void checkIncome(){
        HashMap<String,Integer>yearReportIncome = yearlyReport.getIncome();
        HashMap<String,Integer>monthReportIncome = monthlyReport.getIncome();

        for (String key : yearReportIncome.keySet()) {
            if(yearReportIncome.get(key).equals(monthReportIncome.get(key))){
                System.out.println("������ �� " + key + " ��������� � ������� � �������� �������");
            }else{
                System.out.println("������! ������ �� " + key + " �� ��������� � �������");
            }
        }
    }
    void printErrorMessage(){
        System.out.println("��� ��������� ��� ������ ������ �� �������. �������������� �������� 1 � 2, ����� ������� ��.");
    }
}
