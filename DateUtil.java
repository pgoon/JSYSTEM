package jp.stbnet.stg.hosho.ikou.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import jp.stbnet.stg.hosho.ikou.exception.ApplicationException;

/**
 *
 * ���t�����`�N���X
 *
 * @author NSD.LEE
 *
 */
public final class DateUtil {

	public static final Locale LOCALE_JP = new Locale( "ja", "JP", "JP" );

	private DateUtil () {
	}

	/**
	 * �V�X�e�����t���擾����B
	 *
	 * @return �V�X�e�����t
	 */
    public static Date getSysDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * ���t�I�u�W�F�N�g����N�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@�N
	 */
	public static int getYear ( Date date ) {
		return DateUtil.get( date, Calendar.YEAR );
	}

	/**
	 * ���t�I�u�W�F�N�g�ɔN�̒l���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @param year �N
	 * @return �N�̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setYear ( Date date, int year ) {
		return DateUtil.set( date, Calendar.YEAR, year );
	}

	/**
	 * ���t�I�u�W�F�N�g���猎�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@��
	 */
	public static int getMonth ( Date date ) {
		return DateUtil.get( date, Calendar.MONTH ) + 1;
	}

	/**
	 * ���t�I�u�W�F�N�g�Ɍ��̒l���Z�b�g����
	 *
	 * @param date  �Ώۓ��t�I�u�W�F�N�g
	 * @param month ��
	 * @return ���̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setMonth ( Date date, int month ) {
		return DateUtil.set( date, Calendar.MONTH, month - 1 );
	}

	/**
	 * ���t�I�u�W�F�N�g������̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@��
	 */
	public static int getDay ( Date date ) {
		return DateUtil.get( date, Calendar.DAY_OF_MONTH );
	}

	/**
	 * ���t�I�u�W�F�N�g�ɓ��̒l���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @param day  ��
	 * @return ���̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setDay ( Date date, int day ) {
		return DateUtil.set( date, Calendar.DAY_OF_MONTH, day );
	}

	/**
	 * ���t�I�u�W�F�N�g���玞�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@��
	 */
	public static int getHour ( Date date ) {
		return DateUtil.get( date, Calendar.HOUR_OF_DAY );
	}

	/**
	 * ���t�I�u�W�F�N�g�Ɏ��̒l���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @param hour ��
	 * @return ���̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setHour ( Date date, int hour ) {
		return DateUtil.set( date, Calendar.HOUR_OF_DAY, hour );
	}

	/**
	 * ���t�I�u�W�F�N�g���番�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@��
	 */
	public static int getMinute ( Date date ) {
		return DateUtil.get( date, Calendar.MINUTE );
	}

	/**
	 * ���t�I�u�W�F�N�g�ɕ��̒l���Z�b�g����
	 *
	 * @param date   �Ώۓ��t�I�u�W�F�N�g
	 * @param minute ��
	 * @return ���̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setMinute ( Date date, int minute ) {
		return DateUtil.set( date, Calendar.MINUTE, minute );
	}

	/**
	 * ���t�I�u�W�F�N�g����b�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@�b
	 */
	public static int getSecond ( Date date ) {
		return DateUtil.get( date, Calendar.SECOND );
	}

	/**
	 * ���t�I�u�W�F�N�g�ɕb�̒l���Z�b�g����
	 *
	 * @param date   �Ώۓ��t�I�u�W�F�N�g
	 * @param second �b
	 * @return �b�̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setSecond ( Date date, int second ) {
		return DateUtil.set( date, Calendar.SECOND, second );
	}

	/**
	 * ���t�I�u�W�F�N�g����~���b�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return �~���b
	 */
	public static int getMilliSecond ( Date date ) {
		return DateUtil.get( date, Calendar.MILLISECOND );
	}

