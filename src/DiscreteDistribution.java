public class DiscreteDistribution {
    public static void main(String[] args){
        int num = args.length;
        int sum = 0;

        for (int i = 0; i < num; i++){
            sum += Integer.parseInt(args[i]);
        }

//        for (int i = 0; i < num; i++){
//            double value_chance = (double)Integer.parseInt(args[i]) / sum;
//            double rnd = Math.random();
////            System.out.println(i);
////            System.out.println(Integer.parseInt(args[i]));
////            System.out.println(sum);
////            System.out.println(value_chance);
////            System.out.println(rnd);
//            if (value_chance > rnd){
//                System.out.println(i);
//            }
//        }

        int res = 0;
        double stop = Math.random() * sum;
        int summ_c = 0;
        for (int i = 0; i < num && i <= stop; i++){
            summ_c += Integer.parseInt(args[i]);
            res = i;
        }
        System.out.println(res);
    }
}
