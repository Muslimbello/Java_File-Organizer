package com.fileorganizer.utils;

import java.util.*;

public class FileSimilarity {


    public static double calculateSimilarity(String s1, String s2) {
        double levSim = calculateLevenshteinSimilarity(s1, s2);
        double jaccardSim = calculateJaccardSimilarity(s1, s2);
        double phoneticMatch = soundex(s1).equals(soundex(s2)) ? 1.0 : 0.0;
        return (levSim * 0.5) + (jaccardSim * 0.4) + (phoneticMatch * 0.1);
    }

    private static double calculateLevenshteinSimilarity(String s1, String s2) {
        if (s1.isEmpty() && s2.isEmpty()) return 1.0;
        int maxLength = Math.max(s1.length(), s2.length());
        if (maxLength == 0) return 1.0;

        int distance = levenshteinDistance(s1, s2);
        return 1.0 - ((double) distance / maxLength);
    }

    private static int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1,  // Deletion
                                    dp[i][j - 1] + 1), // Insertion
                            dp[i - 1][j - 1] + cost); // Substitution
                }
            }
        }
        return dp[m][n];
    }


    private static double calculateJaccardSimilarity(String s1, String s2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(s1.toLowerCase().split("\\W+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(s2.toLowerCase().split("\\W+")));

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return union.isEmpty() ? 0 : (double) intersection.size() / union.size();
    }

    private static String soundex(String s) {
        char[] map = "01230120022455012623010202".toCharArray();
        char[] input = s.toUpperCase().toCharArray();

        char firstLetter = input[0];
        StringBuilder result = new StringBuilder();
        result.append(firstLetter);

        char prevDigit = '0';
        for (int i = 1; i < input.length; i++) {
            char c = input[i];
            if (c >= 'A' && c <= 'Z') {
                char digit = map[c - 'A'];
                if (digit != '0' && digit != prevDigit) {
                    result.append(digit);
                }
                prevDigit = digit;
            }
        }

        result.append("0000");
        return result.substring(0, 4);
    }
}
