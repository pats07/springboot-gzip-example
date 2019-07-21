package com.example.springbootgzip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GzipController {

	@PostMapping("/gzip-request")
	public String decompressGZipData(@RequestBody byte[] gZipData) {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		
		GZIPInputStream gzis = null;
		StringBuffer outStr = new StringBuffer();
		
		try {

			gzis = new GZIPInputStream(new ByteArrayInputStream(gZipData));
			BufferedReader bf = new BufferedReader(new InputStreamReader(gzis, "UTF-8"));
			String line;
			while ((line = bf.readLine()) != null) {
				outStr.append(line);
			}
			
			logger.info("Output : {}",outStr.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {

			try {
				if (null != gzis) {
					gzis.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}

		}

		return outStr.toString();
	}

}
