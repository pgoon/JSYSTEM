package jp.stbnet.stg.hosho.ikou.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * DiskUtilクラス
 *
 * @author NSD.LEE
 */
public class DiskUtil {

	/**
	 * ファイルが存在するかのチェック
	 *
	 * @param fullFileName フォルダ名を含んだファイルのフール名
	 * @return true:ファイル存在、false:ファイルなし
	 */
	public static boolean isFile(String fullFileName) throws IOException {
		return new File(fullFileName).exists();
	}

	/**
	 * ディレクトリが存在するかのチェック
	 *
	 * @param fullDirName ディレクトリのフール名
	 * @param true:ディレクトリがない場合は作成、false:ディレクトリ存在チェックのみ
	 * @return true:ディレクトリ存在、false:ディレクトリなし
	 */
	public static boolean isDir(String fullDirName, boolean checkFlg) throws Exception {
		File dir = new File(fullDirName);
		// ディレクトリが存在チェック
		if(dir.exists()) {
			return true;
		} else {
			// ディレクトリがない場合は作成
			if(checkFlg) {
				return dir.mkdir();
			}
			return false;
		}
	}

	/**
	 * ファイルサイズチェック
	 * ファイルが存在しなかった場合はIOException
	 *
	 * @param fullFileName フォルダ名を含んだファイルのフール名
	 * @return ファイルのサイズ（バイト）
	 */
	@SuppressWarnings("resource")
	public static long getFileSize(String fullFileName) throws IOException {
		FileChannel fc =  new FileInputStream(new File(fullFileName)).getChannel();
		return fc.size();
	}

	/**
	 * ファイルを指定した名前で0バイトファイルを作成する。
	 *
	 * @param fullFileName 0バイトファイルのフール名
	 * @return true 生成成功、false　生成失敗
	 */
	@SuppressWarnings("resource")
	public static boolean touchFile(String fullFileName) throws IOException {
		File file = new File(fullFileName);
		new BufferedWriter(new FileWriter(file, false));
		return file.exists();
	}

	/**
	 * ファイルを指定した名前で内容を埋めてファイルを作成する。
	 *
	 * @param fullFileName ファイルのフール名
	 * @param strList 文字列のリスト
	 * @return true 生成成功、false　生成失敗
	 */
	public static boolean createStrFile(String fullFileName, List<String> strList) throws IOException {
		File file = new File(fullFileName);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

		for(String str: strList){
			pw.println(str);
		}
		pw.close();

		return file.exists();
	}

	/**
	 * ファイルを指定した名前で内容を読み込んで文字列リストに返却する
	 *
	 * @param fullFileName ファイルのフール名
	 * @return strList 文字列のリスト
	 */
	public static List<String> readStrFile(String fullFileName) throws IOException {
		List<String> strList = new ArrayList<String>();
		File file = new File(fullFileName);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String str = br.readLine();
		while(str != null){
			strList.add( str );
			str = br.readLine();
		}

		br.close();

		return strList;
	}

	/**
	 * ファイルを指定した名前で内容を読み込んで文字列リストに返却する。
	 * 指定したバイトのサイズごとの文字列で読み込む。
	 *
	 * @param fullFileName ファイルのフール名
	 * @return strList 文字列のリスト
	 */
	public static List<String> readByteStrFile(String fullFileName, int len) throws IOException {
		byte[] dataByte = new byte[len];
		List<String> strList = new ArrayList<String>();
		FileInputStream fi = new FileInputStream(new File(fullFileName));

		while((fi.read(dataByte)) != -1){
			strList.add( new String(dataByte));
		}

		fi.close();

		return strList;
	}

