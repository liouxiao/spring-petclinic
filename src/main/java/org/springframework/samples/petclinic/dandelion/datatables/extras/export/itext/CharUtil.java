package org.springframework.samples.petclinic.dandelion.datatables.extras.export.itext;

import java.util.regex.Pattern;

/**
 * The code is derived from
 * <a href="http://www.micmiu.com/lang/java/java-check-chinese/"> Michael Sun's
 * blog - Java 完美判断中文字符（ 2012-10-24 ）</a>, with slight changes, including:
 *  <ul>
 *  <li>add more Unicode blocks;</li>
 *  <li>add Japanese and Korean support;</li>
 *  </ul>
 *
 */
public class CharUtil {

	// 根据Unicode编码完美的判断中文汉字和符号
	/**
	 * Check whether a character is a Chinese char or not according to its
	 * Unicode code.
	 * 
	 * @param c
	 * @return true if c is a Chinese character.
	 */
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.BOPOMOFO
				|| ub == Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS
				|| ub == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT
				|| ub == Character.UnicodeBlock.KANGXI_RADICALS) {
			return true;
		}
		return false;
	}

	/**
	 * Check whether a character is a Japanese char or not according to its
	 * Unicode code.
	 * 
	 * @param c
	 * @return true if c is a Japanese character.
	 */
	private static boolean isJapanese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.KANBUN // 漢文
				|| ub == Character.UnicodeBlock.HIRAGANA // 平假名
				|| ub == Character.UnicodeBlock.KATAKANA // 片假名
				|| ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) {
			return true;
		}
		return false;
	}

	/**
	 * Check whether a character is a Korean char or not according to its
	 * Unicode code.
	 * 
	 * @param c
	 * @return true if c is a Korean character.
	 */
	private static boolean isKorean(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.HANGUL_JAMO // 韩语字母
				|| ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO // 韩语兼容字母
				|| ub == Character.UnicodeBlock.HANGUL_SYLLABLES
				|| ub == Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A
				|| ub == Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B) {
			return true;
		}
		return false;
	}

	// 完整的判断中文汉字和符号
	/**
	 * Check whether a string contains Chinese character(s).
	 * 
	 * @param strName
	 * @return true if the string contains Chinese character.
	 */
	public static boolean containsChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether a string contains Japanese character(s).
	 * 
	 * @param strName
	 * @return true if the string contains Japanese character.
	 */
	public static boolean containsJapanese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isJapanese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether a string contains Korean character(s).
	 * 
	 * @param strName
	 * @return true if the string contains Korean character.
	 */
	public static boolean containsKorean(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isKorean(c)) {
				return true;
			}
		}
		return false;
	}

	// 只能判断部分CJK字符（CJK统一汉字）
	public static boolean containsCJK(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
		return pattern.matcher(str.trim()).find();
	}

	// 只能判断部分CJK字符（CJK统一汉字）
	public static boolean containsCJKByName(String str) {
		if (str == null) {
			return false;
		}
		// 大小写不同：\\p 表示包含，\\P 表示不包含
		// \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
		String reg = "\\p{InCJK Unified Ideographs}&amp;&amp;\\P{Cn}";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str.trim()).find();
	}
}
