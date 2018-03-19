public class LongestRun {
    public static void main(String[] args){
        int length = 1;
        int length_max = length;
        int num = StdIn.readInt();
        int num2;
        int maxnum = num;

        while(!StdIn.isEmpty()){
            num2 = StdIn.readInt();
            //System.out.println("num1: "+num+" Num2: "+num2);
            if (num == num2) length++;
            else length = 1;
            if (length > length_max){
                maxnum = num2;
                length_max = length;
            }
            num = num2;
           // System.out.println("length: "+length+" length_max: "+length_max+" maxnum: "+maxnum);
        }

        System.out.println("longest run is: "+length_max+" Num is: "+maxnum);
    }
}
