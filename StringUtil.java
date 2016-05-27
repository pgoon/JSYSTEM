package jp.stbnet.stg.hosho.ikou.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

/**
 * <b>文字列ユーティリティ</b>
 *
 * <p>
 * <b>変更履歴</b><br>
 * 2009/01/28 二次ST[A-0137]対応<br>
 * 2009/02/02 二次ST[A-0131]対応<br>
 * 2009/04/XX 090310-1対応<br>
 * 2009/04/27 障害No090305-2対応<br>
 * 2014/11/06 JICC STARSⅡ対応<br>
 * </p>
 *
 * @author Iwata
 *
 */
public class StringUtil {

	/**
	 * <b>指定された桁数になるように文字列の頭に"0"を付加する</b>
	 *
	 * @param str
	 *            対象文字列
	 * @param length
	 *            桁数
	 * @return String "0"を付加した後の値
	 */
	public static String zeroPadding(String str, int length) {
		return StringUtils.leftPad(str, length, "0");
	}
// 2014.11.6 JICC STARSⅡ対応 add start
	/**
	 * <b>指定された桁数になるように文字列の頭に"0"を付加する(Null対応)</b>
	 *
	 * @param str
	 *            対象文字列
	 * @param length
	 *            桁数
	 * @return String "0"を付加した後の値
	 */
	public static String zeroPaddingNull(String str, int length) {
		if( str == null ){
			str = "";
		}
		return StringUtils.leftPad(str, length, "0");
	}
// 2014.11.6 JICC STARSⅡ対応 add end

	/**
	 * <b>保証番号(ソート)のフォーマットを行う。</b><br>
	 * 以下の通りに保証番号を編集する。
	 * <ul>
	 * <li><code>null</code>の場合、空文字を返す。</li>
	 * <li>13桁(旧)の場合、1XXXXXXXXXXXXX形式に編集する。</li>
	 * <li> 9桁(新)の場合、20000XXXXXXXXX形式に編集する。</li>
	 * <li>上記以外の場合、編集せずにそのまま返す。</li>
	 * </ul>
	 *
	 * @param hoshoNo 保証番号
	 * @return 編集後の保証番号
	 */
	public static String sortedHoshoNo(String hoshoNo) {
		StringBuffer sb = new StringBuffer("");
		final String oldBit = "1";
		final String newBit = "2";

		if (hoshoNo == null) {
			return StringUtils.EMPTY;
		} else if (hoshoNo.length() == 9) {
			sb.append(newBit);
			sb.append(zeroPadding(hoshoNo, 13));
		} else if (hoshoNo.length() == 13) {
			sb.append(oldBit);
			sb.append(hoshoNo);
		} else {
			return hoshoNo;
		}
		return sb.toString();
	}

	/**
	 * <b>保証番号のフォーマットを行う。</b> <br>
	 * 以下の通りに保証番号を編集する。
	 * <ul>
	 * <li><code>null</code>の場合、空文字を返す。</li>
	 * <li>9桁の場合、XXXXXX-XX-X形式に編集する。</li>
	 * <li>13桁の場合、X-X-XXXX-XXXXX-XX形式に編集する。</li>
	 * <li>上記以外の場合、編集せずにそのまま返す。</li>
	 * </ul>
	 *
	 * @param hoshoNo
	 *            保証番号
	 * @return 編集後の保証番号
	 */
	public static String hoshoNoFormatter(String hoshoNo) {
		StringBuffer sb = new StringBuffer();
		final String sep = "-";

		if (hoshoNo == null) {
			return StringUtils.EMPTY;
		} else if (hoshoNo.length() == 9) {
			// 999999-99-9
			sb.append(hoshoNo.substring(0, 6)).append(sep);
			sb.append(hoshoNo.substring(6, 8)).append(sep);
			sb.append(hoshoNo.substring(8, 9));
		} else if (hoshoNo.length() == 13) {
			// 9-9-9999-99999-99
			sb.append(hoshoNo.substring(0, 1)).append(sep);
			sb.append(hoshoNo.substring(1, 2)).append(sep);
			sb.append(hoshoNo.substring(2, 6)).append(sep);
			sb.append(hoshoNo.substring(6, 11)).append(sep);
			sb.append(hoshoNo.substring(11, 13));
		} else {
			return hoshoNo;
		}
		return sb.toString();
	}

	/**
	 * <b>指定した文字列の右側に指定した長さになるまで半角スペースを付加する。</b>
	 *
	 * @param val
	 *            文字列
	 * @param length
	 *            文字列 + 半角スペースにする長さ
	 * @return length 文字列 + 半角スペース
	 */
	public static String spaceRPadding(String val, int length) {
		if (val == null) {
			val = "";
		}
		if (!val.equals("") && val.length() > length) {
			return val;
		}
		String ret = val;
		for (int i = 0; i < length - val.length(); i++) {
			ret += " ";
		}
		return ret;
	}

