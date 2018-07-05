package com.wen.luceneCommandLineDemo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by wen on 2018/7/4
 *
 * 参照官方Demo编写，需要在运行时添加args命令行参数
 */
public class SearchFiles {
    public static void main(String[] args) throws IOException, ParseException {
        String usage = "Usage:\tjava org.apache.luceneCommandLineDemo.demo.SearchFiles [-index dir] [-field f] [-repeat n] [-queries file] [-query string] [-raw] [-paging hitsPerPage]\n\nSee http://luceneCommandLineDemo.apache.org/core/4_1_0/demo/ for details.";
        if (args.length > 0 && ("-h".equals(args[0]) || "-help".equals(args[0]))){
            System.out.println(usage);
            System.exit(0);
        }
        String index = "index";
        String field = "contents";
        String queries = null;
        int repeat = 0;
        boolean raw = false;
        String queryString = null;
        int hitsPerPage = 10;
        for (int i = 0; i < args.length; i++){
            if ("-index".equals(args[i])){
                index = args[i+1];
                i++;
            }else if("-field".equals(args[i])){
                field = args[i+1];
                i++;
            }else if("-queries".equals(args[i])){
                queries = args[i+1];
                i++;
            }else if("-query".equals(args[i])){
                queryString = args[i+1];
                i++;
            }else if("-repeat".equals(args[i])){
                repeat = Integer.parseInt(args[i+1]);
                i++;
            }else if("-raw".equals(args[i])){
                raw = true;
            }else if("-paging".equals(args[i])){
                hitsPerPage = Integer.parseInt(args[i+1]);
                if(hitsPerPage <= 0){
                    System.out.println("至少要有一个命中项。");
                    System.exit(1);
                }
                i++;
            }
        }
        //读取指定索引目录下的索引
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
        //根据读取到的索引初始化索引搜索对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //初始化分析器
        Analyzer analyzer = new StandardAnalyzer();
        //初始化缓存字符输入流
        BufferedReader in = null;
        if(queries != null){
            //从文件获取字符输入流
            in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
        }else {
            //从标准输入获取字符输入流
            in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        }
        //初始化查询解析器，field为查询的语汇单元的名称
        QueryParser queryParser = new QueryParser(field, analyzer);
        while (true){
            if (queries == null && queryString == null){
                System.out.println("请输入查询的字段：");
            }
            String line = queryString != null ? queryString : in.readLine();
            if (line == null || line.length() == -1){
                break;
            }
            line = line.trim();
            if (line.length() == 0){
                break;
            }
            //利用查询解析器解析查询内容（如进行分词、过滤等）
            Query query = queryParser.parse(line);
            System.out.println("Searching for: " + query.toString(field));

            if (repeat > 0){
                Date start = new Date();
                for (int i = 0; i < repeat; i++){
                    //执行索引查询
                    indexSearcher.search(query, 100);
                }
                Date end = new Date();
                System.out.println("Time: " + (end.getTime() - start.getTime()) + " ms");
            }
            doPagingSearch(in, indexSearcher, query, hitsPerPage, raw, queries == null && queryString == null);
            if (queryString != null){
                break;
            }
        }
        indexReader.close();
    }

    public static void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query, int hitsPerPage, boolean raw, boolean interactive) throws IOException {
        TopDocs results = searcher.search(query, 5 * hitsPerPage);
        ScoreDoc[] hits = results.scoreDocs;
        int totalHitsNum = Math.toIntExact(results.totalHits);
        System.out.println(totalHitsNum + " total matching documents");

        int start = 0;
        //end用于确定当前页展示的搜索结果的最大下标，此时start=0，即当前页是第一页
        int end = Math.min(totalHitsNum, hitsPerPage);
        while (true){
            if (end > hits.length){
                System.out.println("Only results 1 - " + hits.length + " of " + totalHitsNum + " total matching documents  collected.");
                System.out.println("Collect more (y/n) ?");
                String line = in.readLine();
                if (line.length() == 0 || line.charAt(0) == 'n'){
                    break;
                }
                hits = searcher.search(query, totalHitsNum).scoreDocs;
            }
            //end用于确定当前页展示的搜索结果的最大下标
            end = Math.min(hits.length, start + hitsPerPage);

            for (int i = start; i < end; i++) {
                if (raw) {
                    System.out.println("doc = " + hits[i].doc + " score = " + hits[i].score);
                    continue;
                }
                //hits[i].doc获得的是docId，根据这个docId返回对应的document
                Document document = searcher.doc(hits[i].doc);
                String path = document.get("path");
                if (path != null) {
                    System.out.println((i + 1) + ". " + path);
                    String title = document.get("title");
                    if (title != null) {
                        System.out.println(" Title: " + document.get("title"));
                    }
                } else {
                    System.out.println((i + 1) + ". " + "No path for this document");
                }
            }
            if ((!interactive) || (end == 0)){
                break;
            }
            if (totalHitsNum >= end){
                boolean quit = false;
                while(true){
                    System.out.print("Press ");
                    if (start - hitsPerPage >= 0){
                        System.out.print("(p)revious page, ");
                    }
                    if (start + hitsPerPage < totalHitsNum){
                        System.out.print("(n)ext page, ");
                    }
                    System.out.println("(q)uit or enter number to jump to a page.");
                    String line = in.readLine();
                    if (line.length() == 0 || line.charAt(0) == 'q'){
                        quit = true;
                        break;
                    }
                    if (line.charAt(0) == 'p'){
                        //上一页
                        start = Math.max(0, start - hitsPerPage);
                        break;
                    }else if(line.charAt(0) == 'n'){
                        //下一页
                        if (start + hitsPerPage < totalHitsNum){
                            start += hitsPerPage;
                        }
                        break;
                    }else{
                        //跳转到指定页
                        int page = Integer.parseInt(line);
                        if ((page - 1) * hitsPerPage < totalHitsNum){
                            start = (page - 1) * hitsPerPage;
                            break;
                        }else {
                            System.out.println("No such page.");
                        }
                    }
                }
                if (quit) break;
                end = Math.min(totalHitsNum, start + hitsPerPage);
            }
        }
    }
}
