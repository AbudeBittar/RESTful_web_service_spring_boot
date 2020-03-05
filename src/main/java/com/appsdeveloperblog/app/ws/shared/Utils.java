package com.appsdeveloperblog.app.ws.shared;

/**
 * @author Abudé Bittar
 */

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Utils {   // utilities
	
	public String generateUserId() {
		return UUID.randomUUID().toString();
	}
}
