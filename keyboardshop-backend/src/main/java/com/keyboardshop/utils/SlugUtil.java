package com.keyboardshop.utils;

import java.text.Normalizer;

public class SlugUtil
{
	public static String toSlug(String input)
	{
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		String slug = normalized.replaceAll("[^\\p{ASCII}]", "")
            .replaceAll("[^a-zA-Z0-9\\s]", "")
            .trim()
            .replaceAll("\\s+", "-")
            .toLowerCase();

        return slug;
	}
}
