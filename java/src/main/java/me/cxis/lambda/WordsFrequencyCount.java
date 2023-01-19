package me.cxis.lambda;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词频统计
 * 使用for循环进行词频统计
 * 使用lambda进行词频统计
 */
public class WordsFrequencyCount {

    private Set<String> NON_WORDS = new HashSet<String>(){
        {
            add("the"); add("and"); add("of"); add("to"); add("a");
            add("i"); add("it"); add("in"); add("or"); add("is");
            add("d"); add("s"); add("as"); add("o"); add("but");
            add("be");
        }
    };

    /**
     * for循环的方式统计词频
     * @param words
     * @return
     */
    public Map<String, Integer> wordFreq(String words) {
        List<String> wordsList = regexToList(words, "\\w+");
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        for (String word : wordsList) {
            word = word.toLowerCase();
            if (NON_WORDS.contains(word)) {
                continue;
            }

            if (wordMap.get(word) == null) {
                wordMap.put(word, 1);
            } else {
                wordMap.put(word, wordMap.get(word) + 1);
            }
        }

        return wordMap;
    }

    /**
     * lambda的方式统计词频
     * @param words
     * @return
     */
    public Map<String, Integer> wordFreqLambda(String words) {
        List<String> wordsList = regexToList(words, "\\w+");
        TreeMap<String, Integer> wordMap = new TreeMap<>();

        wordsList.stream()
                .map(String::toLowerCase)
                .filter(word -> !NON_WORDS.contains(word))
                .forEach(word -> wordMap.put(word, wordMap.getOrDefault(word, 0) + 1));

        return wordMap;
    }


    /**
     * 将一段文字通过正则
     * @param words
     * @param regex
     * @return
     */
    private List<String> regexToList(String words, String regex) {
        List<String> wordList = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(words);
        while (matcher.find()) {
            wordList.add(matcher.group());
        }
        return wordList;
    }

    public static void main(String[] args) {
        String words = "This project provides an API Gateway built on top of the Spring Ecosystem, including: Spring 5, Spring Boot 2 and Project Reactor. Spring Cloud Gateway aims to provide a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as: security, monitoring/metrics, and resiliency.";
        WordsFrequencyCount count = new WordsFrequencyCount();
        System.out.println("使用for循环：" + count.wordFreq(words));
        System.out.println("使用lambda：" + count.wordFreqLambda(words));
    }
}
