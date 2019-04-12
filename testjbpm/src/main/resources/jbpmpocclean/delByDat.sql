DELIMITER $$

USE `JBPMPOC`$$

DROP PROCEDURE IF EXISTS `delByDat`$$

CREATE DEFINER=`root`@`%` PROCEDURE `delByDat`(IN `indat` CHAR)
BEGIN

		DECLARE  startTime VARCHAR(19) DEFAULT '2014-00-00 00:00:00';

		DECLARE  tempTime VARCHAR(19) DEFAULT DATE_SUB(NOW(),INTERVAL '2' MONTH);-- 当前时间后退三个月时间

		-- DECLARE endTime  VARCHAR(19) DEFAULT DATE_SUB(tempTime,INTERVAL '3' MONTH);

		DECLARE  MONTHSTR VARCHAR(7) DEFAULT SUBSTR(tempTime,1,7);

		DECLARE  endTime VARCHAR(19) DEFAULT CONCAT(MONTHSTR,'-00 00:00:00');






		DECLARE  hprocessInstanceId BIGINT DEFAULT 0; -- 历史流程实例id
		DECLARE  hprocessInstanceIdStarttime VARCHAR(20) DEFAULT '';  -- 历史流程实例启动时间
		DECLARE  hprocessInstanceIdEndtime VARCHAR(20) DEFAULT '';  -- 历史流程实例结束时间

		DECLARE  hactinstId BIGINT DEFAULT 0;  -- 历史活动实例id

		DECLARE  htaskId BIGINT DEFAULT 0;  -- 历史人工任务id

		DECLARE  hvarId BIGINT DEFAULT 0;  -- 历史流程变量id

		DECLARE  rexecutionId BIGINT  DEFAULT 0; -- 正在执行流程实例id

		DECLARE  rvarId BIGINT  DEFAULT 0; -- 正在执行流程变量id

		DECLARE  rtaskId BIGINT  DEFAULT 0; -- 正在执行人工任务id

		DECLARE  rswinmlanceId BIGINT  DEFAULT 0; -- 泳道id，为了删除partation表记录，本项目无记录

		DECLARE  processCompleteFlag INT DEFAULT 0; --  流程是否结束标识
		DECLARE  taskCompleteFlag INT DEFAULT 0; -- 任务是否结束标识
		DECLARE  doneFlag INT DEFAULT 0; -- 完成标识，0：未完成；1：已完成
		DECLARE  notfound INT DEFAULT 0;-- 是否未找到数据 标记

		-- 启动事物
	  -- START TRANSACTION;
		-- ALTER TABLE jbpm4_hist_var DROP FOREIGN KEY 'FK_HVAR_HPROCI';
		-- ALTER TABLE jbpm4_hist_var ADD CONSTRAINT 'FK_HVAR_HPROCI' FOREIGN KEY ('HPROCI_') REFERENCES jbpm4_hist_procinst('DBID_') ON DELETE CASCADE ;


		/* 声明历史流程实例的游标 */
		-- DECLARE hprocessInstanceRS CURSOR FOR SELECT DBID_,START_,END_ FROM JBPM4_HIST_PROCINST  where START_>="2014-00-00 00:00:00" and  START_<"2015-00-00 00:00:00";
		DECLARE hprocessInstanceRS CURSOR FOR SELECT DBID_,START_,END_ FROM JBPM4_HIST_PROCINST  WHERE START_>=startTime AND  START_<endTime;

		/* 声明历史活动实例的游标 */
		DECLARE hactinstRS CURSOR FOR SELECT DBID_,HTASK_ FROM JBPM4_HIST_ACTINST  WHERE HPROCI_=hprocessInstanceId;

		/* 声明历史活动实例的游标 */
		DECLARE htaskRS CURSOR FOR SELECT DBID_ FROM JBPM4_HIST_TASK WHERE DBID_=hactinstId;

		/* 声明历史活动实例的游标 */
		DECLARE hvarRS CURSOR FOR SELECT DBID_ FROM JBPM4_HIST_VAR WHERE HTASK_=rtaskId;

		/** 声明正在执行流程实例的游标（历史表中因为各种原因未完成的） **/
		DECLARE rexecutionRS CURSOR FOR SELECT DBID_ FROM JBPM4_EXECUTION WHERE DBID_=hprocessInstanceId;

		/** 声明正在执行流程变量的游标(只删除2014年条件下由于各种原因未完成的流程实例所对应的流程变量) */
		DECLARE rvarRS CURSOR FOR SELECT DBID_ FROM JBPM4_VARIABLE WHERE EXECUTION_=hprocessInstanceId;

		/** 声明正在执行的人工任务的游标(只是2014年开始的流程实例所对应的) **/
		DECLARE rtaskRS CURSOR FOR SELECT DBID_ FROM JBPM4_TASK WHERE DBID_=rtaskId;

		/** 声明泳道的结果集游标，为了删除paritation表，该项目没有记录，实际删除条数为0 **/
		DECLARE rswinmlanceRS CURSOR FOR SELECT DBID_ FROM JBPM4_SWIMLANE WHERE DBID_=rswinmlanceId;

		/* 异常处理 */
		DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET doneFlag = 1;


		/** 删除s，使用嵌套循环..... **/
		SET FOREIGN_KEY_CHECKS = 0;

		  OPEN hprocessInstanceRS;
				FETCH hprocessInstanceRS INTO hprocessInstanceId,hprocessInstanceIdStarttime,hprocessInstanceIdEndtime;-- 获取历史流程实例表的数据数据

				REPEAT
					IF hprocessInstanceIdEndtime='' THEN
					 -- 没有结束，执行删除正在执行的流程实例表
						/** 1.查询正在执行的流程实例记录s **/
						SET rexecutionId=hprocessInstanceId; -- 未完成的流程实例与正在执行的流程实例id做对应
						OPEN rexecutionRS;
							FETCH rexecutionRS INTO rexecutionId;
							REPEAT
								/** 2.查询该流程实例下的所有正在执行的流程变量记录s 2**/
								OPEN hvarRS;
									FETCH hvarRS INTO rvarId;
									REPEAT
										/** 3.删除正在执行的流程变量所对应的人工任务记录s 3**/
										DELETE FROM JBPM4_TASK	WHERE DBID_=rvarId;
										/** 3.删除正在执行的流程变量所对应的人工任务记录e 3**/
										DELETE FROM JBPM4_VARIABLE WHERE DBID_=rvarId; -- 单条删除流程变量记录
										FETCH hvarRS INTO rvarId;
									UNTIL doneFlag END REPEAT;
								CLOSE hvarRS;
								/** 2.查询该流程实例下的所有正在执行的流程变量记录e 2**/
							DELETE FROM JBPM4_EXECUTION WHERE DBID_=rexecutionId;  -- 单条删除流程对象记录
							FETCH rexecutionRS INTO rexecutionId;
							UNTIL doneFlag END REPEAT;
						CLOSE rexecutionRS;

					END IF;
					/*** ======删除历史流程记录表相关数据===== **/

					/** 1.查询活动实例表 s **/
					OPEN hactinstRS;
						FETCH hactinstRS INTO hactinstId,htaskId;

						REPEAT
							/** 2.查询历史人工活动表记录s **/
								OPEN htaskRS;
									FETCH htaskRS INTO htaskId;
									REPEAT
										/** 3.删除历史人工任务 **/
										DELETE FROM JBPM4_HIST_TASK WHERE DBID_=htaskId;
										FETCH htaskRS INTO htaskId;
									UNTIL doneFlag END REPEAT;
								CLOSE htaskRS;
							/** 2.查询历史人工活动表记录s **/
							FETCH hactinstRS INTO hactinstId,htaskId;
						UNTIL doneFlag END REPEAT;
					CLOSE hactinstRS;

					/** 1.查询活动实例表 e **/
					/*** ======删除历史流程记录表相关数据===== **/

					/** 删除历史活动实例表 **/
					DELETE FROM JBPM4_HIST_ACTINST WHERE HPROCI_=hprocessInstanceId;
					/** 删除历史流程变量表 **/
					DELETE FROM JBPM4_HIST_VAR WHERE HPROCI_=hprocessInstanceId;

					SET doneFlag=0;
					FETCH hprocessInstanceRS INTO hprocessInstanceId,hprocessInstanceIdStarttime,hprocessInstanceIdEndtime;-- 获取历史流程实例表的数据数据

				UNTIL doneFlag END REPEAT;
			CLOSE hprocessInstanceRS;
			/** 删除历史流程实例记录 **/
			-- delete from JBPM4_HIST_PROCINST where START_>='2014-00-00 00:00:00' and  START_<'2015-00-00 00:00:00';

		DELETE FROM JBPM4_HIST_PROCINST WHERE START_>=startTime AND  START_<endTime;


		SET FOREIGN_KEY_CHECKS = 1;

END$$

DELIMITER ;