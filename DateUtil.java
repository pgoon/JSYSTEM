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
 * 日付操作定義クラス
 *
 * @author NSD.LEE
 *
 */
public final class DateUtil {

	public static final Locale LOCALE_JP = new Locale( "ja", "JP", "JP" );

	private DateUtil () {
	}

	/**
	 * システム日付を取得する。
	 *
	 * @return システム日付
	 */
    public static Date getSysDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 日付オブジェクトから年の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　年
	 */
	public static int getYear ( Date date ) {
		return DateUtil.get( date, Calendar.YEAR );
	}

	/**
	 * 日付オブジェクトに年の値をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @param year 年
	 * @return 年の値が更新された日付オブジェクト
	 */
	public static Date setYear ( Date date, int year ) {
		return DateUtil.set( date, Calendar.YEAR, year );
	}

	/**
	 * 日付オブジェクトから月の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　月
	 */
	public static int getMonth ( Date date ) {
		return DateUtil.get( date, Calendar.MONTH ) + 1;
	}

	/**
	 * 日付オブジェクトに月の値をセットする
	 *
	 * @param date  対象日付オブジェクト
	 * @param month 月
	 * @return 月の値が更新された日付オブジェクト
	 */
	public static Date setMonth ( Date date, int month ) {
		return DateUtil.set( date, Calendar.MONTH, month - 1 );
	}

	/**
	 * 日付オブジェクトから日の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　日
	 */
	public static int getDay ( Date date ) {
		return DateUtil.get( date, Calendar.DAY_OF_MONTH );
	}

	/**
	 * 日付オブジェクトに日の値をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @param day  日
	 * @return 日の値が更新された日付オブジェクト
	 */
	public static Date setDay ( Date date, int day ) {
		return DateUtil.set( date, Calendar.DAY_OF_MONTH, day );
	}

	/**
	 * 日付オブジェクトから時の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　時
	 */
	public static int getHour ( Date date ) {
		return DateUtil.get( date, Calendar.HOUR_OF_DAY );
	}

	/**
	 * 日付オブジェクトに時の値をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @param hour 時
	 * @return 時の値が更新された日付オブジェクト
	 */
	public static Date setHour ( Date date, int hour ) {
		return DateUtil.set( date, Calendar.HOUR_OF_DAY, hour );
	}

	/**
	 * 日付オブジェクトから分の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　分
	 */
	public static int getMinute ( Date date ) {
		return DateUtil.get( date, Calendar.MINUTE );
	}

	/**
	 * 日付オブジェクトに分の値をセットする
	 *
	 * @param date   対象日付オブジェクト
	 * @param minute 分
	 * @return 分の値が更新された日付オブジェクト
	 */
	public static Date setMinute ( Date date, int minute ) {
		return DateUtil.set( date, Calendar.MINUTE, minute );
	}

	/**
	 * 日付オブジェクトから秒の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　秒
	 */
	public static int getSecond ( Date date ) {
		return DateUtil.get( date, Calendar.SECOND );
	}

	/**
	 * 日付オブジェクトに秒の値をセットする
	 *
	 * @param date   対象日付オブジェクト
	 * @param second 秒
	 * @return 秒の値が更新された日付オブジェクト
	 */
	public static Date setSecond ( Date date, int second ) {
		return DateUtil.set( date, Calendar.SECOND, second );
	}

	/**
	 * 日付オブジェクトからミリ秒の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return ミリ秒
	 */
	public static int getMilliSecond ( Date date ) {
		return DateUtil.get( date, Calendar.MILLISECOND );
	}

	/**
	 * 日付オブジェクトにミリ秒の値をセットする
	 *
	 * @param date       対象日付オブジェクト
	 * @param millsecond ミリ秒
	 * @return ミリ秒の値が更新された日付オブジェクト
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
	 * 日付オブジェクトから和暦元号の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　和暦元号
	 */
	public static int getJapaneseEra ( Date date ) {
		return DateUtil.getJapanese( date, Calendar.ERA );
	}

