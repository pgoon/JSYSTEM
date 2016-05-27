package jp.stbnet.stg.hosho.ikou.main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import jp.stbnet.stg.hosho.ikou.exception.ApplicationException;
import jp.stbnet.stg.hosho.ikou.test.VerifyCsvData;
import jp.stbnet.stg.hosho.ikou.utils.BatchIdUtil;
import jp.stbnet.stg.hosho.ikou.utils.DateUtil;
import jp.stbnet.stg.hosho.ikou.utils.HibernateUtils;
import jp.stbnet.stg.hosho.ikou.utils.LogFactory;
import jp.stbnet.stg.hosho.ikou.utils.LogUtil;
import jp.stbnet.stg.hosho.ikou.utils.StringUtil;
import jp.stbnet.stg.hosho.ikou.utils.XMLPasingUtil;

import org.hibernate.Transaction;

/**
 * 移行バッチを代行実行するクラス
 * 外部コマンドより実行プログラムIDをパラメータとして受け取って該当プログラムを実行する。
 * 注意：IKOU_IFインタフェースを実装していないプログラムは実行不可
 *
 * @author NSD.LEE
 *
 */
public class IKOU_BATCH {

	public final static int NORMAL = 0;
	public final static int ERROR = 1;
	public static long startTime = 0L;
	public static long endTime = 0L;

	/**
	 * 各バッチIDにて呼出したプログラムを実行する
	 *
	 *  @param args[0] プログラムID
	 *  @param args[1] バッチID
	 */
	public static void main(String[] args){
		// ****************************************************************************** //
		// ログファイル指定
		// ****************************************************************************** //
		LogFactory.createLogger(null);

		IKOU_BATCH ikouBatch = new IKOU_BATCH();
		ikouBatch.runBatch(args);
	}

	/**
	 * 各バッチIDにて呼出したプログラムを実行する
	 *
	 *  @param args[0] プログラムID
	 *  @param args[1] バッチID
	 */
	private void runBatch(String[] args){

		startTime = System.currentTimeMillis();

		// ****************************************************************************** //
		// CSV取込データ調査用プログラム実行の場合
		// ****************************************************************************** //
		if(StringUtil.isEquals("VERIFY_CSV_DATA", args[0])){
			VerifyCsvData.runVerify(args);
			System.exit(0);
		}

		LogUtil.info(this.getClass(), "===========【 " + "バッチID<" + args[1] + "> プログラムID<" + args[0] + "> 】を開始します。===========");

		// システム処理結果保持変数
		int systemReturn = NORMAL;
		// 実行バッチインスタンス
		IKOU_IF runBat = null;

		try{
			// ****************************************************************************** //
			// パラメータから渡されたバッチIDが存在するかチェック
			// ****************************************************************************** //
			IKOU_BATCH_ID_ENUM batchIdEnum = BatchIdUtil.getBatchIdEnum(args[1]);
			if(batchIdEnum == null){
				throw new ApplicationException("存在しないバッチIDです。：[" + args[1] + "]");
			}

			// ****************************************************************************** //
			// パラメータから渡されたプログラムIDがプログラムID一覧に存在するかチェック
			// ****************************************************************************** //
			List<String> programIdList = XMLPasingUtil.getBatchIdInfo();
			boolean checkFlg = false;
			for(String programID:programIdList){
				if(programID.equals(args[0])){
					checkFlg = true;
					break;
				}
			}
			if(!checkFlg){
				throw new ApplicationException("存在しないプログラムIDです。：[" + args[0] + "]");
			}

			// ****************************************************************************** //
			// 該当プログラムIDにて実行プログラムインスタンスを生成する。
			// ****************************************************************************** //
			Class<?> cl = Class.forName(this.getClass().getPackage().getName() + "." + args[0]);
			runBat = (IKOU_IF) cl.newInstance();

			// ****************************************************************************** //
			// 「臨時データベース生成且つ削除」バッチはHibernateアクセスしない。
			// ****************************************************************************** //
			if(!"IKOU_COMM_0010".equals(args[0]) && !"IKOU_COMM_0020".equals(args[0])){
				// Hibernateコネクション生成（臨時DBアクセスを基本とする。）
				HibernateUtils.openSession(HibernateUtils.ACCESS_RINJI);
				Transaction trx = HibernateUtils.getSession().beginTransaction();

				// トランザクションセット
				runBat.setTrx(trx);
			}

			// ****************************************************************************** //
			// バッチIDとプログラムIDをセット後該当バッチを実行する。
			// ****************************************************************************** //
			IKOU_IF.DEBUG_MODE = XMLPasingUtil.getDebugModeInfo();
			// インスタンスにバッチIDセット
			runBat.setBatchId(batchIdEnum);
			// インスタンスにプログラムIDセット
			runBat.setProgramId(args[0]);
			// バッチ実行
			runBat.runBatch();

			// ****************************************************************************** //
			// 処理完了後Hibernateトランザクションコミット
			// ****************************************************************************** //
			if(runBat.getTrx() != null && runBat.getTrx().isActive()){
				runBat.getTrx().commit();
			}

		}catch(ApplicationException e){
			if(runBat != null){
				if(runBat.getTrx() != null && runBat.getTrx().isActive()){
					runBat.getTrx().rollback();
				}
			}
			systemReturn = ERROR;
			// エラーをログファイルに出力する。
			LogUtil.info(this.getClass(), e.getMessage());

		}catch(Exception e){
			if(runBat != null){
				if(runBat.getTrx() != null && runBat.getTrx().isActive()){
					runBat.getTrx().rollback();
				}
			}
			systemReturn = ERROR;
			// エラーをログファイルに出力する。
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			String str = sw.toString();
			LogUtil.info(this.getClass(), str);

		}finally{
			// ****************************************************************************** //
			// Hibernateコネクションクローズ
			// ****************************************************************************** //
			HibernateUtils.closeSession();

			endTime = System.currentTimeMillis();
			long resTime = endTime - startTime;
			LogUtil.info(this.getClass(), "======================【● バッチ処理時間：" + DateUtil.timeFormat(resTime) + " ●】=======================");

			// 最終処理結果を返却
			System.exit(systemReturn);
		}

	}
}
