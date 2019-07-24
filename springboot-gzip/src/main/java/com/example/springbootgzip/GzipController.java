package com.example.springbootgzip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GzipController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/gzip-request")
	public String decompressGZipData(@RequestBody byte[] gZipData) throws IOException {

	
		StringBuilder outStr = new StringBuilder();
		try (GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(gZipData))) {
			try (BufferedReader bf = new BufferedReader(new InputStreamReader(gzis, "UTF-8"))) {
				String line;
				while ((line = bf.readLine()) != null) {
					outStr.append(line);
				}
				logger.info("Output : {}", outStr.toString());
			}
		}
		return outStr.toString();
	
	}
	
	@PostMapping("/gzip-http-request")
	public String decompressGZipDataUsingHttpRequest(HttpServletRequest request) throws IOException {

		StringBuilder outStr = new StringBuilder();
		try (GZIPInputStream gzis = new GZIPInputStream(request.getInputStream())) {
			try (BufferedReader bf = new BufferedReader(new InputStreamReader(gzis, "UTF-8"))) {
				String line;
				while ((line = bf.readLine()) != null) {
					outStr.append(line);
				}
				logger.info("Output : {}", outStr.toString());
			}
		}
		return outStr.toString();
	
	}

}