	/**
	 * 日付オブジェクトに和暦元号の値をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @param era  和暦元号
	 * @return 和暦元号の値が更新された日付オブジェクト
	 */
	public static Date setJapaneseEra ( Date date, int era ) {
		return DateUtil.setJapanese( date, Calendar.ERA, era );
	}

	/**
	 * 日付オブジェクトから和暦の年の値を取得する
	 *
	 * @param date 対象日付オブジェクト
	 * @return　和暦の年
	 */
	public static int getJapaneseYear ( Date date ) {
		return DateUtil.getJapanese( date, Calendar.YEAR );
	}

	/**
	 * 日付オブジェクトに和暦の年の値をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @param year 和暦の年
	 * @return 和暦の年の値が更新された日付オブジェクト
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
	 * 現在日のオブジェクトを取得する
	 *
	 * 時分秒には「0」がセットされた状態
	 *
	 * @return 現在日
	 */
	public static Date nowDate () {
		return DateUtil.suppressTime( new Date() );
	}

	/**
	 * 現在時刻のオブジェクトを取得する
	 *
	 * 年月日には「0」がセットされた状態
	 *
	 * @return 現在時刻
	 */
	public static Date nowTime () {
		return DateUtil.suppressDate( new Date() );
	}

	/**
	 * 日付フォーマッタを生成する
	 *
	 * @param pattern 書式化文字列
	 * @return フォーマッタ
	 */
	public static DateFormat generateFormat ( String pattern ) {
		return new SimpleDateFormat( pattern );
	}

	/**
	 * ロケール情報を指定した日付フォーマッタを生成する
	 *
	 * @param pattern 書式化文字列
	 * @param locale  ロケール情報
	 * @return フォーマッタ
	 */
	public static DateFormat generateFormat ( String pattern, Locale locale ) {
		return new SimpleDateFormat( pattern, locale );
	}

	/**
	 * 和暦変換用日付フォーマッタを生成する
	 *
	 * @param pattern 書式化文字列
	 * @return フォーマッタ
	 */
	public static DateFormat generateJapaneseFormatter ( String pattern ) {
		return DateUtil.generateFormat( pattern, LOCALE_JP );
	}



	/**
	 * 日付オブジェクトの年月日に「0」をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @return セット後の日付オブジェクト
	 */
	public static Date suppressDate ( Date date ) {
		date = DateUtil.setYear( date, 0 );
		date = DateUtil.setMonth( date, 0 );
		date = DateUtil.setDay( date, 0 );
		return date;
	}

	/**
	 * 日付オブジェクトの時分秒ミリ秒に「0」をセットする
	 *
	 * @param date 対象日付オブジェクト
	 * @return セットの日付オブジェクト
	 */
	public static Date suppressTime ( Date date ) {
		date = DateUtil.setHour( date, 0 );
		date = DateUtil.setMinute( date, 0 );
		date = DateUtil.setSecond( date, 0 );
		date = DateUtil.setMilliSecond( date, 0 );
		return date;
	}


	/**
	 * 引数 pattern の形式でフォーマットされた現在時刻の文字列表現を返す
	 *
	 * @param pattern	日時フォーマット
	 * @return			引数 pattern の形式でフォーマットされた現在時刻
	 */
	public static String formatNow ( String pattern ) {
		return DateUtil.formatNow( DateUtil.generateFormat( pattern ) );
	}

	/**
	 * 引数 formatter で指定した形式でフォーマットされた現在時刻の文字列表現を返す
	 *
	 * @param formatter		日時フォーマット
	 * @return				引数 pattern の形式でフォーマットされた現在時刻
	 */
	public static String formatNow ( DateFormat formatter ) {
		return formatter.format( new Date() );
	}