	/**
	 * ファイルを指定した名前でコピーする
	 * ファイルが存在しなかった場合はIOException
	 *
	 * @param orgFileName フォルダ名を含んだコピー元のファイルのフール名
	 * @param destFileName フォルダ名を含んだコピー先のファイルのフール名
	 * @return true コピー成功、false　コピー失敗
	 */
	@SuppressWarnings("resource")
	public static boolean copyFile(String orgFileName, String destFileName) throws IOException {

		boolean checkFlg = true;

		// ファイル生成
		File orgFile = new File(orgFileName);
		if(!orgFile.exists()) {
			throw new IOException("コピー元のファイルが存在しません。");
		}
		File destFile = new File(destFileName);

		// 元本ファイル用
		FileChannel fcin = null;
		// コピーファイル用
		FileChannel fcout = null;

		try {
			// チャンネルをゲット
			fcin = new FileInputStream(orgFile).getChannel();
			fcout = new FileOutputStream(destFile).getChannel();
			// magic number for Windows, 64MB - 32KB
			int maxCount = (64 * 1024 * 1024) - (32 * 1024);
			// チャンネルを用いてストリーム伝送
			long size = fcin.size();
			long position = 0l;
			while (position < size) {
				position += fcin.transferTo(position, maxCount, fcout);
			}

		} catch (FileNotFoundException e) {
			checkFlg = false;
		} finally {
			if(fcout != null)	try {fcout.close();}catch(Exception e1){checkFlg = false;}
			if(fcin != null)	try {fcin.close();}catch(Exception e1){checkFlg = false;}
		}

		return checkFlg;
	}

	/**
	 * ファイルを指定した名前でリネームする
	 * ファイルが存在しなかった場合はIOException
	 *
	 * @param orgFileName フォルダ名を含んだリネーム元のファイルのフール名
	 * @param destFileName フォルダ名を含んだリネーム先のファイルのフール名
	 * @return true コピー成功、false　コピー失敗
	 */
	public static boolean renameFile(String orgFileName, String destFileName) throws IOException {

		boolean checkFlg = true;

		// ファイル生成
		File orgFile = new File(orgFileName);
		if(!orgFile.exists()) {
			throw new IOException("コピー元のファイルが存在しません。");
		}
		File destFile = new File(destFileName);

		orgFile.renameTo(destFile);

		return checkFlg;
	}

	/**
	 * ファイルを指定した名前で移動する
	 * ファイルが存在しなかった場合はIOException
	 *
	 * @param orgFileName フォルダ名を含んだ移動元のファイルのフール名
	 * @param destFileName フォルダ名を含んだ移動先のファイルのフール名
	 * @return true 移動成功、false　移動失敗
	 */
	public static boolean moveFile(String orgFileName, String destFileName) throws IOException {
		boolean success = false;
		// ファイル生成
		File orgFile = new File(orgFileName);
		if(!orgFile.exists()) {
			throw new IOException("移動元のファイルが存在しません。");
		}

		// 移動先パス
		File destDir = new File(destFileName.substring(0, destFileName.lastIndexOf(File.separator)));

		// 移動先パス存在チェック
		if(destDir.exists()) {
			// ファイルの移動処理
			success = orgFile.renameTo(new File(destFileName));
		} else {
			// 移動先パスが存在しない場合、パスを生成
			if(destDir.mkdir()) {
				// ファイルの移動処理
				success = orgFile.renameTo(new File(destFileName));
			}
		}

		// ファイル移動処理が失敗した場合
		if(!success) {
			// 重複ファイルの存在チェック（移動先）
			File destFile = new File(destFileName);
			if(destFile.exists()) {
				// 重複ファイルがある場合、削除
				if(destFile.delete()) {
					// 移動処理を再び実行（ここで移動できない場合、そのまま処理を終了する）
					success = orgFile.renameTo(new File(destFileName));
				}
			}
		}

		return success;

	}

