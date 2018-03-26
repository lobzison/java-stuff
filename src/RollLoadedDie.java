public class RollLoadedDie {
    public static void main(String[] args){
        int loaded = (int) (1 + Math.random() * 8);
        int result;
        if (loaded <= 3) result = 6;
        else {
            result = (int) (1 + Math.random() * 5);
        }
        System.out.println(result);
    }
}
