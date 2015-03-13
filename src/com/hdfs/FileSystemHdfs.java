package com.hdfs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemHdfs {
	static final String PATH = "hdfs://namenode:9000/";
	static final String DIR = "/d1";
	static final String FILE = "/d1/hello";
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI(PATH), new Configuration());
		
		//创建文件夹     hadoop fs -mkdir   /f1
		fileSystem.mkdirs(new Path(DIR));
		
		//上传文件  -put  src  des
		final FSDataOutputStream out = fileSystem.create(new Path(FILE));
		final FileInputStream in = new FileInputStream("F:/lib.rar");
		IOUtils.copyBytes(in, out, 1024, true);
		
		//下载文件   hadoop fs -get src des
		final FSDataInputStream in2 = fileSystem.open(new Path(FILE));
		IOUtils.copyBytes(in2, System.out, 1024, true);
		
		//浏览文件夹
		final FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));
		for (FileStatus fileStatus : listStatus) {
			String isDir = fileStatus.isDir()?"文件夹":"文件";
			final String permission = fileStatus.getPermission().toString();
			final short replication = fileStatus.getReplication();
			final long len = fileStatus.getLen();
			final String path = fileStatus.getPath().toString();
			System.out.println(isDir+"\t"+permission+"\t"+replication+"\t"+len+"\t"+path);
		}
		
		//删除文件夹
		fileSystem.delete(new Path(DIR), true);
	}
}
