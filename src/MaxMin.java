public class MaxMin {
    public static void main(String[] args) {
        int max = StdIn.readInt();
        int min = max;

        while (!StdIn.isEmpty()) {
            int c = StdIn.readInt();
            max = Math.max(max, c);
            min = Math.min(min, c);
        }
        System.out.println("Min is: "+min+" Max is: "+max);
    }
}