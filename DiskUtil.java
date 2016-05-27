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
 * DiskUtil�N���X
 *
 * @author NSD.LEE
 */
public class DiskUtil {

	/**
	 * �t�@�C�������݂��邩�̃`�F�b�N
	 *
	 * @param fullFileName �t�H���_�����܂񂾃t�@�C���̃t�[����
	 * @return true:�t�@�C�����݁Afalse:�t�@�C���Ȃ�
	 */
	public static boolean isFile(String fullFileName) throws IOException {
		return new File(fullFileName).exists();
	}

	/**
	 * �f�B���N�g�������݂��邩�̃`�F�b�N
	 *
	 * @param fullDirName �f�B���N�g���̃t�[����
	 * @param true:�f�B���N�g�����Ȃ��ꍇ�͍쐬�Afalse:�f�B���N�g�����݃`�F�b�N�̂�
	 * @return true:�f�B���N�g�����݁Afalse:�f�B���N�g���Ȃ�
	 */
	public static boolean isDir(String fullDirName, boolean checkFlg) throws Exception {
		File dir = new File(fullDirName);
		// �f�B���N�g�������݃`�F�b�N
		if(dir.exists()) {
			return true;
		} else {
			// �f�B���N�g�����Ȃ��ꍇ�͍쐬
			if(checkFlg) {
				return dir.mkdir();
			}
			return false;
		}
	}

	/**
	 * �t�@�C���T�C�Y�`�F�b�N
	 * �t�@�C�������݂��Ȃ������ꍇ��IOException
	 *
	 * @param fullFileName �t�H���_�����܂񂾃t�@�C���̃t�[����
	 * @return �t�@�C���̃T�C�Y�i�o�C�g�j
	 */
	@SuppressWarnings("resource")
	public static long getFileSize(String fullFileName) throws IOException {
		FileChannel fc =  new FileInputStream(new File(fullFileName)).getChannel();
		return fc.size();
	}

	/**
	 * �t�@�C�����w�肵�����O��0�o�C�g�t�@�C�����쐬����B
	 *
	 * @param fullFileName 0�o�C�g�t�@�C���̃t�[����
	 * @return true ���������Afalse�@�������s
	 */
	@SuppressWarnings("resource")
	public static boolean touchFile(String fullFileName) throws IOException {
		File file = new File(fullFileName);
		new BufferedWriter(new FileWriter(file, false));
		return file.exists();
	}

