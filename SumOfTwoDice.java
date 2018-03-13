public class SumOfTwoDice {
    public static void main(String[] args) {
        int num_dices = 6;
        int score1 = 1 + (int) Math.random() * num_dices ;
        int score2 = 1 + (int) Math.random() * num_dices;
        System.out.println(score1 +" "+ score2);

        int score_sum = score1 + score2;

        System.out.println("Your score is: "+ score_sum);

    }
}
