package approximatepatternmatching;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ApproximatePatternMatching {

    static String generateRandomMotif(List sequences, int motifSize) {
        String motif = "";
        String sequence;
        int startAt;
        Random ran = new Random();

        sequence = sequences.get((ThreadLocalRandom.current().nextInt(0, sequences.size()))).toString();
        startAt = (ThreadLocalRandom.current().nextInt(0, sequence.length() - motifSize));
        
        for (int i = startAt; i < startAt + motifSize; i++) {
            motif += (String.valueOf(sequence.charAt(i)));
        }

        return motif;
    }

    static int patternCounter(String pattern, String sequence, int maxMutations, int motifSize) {
        int occurrences = 0;
        int errors;
        int i, j;

        for (i = 0; i < sequence.length(); i++) {
            
            //If exceeds sequence size, exit loop
            if (i + motifSize >= sequence.length()) {
                break;
            }

            errors = 0;
            j = 0;
            
            //Compares one character at a time
            while (j < pattern.length() && errors <= maxMutations) {
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

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        String pattern;
        int score = 0;
        int occurrences = 0;
        String motif = "";

        Scanner input = new Scanner(System.in);
        String filename;
        List<String> sequences = new ArrayList<>();
        int iterations = 1000;
        int maxMutations = 2;
        int motifSize = 8;
        String line;

        System.out.print("File name: ");
        filename = input.nextLine();
        File file = new File(filename);
        Path filePath = Paths.get(file.toURI());

        try {

            Scanner filereader = new Scanner(filePath);

            while ((line = filereader.nextLine()) != null && (line.length() == 0)) ;
            while ((line = filereader.nextLine()) != null && (line.length() == 0)) ;
            while ((line = filereader.nextLine()) != null && (line.length() == 0)) ;;

            while (filereader.hasNext()) {
                line = filereader.nextLine();
                if (line != null && line.length() != 0) {
                    sequences.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(sequences);

        for (int i = 0; i < iterations; i++) {
            pattern = generateRandomMotif(sequences, motifSize);
            occurrences = 0;

            //Counts number of times parts of "sequence" match "pattern", with "maxMutations" differences for each string in "sequences"
            for (String sequence : sequences) {
                occurrences += patternCounter(pattern, sequence, maxMutations, motifSize);
            }

            if (occurrences > score) {
                motif = pattern;
                score = occurrences;
            }
        }

        System.out.println("Pattern: " + motif);
        System.out.println("Matches: " + score);
    }
}