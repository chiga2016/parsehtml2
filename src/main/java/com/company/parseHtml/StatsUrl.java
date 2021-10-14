package com.company.parseHtml;

import java.util.Map;

public class StatsUrl {
    private String url;
    private Map<String, Long> map;



    public void printStat() {
        if(!url.isEmpty()&&map!=null) {
            System.out.println("Статистика для сайта " + url + " : \n");
            for (Map.Entry<String, Long> entry: map.entrySet()
            ) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }

    public StatsUrl() {
    }

    public StatsUrl(String url, Map<String, Long> map) {
        this.url = url;
        this.map = map;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Long> getMap() {
        return map;
    }

    public void setMap(Map<String, Long> map) {
        this.map = map;
    }
}
