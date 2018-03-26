import java.util.Arrays;

public class Closest {
    public static void main(String[] args){
        int num_coords = 3;
        double[] coordinates = new double[num_coords];
        for (int i = 0; i < num_coords; i++){
            coordinates[i] = Double.parseDouble(args[i]);
        }

        double min_dist = Double.POSITIVE_INFINITY;
        double[] closest_point = new double[num_coords];

        while(!StdIn.isEmpty()){
            double[] point = new double[num_coords];
            for (int i = 0; i < num_coords; i++){
                point[i] = StdIn.readDouble();
            }

            double pseudo_dist = 0;
            for (int i = 0; i < num_coords; i++){
                pseudo_dist += Math.abs(point[i] - coordinates[i]);
            }

            if (pseudo_dist < min_dist) {
                min_dist = pseudo_dist;
                closest_point = point;
            }
        }
        System.out.println(Arrays.toString(closest_point));
    }
}
