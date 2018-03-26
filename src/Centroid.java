import java.util.Arrays;

public class Centroid {
    public static void main(String[] args){
        int NUM_COORDS = 2;
        double[] mass_point = new double[NUM_COORDS + 1];
        double[] total_mass_point = new double[NUM_COORDS + 1];

        while(!StdIn.isEmpty()){
            for (int i = 0; i < NUM_COORDS + 1; i++){
                mass_point[i] = StdIn.readDouble();
            }

            for (int i = 0; i < NUM_COORDS + 1; i++){
                if (i < NUM_COORDS) total_mass_point[i] += mass_point[i] * mass_point[NUM_COORDS];
                else total_mass_point[i] += mass_point[NUM_COORDS];
            }
        }

        for (int i = 0; i < NUM_COORDS; i++){
            total_mass_point[i] = total_mass_point[i] / total_mass_point[NUM_COORDS];

        }
        System.out.println(Arrays.toString(total_mass_point));
    }
}
