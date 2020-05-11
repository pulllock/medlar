package me.cxis.es.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateIndexExample {

    public static void main(String[] args) {
        Article article1 = new Article(
            1L,
            "快讯井冈山兰考等28个贫困县脱贫“摘帽”",
            "2月26日，江西省井冈山市宣布脱贫“摘帽”，成为我国贫困退出机制建立后首个脱贫“摘帽”的贫困县。3月27日，河南省兰考县宣布退出贫困县。2017年全国共有28个贫困县脱贫“摘帽”。党的十八大以来，我国脱贫攻坚战取得决定性进展，6000多万贫困人口稳定脱贫，贫困发生率从10.2%降到4%以下，预计2017年减贫人数在1000万人以上",
            232
        );

        Article article2 = new Article(
            2L,
            "快讯河北雄安新区设立",
            "4月1日，我国发布中共中央、国务院决定设立河北雄安新区的通知。设立河北雄安新区是以习近平同志为核心的党中央作出的一项重大的历史性战略选择，是千年大计、国家大事，对于集中疏解北京非首都功能，探索人口经济密集地区优化开发新模式，调整优化京津冀城市布局和空间结构，培育创新驱动发展新引擎，具有重大现实意义和深远历史意义。",
            23444
        );

        Article article3 = new Article(
            3L,
            "快讯“一带一路”国际合作高峰论坛等成功举办",
            "5月14日至15日，“一带一路”国际合作高峰论坛在北京成功举行，国家主席习近平出席开幕式并发表主旨演讲。这是“一带一路”框架下最高规格的国际会议，也是新中国成立以来由中国首倡、中国主办的层级最高、规模最大的多边外交活动。",
            88997
        );

        // IK分词器
        Analyzer analyzer = new IKAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Directory directory = null;
        IndexWriter indexWriter = null;

        // 索引目录
        Path indexPath = Paths.get("index_dir");

        long startTime = System.currentTimeMillis();
        try {
            if (!Files.isReadable(indexPath)) {
                System.out.println("Document directory: " + indexPath.toAbsolutePath() + " does not exist");
                System.exit(1);
            }

            directory = FSDirectory.open(indexPath);
            indexWriter = new IndexWriter(directory, indexWriterConfig);

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

            Document document1 = new Document();
            document1.add(new Field("id", String.valueOf(article1.getId()), idType));
            document1.add(new Field("title", article1.getTitle(), titleType));
            document1.add(new Field("content", article1.getContent(), contentType));
            document1.add(new IntPoint("replay", article1.getReplay()));
            document1.add(new StoredField("replay_display", article1.getReplay()));

            Document document2 = new Document();
            document2.add(new Field("id", String.valueOf(article2.getId()), idType));
            document2.add(new Field("title", article2.getTitle(), titleType));
            document2.add(new Field("content", article2.getContent(), contentType));
            document2.add(new IntPoint("replay", article2.getReplay()));
            document2.add(new StoredField("replay_display", article2.getReplay()));

            Document document3 = new Document();
            document3.add(new Field("id", String.valueOf(article3.getId()), idType));
            document3.add(new Field("title", article3.getTitle(), titleType));
            document3.add(new Field("content", article3.getContent(), contentType));
            document3.add(new IntPoint("replay", article3.getReplay()));
            document3.add(new StoredField("replay_display", article3.getReplay()));

            indexWriter.addDocument(document1);
            indexWriter.addDocument(document2);
            indexWriter.addDocument(document3);

            indexWriter.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                indexWriter.close();
                directory.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        long endTime = System.currentTimeMillis();
        System.out.println("索引文档耗时：" + (endTime - startTime));
    }
}
