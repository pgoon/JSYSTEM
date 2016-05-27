@echo off
rem ################################################################
rem 機能名：移行バッチの共通環境変数定義
rem 実行方式：親バッチからコール
rem バッチID：なし
rem 実行スクリプト：無し
rem ★編集箇所：①ログ出力フォルダ、②JAVA_HOME定義
rem ################################################################

rem ========================================【バッチ起動初期化】
rem バッチファイルが存在するフォルダへ移動
PUSHD %~DP0
rem エラーレベルコード初期化
cd .

rem ========================================【環境変数定義】
rem ①ログ出力フォルダ
SET LOG_DIR=C:/IKOU/IKOU_BATCH/logs

rem ========================================【ログ生成フォルダ作成】
IF NOT EXIST "%LOG_DIR%" MKDIR "%LOG_DIR%"

CD..
SET CURRENT_DIR=%CD%

rem ========================================【Java実行環境設定】
rem ②JAVA_HOME定義
IF NOT EXIST "%JAVA_HOME%" SET JAVA_HOME=C:\pleiades\java\8

SET JAR_FILE=%CURRENT_DIR%\IKOU_BATCH.jar
SET JAVA_CMD=%JAVA_HOME%\bin\java
SET JAVA_OPTION=-Dlog4j.configuration="file:../lib/log4j.xml" -jar -Xms128m -Xmx512m -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
SET PATH=%PATH%;.
PUSHD %~DP0
