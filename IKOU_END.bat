rem =======================�y�������ʔ���z=========================
IF %ERRORLEVEL% EQU 0 GOTO NORMAL-RTN
IF %ERRORLEVEL% NEQ 0 GOTO ERROR-RTN

rem =======================�y����I�������z=========================
:NORMAL-RTN
ECHO ************************************************************** >> %LOG_FILE%
ECHO                       ����I���i%ERRORLEVEL%�j                 >> %LOG_FILE%
GOTO END-RTN

rem =======================�y�ُ�I�������z=========================
:ERROR-RTN
ECHO ************************************************************** >> %LOG_FILE%
ECHO                       �ُ�I���i%ERRORLEVEL%�j                 >> %LOG_FILE%
GOTO END-RTN

rem =======================�y�o�b�`�I�������z=======================
:END-RTN
ECHO �y%BATCH_ID%�z %DATE% %TIME% �o�b�` End                        >> %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%
