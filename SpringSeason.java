public class SpringSeason {
    public static void main(String[] args) {
        int day = Integer.parseInt(args[1]);
        int month = Integer.parseInt(args[0]);
        boolean between_dates = (day >= 20 && month == 3)||
                (day >= 1 && day <= 30 && month == 4)||
                (day >= 1 && day <= 31 && month == 5)||
                (day >= 1 && day <= 20 && month == 6);
        System.out.println(between_dates);
    }
}
