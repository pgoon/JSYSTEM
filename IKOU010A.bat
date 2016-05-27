@echo off
rem ======================【環境変数定義】=======================================
CALL IKOU_INFO.bat

rem ======================【バッチ定義】=========================================
SET BATCH_ID=IKOU010A
SET PROGRAM_ID=IKOU_COMM_0010

SET LOG_FILE=%LOG_DIR%/%BATCH_ID%.batch.log
ECHO  > %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%
ECHO 【機能名】臨時データベースとワークテーブル作成                 >> %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%
ECHO 【%BATCH_ID%】 %DATE% %TIME% バッチ Start                      >> %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%

rem ======================【Javaプログラム実行】==================================
"%JAVA_CMD%" %JAVA_OPTION% %JAR_FILE% %PROGRAM_ID% %BATCH_ID%       >> %LOG_FILE%

CALL IKOU_END.bat
