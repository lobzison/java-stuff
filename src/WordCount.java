public class WordCount {
    public static void main(String[] args){
        int count = 0;
        while(!StdIn.isEmpty()){
            StdIn.readString();
            count++;
        }

        System.out.print(count);
    }
}
