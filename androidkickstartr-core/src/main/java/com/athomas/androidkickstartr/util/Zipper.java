package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

	public static void zip(File srcFolder, File zipFile) throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
		addDir(srcFolder, out);
		out.close();
	}

	private static void addDir(File dirObj, ZipOutputStream out) throws IOException {
		File[] files = dirObj.listFiles();
		byte[] tmpBuf = new byte[1024];

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				addDir(file, out);
				continue;
			}
			FileInputStream in = new FileInputStream(file.getPath());

			String zipPath = file.getPath();
			zipPath = zipPath.replace("target/", "");
			out.putNextEntry(new ZipEntry(zipPath));
			int len;
			while ((len = in.read(tmpBuf)) > 0) {
				out.write(tmpBuf, 0, len);
			}
			out.closeEntry();
			in.close();
		}
	}

}
