#include <cstdlib>
#include <string>
#include <iostream>

using namespace std;

int patternCounter(string pattern, string sequence, int maxMutations) {
    int occurrences = 0;
    int errors;
    int i, j;

    for (int i = 1; i < sequence.size(); i++) {
        errors = 0;
        j = 0;
        while (j != pattern.size() || errors <= maxMutations) {
            if (pattern.at(j) != sequence.at(i+j)) {
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

int main(int argc, char** argv) {

    string pattern, sequence;
    int occurrences, maxMutations;

    cout << "Pattern, sequence, maximum mutations\n";
    cin >> pattern >> sequence >> maxMutations;

    //Counts number of occurrences parts of "sequence" match "pattern", with "maxMutations" number of differences
    occurrences = patternCounter(pattern, sequence, maxMutations);

    cout << "Matches: " + occurrences;

    return 0;
}