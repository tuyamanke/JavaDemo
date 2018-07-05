package com.wen.luceneCommandLineDemo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Created by wen on 2018/7/4
 *
 * 参照官方Demo编写，需要在运行时添加args命令行参数
 */
public class IndexFiles {
    private IndexFiles() {
    }

    /**
     * 索引一个目录下所有的txt文件
     * @param args
     */
    public static void main(String[] args) {
        String usage = "java org.apache.luceneCommandLineDemo.demo.IndexFiles" +
                " [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n" +
                "INDEX_PATH：是索引存放的目录\n" +
                "DOCS_PATH：是要被索引的文件的目录";
        String indexPath = "index";
        String docsPath = null;
        boolean create = true;
        for (int i = 0; i < args.length; i++){
            if ("-index".equals(args[i])){
                indexPath = args[i+1];
                i++;
            }else if ("-docs".equals(args[i])){
                docsPath = args[i+1];
                i++;
            }else if ("-update".equals(args[i])){
                create = false;
            }
        }
        if (docsPath == null){
            System.err.println("用法：" + usage);
            System.exit(1);
        }

        final Path docsDirectory = Paths.get(docsPath);
        if (!Files.isReadable(docsDirectory)){
            System.out.println("被索引的文件的目录：‘" + docsDirectory.toAbsolutePath() + "’ 不存在或者没有读权限，请检查该目录。");
            System.exit(1);
        }
        Date start = new Date();
        try {
            System.out.println("开始往目录：" + indexPath + " 中写索引...");
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            if (create){
                indexWriterConfig.setOpenMode(OpenMode.CREATE);
            }else {
                indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
            }
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexDocs(indexWriter, docsDirectory);

            indexWriter.close();
            Date end = new Date();
            System.out.println("共耗时：" + (end.getTime() - start.getTime()) + " 毫秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void indexDocs(IndexWriter indexWriter, Path path) throws IOException {
        if (Files.isDirectory(path)){
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes basicFileAttributes) throws IOException {
                    indexDocs(indexWriter, file, basicFileAttributes.lastModifiedTime().toMillis());
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    static void indexDocs(IndexWriter indexWriter, Path file, long lastModified) throws IOException {
        try (InputStream inputStream = Files.newInputStream(file)){
            Document document = new Document();
            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
            document.add(pathField);
            document.add(new LongPoint("modified", lastModified));
            document.add(new TextField("contents", new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))));

            if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE){
                System.out.println("新增的索引文件：" + file);
                indexWriter.addDocument(document);
            }else {
                System.out.println("更新索引文件：" + file);
                indexWriter.updateDocument(new Term("path", file.toString()), document);
            }
        }
    }
}















