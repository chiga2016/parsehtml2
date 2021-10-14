package com.company.parseHtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;


public class ParserHtml {

    private static final Logger log = getLogger(ParserHtml.class);

    List<String> list = new ArrayList<String>();
    {
        list.add(" ");
        list.add(",");
        list.add(".");
        list.add("!");
        list.add("?");
        list.add("\"");
        list.add(";");
        list.add(":");
        list.add("[");
        list.add("]");
        list.add("(");
        list.add(")");
        list.add("\n");
        list.add("\r");
        list.add("\t");
    }

    public StatsUrl parseUrlAndGetStats(String url) throws IOException {
        String textPage = parseHtmlAndGetText(url);
        Map<String, Long> wordsAndCountMap = getWordsAndCountMap(textPage);
        Map<String, Long> sortedMap = getSortedMap(wordsAndCountMap);

        return new StatsUrl(url, sortedMap);
    }


    public String parseHtmlAndGetText(String url) throws IOException {
        log.info("get text " +url);
        Document doc = Jsoup.connect(url)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .get();
        Element body = doc.body();
        String string = body.text();
        return string;
    }


    public Map<String, Long> getWordsAndCountMap (String string){
        log.info("parsing text");
        Map<String, Long> map = new HashMap<>();
        String separatorsString = String.join("|\\", list);
        List<String> words = Arrays.asList(string.split(separatorsString));
        //Map<String, Long> map = words.stream().collect(Collectors.groupingBy(p -> p.toString(), Collectors.counting()));
        for (String str:words
             ) {
            //System.out.println(str + " " + isValidate(str));
            if(isValidate(str)){
                map.put(str, map.getOrDefault(str,0L)+1);
            }
        }
        return map;
    }

    public boolean isValidate (String word){
        if (word.matches("^[A-Za-zА-Яа-я][\\S]*$")) {
            return true;
        }
        return false;
    }

    public Map<String, Long> getSortedMap (Map<String, Long> map){
        Map sortedMap = new LinkedHashMap();
        ArrayList<Long> arrayList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            arrayList.add((Long) entry.getValue());
        }
        Collections.sort(arrayList, Comparator.reverseOrder());
        //System.out.println(arrayList);
        for(Long num : arrayList) {
            for (Map.Entry entry : map.entrySet()) {
                if ( entry.getValue().equals(num)  ) {
                    sortedMap.put(entry.getKey(), num);
                }
            }

        }
        return sortedMap;
    }

}