	/**
	 * 引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 *
	 * @param date		指定日時
	 * @param pattern	日時フォーマット
	 * @return			引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatDate ( Date date, String pattern ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern ) );
	}

	/**
	 * 引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 * 日時形式は、引数 locale で指定されるシンボルを使用してフォーマットを行う
	 *
	 * @param date		指定日時
	 * @param pattern	日時フォーマット
	 * @param locale	指定ロケール
	 * @return			引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatDate ( Date date, String pattern, Locale locale ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern, locale ) );
	}

	/**
	 *  引数 formatter で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 *
	 * @param formatter		日時フォーマット
	 * @return				引数 formatter で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatDate ( Date date, DateFormat formatter ) {
		return formatter.format( date );
	}

	/**
	 * 引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 * date が null の場合、引数 defaultString を返す
	 *
	 * @param date				指定日時
	 * @param pattern			日時フォーマット
	 * @param defaultString		date が null であった場合にデフォルトで返される文字列
	 * @return					引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatDate ( Date date, String pattern, String defaultString ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern ), defaultString );
	}

	/**
	 * 引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 * 日時形式は、引数 locale で指定されるシンボルを使用してフォーマットを行う
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				指定日時
	 * @param pattern			日時フォーマット
	 * @param locale			指定ロケール
	 * @param defaultString		date が null であった場合にデフォルトで返される文字列
	 * @return					引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatDate ( Date date, String pattern, Locale locale, String defaultString ) {
		return DateUtil.formatDate( date, DateUtil.generateFormat( pattern, locale ), defaultString );
	}

	/**
	 * 引数 formatter で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				指定日時
	 * @param formatter			日時フォーマット
	 * @param defaultString		date が null であった場合にデフォルトで返される文字列
	 * @return					引数 formatter で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatDate ( Date date, DateFormat formatter, String defaultString ) {
		if ( date == null ) return defaultString;

		return formatter.format( date );
	}

	/**
	 * 引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 * 日本のロケールのシンボルを含むフォーマットを使用する
	 *
	 * @param date			指定日時
	 * @param pattern		日時フォーマット
	 * @return				引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatJapaneseDate ( Date date, String pattern ) {
		return DateUtil.formatDate( date, pattern, LOCALE_JP );
	}

	/**
	 * 引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現を返す
	 * 日本のロケールのシンボルを含むフォーマットを使用する
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				指定日時
	 * @param pattern			日時フォーマット
	 * @param defaultString		date が null であった場合にデフォルトで返される文字列
	 * @return					引数 pattern で指定した形式でフォーマットされた引数 date の文字列表現
	 */
	public static String formatJapaneseDate ( Date date, String pattern, String defaultString ) {
		return DateUtil.formatDate( date, pattern, LOCALE_JP, defaultString );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 *
	 * @param date				文字列表現された日付
	 * @param pattern			日付のフォーマット
	 * @return					引数 date の文字列表現された日付の Date オブジェクト
	 * @throws ParseException
	 */
	public static Date parseDate ( String date, String pattern ) throws ParseException {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern ) );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 *
	 * @param date				文字列表現された日付
	 * @param pattern			日付のフォーマット
	 * @param locale			ロケール
	 * @return					引数 date の文字列表現された日付の Date オブジェクト
	 * @throws ParseException
	 */
	public static Date parseDate ( String date, String pattern, Locale locale ) throws ParseException {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern, locale ) );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 *
	 * @param date				文字列表現された日付
	 * @param formatter			日付のフォーマット
	 * @return					引数 date の文字列表現された日付の Date オブジェクト
	 * @throws ParseException
	 */
	public static Date parseDate ( String date, DateFormat formatter ) throws ParseException {
		return formatter.parse( date );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				文字列表現された日付
	 * @param pattern			日付のフォーマット
	 * @param defaultDate		date が null であった場合にデフォルトで返される文字列
	 * @return			引数 date の文字列表現された日付の Date オブジェクト
	 */
	public static Date parseDate ( String date, String pattern, Date defaultDate ) {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern ), defaultDate );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				文字列表現された日付
	 * @param pattern			日付のフォーマット
	 * @param locale			ロケール
	 * @param defaultDate		date が null であった場合にデフォルトで返される文字列
	 * @return			引数 date の文字列表現された日付の Date オブジェクト
	 */
	public static Date parseDate ( String date, String pattern, Locale locale, Date defaultDate ) {
		return DateUtil.parseDate( date, DateUtil.generateFormat( pattern, locale ), defaultDate );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				文字列表現された日付
	 * @param formatter			日付のフォーマット
	 * @param defaultDate		date が null であった場合にデフォルトで返される文字列
	 * @return				引数 date の文字列表現された日付の Date オブジェクト
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
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 * ロケールは日本のものを使用する
	 *
	 * @param date				文字列表現された日付
	 * @param pattern			日付のフォーマット
	 * @return					引数 date の文字列表現された日付の Date オブジェクト
	 * @throws ParseException
	 */
	public static Date parseJapaneseDate ( String date, String pattern ) throws ParseException {
		return DateUtil.parseDate( date, pattern, LOCALE_JP );
	}

	/**
	 * 引数 date の文字列表現された日付を、Date オブジェクトに変換する
	 * ロケールは日本のものを使用する
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param date				文字列表現された日付
	 * @param pattern			日付のフォーマット
	 * @param defaultDate		date が null であった場合にデフォルトで返される文字列
	 * @return					引数 date の文字列表現された日付の Date オブジェクト
	 * @throws ParseException
	 */
	public static Date parseJapaneseDate ( String date, String pattern, Date defaultDate ) {
		return DateUtil.parseDate( date, pattern, LOCALE_JP, defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year ) {
		return DateUtil.generateDate( year, 1 );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year, int month ) {
		return DateUtil.generateDate( year, month, 1 );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year, int month, int day ) {
		return DateUtil.generateDate( year, month, day, 0 );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year, int month, int day, int hour ) {
		return DateUtil.generateDate( year, month, day, hour, 0 );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year, int month, int day, int hour, int minute ) {
		return DateUtil.generateDate( year, month, day, hour, minute, 0 );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year, int month, int day, int hour, int minute, int second ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, 0 );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( int year, int month, int day, int hour, int minute, int second, int millisecond ) {
		Date ret = new GregorianCalendar( year, month - 1, day, hour, minute, second ).getTime();
		ret = DateUtil.setMilliSecond( ret, millisecond );
		return ret;
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year ) {
		return DateUtil.generateDate( year, "1" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month ) {
		return DateUtil.generateDate( year, month, "1" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day ) {
		return DateUtil.generateDate( year, month, day, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour ) {
		return DateUtil.generateDate( year, month, day, hour, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateDate( year, month, day, hour, minute, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @return			引数で指定した日付オブジェクト
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
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, Date defaultDate ) {
		return DateUtil.generateDate( year, "1", defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, Date defaultDate ) {
		return DateUtil.generateDate( year, month, "1", defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, "0", defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, hour, "0", defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, hour, minute, "0", defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second, Date defaultDate ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, "0", defaultDate );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * また、date が null の場合、引数 defaultString を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @param defaultDate		date が null であった場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDate ( String year, String month, String day, String hour, String minute, String second, String millisecond, Date defaultDate ) {
		try {
			return DateUtil.generateDate( year, month, day, hour, minute, second, millisecond );
		} catch ( Exception ex ) {
			return defaultDate;
		}
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year ) {
		return DateUtil.generateDateDefNull( year, "1" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year, String month ) {
		return DateUtil.generateDateDefNull( year, month, "1" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year, String month, String day ) {
		return DateUtil.generateDateDefNull( year, month, day, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour ) {
		return DateUtil.generateDateDefNull( year, month, day, hour, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateDateDefNull( year, month, day, hour, minute, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateDateDefNull( year, month, day, hour, minute, second, "0" );
	}

	/**
	 * 引数で指定した日付オブジェクトを返す
	 * フォーマットに失敗した場合には null を返す
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateDateDefNull ( String year, String month, String day, String hour, String minute, String second, String millisecond ) {
		return DateUtil.generateDate( year, month, day, hour, minute, second, millisecond, (Date)null );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year ) {
		return DateUtil.generateJapaneseDate( era, year, 1 );
	}


	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year, int month ) {
		return DateUtil.generateJapaneseDate( era, year, month, 1 );
	}


	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, 0 );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, 0 );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour, int minute ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, 0 );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour, int minute, int second ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, 0 );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( int era, int year, int month, int day, int hour, int minute, int second, int millisecond ) {
		Calendar calendar = generateJapaneseCalendar();
		calendar.set( Calendar.ERA, era );
		calendar.set( year, month - 1, day, hour, minute, second );
		calendar.set( Calendar.MILLISECOND, millisecond );
		return calendar.getTime();
	}


	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year ) {
		return DateUtil.generateJapaneseDate( era, year, "1" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month ) {
		return DateUtil.generateJapaneseDate( era, year, month, "1" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @return			引数で指定した日付オブジェクト
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
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, "1", defaultDate );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, "1", defaultDate );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, "0", defaultDate );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, "0", defaultDate );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, "0", defaultDate );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDate ( String era, String year, String month, String day, String hour, String minute, String second, Date defaultDate ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, "0", defaultDate );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合、デフォルトで引数 defaultDate の日付を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @param defaultDate		日付オブジェクトへの変換が失敗した場合にデフォルトで返される日付
	 * @return			引数で指定した日付オブジェクト
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
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, "1" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, "1" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, hour, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour, String minute ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, hour, minute, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour, String minute, String second ) {
		return DateUtil.generateJapaneseDateDefNull( era, year, month, day, hour, minute, second, "0" );
	}

	/**
	 * 引数で指定した日本ロケールの日付オブジェクトを返す
	 * 日付オブジェクトへの変換が失敗した場合は null を返す
	 *
	 * @param era		元号
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @param hour		時
	 * @param minute	分
	 * @param second   秒
	 * @param millisecond ミリ秒
	 * @return			引数で指定した日付オブジェクト
	 */
	public static Date generateJapaneseDateDefNull ( String era, String year, String month, String day, String hour, String minute, String second, String millisecond ) {
		return DateUtil.generateJapaneseDate( era, year, month, day, hour, minute, second, millisecond, (Date)null );
	}

	/**
	 * 引数 date の年に引数 amount の数を足す
	 *
	 * @param date		年を足す日付オブジェクト
	 * @param amount	足す数
	 * @return			引数 amount の数が年に足された日付オブジェクト
	 */
	public static Date addYear ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.YEAR, amount );
	}

	/**
	 * 引数 date の月に引数 amount の数を足す
	 *
	 * @param date		月を足す日付オブジェクト
	 * @param amount	足す数
	 * @return			引数 amount の数が月に足された日付オブジェクト
	 */
	public static Date addMonth ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.MONTH, amount );
	}

	/**
	 * 引数 date の日に引数 amount の数を足す
	 *
	 * @param date		日を足す日付オブジェクト
	 * @param amount	足す数
	 * @return			引数 amount の数が日に足された日付オブジェクト
	 */
	public static Date addDay ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.DAY_OF_MONTH, amount );
	}

	/**
	 * 引数 date の時に引数 amount の数を足す
	 *
	 * @param date		時を足す日付オブジェクト
	 * @param amount	足す数
	 * @return			引数 amount の数が時に足された日付オブジェクト
	 */
	public static Date addHour ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.HOUR_OF_DAY, amount );
	}

	/**
	 * 引数 date の分に引数 amount の数を足す
	 *
	 * @param date		分を足す日付オブジェクト
	 * @param amount	足す数
	 * @return			引数 amount の数が分に足された日付オブジェクト
	 */
	public static Date addMinute ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.MINUTE, amount );
	}

	/**
	 * 引数 date の秒に引数 amount の数を足す
	 *
	 * @param date		秒を足す日付オブジェクト
	 * @param amount	足す数
	 * @return			引数 amount の数が秒に足された日付オブジェクト
	 */
	public static Date addSecond ( Date date, int amount ) {
		return DateUtil.add( date, Calendar.SECOND, amount );
	}

	/**
	 * 引数 date の、引数 field で指定されたフィールドに引数 amout の数を足す
	 *
	 * @param date		日付オブジェクト
	 * @param field		引数 amout の数を足すフィールド
	 * @param amount	足す数
	 * @return			引数 field で指定されたフィールドに引数 amout の数が足された日付オブジェクト
	 */
	private static Date add ( Date date, int field, int amount ) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( field, amount );
		return gc.getTime();
	}

	/**
	 * 引数 base と引数 another の日付の差分を返す
	 *
	 * @param base		比較元の日付
	 * @param another	比較対象の日付
	 * @return			引数 base と引数 another の日付の差分
	 */
	public static long diffDay ( Date base, Date another ) {
		return (base.getTime() - another.getTime()) / (1000 * 60 * 60 * 24);
	}


	/**
	 * 引数 date がうるう年かどうかを検査する
	 *
	 * @param date		検査対象の日付
	 * @return			引数 date の年がうるう年であれば真、そうでなければ偽
	 */
	public static boolean isLeapYear ( Date date ) {
		if ( date == null ) return false;
		int year = DateUtil.getYear( date );
		return ((year % 400) == 0 || ((year % 100) != 0 && (year % 4) == 0));
	}

    /**
     * 時・分・秒・ﾐﾘ秒を「0」に初期化する。
     *
     * @param date 初期化する日付
     * @return 時・分・秒・ﾐﾘ秒を「0」に初期化された日付
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
     * カレンダの規則に基づいて、指定された時間量を年月日に加算または減算する。
     *
     * @param date 加算または減算する日付
     * @param year 指定された時間量（年）
     * @param month 指定された時間量（月）
     * @param day 指定された時間量（日）
     * @return 年月日に指定された時間量が加算または減算された日付
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
	 * 先日付と後日付の日数差を返す。
	 *
	 * @param Date 先日付
	 * @param Date 後日付
	 * @return long 日数差
	 */
	public static long betweenDays(Date beforeDate, Date afterDate) {
		return betweenDays(beforeDate, afterDate, 0);
	}

	/**
	 * 先日付と後日付の日数差を返す。
	 *
	 * @param Date 先日付
	 * @param Date 後日付
	 * @param long 日数差に加算する日数
	 * @return long 日数差
	 * @throws ParseException
	 */
	public static long betweenDays(String beforeDate, String afterDate, long addDay) throws ParseException {
		return betweenDays(toDate(beforeDate), toDate(afterDate), addDay);
	}

	/**
	 * 先日付と後日付の日数差を返す。
	 *
	 * @param Date 先日付
	 * @param Date 後日付
	 * @return long 日数差
	 * @throws ParseException
	 */
	public static long betweenDays(String beforeDate, String afterDate) throws ParseException {
		return betweenDays(toDate(beforeDate), toDate(afterDate));
	}

	/**
	 * 二つの日付の日数差を返却する
	 *
	 * @param Date 年月日
	 * @param Date 年月日
	 * @param long 日数差に加算する日数
	 * @return long 日数差
	 */
	public static long betweenDays(Date beforeDate, Date afterDate, long addDay) {

		// １日は86400000ミリーセカンド
		return (afterDate.getTime() - beforeDate.getTime()) / 86400000 + addDay;
	}

	/**
	 * 指定されたパターンとデフォルトロケールのデフォルト日付フォーマット記号を使って SimpleDateFormat を構築し、
	 * 文字列の先頭からテキストを解析して日付を生成する。
	 *
	 * @param str 文字列
	 * @param format パターン
	 * @return 解析して生成された日付
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
	 * 文字列の先頭からテキストを解析して日付を生成する。
	 *
	 * @param str 文字列
	 * @return 解析して生成された日付
	 * @throws ParseException
	 */
	public static Date toDate(String str) throws ParseException {
		return toDate(str, "yyyy/MM/dd");
	}

	/**
     * long型を時間表現に直します。
     *
     * @param time 時間を表すlong形
     * @return XX:XX:XX.XXX形式の文字列
     */
    public static String timeFormat(long time) {
        int milli = (int) time % 1000;
        time /= 1000;
        int second = (int) time % 60;
        time /= 60;
        int minute = (int) time % 60;
        int hour = (int) time / 60;
        return String.format("%02d時 %02d分 %02d秒 %03d", hour, minute, second, milli);
    }
}