	/**
	 * ���t�I�u�W�F�N�g�Ƀ~���b�̒l���Z�b�g����
	 *
	 * @param date       �Ώۓ��t�I�u�W�F�N�g
	 * @param millsecond �~���b
	 * @return �~���b�̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setMilliSecond ( Date date, int millisecond ) {
		return DateUtil.set( date, Calendar.MILLISECOND, millisecond );
	}

	private static int get ( Date date, int field ) {
		return DateUtil.get( date, field, new GregorianCalendar() );
	}

	private static int get ( Date date, int field, Calendar c ) {
		c.setTime( date );
		return c.get( field );
	}

	private static Date set ( Date date, int field, int value ) {
		return DateUtil.set( date, field, value, new GregorianCalendar() );
	}

	private static Date set ( Date date, int field, int value, Calendar c ) {
		c.setTime( date );
		c.set( field, value );
		return c.getTime();
	}

	/**
	 * ���t�I�u�W�F�N�g����a����̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@�a���
	 */
	public static int getJapaneseEra ( Date date ) {
		return DateUtil.getJapanese( date, Calendar.ERA );
	}

	/**
	 * ���t�I�u�W�F�N�g�ɘa����̒l���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @param era  �a���
	 * @return �a����̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setJapaneseEra ( Date date, int era ) {
		return DateUtil.setJapanese( date, Calendar.ERA, era );
	}

	/**
	 * ���t�I�u�W�F�N�g����a��̔N�̒l���擾����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return�@�a��̔N
	 */
	public static int getJapaneseYear ( Date date ) {
		return DateUtil.getJapanese( date, Calendar.YEAR );
	}

	/**
	 * ���t�I�u�W�F�N�g�ɘa��̔N�̒l���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @param year �a��̔N
	 * @return �a��̔N�̒l���X�V���ꂽ���t�I�u�W�F�N�g
	 */
	public static Date setJapaneseYear ( Date date, int year ) {
		return DateUtil.setJapanese( date, Calendar.YEAR, year );
	}

	private static int getJapanese ( Date date, int field ) {
		return DateUtil.get( date, field, DateUtil.generateJapaneseCalendar() );
	}

	private static Date setJapanese ( Date date, int field, int value ) {
		return DateUtil.set( date, field, value, DateUtil.generateJapaneseCalendar() );
	}

	private static Calendar generateJapaneseCalendar () {
		return Calendar.getInstance( LOCALE_JP );
	}



	/**
	 * ���ݓ��̃I�u�W�F�N�g���擾����
	 *
	 * �����b�ɂ́u0�v���Z�b�g���ꂽ���
	 *
	 * @return ���ݓ�
	 */
	public static Date nowDate () {
		return DateUtil.suppressTime( new Date() );
	}

	/**
	 * ���ݎ����̃I�u�W�F�N�g���擾����
	 *
	 * �N�����ɂ́u0�v���Z�b�g���ꂽ���
	 *
	 * @return ���ݎ���
	 */
	public static Date nowTime () {
		return DateUtil.suppressDate( new Date() );
	}

	/**
	 * ���t�t�H�[�}�b�^�𐶐�����
	 *
	 * @param pattern ������������
	 * @return �t�H�[�}�b�^
	 */
	public static DateFormat generateFormat ( String pattern ) {
		return new SimpleDateFormat( pattern );
	}

	/**
	 * ���P�[�������w�肵�����t�t�H�[�}�b�^�𐶐�����
	 *
	 * @param pattern ������������
	 * @param locale  ���P�[�����
	 * @return �t�H�[�}�b�^
	 */
	public static DateFormat generateFormat ( String pattern, Locale locale ) {
		return new SimpleDateFormat( pattern, locale );
	}

	/**
	 * �a��ϊ��p���t�t�H�[�}�b�^�𐶐�����
	 *
	 * @param pattern ������������
	 * @return �t�H�[�}�b�^
	 */
	public static DateFormat generateJapaneseFormatter ( String pattern ) {
		return DateUtil.generateFormat( pattern, LOCALE_JP );
	}



	/**
	 * ���t�I�u�W�F�N�g�̔N�����Ɂu0�v���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return �Z�b�g��̓��t�I�u�W�F�N�g
	 */
	public static Date suppressDate ( Date date ) {
		date = DateUtil.setYear( date, 0 );
		date = DateUtil.setMonth( date, 0 );
		date = DateUtil.setDay( date, 0 );
		return date;
	}

