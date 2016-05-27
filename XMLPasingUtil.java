package jp.stbnet.stg.hosho.ikou.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jp.stbnet.stg.hosho.ikou.bean.DirInfoBean;
import jp.stbnet.stg.hosho.ikou.bean.HibernateAccessInfoBean;
import jp.stbnet.stg.hosho.ikou.bean.OiTsukiFileInfoBean;
import jp.stbnet.stg.hosho.ikou.bean.ServerInfoBean;
import jp.stbnet.stg.hosho.ikou.exception.ApplicationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * 外部環境変数を管理するクラス
 *
 * @author NSD.PARK
 *
 */
public class XMLPasingUtil {

	// システム環境ファイル名定義
	private static final String IKOU_CFG_XML = "ikou.cfg.xml";
	private static final String HIBERNATE_CFG_XML = "hibernate3.cfg.xml";

	// XML定義内の検索タグ定義
	private static final String HONBAN_SERVER_INFO = "HONBAN-SERVER";
	private static final String RINJI_SERVER_INFO = "RINJI-SERVER";
	private static final String RSV_SERVER_INFO = "RSV-SERVER";
	private static final String DIR_INFO = "DIR-INFO";
	private static final String DDL_CREATE_INFO = "DDL-CREATE";
	private static final String DDL_CHANGE_INFO = "DDL-CHANGE";
	private static final String RSV_DDL_CHANGE_INFO = "RSV-DDL-CHANGE";
	private static final String DDL_DROP_INFO = "DDL-DROP";
	private static final String PROGRAM_ID_INFO = "PROGRAM-ID";
	private static final String HIBERNATE_INFO = "HIBERNATE-INFO";
	private static final String CSV_FILENAME_INFO = "CSV-FILENAME-INFO";
	private static final String SQL_FILENAME_INFO = "SQL-FILENAME-INFO";
	private static final String BAK_FILENAME_INFO = "BAK-FILENAME-INFO";
	private static final String FTP_FILENAME_INFO = "FTP-FILENAME-INFO";
	private static final String CSV_NAME = "CSV-NAME";
	private static final String RINJI_TBL_NAME = "RINJI-TBL-NAME";
	private static final String BAK_NAME = "BAK-NAME";
	private static final String FILE_NAME = "FILE-NAME";
	private static final String DEBUG_MODE = "DEBUG-MODE";
	private static final String IKOU_YMD = "IKOU-YMD";
	private static final String OITSUKI_INFO = "OITSUKI-INFO";
	private static final String OITSUKI_FILE = "OITSUKI-FILE";
	private static final String DATA_CREANSING = "DATA-CREANSING";
	private static final String TABLE_NAME = "TABLE-NAME";
	private static final String FILE_INFO = "FILE-INFO";

	private static Document doc = null;

	public static DocumentBuilderFactory factory = null;

	private static String cfgFilePath = null;
	private static String hibernateCfgFile = null;

