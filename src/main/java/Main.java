import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ParserHtml parserHtml = new ParserHtml();
        String text = parserHtml.parseHtmlAndGetText("https://www.simbirsoft.com/");
        System.out.println(text);

//        String separatorsString = String.join("|\\", parserHtml.list);
//        System.out.println(separatorsString);
//        String[] words = text.split(separatorsString);
//        System.out.println(words);
//        for (int i = 0; i < words.length; i++) {
//            System.out.println(words[i]);
//        }
        System.out.println(parserHtml.getWordsAndCountMap(text));
    }
}
