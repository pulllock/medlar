package me.cxis.es.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HighlighterExample {

    public static void main(String[] args) {

        try {
            Path indexPath = Paths.get("index_dir");
            Directory directory = FSDirectory.open(indexPath);
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            Analyzer analyzer = new IKAnalyzer();
            QueryParser parser = new QueryParser("title", analyzer);
            parser.setDefaultOperator(QueryParser.Operator.AND);

            Query query = parser.parse("贫困县脱贫");
            System.out.println("Query: " + query.toString());

            QueryScorer scorer = new QueryScorer(query, "title");
            SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<span stype='color:red'>", "</span>");
            Highlighter highlighter = new Highlighter(htmlFormatter, scorer);

            TopDocs topDocs = indexSearcher.search(query, 10);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(document);

                TokenStream tokenStream = TokenSources.getAnyTokenStream(indexSearcher.getIndexReader(), scoreDoc.doc, "title", analyzer);
                Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
                highlighter.setTextFragmenter(fragmenter);
                String str = highlighter.getBestFragment(tokenStream, document.get("title"));
                System.out.println("高亮片段：" + str);
            }
            directory.close();
            indexReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
