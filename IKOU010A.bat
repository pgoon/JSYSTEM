@echo off
rem ======================�y���ϐ���`�z=======================================
CALL IKOU_INFO.bat

rem ======================�y�o�b�`��`�z=========================================
SET BATCH_ID=IKOU010A
SET PROGRAM_ID=IKOU_COMM_0010

SET LOG_FILE=%LOG_DIR%/%BATCH_ID%.batch.log
ECHO  > %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%
ECHO �y�@�\���z�Վ��f�[�^�x�[�X�ƃ��[�N�e�[�u���쐬                 >> %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%
ECHO �y%BATCH_ID%�z %DATE% %TIME% �o�b�` Start                      >> %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%

rem ======================�yJava�v���O�������s�z==================================
"%JAVA_CMD%" %JAVA_OPTION% %JAR_FILE% %PROGRAM_ID% %BATCH_ID%       >> %LOG_FILE%

CALL IKOU_END.bat
