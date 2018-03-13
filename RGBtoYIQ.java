public class RGBtoYIQ {
    public static void main(String[] args){
        int r = Integer.parseInt(args[0]);
        int g = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);

        int y = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        int i = Math.max((int) (0.596 * r - 0.274 * g - 0.322 * b), 0);
        int q = Math.max((int) (0.211 * r - 0.522 * g + 0.311 * b), 0);

        System.out.println(y);
        System.out.println(i);
        System.out.println(q);
    }
}