package me.cxis.es.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.StringReader;

public class IKAnalyzerExample {

    private static String stringCh = "是一个开源的，基于java语言开发的轻量级的中文分词工具包。";

    private static String stringEn = "Luke is a handy development and diagnostic tool, which accesses already existing Lucene indexes and allows you to display and modify their content in several ...";

    public static void main(String[] args) {
        Analyzer analyzer = null;

        analyzer = new SmartChineseAnalyzer();

        print(analyzer, stringCh);
        print(analyzer, stringEn);

        analyzer = new IKAnalyzer();
        print(analyzer, stringCh);
        print(analyzer, stringEn);

        analyzer.close();
    }

    public static void print(Analyzer analyzer, String str) {
        try {
            StringReader reader = new StringReader(str);
            TokenStream stream = analyzer.tokenStream(str, reader);
            stream.reset();
            CharTermAttribute termAttribute = stream.getAttribute(CharTermAttribute.class);
            System.out.println("分词器：" + analyzer.getClass().getName() +"，分词结果： ");
            while (stream.incrementToken()) {
                System.out.print(termAttribute.toString() + "|");
            }
            stream.close();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
