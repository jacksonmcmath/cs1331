import java.io.File;
import java.util.Scanner;

/**
 * A source model for a Markov chain.
 *
 * @author jmcmath3
 * @version 1
 */
public class SourceModel {
    private String name;
    private int[][] counts = new int[26][26];
    private double[][] probs = new double[26][26];

    /**
     * Creates and trains a Markov chain
     *
     * @param name the name of the model being trained
     * @param filename the filename of the .corpus file to train from
     */
    public SourceModel(String name, String filename) throws Exception {
        this.name = name;

        System.out.printf("Training %s model ... ", name);
        // Read through .corpus file
        Scanner sc = new Scanner(new File(filename));
        String corpus = "";
        while (sc.hasNext()) {
            corpus += sc.next();
        }
        corpus = corpus.toLowerCase();
        corpus = corpus.replaceAll("[^a-z]", "");

        // Fill in matrix of bigram counts
        for (int i = 1; i < corpus.length(); i++) {
            this.counts[corpus.charAt(i - 1) - 'a'][corpus.charAt(i) - 'a']++;
        }

        // Create matrix to store row sums
        int[] sums = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                sums[i] += this.counts[i][j];
            }
        }

        // Fill in matrix of probabilities
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (sums[i] > 0) {
                    this.probs[i][j] = this.counts[i][j] / (double) sums[i];
                } else {
                    this.probs[i][j] = 0.0;
                }
                if (this.probs[i][j] == 0.0) {
                    this.probs[i][j] = 0.01;
                }
            }
        }
        System.out.printf("done.\n");
    }

    /**
    * @return this sourcemodel's name
    */
    public String getName() {
        // returns the name of the SourceModel
        return this.name;
    }

    /**
    * @return this sourcemodel's string representation
    */
    public String toString() {
        String str = "Model: " + this.name + "\n";
        for (int i = 0; i < 26; i++) {
            str += String.format("  %s  ", (char) ('a' + i));
            if (i == 25) {
                str += "\n";
            }
        }
        for (int i = 0; i < 26; i++) {
            str += String.format("%s", (char) ('a' + i));
            for (int j = 0; j < 26; j++) {
                str += String.format(" %.2f", this.probs[i][j]);
            }
            str += "\n";
        }

        return str;
    }
    /**
    * @param str string to be tested
    * @return the probability that str was generated by the source model
    */
    public double probability(String str) {
        double prob =  1.0;
        str = str.toLowerCase();
        str = str.replaceAll("[^a-z]", "");

        for (int i = 1; i < str.length(); i++) {
            prob *= this.probs[str.charAt(i - 1) - 'a'][str.charAt(i) - 'a'];
        }
        return prob;
    }

    /**
    * @param args command line arguments
    */
    public static void main(String[] args) throws Exception {
        // makes SourceModel runnable from the command line
        SourceModel[] models = new SourceModel[args.length - 1];
        for (int i = 0; i < models.length; i++) {
            models[i] = new SourceModel(args[i].split("[.]")[0], args[i]);
        }
        System.out.println("Analyzing: " + args[args.length - 1]);
        double[] probs = new double[models.length];
        String[] names = new String[models.length];
        double sum = 0.0;
        for (int i = 0; i < models.length; i++) {
            probs[i] = models[i].probability(args[args.length - 1]);
            names[i] = models[i].getName();
            sum += probs[i];
        }

        String formatSpec = "Probability that test string is %8s: %.2f\n";
        int maxInd = 0;
        for (int i = 0; i < models.length; i++) {
            System.out.printf(formatSpec, names[i], probs[i] / sum);
            if (probs[i] > probs[maxInd]) {
                maxInd = i;
            }
        }
        System.out.printf("Test string is most likely %s.\n", names[maxInd]);
    }
}