	/**
	 * <b>空文字置換</b><br>
	 * 指定した文字列がnullの場合、空文字を返す。
	 *
	 * @param input
	 *            文字列
	 * @return 処理後の文字列
	 */
	public static String nullToEmpty(String input) {
		if (input == null) {
			return StringUtils.EMPTY;
		}
		return input;
	}

	/**
	 * <b>左トリム</b>
	 * @param input 文字列
	 * @return 左トリムした文字列
	 */
	public static String ltrim(String input) {
		if (input == null) {
			return null;
		}
		int pos = 0;
		for (; pos < input.length(); pos++) {
			if (" ".indexOf(input.charAt(pos)) < 0) {
				break;
			}
		}
		return input.substring(pos);
	}

	/**
	 * <b>右トリム</b>
	 *
	 * @param input
	 *            文字列
	 * @return 右トリムした文字列
	 */
	public static String rtrim(String input) {
		if (input == null) {
			return null;
		}
		int pos = input.length() - 1;
		for (; pos >= 0; pos--) {
			if (" ".indexOf(input.charAt(pos)) < 0) {
				break;
			}
		}
		return input.substring(0, pos + 1);
	}

	/**
	 * <b>文末切り捨て処理</b><br>
	 * 対象文字列が最大Byte数を超えない最長の文字列を先頭から返す。
	 *
	 * <pre>
	 *  例)
	 *  あいうえお, 15byte → あいうえお
	 *  あいうえお,  8byte → あいうえ
	 *  あいうえお,  5byte → あい
	 * </pre>
	 *
	 * @param input
	 *            対象文字列
	 * @param maxByteSize
	 *            最大Byte数
	 * @return 編集後の文字列
	 * @since 二次ST[A-0137]対応
	 */
	public static String cutEnd(String input, int maxByteSize) {
		if (input == null) {
			return null;
		}
		int lastIndex = input.length();
		for (; lastIndex > 0; lastIndex--) {
			if (input.substring(0, lastIndex).getBytes().length <= maxByteSize) {
				break;
			}
		}
		return input.substring(0, lastIndex);
	}

	/**
	 * <b>文字列変換(数値)</b><br>
	 * 数値を文字列に変換する。<code>null</code>の場合は<code>null</code>を返す。
	 *
	 * @param num
	 *            変換対象数値
	 * @return 文字列変換した数値
	 * @since 二次ST[A-0131]対応
	 */
	public static String toString(BigDecimal num) {
		if (num == null) {
			return null;
		}
		return num.toString();
	}

	/**
	 * <b>スケール設定</b><br>
	 * 文字列が数値の場合、スケールを設定した数値文字列を返す。<br>
	 * 文字列が数値変換できない場合、文字列をそのままを返す。
	 *
	 * @param input
	 *            文字列
	 * @param scale
	 *            スケール
	 * @return スケール設定後の数値文字列
	 * @since 障害No090305-2対応
	 */
	public static String setNumberScale(String input, int scale) {
		String retValue = null;
		try {
			BigDecimal inputNum = new BigDecimal(input).setScale(scale, BigDecimal.ROUND_DOWN);
			retValue = inputNum.toString();
		} catch (Exception e) {
			return input;
		}
		return retValue;
	}

    public static boolean isEmpty(String arg){
    	if(arg == null || arg.trim().equals("")){
    		return true;
    	}

    	return false;
    }

    public static boolean isNotEmpty(String arg){
    	return !isEmpty(arg);
    }

    public static String numberFormat(long number){
    	NumberFormat nfNum = NumberFormat.getNumberInstance();
    	return nfNum.format(number);
    }

    /**
     * 文字列が数値であるか判定する。
     *
     * @param str 文字列
     * @return [true] 数値 / [false] 数値では無い
     */
	public static boolean isNum(String num) {
		try {
			new BigDecimal(num);
			return true;
		} catch(Exception e){
			return false;
		}
	}

    /**
     * 二つの文字列が同じかどうか判定する。
     *
     * @param org 比較基準文字列
     * @param tar 比較対象文字列
     * @return [true] 同じ / [false] 異なる
     */
	public static boolean equals(String org, String tar) {
		if(org == null || tar == null){
			return false;
		}

		return org.equals(tar);
	}

	/**
     * 文字列１と文字列２が同じか判定する。
     *
     * @param str1 文字列１
     * @param str2 文字列２
     * @return [true] 同じ / [false] 違う
     */
    public static boolean isEquals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            return str1.trim().equals(str2.trim());
        }
        return false;
    }

	/**
	 * 文字列１が文字列２配列にあるか判定する。
	 *
	 * @param str1 文字列１
	 * @param str2 文字列２配列
	 * @return [true] ある / [false] なし
	 */
	public static boolean hasEquals(String str1, String... str2) {
		for(int i=0; i<str2.length; i++) {
			if(isEquals(str1, str2[i])) {
				return true;
			}
		}
		return false;
	}



}
