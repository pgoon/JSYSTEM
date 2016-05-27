rem =======================yˆ—Œ‹‰Ê”»’èz=========================
IF %ERRORLEVEL% EQU 0 GOTO NORMAL-RTN
IF %ERRORLEVEL% NEQ 0 GOTO ERROR-RTN

rem =======================y³íI—¹ˆ—z=========================
:NORMAL-RTN
ECHO ************************************************************** >> %LOG_FILE%
ECHO                       ³íI—¹i%ERRORLEVEL%j                 >> %LOG_FILE%
GOTO END-RTN

rem =======================yˆÙíI—¹ˆ—z=========================
:ERROR-RTN
ECHO ************************************************************** >> %LOG_FILE%
ECHO                       ˆÙíI—¹i%ERRORLEVEL%j                 >> %LOG_FILE%
GOTO END-RTN

rem =======================yƒoƒbƒ`I—¹ˆ—z=======================
:END-RTN
ECHO y%BATCH_ID%z %DATE% %TIME% ƒoƒbƒ` End                        >> %LOG_FILE%
ECHO ************************************************************** >> %LOG_FILE%
