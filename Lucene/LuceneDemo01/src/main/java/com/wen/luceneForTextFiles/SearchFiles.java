package com.wen.luceneForTextFiles;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by wen on 2018/7/4
 */
public class SearchFiles {

    public static void main(String[] args) throws IOException, ParseException {
        String field = "contents";
        String indexPathString = "D:\\Lucene\\index\\";
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPathString)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser queryParser = new QueryParser(field, analyzer);
        //搜索的关键字String
        Query query = queryParser.parse("斯坦福大学");
        System.out.println("Searching for: " + query.toString(field));
        //第二个参数指明要返回的搜索结果的最大数量
        TopDocs results = indexSearcher.search(query, 100);
        ScoreDoc[] hits = results.scoreDocs;
        int totalHitsNum = Math.toIntExact(results.totalHits);
        System.out.println("总共命中了 " + totalHitsNum + " 个文档");
        for (int i = 0; i < totalHitsNum; i++){
            //hits[i].doc获得的是docId，根据这个docId返回对应的document
            Document document = indexSearcher.doc(hits[i].doc);
            System.out.println("命中的文档ID：" + hits[i].doc + "； 得分score：" + hits[i].score + "； 文档路径：" + document.get("path"));
        }
        indexReader.close();
    }
}
