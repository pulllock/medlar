package me.cxis.es.lucene.index.query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 布尔搜索
 */
public class BooleanQueryExample {

    public static void main(String[] args) {

        try {
            Path indexPath = Paths.get("index_dir");
            Directory directory = FSDirectory.open(indexPath);
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);


            Query query1 = new TermQuery(new Term("title", "快讯"));
            Query query2 = new TermQuery(new Term("content", "国家"));
            System.out.println("Query1: " + query1.toString());
            System.out.println("Query2: " + query2.toString());

            BooleanClause clause1 = new BooleanClause(query1, BooleanClause.Occur.MUST);
            BooleanClause clause2 = new BooleanClause(query2, BooleanClause.Occur.MUST_NOT);
            BooleanQuery booleanQuery = new BooleanQuery
                    .Builder()
                    .add(clause1)
                    .add(clause2)
                    .build();

            TopDocs topDocs = indexSearcher.search(booleanQuery, 10);
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
