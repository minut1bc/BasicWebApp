package com.develogical;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.toLowerCase().contains("imperial")) {
            return "Imperial College is a university in London";
        }

        if (query.toLowerCase().contains("what is your name")) {
            return "Team0";
        }

        Pattern plusPattern = Pattern.compile("what is (-?\\d+) plus (-?\\d+)");
        Pattern minusPattern = Pattern.compile("what is (-?\\d+) minus (-?\\d+)");
        Pattern multipliedPattern = Pattern.compile("what is (-?\\d+) multiplied by (-?\\d+)");
        Pattern largestPattern = Pattern.compile("which of the following numbers is the largest: (-?\\d+(?:, -?\\d+)+)");
        Pattern squareAndCubePattern = Pattern.compile("which of the following numbers is both a square and a cube: (-?\\d+(?:, -?\\d+)+)");
        Pattern primesPattern = Pattern.compile("which of the following numbers are primes: (-?\\d+(?:, -?\\d+)+)");

        Matcher plusMatcher = plusPattern.matcher(query);
        Matcher minusMatcher = minusPattern.matcher(query);
        Matcher multipliedMatcher = multipliedPattern.matcher(query);
        Matcher largestMatcher = largestPattern.matcher(query);
        Matcher squareAndCubeMatcher = squareAndCubePattern.matcher(query);
        Matcher primesMatcher = primesPattern.matcher(query);

        if (plusMatcher.matches()) {
            int x = Integer.parseInt(plusMatcher.group(1));
            int y = Integer.parseInt(plusMatcher.group(2));

            return String.format("%d", x + y);
        }

        if (minusMatcher.matches()) {
            int x = Integer.parseInt(minusMatcher.group(1));
            int y = Integer.parseInt(minusMatcher.group(2));

            return String.format("%d", x - y);
        }

        if (multipliedMatcher.matches()) {
            int x = Integer.parseInt(multipliedMatcher.group(1));
            int y = Integer.parseInt(multipliedMatcher.group(2));

            return String.format("%d", x * y);
        }

        if (largestMatcher.matches()) {
            int largest = Arrays.stream(largestMatcher.group(1).split(", "))
                .mapToInt(Integer::valueOf)
                .max()
                .getAsInt();

            return String.format("%d", largest);
        }

        if (squareAndCubeMatcher.matches()) {
            int squareAndCube = Arrays.stream(squareAndCubeMatcher.group(1).split(", "))
                .mapToInt(Integer::valueOf)
                .filter(i -> Math.pow(Math.floor(Math.sqrt(i)), 2) == i && Math.pow(Math.floor(Math.cbrt(i)), 3) == i)
                .findFirst()
                .orElse(0);

            return String.format("%d", squareAndCube);
        }

        if (primesMatcher.matches()) {
            return "";
        }

        return "";
    }
}
