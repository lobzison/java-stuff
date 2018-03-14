public class PrePostIncrement {
    public static void main(String[] args){
        int i = 0;
        int j = 0;
        int k = 0;

        i = 5;

        j = i++;
        // j = i
        // i = i + 1
        //i = 6, j = 5
        k = ++i;
        //i = i + 1
        //k = i
        //i = 7
        //k = 7
        System.out.println("i = "+ i);
        System.out.println("j = "+ j);
        System.out.println("k = "+ k);
        //expected 7 5 7
    }
}
