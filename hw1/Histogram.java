import java.io.File;
import java.util.Scanner;

/**
* Parses a .csv file and creates a histogram of the grades within.
*
* @author jmcmath3
* @version 1.0
*
**/
public class Histogram {

    /**
    * Reads command line arguments, calculates bins, reads file and fills bins,
    * and prints the histogram.
    *
    * @param args the command line arguments
    **/
    public static void main(String[] args) throws Exception {
        int bins;
        int[] grades;
        Scanner sc;

        // Read arguments
        if (args.length < 1) {
            throw new IllegalArgumentException("Too few arguments.");
        } else if (args.length < 2) {
            Scanner in = new Scanner(System.in);
            System.out.printf("How many bins would you like?\n");
            bins = in.nextInt();
            sc = new Scanner(new File(args[0]));
        } else if (args.length < 3) {
            sc = new Scanner(new File(args[0]));
            bins = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException("Too many arguments.");
        }

        // Create bins
        int delta = 101 / bins;
        int rem = 101 % bins;
        int[] binRanges = new int[bins * 2];
        for (int i = 0; i < 2 * bins; i++) {
            if (i % 2 == 1) {
                if (i - 1 == 2 * bins - 2) {
                    binRanges[i] = binRanges[i - 1] - delta - rem + 1;
                } else {
                    binRanges[i] = binRanges[i - 1] - delta + 1;
                }
            } else {
                if (i == 0) {
                    binRanges[i] = 100;
                } else {
                    binRanges[i] = binRanges[i - 1] - 1;
                }
            }
        }

        // Read file and populate bins
        int[] data = new int[bins];
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            int val = Integer.parseInt(line[1].trim());
            for (int i = 0; i < bins; i++) {
                if (val <= binRanges[2 * i] && val >= binRanges[2 * i + 1]) {
                    if (data[i] == 0) {
                        data[i] = 1;
                        break;
                    } else {
                        data[i]++;
                        break;
                    }
                }
            }
        }

        // Print histogram
        for (int i = 0; i < bins; i++) {
            if (binRanges[1] > 99) {
                System.out.printf("%3d - %3d | ", binRanges[2 * i],
                                binRanges[2 * i + 1]);
            } else {
                System.out.printf("%3d - %2d | ", binRanges[2 * i],
                                binRanges[2 * i + 1]);
            }
            if (data[i] == 0) {
                System.out.printf("\n");
            } else {
                for (int j = 0; j < data[i]; j++) {
                    if (j == data[i] - 1) {
                        System.out.printf("[]\n");
                    } else {
                        System.out.printf("[]");
                    }
                }
            }
        }
    }
}
