package me.cxis.es.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteIndexExample {

    public static void main(String[] args) {
        deleteDocument("title", "井冈山");
    }

    private static void deleteDocument(String field, String key) {
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        Path indexPath = Paths.get("index_dir");
        Directory directory;

        try {
            directory = FSDirectory.open(indexPath);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.deleteDocuments(new Term(field, key));
            indexWriter.commit();
            indexWriter.close();
            System.out.println("删除完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