	/**
	 * �t�@�C�����w�肵�����O�œ��e�𖄂߂ăt�@�C�����쐬����B
	 *
	 * @param fullFileName �t�@�C���̃t�[����
	 * @param strList ������̃��X�g
	 * @return true ���������Afalse�@�������s
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
	 * �t�@�C�����w�肵�����O�œ��e��ǂݍ���ŕ����񃊃X�g�ɕԋp����
	 *
	 * @param fullFileName �t�@�C���̃t�[����
	 * @return strList ������̃��X�g
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
	 * �t�@�C�����w�肵�����O�œ��e��ǂݍ���ŕ����񃊃X�g�ɕԋp����B
	 * �w�肵���o�C�g�̃T�C�Y���Ƃ̕�����œǂݍ��ށB
	 *
	 * @param fullFileName �t�@�C���̃t�[����
	 * @return strList ������̃��X�g
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
	 * �t�@�C�����w�肵�����O�ŃR�s�[����
	 * �t�@�C�������݂��Ȃ������ꍇ��IOException
	 *
	 * @param orgFileName �t�H���_�����܂񂾃R�s�[���̃t�@�C���̃t�[����
	 * @param destFileName �t�H���_�����܂񂾃R�s�[��̃t�@�C���̃t�[����
	 * @return true �R�s�[�����Afalse�@�R�s�[���s
	 */
	@SuppressWarnings("resource")
	public static boolean copyFile(String orgFileName, String destFileName) throws IOException {

		boolean checkFlg = true;

		// �t�@�C������
		File orgFile = new File(orgFileName);
		if(!orgFile.exists()) {
			throw new IOException("�R�s�[���̃t�@�C�������݂��܂���B");
		}
		File destFile = new File(destFileName);

		// ���{�t�@�C���p
		FileChannel fcin = null;
		// �R�s�[�t�@�C���p
		FileChannel fcout = null;

		try {
			// �`�����l�����Q�b�g
			fcin = new FileInputStream(orgFile).getChannel();
			fcout = new FileOutputStream(destFile).getChannel();
			// magic number for Windows, 64MB - 32KB
			int maxCount = (64 * 1024 * 1024) - (32 * 1024);
			// �`�����l����p���ăX�g���[���`��
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
	 * �t�@�C�����w�肵�����O�Ń��l�[������
	 * �t�@�C�������݂��Ȃ������ꍇ��IOException
	 *
	 * @param orgFileName �t�H���_�����܂񂾃��l�[�����̃t�@�C���̃t�[����
	 * @param destFileName �t�H���_�����܂񂾃��l�[����̃t�@�C���̃t�[����
	 * @return true �R�s�[�����Afalse�@�R�s�[���s
	 */
	public static boolean renameFile(String orgFileName, String destFileName) throws IOException {

		boolean checkFlg = true;

		// �t�@�C������
		File orgFile = new File(orgFileName);
		if(!orgFile.exists()) {
			throw new IOException("�R�s�[���̃t�@�C�������݂��܂���B");
		}
		File destFile = new File(destFileName);

		orgFile.renameTo(destFile);

		return checkFlg;
	}

	/**
	 * �t�@�C�����w�肵�����O�ňړ�����
	 * �t�@�C�������݂��Ȃ������ꍇ��IOException
	 *
	 * @param orgFileName �t�H���_�����܂񂾈ړ����̃t�@�C���̃t�[����
	 * @param destFileName �t�H���_�����܂񂾈ړ���̃t�@�C���̃t�[����
	 * @return true �ړ������Afalse�@�ړ����s
	 */
	public static boolean moveFile(String orgFileName, String destFileName) throws IOException {
		boolean success = false;
		// �t�@�C������
		File orgFile = new File(orgFileName);
		if(!orgFile.exists()) {
			throw new IOException("�ړ����̃t�@�C�������݂��܂���B");
		}

		// �ړ���p�X
		File destDir = new File(destFileName.substring(0, destFileName.lastIndexOf(File.separator)));

		// �ړ���p�X���݃`�F�b�N
		if(destDir.exists()) {
			// �t�@�C���̈ړ�����
			success = orgFile.renameTo(new File(destFileName));
		} else {
			// �ړ���p�X�����݂��Ȃ��ꍇ�A�p�X�𐶐�
			if(destDir.mkdir()) {
				// �t�@�C���̈ړ�����
				success = orgFile.renameTo(new File(destFileName));
			}
		}

		// �t�@�C���ړ����������s�����ꍇ
		if(!success) {
			// �d���t�@�C���̑��݃`�F�b�N�i�ړ���j
			File destFile = new File(destFileName);
			if(destFile.exists()) {
				// �d���t�@�C��������ꍇ�A�폜
				if(destFile.delete()) {
					// �ړ��������Ăю��s�i�����ňړ��ł��Ȃ��ꍇ�A���̂܂܏������I������j
					success = orgFile.renameTo(new File(destFileName));
				}
			}
		}

		return success;

	}

	/**
	 * �w�肵���t�@�C�����폜����B
	 * �t�@�C�������݂��Ȃ������ꍇ��false�����^�[������B
	 *
	 * @param fullFileName �폜�Ώۃt�@�C���̃t�[����
	 * @return true �폜�����Afalse�@�폜���s
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
	 * �w�肵���f�B���N�g�����폜����B
	 * �f�B���N�g�������݂��Ȃ������ꍇ��false�����^�[������B
	 *
	 * @param fullFileName �폜�Ώۃf�B���N�g���̃t�[����
	 * @param true:�f�B���N�g�����󂢂Ă��Ȃ��ꍇ�͒��̃t�@�C�����폜�A
	 *         false:�f�B���N�g�����󂢂Ă��Ȃ��ꍇ�͍폜����false�����^�[������
	 * @return true �폜�����Afalse�@�폜���s
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
	 * �w��f�B���N�g���̒��ɂ���t�@�C���ꗗ���擾����B
	 * �f�B���N�g�������݂��Ȃ������ꍇ��IOException
	 * �w��f�B���N�g���̒��Ƀt�@�C�����Ȃ������ꍇ�̓T�C�Y0�̃��X�g�����^�[��
	 * �w��f�B���N�g���̉��ʃf�B���N�g���͎擾���Ȃ�
	 *
	 * @param fullDirName �w��f�B���N�g���̃t�[����
	 * @param extName �擾����t�@�C���̊g���q���ANULL�̏ꍇ�͂��ׂẴt�@�C��
	 * @return list �t�@�C���̃t�[�������X�g
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

		// ���t���Ń��X�g���\�[�g�i�Â����j
		Collections.sort(filesList, String.CASE_INSENSITIVE_ORDER);

		return filesList;
	}

	/**
	 * �w�肵�����k(ZIP)�t�@�C���ɉ𓀏������s���B
	 *
	 * @param zipFileFullPath �Ώۈ��k�t�@�C���̃t�[����
	 * @return TRUE �𓀐����AFALSE �𓀎��s
	 * @throws Exception
	 */
	public static boolean unZip(String zipFileFullPath) throws Exception {

		File baseFile = new File(zipFileFullPath);
		File baseDir = new File(baseFile.getParent());

		ZipFile zipFile = null;

		try{
			//ZIP�t�@�C���I�u�W�F�N�g�쐬
			zipFile = new ZipFile(baseFile);
			//ZIP�t�@�C�����̃t�@�C�����
			Enumeration<? extends ZipEntry> enumZip = zipFile.entries();

			//ZIP�t�@�C�����̑S�Ẵt�@�C����W�J
			while(enumZip.hasMoreElements()){

				//ZIP���̃G���g�����擾
				ZipEntry zipEntry = (java.util.zip.ZipEntry)enumZip.nextElement();

				//�o�̓t�@�C���擾

				File outFile = new File(baseDir , zipEntry.getName());

				//���k�t�@�C�����̓X�g���[���쐬
				BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(zipEntry));

				//�o�̓I�u�W�F�N�g�擾
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));

				//�Ǎ��݃o�b�t�@�쐬
				byte[] buf = new byte[4096*4];

				//�𓀃t�@�C���o��
				int readSize = 0;
				while((readSize = in.read(buf)) != -1){
					out.write(buf, 0, readSize);
				}

				//�N���[�Y
				try{out.close();}catch(Exception e){}
				try{in.close();}catch(Exception e){}

			}
			//�𓀏�������
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
	 * �w�肵���f�B���N�g���̑S�Ẵt�@�C�����폜����B
	 * �f�B���N�g�������݂��Ȃ������ꍇ��false�����^�[������B
	 *
	 * @param fullFileName �t�@�C���폜�Ώۃf�B���N�g���̃t�[����
	 * @return true �폜�����Afalse�@�폜���s
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
