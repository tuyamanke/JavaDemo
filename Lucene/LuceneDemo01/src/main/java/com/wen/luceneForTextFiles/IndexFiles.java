package com.wen.luceneForTextFiles;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.*;
import java.util.Date;

/**
 * Created by wen on 2018/7/4
 *
 * 利用SmartCN中文分词器进行分词
 */
public class IndexFiles {
    public static void main(String[] args) throws Exception {
        //存放要被索引的源文件的目录字符串
        String docsPathString = "D:\\Lucene\\document\\";
        //存放索引文件的目录字符串
        String indexPathString = "D:\\Lucene\\index\\";
        //获取源文件路径
        Path docsPath = Paths.get(docsPathString);
        //获取存放索引文件的文件夹
        Directory indexDirectory = FSDirectory.open(Paths.get(indexPathString));
        //初始化分词器
        Analyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);
        File[] docFiles = new File(docsPathString).listFiles();
        System.out.println("开始往目录：" + indexPathString + " 中写索引...");
        Date start= new Date();
        for (File docFile : docFiles){
            System.out.println("文件 " + docFile.getCanonicalPath() + "正在被索引...");
            String temp = fileReadAll(docFile.getCanonicalPath(), "GBK");
            Document document = new Document();
            document.add(new StringField("path", docFile.getPath(), Field.Store.YES));
            document.add(new LongPoint("modified", docFile.lastModified()));
            document.add(new TextField("contents", temp, Field.Store.YES));
            if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE){
                indexWriter.addDocument(document);
            }else{
                indexWriter.updateDocument(new Term("path", docsPath.toString()), document);
            }
        }
        Date end = new Date();
        System.out.println("索引建立完毕，共耗时：" + (end.getTime() - start.getTime()) + " 毫秒");
        indexWriter.close();
    }
    public static String fileReadAll(String fileName, String charSet) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charSet));
        String line = new String();
        String temp = new String();
        while((line = bufferedReader.readLine()) != null) {
            temp += line;
        }
        bufferedReader.close();
        return temp;
    }
}
