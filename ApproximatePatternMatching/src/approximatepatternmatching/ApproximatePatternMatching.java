package approximatepatternmatching;

import java.util.Scanner;

public class ApproximatePatternMatching {

    static int patternCounter(String pattern, String sequence, int maxMutations) {
        int occurrences = 0;
        int errors;
        int i, j;

        for (i = 0; i < sequence.length(); i++) {

            if (i + 2 >= sequence.length()) {
                break;
            }

            errors = 0;
            j = 0;
            while (j < pattern.length()) {
                if (errors > maxMutations) {
                    break;
                }

                if (pattern.charAt(j) != sequence.charAt(i + j)) {
                    errors += 1;
                }
                j++;

            }

            if (errors <= maxMutations) {
                occurrences += 1;
            }
        }
        return occurrences;
    }

    public static void main(String[] args) {
        String pattern, sequence;
        int occurrences, maxMutations;
        Scanner reader = new Scanner(System.in);

        System.out.println("Pattern, sequence, maximum mutations");
        pattern = reader.nextLine();
        sequence = reader.nextLine();
        maxMutations = reader.nextInt();

        //Counts number of occurrences parts of "sequence" match "pattern", with "maxMutations" number of differences
        occurrences = patternCounter(pattern, sequence, maxMutations);
        System.out.println("Matches: " + occurrences);
    }
}
