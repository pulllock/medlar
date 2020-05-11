package me.cxis.es.lucene.index.query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 多域搜索
 */
public class MultiFieldQueryParserExample {

    public static void main(String[] args) {

        try {
            Path indexPath = Paths.get("index_dir");
            Directory directory = FSDirectory.open(indexPath);
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            Analyzer analyzer = new IKAnalyzer();
            String[] fields = {"title", "content"};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);

            Query query = parser.parse("国家");
            System.out.println("Query: " + query.toString());

            TopDocs topDocs = indexSearcher.search(query, 10);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(document);
            }
            directory.close();
            indexReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
