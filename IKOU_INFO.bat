@echo off
rem ################################################################
rem �@�\���F�ڍs�o�b�`�̋��ʊ��ϐ���`
rem ���s�����F�e�o�b�`����R�[��
rem �o�b�`ID�F�Ȃ�
rem ���s�X�N���v�g�F����
rem ���ҏW�ӏ��F�@���O�o�̓t�H���_�A�AJAVA_HOME��`
rem ################################################################

rem ========================================�y�o�b�`�N���������z
rem �o�b�`�t�@�C�������݂���t�H���_�ֈړ�
PUSHD %~DP0
rem �G���[���x���R�[�h������
cd .

rem ========================================�y���ϐ���`�z
rem �@���O�o�̓t�H���_
SET LOG_DIR=C:/IKOU/IKOU_BATCH/logs

rem ========================================�y���O�����t�H���_�쐬�z
IF NOT EXIST "%LOG_DIR%" MKDIR "%LOG_DIR%"

CD..
SET CURRENT_DIR=%CD%

rem ========================================�yJava���s���ݒ�z
rem �AJAVA_HOME��`
IF NOT EXIST "%JAVA_HOME%" SET JAVA_HOME=C:\pleiades\java\8

SET JAR_FILE=%CURRENT_DIR%\IKOU_BATCH.jar
SET JAVA_CMD=%JAVA_HOME%\bin\java
SET JAVA_OPTION=-Dlog4j.configuration="file:../lib/log4j.xml" -jar -Xms128m -Xmx512m -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
SET PATH=%PATH%;.
PUSHD %~DP0
