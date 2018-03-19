public class Stats {
    public static void main(String[] args){
        int num_vals = Integer.parseInt(args[0]);

        double nums[] = new double[num_vals];
        for (int i = 0; i < num_vals; i++){
            nums[i] = StdIn.readDouble();
        }

        double sum = 0;
        for (int i = 0; i <num_vals; i++){
            sum += nums[i];
        }

        double std_dev = 0;
        double avg = sum / num_vals;
        for (int i = 0; i < num_vals; i++){
            std_dev += Math.pow((nums[i] - avg), 2.0);
        }
        std_dev = Math.sqrt(std_dev / (num_vals - 1));

        System.out.println("Mean is: "+avg+" std dev is: "+ std_dev);
    }
}