	/**
	 * 業務日付（移行日）情報取得
	 */
	public static String getIkouYMDInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> ikouYMDInfo = new ArrayList<String>();
		getPasingInfo(IKOU_YMD, ikouYMDInfo);
		return ikouYMDInfo.get(0);
	}

	/**
	 * デバック環境情報取得
	 */
	public static String getDebugModeInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> debugModeInfo = new ArrayList<String>();
		getPasingInfo(DEBUG_MODE, debugModeInfo);
		return debugModeInfo.get(0);
	}

	/**
	 *	本番DBサーバー情報取得
	 */
	public static ServerInfoBean getHonbanServerInfo() throws ParserConfigurationException, SAXException, IOException{
		ServerInfoBean serverInfoBean = new ServerInfoBean();
		getPasingInfo(HONBAN_SERVER_INFO, serverInfoBean);
		return serverInfoBean;
	}

	/**
	 *	臨時DBサーバー情報取得
	 */
	public static ServerInfoBean getRinjiServerInfo() throws ParserConfigurationException, SAXException, IOException{
		ServerInfoBean serverInfoBean = new ServerInfoBean();
		getPasingInfo(RINJI_SERVER_INFO, serverInfoBean);
		return serverInfoBean;
	}

	/**
	 *	HOSHO_RSV DBサーバー情報取得
	 */
	public static ServerInfoBean getHoshoRsvServerInfo() throws ParserConfigurationException, SAXException, IOException {
		ServerInfoBean serverInfoBean = new ServerInfoBean();
		getPasingInfo(RSV_SERVER_INFO, serverInfoBean);
		return serverInfoBean;
	}

	/**
	 *	フォルダ情報取得
	 */
	public static DirInfoBean getDirInfo() throws ParserConfigurationException, SAXException, IOException{
		DirInfoBean dirInfoBean = new DirInfoBean();
		getPasingInfo(DIR_INFO, dirInfoBean);
		return dirInfoBean;
	}

	/**
	 * 外部入力ファイルの情報取得
	 */
	public static String getFileInfo(String batchId) throws ParserConfigurationException, SAXException, IOException{
		List<String[]> fileInfoList = new ArrayList<String[]>();
		getPasingInfo(FILE_INFO, fileInfoList);

		for(int i=0; i<fileInfoList.size(); i++){
			String[] strArr = fileInfoList.get(i);

			if(strArr[0].equals(batchId)){
				return strArr[1]; //ファイル名
			}
		}
		return null;
	}

	/**
	 * 臨時データベース生成とテーブル作成DDL文の定義情報取得
	 */
	public static List<String> getDDLCreateInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> ddlList = new ArrayList<String>();
		getPasingInfo(DDL_CREATE_INFO, ddlList);
		return ddlList;
	}

	/**
	 * 本番データベースのテーブル作成と項目追加DDL文の定義情報取得
	 */
	public static List<String> getDDLChangeInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> ddlList = new ArrayList<String>();
		getPasingInfo(DDL_CHANGE_INFO, ddlList);
		return ddlList;
	}

	/**
	 * HOSHO_RSVデータベースのテーブル作成と項目追加DDL文の定義情報取得
	 */
	public static List<String> getRSVDDLChangeInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> ddlList = new ArrayList<String>();
		getPasingInfo(RSV_DDL_CHANGE_INFO, ddlList);
		return ddlList;
	}

	/**
	 * 臨時データベース削除（DROP）DDL文の定義情報取得
	 */
	public static String getDDLDropInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> ddlList = new ArrayList<String>();
		getPasingInfo(DDL_DROP_INFO, ddlList);
		return ddlList.get(0);
	}

	/**
	 * Java側バッチプログラムID一覧の定義
	 */
	public static List<String> getBatchIdInfo() throws ParserConfigurationException, SAXException, IOException{
		List<String> batchIdList = new ArrayList<String>();
		getPasingInfo(PROGRAM_ID_INFO, batchIdList);
		return batchIdList;
	}

	/**
	 * Hibernate環境設定ファイルの定義
	 */
	public static String getHibernateCfgFile() throws ApplicationException{
		if(hibernateCfgFile == null){
			String parentPath = new File(".").getAbsoluteFile().getParent();
			hibernateCfgFile = searchFilePath(new File(parentPath), HIBERNATE_CFG_XML);
			if(hibernateCfgFile==null){
				throw new ApplicationException(parentPath + "下位フォルダのどこにもHibernate環境設定ファイル「" + HIBERNATE_CFG_XML+"」が見つかりません。");
			}
		}

		return hibernateCfgFile + File.separator + HIBERNATE_CFG_XML;
	}

	/**
	 * 取込CSVファイル名と取込対象テーブル名の定義情報取得
	 */
	public static String[] getCSVFileNameInfo(String batchId) throws ParserConfigurationException, SAXException, IOException{
		List<String[]> fileNameList = new ArrayList<String[]>();
		getPasingInfo(CSV_FILENAME_INFO, fileNameList);

		for(int i=0; i<fileNameList.size(); i++){
			String[] fileInfo = fileNameList.get(i);
			if(batchId.equals(fileInfo[0])){
				String[] strArr = new String[2];
				strArr[0] = fileInfo[1];	// 取込対象テーブル名
				strArr[1] = fileInfo[2];	// 取込CSVファイル名
				return strArr;
			}
		}
		return null;
	}

	/**
	 * SQLスクリプト名と取込対象テーブル名の定義情報取得
	 */
	public static String[] getSQLFileNameInfo(String batchId) throws ParserConfigurationException, SAXException, IOException{
		List<String[]> fileNameList = new ArrayList<String[]>();
		getPasingInfo(SQL_FILENAME_INFO, fileNameList);

		for(int i=0; i<fileNameList.size(); i++){
			String[] fileInfo = fileNameList.get(i);
			if(batchId.equals(fileInfo[0])){
				String[] strArr = new String[2];
				strArr[0] = fileInfo[1];	// 移行先テーブル名
				strArr[1] = fileInfo[2];	// 移行元テーブル名
				return strArr;
			}
		}
		return null;
	}

	/**
	 * BAK対象ファイル名の定義情報取得
	 */
	public static String getBAKFileNameInfo(String batchId) throws ParserConfigurationException, SAXException, IOException {
		List<String[]> fileNameList = new ArrayList<String[]>();
		getPasingInfo(BAK_FILENAME_INFO, fileNameList);

		for(int i=0; i<fileNameList.size(); i++){
			String[] strArr = fileNameList.get(i);

			if(strArr[0].equals(batchId)){
				return strArr[1]; //BAKファイル名
			}
		}
		return null;
	}

	/**
	 * FTP対象ファイル名の定義情報取得
	 */
	public static String getFTPFileNameInfo(String batchId) throws  ParserConfigurationException, SAXException, IOException {
		List<String[]> fileNameList = new ArrayList<String[]>();
		getPasingInfo(FTP_FILENAME_INFO, fileNameList);

		for(int i=0; i<fileNameList.size(); i++){
			String[] strArr = fileNameList.get(i);

			if(strArr[0].equals(batchId)){
				return strArr[1]; //FTP対象ファイル名
			}
		}
		return null;
	}

	public static OiTsukiFileInfoBean getOitsukiFileNameInfo(String batchId) throws  ParserConfigurationException, SAXException, IOException {


		List<String[]> fileNameList = new ArrayList<String[]>();

		List<String> inputFileList = new ArrayList<String>();
		String outputFile = "";
		String kijunFile = "";

		getPasingInfo(OITSUKI_INFO, fileNameList);

		for(int i = 0; i < fileNameList.size(); i++){
			String[] strArr = fileNameList.get(i);

			if(strArr[0].equals(batchId)){
				if(strArr[1].equals("input")){
					inputFileList.add(strArr[2]);
				}
				if(strArr[1].equals("output")){
					outputFile = strArr[2];
				}
				if(strArr[1].equals("kijun")){
					kijunFile = strArr[2];
				}
			}else{
				continue;
			}
		}

		OiTsukiFileInfoBean bean = new OiTsukiFileInfoBean();
		bean.setInputFileList(inputFileList);
		bean.setKijunFile(kijunFile);
		bean.setOutputFile(outputFile);

		return bean;
	}

	/**
	 * データクレンジング対象テーブル名取得
	 */
	public static String getDataCreansingInfo(String batchId) throws ParserConfigurationException, SAXException, IOException {

		List<String[]> tableNameList = new ArrayList<String[]>();
		getPasingInfo(DATA_CREANSING, tableNameList);

		for(int i=0; i<tableNameList.size(); i++){
			String[] strArr = tableNameList.get(i);

			if(strArr[0].equals(batchId)){
				return strArr[1]; //テーブル名
			}
		}
		return null;
	}



	/**
	 * Hibernateアクセス情報（基本的に臨時データベースのアクセス）
	 */
	public static HibernateAccessInfoBean getHibernateAccessInfo() throws ParserConfigurationException, SAXException, IOException{
		HibernateAccessInfoBean hibernateInfoBean = new HibernateAccessInfoBean();
		getPasingInfo(HIBERNATE_INFO, hibernateInfoBean);
		return hibernateInfoBean;
	}

	private static void getPasingInfo(String infoKind, Object objInfo) throws ParserConfigurationException, SAXException, IOException{
		if(doc == null){
			//IKOU.CFG.XML読込
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			if(cfgFilePath == null){
				String parentPath = new File(".").getAbsoluteFile().getParent();
				cfgFilePath = searchFilePath(new File(parentPath), IKOU_CFG_XML);
				if(cfgFilePath==null){
					throw new ApplicationException(parentPath + "下位フォルダのどこにも環境設定ファイル「" + IKOU_CFG_XML+"」が見つかりません。");
				}
			}

			String sss = cfgFilePath + File.separator + IKOU_CFG_XML;
			File f = new File(sss);
			doc = builder.parse(f);
		}

		if(IKOU_YMD.equals(infoKind)){
			//----------------------------------------------------------
			// デバック環境情報取得
			//----------------------------------------------------------
			List<String> debugModeInfo = (List<String>)objInfo;
			NodeList serverNodeList = doc.getElementsByTagName(IKOU_YMD);

			Node node = serverNodeList.item(0).getFirstChild();
			debugModeInfo.add(node.getTextContent());

		}else if(DEBUG_MODE.equals(infoKind)){
			//----------------------------------------------------------
			// デバック環境情報取得
			//----------------------------------------------------------
			List<String> debugModeInfo = (List<String>)objInfo;
			NodeList serverNodeList = doc.getElementsByTagName(DEBUG_MODE);

			Node node = serverNodeList.item(0).getFirstChild();
			debugModeInfo.add(node.getTextContent());

		}else if(HONBAN_SERVER_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// 本番DBサーバー情報取得
			//-----------------------------------------------------------
			ServerInfoBean serverInfoBean = (ServerInfoBean)objInfo;
			NodeList serverNodeList = doc.getElementsByTagName(HONBAN_SERVER_INFO);

			for(int i=0; i<serverNodeList.getLength(); i++){
				for(Node node = serverNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "ADDRESS": serverInfoBean.setServerAddr(node.getTextContent()); break;
					case "DB-NAME": serverInfoBean.setDbName(node.getTextContent()); break;
					case "SCHEMA": serverInfoBean.setSchema(node.getTextContent()); break;
					case "ID": serverInfoBean.setUserId(node.getTextContent()); break;
					case "PASSWORD": serverInfoBean.setUserPw(node.getTextContent()); break;
					}
				}
			}
		}else if(RINJI_SERVER_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// 臨時DBサーバー情報取得
			//-----------------------------------------------------------
			ServerInfoBean serverInfoBean = (ServerInfoBean)objInfo;
			NodeList serverNodeList = doc.getElementsByTagName(RINJI_SERVER_INFO);

			for(int i=0; i<serverNodeList.getLength(); i++){
				for(Node node = serverNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "ADDRESS": serverInfoBean.setServerAddr(node.getTextContent()); break;
					case "DB-NAME": serverInfoBean.setDbName(node.getTextContent()); break;
					case "SCHEMA": serverInfoBean.setSchema(node.getTextContent()); break;
					case "ID": serverInfoBean.setUserId(node.getTextContent()); break;
					case "PASSWORD": serverInfoBean.setUserPw(node.getTextContent()); break;
					}
				}
			}
		}else if(RSV_SERVER_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// HOSHO_RSV DBサーバー情報取得
			//-----------------------------------------------------------
			ServerInfoBean serverInfoBean = (ServerInfoBean)objInfo;
			NodeList serverNodeList = doc.getElementsByTagName(RSV_SERVER_INFO);

			for(int i=0; i<serverNodeList.getLength(); i++){
				for(Node node = serverNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "ADDRESS": serverInfoBean.setServerAddr(node.getTextContent()); break;
					case "DB-NAME": serverInfoBean.setDbName(node.getTextContent()); break;
					case "SCHEMA": serverInfoBean.setSchema(node.getTextContent()); break;
					case "ID": serverInfoBean.setUserId(node.getTextContent()); break;
					case "PASSWORD": serverInfoBean.setUserPw(node.getTextContent()); break;
					}
				}
			}
		}else if(DIR_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// フォルダ定義情報取得
			//-----------------------------------------------------------
			DirInfoBean dirInfoBean = (DirInfoBean)objInfo;
			NodeList dirNodeList = doc.getElementsByTagName(DIR_INFO);

			for(int i=0; i<dirNodeList.getLength(); i++){
				for(Node node = dirNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "DIR-LOG": dirInfoBean.setDirLog(node.getTextContent()); break;
					case "DIR-SQLS-H-DDL": dirInfoBean.setDirHonbanDdl(node.getTextContent()); break;
					case "DIR-SQLS-R-DDL": dirInfoBean.setDirRinjiDdl(node.getTextContent()); break;
					case "DIR-SQLS-ETC": dirInfoBean.setDirEtc(node.getTextContent()); break;
					case "DIR-BULK": dirInfoBean.setDirBulk(node.getTextContent()); break;
					case "DIR-FTP": dirInfoBean.setDirFtp(node.getTextContent()); break;
					case "DIR-CSV": dirInfoBean.setDirCsv(node.getTextContent()); break;
					case "DIR-BACKUP": dirInfoBean.setDirBackup(node.getTextContent()); break;
					}
				}
			}
		}else if(FILE_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// 入力ファイル定義情報取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(FILE_INFO);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				Node node = batchIdNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String[] strArr = new String[2];

					NodeList fileChildrenList = node.getChildNodes();
					for(int j=0; j<fileChildrenList.getLength(); j++){
						node = fileChildrenList.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							strArr = new String[2];
							strArr[0] = node.getNodeName();				//バッチID
						}

						NodeList csvChildrenList = node.getChildNodes();
						for(int k=0; k<csvChildrenList.getLength(); k++){
							node = csvChildrenList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								if(FILE_NAME.equals(node.getNodeName())){
									strArr[1] = node.getTextContent();	// ファイル名
									fileNameList.add(strArr);
								}
							}
						}
					}
				}
			}

		}else if(DDL_CREATE_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// 臨時データベース生成とテーブル作成DDL文の定義情報取得
			//-----------------------------------------------------------
			List<String> dllList = (List<String>)objInfo;
			NodeList dllCreateNodeList = doc.getElementsByTagName(DDL_CREATE_INFO);

			for(int i=0; i<dllCreateNodeList.getLength(); i++){
				for(Node node = dllCreateNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "DDL": dllList.add(node.getTextContent());
					break;
					}
				}
			}
		}else if(DDL_CHANGE_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// 本番データベースのテーブル作成と項目追加DDL文の定義情報取得
			//-----------------------------------------------------------
			List<String> dllList = (List<String>)objInfo;
			NodeList dllChangeNodeList = doc.getElementsByTagName(DDL_CHANGE_INFO);

			for(int i=0; i<dllChangeNodeList.getLength(); i++){
				for(Node node = dllChangeNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "DDL": dllList.add(node.getTextContent());
					break;
					}
				}
			}
		}else if(RSV_DDL_CHANGE_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// HOSHO_RSVデータベースのテーブル作成と項目追加DDL文の定義情報取得
			//-----------------------------------------------------------
			List<String> dllList = (List<String>)objInfo;
			NodeList dllChangeNodeList = doc.getElementsByTagName(RSV_DDL_CHANGE_INFO);

			for(int i=0; i<dllChangeNodeList.getLength(); i++){
				for(Node node = dllChangeNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "DDL": dllList.add(node.getTextContent());
					break;
					}
				}
			}
		}else if(DDL_DROP_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// 臨時データベース削除（DROP）DDL文の定義情報取得
			//-----------------------------------------------------------
			List<String> dllList = (List<String>)objInfo;
			NodeList dllDropNodeList = doc.getElementsByTagName(DDL_DROP_INFO);

			for(int i=0; i<dllDropNodeList.getLength(); i++){
				for(Node node = dllDropNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "DDL": dllList.add(node.getTextContent());
					break;
					}
				}
			}
		}else if(PROGRAM_ID_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// Java側バッチプログラムID一覧の定義情報取得
			//-----------------------------------------------------------
			List<String> batchIdList = (List<String>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(PROGRAM_ID_INFO);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				for(Node node = batchIdNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "ID": batchIdList.add(node.getTextContent());
					break;
					}
				}
			}
		}else if(CSV_FILENAME_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// バッチIDに該当する取込対象ファイル名のパラメータ取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(CSV_FILENAME_INFO);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				Node node = batchIdNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String[] strArr = new String[3];
					NodeList fileChildrenList = node.getChildNodes();
					for(int j=0; j<fileChildrenList.getLength(); j++){
						node = fileChildrenList.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element)node;
							strArr = new String[3];
							strArr[0] = node.getNodeName();					//バッチID
							strArr[1] = element.getAttribute("name");		// 取込対象テーブル名
						}
						NodeList csvChildrenList = node.getChildNodes();
						for(int k=0; k<csvChildrenList.getLength(); k++){
							node = csvChildrenList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								if(CSV_NAME.equals(node.getNodeName()))
									strArr[2] = node.getTextContent();		// 取込CSVファイル名
								fileNameList.add(strArr);
							}
						}
					}
				}
			}
		}else if(SQL_FILENAME_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// バッチIDに該当する臨時DBと本番DBの対象テーブル名取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(SQL_FILENAME_INFO);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				Node node = batchIdNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String[] strArr = new String[3];
					NodeList fileChildrenList = node.getChildNodes();
					for(int j=0; j<fileChildrenList.getLength(); j++){
						node = fileChildrenList.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element)node;
							strArr = new String[3];
							strArr[0] = node.getNodeName();					//バッチID
							strArr[1] = element.getAttribute("name");		// 移行先テーブル名
						}
						NodeList csvChildrenList = node.getChildNodes();
						for(int k=0; k<csvChildrenList.getLength(); k++){
							node = csvChildrenList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								if(RINJI_TBL_NAME.equals(node.getNodeName()))
									strArr[2] = node.getTextContent();		// 移行元テーブル名
								fileNameList.add(strArr);
							}
						}
					}
				}
			}
		}else if(BAK_FILENAME_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// バッチIDに該当するBAK対象ファイル名のパラメータを取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(BAK_FILENAME_INFO);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				Node node = batchIdNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String[] strArr = new String[2];

					NodeList fileChildrenList = node.getChildNodes();
					for(int j=0; j<fileChildrenList.getLength(); j++){
						node = fileChildrenList.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							strArr = new String[2];
							strArr[0] = node.getNodeName();					//バッチID
						}

						NodeList csvChildrenList = node.getChildNodes();
						for(int k=0; k<csvChildrenList.getLength(); k++){
							node = csvChildrenList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								if(BAK_NAME.equals(node.getNodeName())){
									strArr[1] = node.getTextContent();		// BAKファイル名
									fileNameList.add(strArr);
								}
							}
						}
					}
				}
			}
		}else if(FTP_FILENAME_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// バッチIDに該当するFTP対象ファイル名のパラメータを取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(FTP_FILENAME_INFO);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				Node node = batchIdNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String[] strArr = new String[2];

					NodeList fileChildrenList = node.getChildNodes();
					for(int j=0; j<fileChildrenList.getLength(); j++){
						node = fileChildrenList.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							strArr = new String[2];
							strArr[0] = node.getNodeName();					//バッチID
						}

						NodeList csvChildrenList = node.getChildNodes();
						for(int k=0; k<csvChildrenList.getLength(); k++){
							node = csvChildrenList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								if(FILE_NAME.equals(node.getNodeName())){
									strArr[1] = node.getTextContent();	// パラメータ名
									fileNameList.add(strArr);
								}
							}
						}
					}
				}
			}
		}else if(OITSUKI_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// バッチIDに該当する追いつき処理のファイル名取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(OITSUKI_INFO);

			if(batchIdNodeList.getLength() > 0){

				Node node = batchIdNodeList.item(0);

				if(node.getNodeType() == Node.ELEMENT_NODE){

					NodeList nodeChildrenList = node.getChildNodes();
					for(int i = 0; i < nodeChildrenList.getLength(); i++){
						node = nodeChildrenList.item(i);
						if(node.getNodeType() == Node.ELEMENT_NODE){
							String batchId = node.getNodeName();

							NodeList fileNameNodeList = node.getChildNodes();
							for(int j = 0; j < fileNameNodeList.getLength(); j++){
								node = fileNameNodeList.item(j);
								if(node.getNodeType() == Node.ELEMENT_NODE){
									String[] strArr = new String[3];
									if(OITSUKI_FILE.equals(node.getNodeName())){
										Element element = (Element)node;
										strArr[0] = batchId;
										strArr[1] = element.getAttribute("name");
										strArr[2] = node.getTextContent();
									}
									fileNameList.add(strArr);

								}
							}
						}
					}
				}
			}
		}else if(DATA_CREANSING.equals(infoKind)){
			//-----------------------------------------------------------
			// バッチIDに該当するデータクレンジング対象テーブル名取得
			//-----------------------------------------------------------
			List<String[]> fileNameList = (List<String[]>)objInfo;
			NodeList batchIdNodeList = doc.getElementsByTagName(DATA_CREANSING);

			for(int i=0; i<batchIdNodeList.getLength(); i++){
				Node node = batchIdNodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String[] strArr = new String[2];

					NodeList fileChildrenList = node.getChildNodes();
					for(int j=0; j<fileChildrenList.getLength(); j++){
						node = fileChildrenList.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							strArr = new String[2];
							strArr[0] = node.getNodeName();				//バッチID
						}

						NodeList csvChildrenList = node.getChildNodes();
						for(int k=0; k<csvChildrenList.getLength(); k++){
							node = csvChildrenList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								if(TABLE_NAME.equals(node.getNodeName())){
									strArr[1] = node.getTextContent();	// テーブル名
									fileNameList.add(strArr);
								}
							}
						}
					}
				}
			}
		}else if(HIBERNATE_INFO.equals(infoKind)){
			//-----------------------------------------------------------
			// Hibernateアクセス情報（基本的に臨時データベースのアクセス）情報取得
			//-----------------------------------------------------------
			HibernateAccessInfoBean hibernateInfoBean = (HibernateAccessInfoBean)objInfo;
			NodeList hibernateNodeList = doc.getElementsByTagName(HIBERNATE_INFO);

			for(int i=0; i<hibernateNodeList.getLength(); i++){
				for(Node node = hibernateNodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()){

					String nodeName = node.getNodeName().toUpperCase();

					switch (nodeName){
					case "DRIVER": hibernateInfoBean.setDriver(node.getTextContent()); break;
					case "CONNECT": hibernateInfoBean.setConnect(node.getTextContent()); break;
					case "DIALECT":hibernateInfoBean.setDialect(node.getTextContent()); break;
					case "SHOW": hibernateInfoBean.setShow(node.getTextContent()); break;
					case "ID": hibernateInfoBean.setId(node.getTextContent()); break;
					case "PASSWORD": hibernateInfoBean.setPassword(node.getTextContent()); break;
					}
				}
			}
		}
	}
	/**
	 * 移行環境設定(XML)ファイルが存在しているPathを検索する
	 * @param dir
	 * @return
	 */
	private static String searchFilePath(File dir, String fileName){

		File[] files = dir.listFiles();

		if( files != null){
			for(File f : files){
				if(f.isDirectory()){
					String resultPath = searchFilePath(f, fileName);
					if(resultPath != null){
						return resultPath;
					}
				}else if(f.isFile()){
					if(f.getName().equals(fileName)){
						return f.getParent();
					}
				}
			}
		}
		return null;
	}

}