	/**
	 * 指定したファイルを削除する。
	 * ファイルが存在しなかった場合はfalseをリターンする。
	 *
	 * @param fullFileName 削除対象ファイルのフール名
	 * @return true 削除成功、false　削除失敗
	 */
	public static boolean delFile(String fullFileName) throws IOException {
		File file = new File(fullFileName);
		if(file.exists()) {
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 指定したディレクトリを削除する。
	 * ディレクトリが存在しなかった場合はfalseをリターンする。
	 *
	 * @param fullFileName 削除対象ディレクトリのフール名
	 * @param true:ディレクトリが空いていない場合は中のファイルも削除、
	 *         false:ディレクトリが空いていない場合は削除せずfalseをリターンする
	 * @return true 削除成功、false　削除失敗
	 */
	public static boolean delDir(String fullDirName, boolean actFlg) throws IOException {

		boolean resultFlg = true;
		File dir = new File(fullDirName);
		if(dir.exists()) {
			File[] files = dir.listFiles();
			if(files != null){
				if(files.length == 0) {
					return dir.delete();
				} else {
					if(actFlg) {
						for(int i=0; i<files.length; i++) {
							if(files[i].isDirectory()) {
								resultFlg &= delDir(files[i].getAbsolutePath(), true);
							} else {
								resultFlg &= files[i].delete();
							}
						}
						resultFlg &= dir.delete();
					} else {
						resultFlg &= false;
					}
				}
			}
		} else {
			resultFlg &= false;
		}
		return resultFlg;
	}

	/**
	 * 指定ディレクトリの中にあるファイル一覧を取得する。
	 * ディレクトリが存在しなかった場合はIOException
	 * 指定ディレクトリの中にファイルがなかった場合はサイズ0のリストをリターン
	 * 指定ディレクトリの下位ディレクトリは取得しない
	 *
	 * @param fullDirName 指定ディレクトリのフール名
	 * @param extName 取得するファイルの拡張子名、NULLの場合はすべてのファイル
	 * @return list ファイルのフール名リスト
	 */
	public static List<String> getFileList(String fullDirName, String extName) throws IOException {
		List<String> filesList = new ArrayList<String>();
		File dir = new File(fullDirName);
		if(dir.exists()) {
			File[] files = dir.listFiles();
			if(files != null){
				for(int i=0; i<files.length; i++) {
					if(!files[i].isDirectory()) {
						String fileName = files[i].getName();
						if(extName != null) {
							String ss = fileName.substring(fileName.lastIndexOf(".")+1);
							if(extName.equals(ss)) {
								filesList.add(fileName);
							}
						} else {
							filesList.add(fileName);
						}
					}
				}
			}
		}

		// 日付名でリストをソート（古い順）
		Collections.sort(filesList, String.CASE_INSENSITIVE_ORDER);

		return filesList;
	}

	/**
	 * 指定した圧縮(ZIP)ファイルに解凍処理を行う。
	 *
	 * @param zipFileFullPath 対象圧縮ファイルのフール名
	 * @return TRUE 解凍成功、FALSE 解凍失敗
	 * @throws Exception
	 */
	public static boolean unZip(String zipFileFullPath) throws Exception {

		File baseFile = new File(zipFileFullPath);
		File baseDir = new File(baseFile.getParent());

		ZipFile zipFile = null;

		try{
			//ZIPファイルオブジェクト作成
			zipFile = new ZipFile(baseFile);
			//ZIPファイル内のファイルを列挙
			Enumeration<? extends ZipEntry> enumZip = zipFile.entries();

			//ZIPファイル内の全てのファイルを展開
			while(enumZip.hasMoreElements()){

				//ZIP内のエントリを取得
				ZipEntry zipEntry = (java.util.zip.ZipEntry)enumZip.nextElement();

				//出力ファイル取得

				File outFile = new File(baseDir , zipEntry.getName());

				//圧縮ファイル入力ストリーム作成
				BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(zipEntry));

				//出力オブジェクト取得
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));

				//読込みバッファ作成
				byte[] buf = new byte[4096*4];

				//解凍ファイル出力
				int readSize = 0;
				while((readSize = in.read(buf)) != -1){
					out.write(buf, 0, readSize);
				}

				//クローズ
				try{out.close();}catch(Exception e){}
				try{in.close();}catch(Exception e){}

			}
			//解凍処理成功
			return true;

		} catch (Exception e) {
			throw e;
		}finally{
			if(zipFile != null){
				try{zipFile.close(); }catch(Exception e){}
			}
		}
	}

	public static BufferedReader getFileReader(String fileFullPathName) throws FileNotFoundException{
		File file = new File(fileFullPathName);
		return new BufferedReader(new FileReader(file));
	}

	/**
	 * 指定したディレクトリの全てのファイルを削除する。
	 * ディレクトリが存在しなかった場合はfalseをリターンする。
	 *
	 * @param fullFileName ファイル削除対象ディレクトリのフール名
	 * @return true 削除成功、false　削除失敗
	 */
	public static boolean delFileTotal(String fullDirName) throws IOException {

		boolean resultFlg = true;
		File dir = new File(fullDirName);
		if(dir.exists()) {
			File[] files = dir.listFiles();
			if(files != null){
				if(files.length == 0) {
					return resultFlg;
				} else {
					for(int i=0; i<files.length; i++) {
							resultFlg &= files[i].delete();
					}
				}
			}
		} else {
			resultFlg &= false;
		}
		return resultFlg;
	}

}
