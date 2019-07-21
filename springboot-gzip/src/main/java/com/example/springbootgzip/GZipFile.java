package com.example.springbootgzip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipFile {

	private static final String OUTPUT_GZIP_FILE = "E:\\workspace\\gzip\\file1.gz";
	private static final String INPUT_TEXT_FILE = "E:\\workspace\\gzip\\file1.txt";

	private static final String OUTPUT_TEXT_FILE = "E:\\workspace\\gzip\\file.txt";
	private static final String INPUT_GZIP_FILE = "E:\\workspace\\gzip\\file1.gz";

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		GZipFile gZip = new GZipFile();

		// gZip.gzipIt();
		byte[] encoded = gZip.gzipTxtIt("This is Java");
		System.out.println(" Encoded ==> " + encoded);
		String decoded = gZip.gunzipText(encoded);
		System.out.println(" Decoded ==> " + decoded);
		//gZip.gunzipIt();
	}

	public byte[] gzipTxtIt(String text) {

		byte[] bytes = null;
		try {
		
			ByteArrayOutputStream obj = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(obj);
			gzip.write(text.getBytes("UTF-8"));
			gzip.close();
			bytes = obj.toByteArray();

			return bytes;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
	
	
	public String gunzipText(byte[] bytes) throws UnsupportedEncodingException, IOException {

		 GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
	        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
	        String outStr = "";
	        String line;
	        while ((line=bf.readLine())!=null) {
	          outStr += line;
	        }
	        System.out.println("Output String lenght : " + outStr.length());
	        return outStr;

	}

	public void gzipIt() {

		byte[] buffer = new byte[1024];

		try {
			GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(OUTPUT_GZIP_FILE));
			FileInputStream in = new FileInputStream(INPUT_TEXT_FILE);
			int len;
			while ((len = in.read(buffer)) > 0) {
				gzos.write(buffer, 0, len);
			}

			in.close();

			gzos.finish();
			gzos.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void gunzipIt() {

		byte[] buffer = new byte[1024];

		try {

			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));

			FileOutputStream out = new FileOutputStream(OUTPUT_TEXT_FILE);

			int len;
			while ((len = gzis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			gzis.close();
			out.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
