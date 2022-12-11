import  java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker(yearlyReport,monthlyReport);
        while(true){
            menu.printMainMenu();
            int userInput = scanner.nextInt();
            if(userInput == 1) {
                for (int i = 1; i < 4 ; i ++){
                    monthlyReport.loadReports(i);
                }
            } else if(userInput == 2){
                yearlyReport.loadReport("resources\\y.2021.csv");
            } else if (userInput == 3) {
                checker.checkReports();

            } else if (userInput == 4) {
                monthlyReport.printInfoAboutMonthlyReport(1);
                monthlyReport.printInfoAboutMonthlyReport(2);
                monthlyReport.printInfoAboutMonthlyReport(3);

            } else if (userInput == 5) {
                yearlyReport.printInfoAboutYearReport("y.2021.csv");
            } else if (userInput == 0) {
                break;
            }else{
                System.out.println("Такая команда отсутсвует. Выберите одну из предложенных.");
            }
        }
    }

}