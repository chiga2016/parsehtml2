import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ParserHtml {

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



    public String parseHtmlAndGetText(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .get();
        Element body = doc.body();
        String string = body.text();
        return string;
    }


    public Map<String, Long> getWordsAndCountMap (String string){
        String separatorsString = String.join("|\\", list);
        List<String> words = Arrays.asList(string.split(separatorsString));

//        for (int i = 0; i < words.length ; i++) {
//
//        }
        Map<String, Long> map = words.stream().collect(Collectors.groupingBy(p -> p.toString(), Collectors.counting()));
        //.collect(Collectors.groupingBy(p->p.toString()), Collectors.counting());



        return map;
    }

}
