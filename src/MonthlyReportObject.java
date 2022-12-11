public class MonthlyReportObject  {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;
    int monthNumber;

    public MonthlyReportObject(String itemName, boolean isExpense, int quantity, int sumOfOne, int monthNumber) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.monthNumber = monthNumber;
    }



    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
