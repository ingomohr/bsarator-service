package org.ingomohr.bsarator.service.internal.adapter;

import static java.util.Objects.requireNonNull;

public class SimpleBSAratorTextAdapter {

	public String adapt(String text) {
		requireNonNull(text);

		char[] cs = text.toLowerCase().toCharArray();

		for (int i = 0, n = cs.length; i < n; i++) {
			char c = cs[i];

			if (i == 0 || (i % 3) == 0) {
				c = Character.toUpperCase(c);
				cs[i] = c;
			}
		}

		return String.valueOf(cs);
	}

}