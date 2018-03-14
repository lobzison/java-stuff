public class Hellos {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        for (int i = 1; i <= n; i++){
            String ending = "th";
            if (!(i > 10 && i < 20))
            switch (i % 10) {
                case 1: ending = "st"; break;
                case 2: ending = "nd"; break;
                case 3: ending = "rd"; break;
            }

            System.out.println(i+ending+" Hello");
        }
    }
}