	/**
	 * ���t�I�u�W�F�N�g�̎����b�~���b�Ɂu0�v���Z�b�g����
	 *
	 * @param date �Ώۓ��t�I�u�W�F�N�g
	 * @return �Z�b�g�̓��t�I�u�W�F�N�g
	 */
	public static Date suppressTime ( Date date ) {
		date = DateUtil.setHour( date, 0 );
		date = DateUtil.setMinute( date, 0 );
		date = DateUtil.setSecond( date, 0 );
		date = DateUtil.setMilliSecond( date, 0 );
		return date;
	}


	/**
	 * ���� pattern �̌`���Ńt�H�[�}�b�g���ꂽ���ݎ����̕�����\����Ԃ�
	 *
	 * @param pattern	�����t�H�[�}�b�g
	 * @return			���� pattern �̌`���Ńt�H�[�}�b�g���ꂽ���ݎ���
	 */
	public static String formatNow ( String pattern ) {
		return DateUtil.formatNow( DateUtil.generateFormat( pattern ) );
	}

	/**
	 * ���� formatter �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���ݎ����̕�����\����Ԃ�
	 *
	 * @param formatter		�����t�H�[�}�b�g
	 * @return				���� pattern �̌`���Ńt�H�[�}�b�g���ꂽ���ݎ���
	 */
	public static String formatNow ( DateFormat formatter ) {
		return formatter.format( new Date() );
	}

