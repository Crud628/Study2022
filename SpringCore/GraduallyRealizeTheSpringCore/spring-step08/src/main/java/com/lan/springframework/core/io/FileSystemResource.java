package com.lan.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午7:53:08
 * @TODO
 * 
 */
public class FileSystemResource implements Resource {
	private final File file;

	private final String path;

	public FileSystemResource(File file) {
		this.file = file;
		this.path = file.getPath();
	}

	public FileSystemResource(String path) {
		this.file = new File(path);
		this.path = path;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	public final String getPath() {
		return this.path;
	}

}
