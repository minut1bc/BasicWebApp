package com.develogical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        Pattern numberPattern = Pattern.compile("-?\\d+");
        Matcher numberMatcher = numberPattern.matcher(query);

        List<Integer> numbers = new ArrayList<>();

        while (numberMatcher.find()) {
            numbers.add(Integer.valueOf(numberMatcher.group()));
        }

        if (query.contains("plus")) {
            return String.valueOf(numbers.get(0) + numbers.get(1));
        }

        if (query.contains("minus")) {
            return String.valueOf(numbers.get(0) - numbers.get(1));
        }

        if (query.contains("multiplied")) {
            return String.valueOf(numbers.get(0) * numbers.get(1));
        }

        if (query.contains("largest")) {
            return String.valueOf(numbers.stream().max(Integer::compare));
        }

        if (query.contains("square") && query.contains("cube")) {
            return String.valueOf(numbers
                .stream()
                .filter(i -> Math.pow(Math.floor(Math.sqrt(i)), 2) == i && Math.pow(Math.floor(Math.cbrt(i)), 3) == i)
                .findFirst()
                .orElse(0));
        }

        if (query.contains("prime")) {
          return numbers
              .stream()
              .filter(QueryProcessor::isPrime)
              .map(String::valueOf)
              .collect(Collectors.joining(" "));
        }

        return "";
    }

    private static boolean isPrime(int x) {
      for (int i = 2; i < Math.sqrt(x) + 1; i++) {
        if (Math.pow(i, 2) == x) {
          return false;
        }
      }

      return true;
    }
}