	/**
	 * ���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 *
	 * @param date		�w�����
	 * @param pattern	�����t�H�[�}�b�g
	 * @return			���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatDate ( Date date, String pattern ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern ) );
	}

	/**
	 * ���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 * �����`���́A���� locale �Ŏw�肳���V���{�����g�p���ăt�H�[�}�b�g���s��
	 *
	 * @param date		�w�����
	 * @param pattern	�����t�H�[�}�b�g
	 * @param locale	�w�胍�P�[��
	 * @return			���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatDate ( Date date, String pattern, Locale locale ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern, locale ) );
	}

	/**
	 *  ���� formatter �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 *
	 * @param formatter		�����t�H�[�}�b�g
	 * @return				���� formatter �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatDate ( Date date, DateFormat formatter ) {
		return formatter.format( date );
	}

	/**
	 * ���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 * date �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				�w�����
	 * @param pattern			�����t�H�[�}�b�g
	 * @param defaultString		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return					���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatDate ( Date date, String pattern, String defaultString ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern ), defaultString );
	}

	/**
	 * ���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 * �����`���́A���� locale �Ŏw�肳���V���{�����g�p���ăt�H�[�}�b�g���s��
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				�w�����
	 * @param pattern			�����t�H�[�}�b�g
	 * @param locale			�w�胍�P�[��
	 * @param defaultString		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return					���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatDate ( Date date, String pattern, Locale locale, String defaultString ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern, locale ), defaultString );
	}

	/**
	 * ���� formatter �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				�w�����
	 * @param formatter			�����t�H�[�}�b�g
	 * @param defaultString		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return					���� formatter �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatDate ( Date date, DateFormat formatter, String defaultString ) {
		if ( date == null ) return defaultString;

		return formatter.format( date );
	}

	/**
	 * ���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 * ���{�̃��P�[���̃V���{�����܂ރt�H�[�}�b�g���g�p����
	 *
	 * @param date			�w�����
	 * @param pattern		�����t�H�[�}�b�g
	 * @return				���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatJapaneseDate ( Date date, String pattern ) {
		return DateUtil.formatDate( date, pattern, LOCALE_JP );
	}

	/**
	 * ���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\����Ԃ�
	 * ���{�̃��P�[���̃V���{�����܂ރt�H�[�}�b�g���g�p����
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				�w�����
	 * @param pattern			�����t�H�[�}�b�g
	 * @param defaultString		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return					���� pattern �Ŏw�肵���`���Ńt�H�[�}�b�g���ꂽ���� date �̕�����\��
	 */
	public static String formatJapaneseDate ( Date date, String pattern, String defaultString ) {
		return DateUtil.formatDate( date, pattern, LOCALE_JP, defaultString );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 *
	 * @param date				������\�����ꂽ���t
	 * @param pattern			���t�̃t�H�[�}�b�g
	 * @return					���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 * @throws ParseException
	 */
	public static Date parseDate ( String date, String pattern ) throws ParseException {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern ) );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 *
	 * @param date				������\�����ꂽ���t
	 * @param pattern			���t�̃t�H�[�}�b�g
	 * @param locale			���P�[��
	 * @return					���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 * @throws ParseException
	 */
	public static Date parseDate ( String date, String pattern, Locale locale ) throws ParseException {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern, locale ) );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 *
	 * @param date				������\�����ꂽ���t
	 * @param formatter			���t�̃t�H�[�}�b�g
	 * @return					���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 * @throws ParseException
	 */
	public static Date parseDate ( String date, DateFormat formatter ) throws ParseException {
		return formatter.parse( date );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				������\�����ꂽ���t
	 * @param pattern			���t�̃t�H�[�}�b�g
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return			���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 */
	public static Date parseDate ( String date, String pattern, Date defaultDate ) {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern ), defaultDate );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				������\�����ꂽ���t
	 * @param pattern			���t�̃t�H�[�}�b�g
	 * @param locale			���P�[��
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return			���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 */
	public static Date parseDate ( String date, String pattern, Locale locale, Date defaultDate ) {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern, locale ), defaultDate );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				������\�����ꂽ���t
	 * @param formatter			���t�̃t�H�[�}�b�g
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return				���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 */
	public static Date parseDate ( String date, DateFormat formatter, Date defaultDate ) {
		if ( date == null ) return defaultDate;

		try {
			return formatter.parse( date );
		} catch ( Exception ex ) {
			return defaultDate;
		}
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 * ���P�[���͓��{�̂��̂��g�p����
	 *
	 * @param date				������\�����ꂽ���t
	 * @param pattern			���t�̃t�H�[�}�b�g
	 * @return					���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 * @throws ParseException
	 */
	public static Date parseJapaneseDate ( String date, String pattern ) throws ParseException {
		return DateUtil.parseDate( date, pattern, LOCALE_JP );
	}

	/**
	 * ���� date �̕�����\�����ꂽ���t���ADate �I�u�W�F�N�g�ɕϊ�����
	 * ���P�[���͓��{�̂��̂��g�p����
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param date				������\�����ꂽ���t
	 * @param pattern			���t�̃t�H�[�}�b�g
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ���镶����
	 * @return					���� date �̕�����\�����ꂽ���t�� Date �I�u�W�F�N�g
	 * @throws ParseException
	 */
	public static Date parseJapaneseDate ( String date, String pattern, Date defaultDate ) {
		return DateUtil.parseDate( date, pattern, LOCALE_JP, defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year ) {
		return DateUtil.generateDate( year, 1 );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year, int month ) {
		return DateUtil.generateDate( year, month, 1 );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year, int month, int day ) {
		return DateUtil.generateDate( year, month, day, 0 );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year, int month, int day, int hour ) {
		return DateUtil.generateDate( year, month, day, hour, 0 );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year, int month, int day, int hour, int minute ) {
		return DateUtil.generateDate( year, month, day, hour, minute, 0 );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year, int month, int day, int hour, int minute, int second ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, 0 );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( int year, int month, int day, int hour, int minute, int second, int millisecond ) {
		Date ret = new GregorianCalendar( year, month - 1, day, hour, minute, second ).getTime();
		ret = DateUtil.setMilliSecond( ret, millisecond );
		return ret;
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year ) {
		return DateUtil.generateDate( year, "1" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month ) {
		return DateUtil.generateDate( year, month, "1" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day ) {
		return DateUtil.generateDate( year, month, day, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour ) {
		return DateUtil.generateDate( year, month, day, hour, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateDate( year, month, day, hour, minute, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second, String millisecond ) {
		int iyear, imonth, iday, ihour, iminute, isecond, imillisecond;
		iyear = imonth = iday = ihour = iminute = isecond = imillisecond = 0;
		try {
			iyear = Integer.parseInt( year );
			imonth = Integer.parseInt( month );
			iday = Integer.parseInt( day );
			ihour = Integer.parseInt( hour );
			iminute = Integer.parseInt( minute );
			isecond = Integer.parseInt( second );
			imillisecond = Integer.parseInt( millisecond );
		} catch ( Exception ex ) {
			throw new ApplicationException( ex );
		}
		return DateUtil.generateDate( iyear, imonth, iday, ihour, iminute, isecond, imillisecond );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, Date defaultDate ) {
		return DateUtil.generateDate( year, "1", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, Date defaultDate ) {
		return DateUtil.generateDate( year, month, "1", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, hour, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, hour, minute, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �܂��Adate �� null �̏ꍇ�A���� defaultString ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @param defaultDate		date �� null �ł������ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second, String millisecond, Date defaultDate ) {
		try {
			return DateUtil.generateDate( year, month, day, hour, minute, second, millisecond );
		} catch ( Exception ex ) {
			return defaultDate;
		}
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year ) {
		return DateUtil.generateDateDefNull( year, "1" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year, String month ) {
		return DateUtil.generateDateDefNull( year, month, "1" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year, String month, String day ) {
		return DateUtil.generateDateDefNull( year, month, day, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour ) {
		return DateUtil.generateDateDefNull( year, month, day, hour, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateDateDefNull( year, month, day, hour, minute, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateDateDefNull( year, month, day, hour, minute, second, "0" );
	}

	/**
	 * �����Ŏw�肵�����t�I�u�W�F�N�g��Ԃ�
	 * �t�H�[�}�b�g�Ɏ��s�����ꍇ�ɂ� null ��Ԃ�
	 *
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour, String minute, String second, String millisecond ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, millisecond, (Date)null );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year ) {
		return DateUtil.generateJapaneseDate( era, year, 1 );
	}


	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year, int month ) {
		return DateUtil.generateJapaneseDate( era, year, month, 1 );
	}


	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, 0 );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, 0 );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour, int minute ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, 0 );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour, int minute, int second ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, 0 );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour, int minute, int second, int millisecond ) {
		Calendar calendar = generateJapaneseCalendar();
		calendar.set( Calendar.ERA, era );
		calendar.set( year, month - 1, day, hour, minute, second );
		calendar.set( Calendar.MILLISECOND, millisecond );
		return calendar.getTime();
	}


	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year ) {
		return DateUtil.generateJapaneseDate( era, year, "1" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month ) {
		return DateUtil.generateJapaneseDate( era, year, month, "1" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, String second, String millisecond ) {
		int iera, iyear, imonth, iday, ihour, iminute, isecond, imillisecond;
		iera = iyear = imonth = iday = ihour = iminute = isecond = imillisecond = 0;
		try {
			iera = JapaneseEraEnum.convertCode( era ).getCalendarValue();
			iyear = Integer.parseInt( year );
			imonth = Integer.parseInt( month );
			iday = Integer.parseInt( day );
			ihour = Integer.parseInt( hour );
			iminute = Integer.parseInt( minute );
			isecond = Integer.parseInt( second );
			imillisecond = Integer.parseInt( millisecond );
		} catch ( Exception ex ) {
			throw new ApplicationException( ex );
		}
		return DateUtil.generateJapaneseDate( iera, iyear, imonth, iday, ihour, iminute, isecond, imillisecond );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, "1", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, "1", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, String second, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, "0", defaultDate );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�A�f�t�H���g�ň��� defaultDate �̓��t��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @param defaultDate		���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�Ƀf�t�H���g�ŕԂ������t
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDate (
			String era, String year, String month, String day, String hour, String minute, String second, String millisecond, Date defaultDate ) {
		try {
			return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, millisecond );
		} catch ( Exception ex ) {
			return defaultDate;
		}
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, "1" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, "1" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, hour, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, hour, minute, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, hour, minute, second, "0" );
	}

	/**
	 * �����Ŏw�肵�����{���P�[���̓��t�I�u�W�F�N�g��Ԃ�
	 * ���t�I�u�W�F�N�g�ւ̕ϊ������s�����ꍇ�� null ��Ԃ�
	 *
	 * @param era		����
	 * @param year		�N
	 * @param month		��
	 * @param day		��
	 * @param hour		��
	 * @param minute	��
	 * @param second   �b
	 * @param millisecond �~���b
	 * @return			�����Ŏw�肵�����t�I�u�W�F�N�g
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour, String minute, String second, String millisecond ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, millisecond, (Date)null );
	}

	/**
	 * ���� date �̔N�Ɉ��� amount �̐��𑫂�
	 *
	 * @param date		�N�𑫂����t�I�u�W�F�N�g
	 * @param amount	������
	 * @return			���� amount �̐����N�ɑ����ꂽ���t�I�u�W�F�N�g
	 */
	public static Date addYear ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.YEAR, amount );
	}

	/**
	 * ���� date �̌��Ɉ��� amount �̐��𑫂�
	 *
	 * @param date		���𑫂����t�I�u�W�F�N�g
	 * @param amount	������
	 * @return			���� amount �̐������ɑ����ꂽ���t�I�u�W�F�N�g
	 */
	public static Date addMonth ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.MONTH, amount );
	}

	/**
	 * ���� date �̓��Ɉ��� amount �̐��𑫂�
	 *
	 * @param date		���𑫂����t�I�u�W�F�N�g
	 * @param amount	������
	 * @return			���� amount �̐������ɑ����ꂽ���t�I�u�W�F�N�g
	 */
	public static Date addDay ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.DAY_OF_MONTH, amount );
	}

	/**
	 * ���� date �̎��Ɉ��� amount �̐��𑫂�
	 *
	 * @param date		���𑫂����t�I�u�W�F�N�g
	 * @param amount	������
	 * @return			���� amount �̐������ɑ����ꂽ���t�I�u�W�F�N�g
	 */
	public static Date addHour ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.HOUR_OF_DAY, amount );
	}

	/**
	 * ���� date �̕��Ɉ��� amount �̐��𑫂�
	 *
	 * @param date		���𑫂����t�I�u�W�F�N�g
	 * @param amount	������
	 * @return			���� amount �̐������ɑ����ꂽ���t�I�u�W�F�N�g
	 */
	public static Date addMinute ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.MINUTE, amount );
	}

	/**
	 * ���� date �̕b�Ɉ��� amount �̐��𑫂�
	 *
	 * @param date		�b�𑫂����t�I�u�W�F�N�g
	 * @param amount	������
	 * @return			���� amount �̐����b�ɑ����ꂽ���t�I�u�W�F�N�g
	 */
	public static Date addSecond ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.SECOND, amount );
	}

	/**
	 * ���� date �́A���� field �Ŏw�肳�ꂽ�t�B�[���h�Ɉ��� amout �̐��𑫂�
	 *
	 * @param date		���t�I�u�W�F�N�g
	 * @param field		���� amout �̐��𑫂��t�B�[���h
	 * @param amount	������
	 * @return			���� field �Ŏw�肳�ꂽ�t�B�[���h�Ɉ��� amout �̐��������ꂽ���t�I�u�W�F�N�g
	 */
	private static Date add ( Date date, int field, int amount ) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( field, amount );
		return gc.getTime();
	}

	/**
	 * ���� base �ƈ��� another �̓��t�̍�����Ԃ�
	 *
	 * @param base		��r���̓��t
	 * @param another	��r�Ώۂ̓��t
	 * @return			���� base �ƈ��� another �̓��t�̍���
	 */
	public static long diffDay ( Date base, Date another ) {
		return (base.getTime() - another.getTime()) / (1000 * 60 * 60 * 24);
	}


	/**
	 * ���� date �����邤�N���ǂ�������������
	 *
	 * @param date		�����Ώۂ̓��t
	 * @return			���� date �̔N�����邤�N�ł���ΐ^�A�����łȂ���΋U
	 */
	public static boolean isLeapYear ( Date date ) {
		if ( date == null ) return false;
		int year = DateUtil.getYear( date );
		return ((year % 400) == 0 || ((year % 100) != 0 && (year % 4) == 0));
	}

    /**
     * ���E���E�b�E�ؕb���u0�v�ɏ���������B
     *
     * @param date ������������t
     * @return ���E���E�b�E�ؕb���u0�v�ɏ��������ꂽ���t
     */
    public static Date resetTime(Date date) {
    	Calendar tempCal = Calendar.getInstance();
    	tempCal.setTime(date);
    	tempCal.set(Calendar.HOUR_OF_DAY, 0);
    	tempCal.set(Calendar.MINUTE, 0);
    	tempCal.set(Calendar.SECOND, 0);
    	tempCal.set(Calendar.MILLISECOND, 0);
    	return tempCal.getTime();
    }

    /**
     * �J�����_�̋K���Ɋ�Â��āA�w�肳�ꂽ���ԗʂ�N�����ɉ��Z�܂��͌��Z����B
     *
     * @param date ���Z�܂��͌��Z������t
     * @param year �w�肳�ꂽ���ԗʁi�N�j
     * @param month �w�肳�ꂽ���ԗʁi���j
     * @param day �w�肳�ꂽ���ԗʁi���j
     * @return �N�����Ɏw�肳�ꂽ���ԗʂ����Z�܂��͌��Z���ꂽ���t
     */
	public static Date addDate(Date date, int year, int month, int day) {
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(date);
		tempCal.add(Calendar.YEAR, year);
		tempCal.add(Calendar.MONTH, month);
		tempCal.add(Calendar.DAY_OF_MONTH, day);
		Date returnDate = tempCal.getTime();
		return returnDate;
	}

	/**
	 * ����t�ƌ���t�̓�������Ԃ��B
	 *
	 * @param Date ����t
	 * @param Date ����t
	 * @return long ������
	 */
	public static long betweenDays(Date beforeDate, Date afterDate) {
		return betweenDays(beforeDate, afterDate, 0);
	}

	/**
	 * ����t�ƌ���t�̓�������Ԃ��B
	 *
	 * @param Date ����t
	 * @param Date ����t
	 * @param long �������ɉ��Z�������
	 * @return long ������
	 * @throws ParseException
	 */
	public static long betweenDays(String beforeDate, String afterDate, long addDay) throws ParseException {
		return betweenDays(toDate(beforeDate), toDate(afterDate), addDay);
	}

	/**
	 * ����t�ƌ���t�̓�������Ԃ��B
	 *
	 * @param Date ����t
	 * @param Date ����t
	 * @return long ������
	 * @throws ParseException
	 */
	public static long betweenDays(String beforeDate, String afterDate) throws ParseException {
		return betweenDays(toDate(beforeDate), toDate(afterDate));
	}

	/**
	 * ��̓��t�̓�������ԋp����
	 *
	 * @param Date �N����
	 * @param Date �N����
	 * @param long �������ɉ��Z�������
	 * @return long ������
	 */
	public static long betweenDays(Date beforeDate, Date afterDate, long addDay) {

		// �P����86400000�~���[�Z�J���h
		return (afterDate.getTime() - beforeDate.getTime()) / 86400000 + addDay;
	}

	/**
	 * �w�肳�ꂽ�p�^�[���ƃf�t�H���g���P�[���̃f�t�H���g���t�t�H�[�}�b�g�L�����g���� SimpleDateFormat ���\�z���A
	 * ������̐擪����e�L�X�g����͂��ē��t�𐶐�����B
	 *
	 * @param str ������
	 * @param format �p�^�[��
	 * @return ��͂��Đ������ꂽ���t
	 * @throws ParseException
	 */
	public static Date toDate(String str, String format) throws ParseException {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(str);
	}

	/**
	 * ������̐擪����e�L�X�g����͂��ē��t�𐶐�����B
	 *
	 * @param str ������
	 * @return ��͂��Đ������ꂽ���t
	 * @throws ParseException
	 */
	public static Date toDate(String str) throws ParseException {
		return toDate(str, "yyyy/MM/dd");
	}

	/**
     * long�^�����ԕ\���ɒ����܂��B
     *
     * @param time ���Ԃ�\��long�`
     * @return XX:XX:XX.XXX�`���̕�����
     */
    public static String timeFormat(long time) {
        int milli = (int) time % 1000;
        time /= 1000;
        int second = (int) time % 60;
        time /= 60;
        int minute = (int) time % 60;
        int hour = (int) time / 60;
        return String.format("%02d�� %02d�� %02d�b %03d", hour, minute, second, milli);
    }
}
