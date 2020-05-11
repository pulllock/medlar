package me.cxis.es.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UpdateIndexExample {

    public static void main(String[] args) {
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        Path indexPath = Paths.get("index_dir");
        Directory directory = null;

        try {
            directory = FSDirectory.open(indexPath);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

            // 设置新闻id索引并存储
            FieldType idType = new FieldType();
            idType.setIndexOptions(IndexOptions.DOCS);
            idType.setStored(true);

            // 设置新闻标题索引文档、词项频率、位移信息、偏移量，存储并词条化
            FieldType titleType = new FieldType();
            titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            titleType.setStored(true);
            titleType.setTokenized(true);

            FieldType contentType = new FieldType();
            contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            contentType.setStored(true);
            contentType.setTokenized(true);
            contentType.setStoreTermVectors(true);
            contentType.setStoreTermVectorPositions(true);
            contentType.setStoreTermVectorOffsets(true);
            contentType.setStoreTermVectorPayloads(true);

            Document document = new Document();
            document.add(new Field("id", "2", idType));
            document.add(new Field("title", "河北雄安新区设立大新闻", titleType));
            document.add(new Field("content", "内容被修改了。。。", contentType));
            indexWriter.updateDocument(new Term("title", "新区"), document);
            indexWriter.commit();
            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
