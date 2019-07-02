package com.maia.project.services.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {

	public static String getSecurityHash(String text) {
		String hash = DigestUtils.sha256Hex(text);
		return hash;
	}
}
