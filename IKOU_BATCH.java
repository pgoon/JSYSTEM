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
 * �ڍs�o�b�`���s���s����N���X
 * �O���R�}���h�����s�v���O����ID���p�����[�^�Ƃ��Ď󂯎���ĊY���v���O���������s����B
 * ���ӁFIKOU_IF�C���^�t�F�[�X���������Ă��Ȃ��v���O�����͎��s�s��
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
	 * �e�o�b�`ID�ɂČďo�����v���O���������s����
	 *
	 *  @param args[0] �v���O����ID
	 *  @param args[1] �o�b�`ID
	 */
	public static void main(String[] args){
		// ****************************************************************************** //
		// ���O�t�@�C���w��
		// ****************************************************************************** //
		LogFactory.createLogger(null);

		IKOU_BATCH ikouBatch = new IKOU_BATCH();
		ikouBatch.runBatch(args);
	}

	/**
	 * �e�o�b�`ID�ɂČďo�����v���O���������s����
	 *
	 *  @param args[0] �v���O����ID
	 *  @param args[1] �o�b�`ID
	 */
	private void runBatch(String[] args){

		startTime = System.currentTimeMillis();

		// ****************************************************************************** //
		// CSV�捞�f�[�^�����p�v���O�������s�̏ꍇ
		// ****************************************************************************** //
		if(StringUtil.isEquals("VERIFY_CSV_DATA", args[0])){
			VerifyCsvData.runVerify(args);
			System.exit(0);
		}

		LogUtil.info(this.getClass(), "===========�y " + "�o�b�`ID<" + args[1] + "> �v���O����ID<" + args[0] + "> �z���J�n���܂��B===========");

		// �V�X�e���������ʕێ��ϐ�
		int systemReturn = NORMAL;
		// ���s�o�b�`�C���X�^���X
		IKOU_IF runBat = null;

		try{
			// ****************************************************************************** //
			// �p�����[�^����n���ꂽ�o�b�`ID�����݂��邩�`�F�b�N
			// ****************************************************************************** //
			IKOU_BATCH_ID_ENUM batchIdEnum = BatchIdUtil.getBatchIdEnum(args[1]);
			if(batchIdEnum == null){
				throw new ApplicationException("���݂��Ȃ��o�b�`ID�ł��B�F[" + args[1] + "]");
			}

			// ****************************************************************************** //
			// �p�����[�^����n���ꂽ�v���O����ID���v���O����ID�ꗗ�ɑ��݂��邩�`�F�b�N
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
				throw new ApplicationException("���݂��Ȃ��v���O����ID�ł��B�F[" + args[0] + "]");
			}

			// ****************************************************************************** //
			// �Y���v���O����ID�ɂĎ��s�v���O�����C���X�^���X�𐶐�����B
			// ****************************************************************************** //
			Class<?> cl = Class.forName(this.getClass().getPackage().getName() + "." + args[0]);
			runBat = (IKOU_IF) cl.newInstance();

			// ****************************************************************************** //
			// �u�Վ��f�[�^�x�[�X�������폜�v�o�b�`��Hibernate�A�N�Z�X���Ȃ��B
			// ****************************************************************************** //
			if(!"IKOU_COMM_0010".equals(args[0]) && !"IKOU_COMM_0020".equals(args[0])){
				// Hibernate�R�l�N�V���������i�Վ�DB�A�N�Z�X����{�Ƃ���B�j
				HibernateUtils.openSession(HibernateUtils.ACCESS_RINJI);
				Transaction trx = HibernateUtils.getSession().beginTransaction();

				// �g�����U�N�V�����Z�b�g
				runBat.setTrx(trx);
			}

			// ****************************************************************************** //
			// �o�b�`ID�ƃv���O����ID���Z�b�g��Y���o�b�`�����s����B
			// ****************************************************************************** //
			IKOU_IF.DEBUG_MODE = XMLPasingUtil.getDebugModeInfo();
			// �C���X�^���X�Ƀo�b�`ID�Z�b�g
			runBat.setBatchId(batchIdEnum);
			// �C���X�^���X�Ƀv���O����ID�Z�b�g
			runBat.setProgramId(args[0]);
			// �o�b�`���s
			runBat.runBatch();

			// ****************************************************************************** //
			// ����������Hibernate�g�����U�N�V�����R�~�b�g
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
			// �G���[�����O�t�@�C���ɏo�͂���B
			LogUtil.info(this.getClass(), e.getMessage());

		}catch(Exception e){
			if(runBat != null){
				if(runBat.getTrx() != null && runBat.getTrx().isActive()){
					runBat.getTrx().rollback();
				}
			}
			systemReturn = ERROR;
			// �G���[�����O�t�@�C���ɏo�͂���B
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			String str = sw.toString();
			LogUtil.info(this.getClass(), str);

		}finally{
			// ****************************************************************************** //
			// Hibernate�R�l�N�V�����N���[�Y
			// ****************************************************************************** //
			HibernateUtils.closeSession();

			endTime = System.currentTimeMillis();
			long resTime = endTime - startTime;
			LogUtil.info(this.getClass(), "======================�y�� �o�b�`�������ԁF" + DateUtil.timeFormat(resTime) + " ���z=======================");

			// �ŏI�������ʂ�ԋp
			System.exit(systemReturn);
		}

	}
}
