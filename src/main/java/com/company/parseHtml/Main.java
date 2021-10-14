package com.company.parseHtml;

import org.slf4j.Logger;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    public static void main(String[] args) {
        final Logger log = getLogger(Main.class);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите URL");
            String url = "https://www.simbirsoft.com/"; //bufferedReader.readLine(); //"https://www.simbirsoft.com/"; //
            log.info("input " + url);
            ParserHtml parserHtml = new ParserHtml();
            StatsUrl statsUrl = parserHtml.parseUrlAndGetStats(url);
            statsUrl.printStat();
        }
        catch (Exception ex){
              log.error(ex.toString() );
        }
    }
}
