package jp.stbnet.stg.hosho.ikou.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

/**
 * <b>�����񃆁[�e�B���e�B</b>
 *
 * <p>
 * <b>�ύX����</b><br>
 * 2009/01/28 ��ST[A-0137]�Ή�<br>
 * 2009/02/02 ��ST[A-0131]�Ή�<br>
 * 2009/04/XX 090310-1�Ή�<br>
 * 2009/04/27 ��QNo090305-2�Ή�<br>
 * 2014/11/06 JICC STARS�U�Ή�<br>
 * </p>
 *
 * @author Iwata
 *
 */
public class StringUtil {

	/**
	 * <b>�w�肳�ꂽ�����ɂȂ�悤�ɕ�����̓���"0"��t������</b>
	 *
	 * @param str
	 *            �Ώە�����
	 * @param length
	 *            ����
	 * @return String "0"��t��������̒l
	 */
	public static String zeroPadding(String str, int length) {
		return StringUtils.leftPad(str, length, "0");
	}
// 2014.11.6 JICC STARS�U�Ή� add start
	/**
	 * <b>�w�肳�ꂽ�����ɂȂ�悤�ɕ�����̓���"0"��t������(Null�Ή�)</b>
	 *
	 * @param str
	 *            �Ώە�����
	 * @param length
	 *            ����
	 * @return String "0"��t��������̒l
	 */
	public static String zeroPaddingNull(String str, int length) {
		if( str == null ){
			str = "";
		}
		return StringUtils.leftPad(str, length, "0");
	}
// 2014.11.6 JICC STARS�U�Ή� add end

	/**
	 * <b>�ۏؔԍ�(�\�[�g)�̃t�H�[�}�b�g���s���B</b><br>
	 * �ȉ��̒ʂ�ɕۏؔԍ���ҏW����B
	 * <ul>
	 * <li><code>null</code>�̏ꍇ�A�󕶎���Ԃ��B</li>
	 * <li>13��(��)�̏ꍇ�A1XXXXXXXXXXXXX�`���ɕҏW����B</li>
	 * <li> 9��(�V)�̏ꍇ�A20000XXXXXXXXX�`���ɕҏW����B</li>
	 * <li>��L�ȊO�̏ꍇ�A�ҏW�����ɂ��̂܂ܕԂ��B</li>
	 * </ul>
	 *
	 * @param hoshoNo �ۏؔԍ�
	 * @return �ҏW��̕ۏؔԍ�
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
	 * <b>�ۏؔԍ��̃t�H�[�}�b�g���s���B</b> <br>
	 * �ȉ��̒ʂ�ɕۏؔԍ���ҏW����B
	 * <ul>
	 * <li><code>null</code>�̏ꍇ�A�󕶎���Ԃ��B</li>
	 * <li>9���̏ꍇ�AXXXXXX-XX-X�`���ɕҏW����B</li>
	 * <li>13���̏ꍇ�AX-X-XXXX-XXXXX-XX�`���ɕҏW����B</li>
	 * <li>��L�ȊO�̏ꍇ�A�ҏW�����ɂ��̂܂ܕԂ��B</li>
	 * </ul>
	 *
	 * @param hoshoNo
	 *            �ۏؔԍ�
	 * @return �ҏW��̕ۏؔԍ�
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
	 * <b>�w�肵��������̉E���Ɏw�肵�������ɂȂ�܂Ŕ��p�X�y�[�X��t������B</b>
	 *
	 * @param val
	 *            ������
	 * @param length
	 *            ������ + ���p�X�y�[�X�ɂ��钷��
	 * @return length ������ + ���p�X�y�[�X
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
	 * <b>�󕶎��u��</b><br>
	 * �w�肵��������null�̏ꍇ�A�󕶎���Ԃ��B
	 *
	 * @param input
	 *            ������
	 * @return ������̕�����
	 */
	public static String nullToEmpty(String input) {
		if (input == null) {
			return StringUtils.EMPTY;
		}
		return input;
	}

	/**
	 * <b>���g����</b>
	 * @param input ������
	 * @return ���g��������������
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
	 * <b>�E�g����</b>
	 *
	 * @param input
	 *            ������
	 * @return �E�g��������������
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
	 * <b>�����؂�̂ď���</b><br>
	 * �Ώە����񂪍ő�Byte���𒴂��Ȃ��Œ��̕������擪����Ԃ��B
	 *
	 * <pre>
	 *  ��)
	 *  ����������, 15byte �� ����������
	 *  ����������,  8byte �� ��������
	 *  ����������,  5byte �� ����
	 * </pre>
	 *
	 * @param input
	 *            �Ώە�����
	 * @param maxByteSize
	 *            �ő�Byte��
	 * @return �ҏW��̕�����
	 * @since ��ST[A-0137]�Ή�
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
	 * <b>������ϊ�(���l)</b><br>
	 * ���l�𕶎���ɕϊ�����B<code>null</code>�̏ꍇ��<code>null</code>��Ԃ��B
	 *
	 * @param num
	 *            �ϊ��Ώې��l
	 * @return ������ϊ��������l
	 * @since ��ST[A-0131]�Ή�
	 */
	public static String toString(BigDecimal num) {
		if (num == null) {
			return null;
		}
		return num.toString();
	}

	/**
	 * <b>�X�P�[���ݒ�</b><br>
	 * �����񂪐��l�̏ꍇ�A�X�P�[����ݒ肵�����l�������Ԃ��B<br>
	 * �����񂪐��l�ϊ��ł��Ȃ��ꍇ�A����������̂܂܂�Ԃ��B
	 *
	 * @param input
	 *            ������
	 * @param scale
	 *            �X�P�[��
	 * @return �X�P�[���ݒ��̐��l������
	 * @since ��QNo090305-2�Ή�
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
     * �����񂪐��l�ł��邩���肷��B
     *
     * @param str ������
     * @return [true] ���l / [false] ���l�ł͖���
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
     * ��̕����񂪓������ǂ������肷��B
     *
     * @param org ��r�������
     * @param tar ��r�Ώە�����
     * @return [true] ���� / [false] �قȂ�
     */
	public static boolean equals(String org, String tar) {
		if(org == null || tar == null){
			return false;
		}

		return org.equals(tar);
	}

	/**
     * ������P�ƕ�����Q�����������肷��B
     *
     * @param str1 ������P
     * @param str2 ������Q
     * @return [true] ���� / [false] �Ⴄ
     */
    public static boolean isEquals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            return str1.trim().equals(str2.trim());
        }
        return false;
    }

	/**
	 * ������P��������Q�z��ɂ��邩���肷��B
	 *
	 * @param str1 ������P
	 * @param str2 ������Q�z��
	 * @return [true] ���� / [false] �Ȃ�
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
