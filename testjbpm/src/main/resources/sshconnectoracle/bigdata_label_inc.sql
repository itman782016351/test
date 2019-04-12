/*
create table CX_NEW_AST_LBL
(
  ROW_ID           VARCHAR2(15 CHAR) not null,
  CREATED          DATE default sysdate not null,
  CREATED_BY       VARCHAR2(15 CHAR) not null,
  LAST_UPD         DATE default sysdate not null,
  LAST_UPD_BY      VARCHAR2(15 CHAR) not null,
  MODIFICATION_NUM NUMBER(10) default 0 not null,
  CONFLICT_ID      VARCHAR2(15 CHAR) default '0' not null,
  DB_LAST_UPD      DATE,
  DB_LAST_UPD_SRC  VARCHAR2(50 CHAR),
  LABEL            VARCHAR2(2000 CHAR),
  SERVICE_97_NO    VARCHAR2(100 CHAR),
  SERVICE_NO       VARCHAR2(100 CHAR),
  STATUS           VARCHAR2(30 CHAR),
  STATUS_LOG       VARCHAR2(1000 CHAR),
  TYPE VARCHAR2(20)
)
*/



DECLARE

    B_FLG        INTEGER := 0;
  V_ERROR_CODE NUMBER;
  V_ERROR_MESS VARCHAR2(200);
    V_CNT NUMBER;
    V_CNT1 NUMBER:=0;
    CURSOR CUR_LBL IS
        SELECT * FROM CX_NEW_AST_LBL WHERE STATUS = '新建' and type in ('1','2') and rownum<=1000 ;
BEGIN
  loop
        B_FLG  := 0;
    FOR CUR_ITEM IN CUR_LBL LOOP
          V_CNT1 := V_CNT1+1;
            B_FLG   := 1;
            IF CUR_ITEM.TYPE = '1' THEN
                
              begin
                INSERT 
                INTO SIEBEL.S_ASSET_XM XM
                    (PAR_ROW_ID,
                     NAME,
                     TYPE,
                     ATTRIB_12,
                     ATTRIB_01,
                     ATTRIB_04,
                     CONFLICT_ID,
                     CREATED_BY,
                     LAST_UPD_BY,
                     MODIFICATION_NUM,
                     ROW_ID)
                    SELECT A.ROW_ID,
                           CUR_ITEM.LABEL,
                           'ASSETLABEL',
                           SYSDATE,
                           CUR_ITEM.SERVICE_NO,
                           CUR_ITEM.SERVICE_97_NO,
                           0,
                           '0-1',
                           '0-1',
                           0,
                           SIEBEL.S_SEQUENCE_PKG.GET_NEXT_ROWID
                      FROM SIEBEL.S_ASSET A
                     WHERE A.PAR_ASSET_ID IS NULL
                       AND A.STATUS_CD = '活动'
                       AND A.X_TRACK_NUM = CUR_ITEM.SERVICE_97_NO;

                UPDATE CX_NEW_AST_LBL
                   SET STATUS = '成功', LAST_UPD = SYSDATE - 1 / 3
                 WHERE ROW_ID = CUR_ITEM.ROW_ID;
              exception 
                 when others then
                 UPDATE CX_NEW_AST_LBL
                   SET STATUS     = '失败',
                       STATUS_LOG = '标签已存在',
                       LAST_UPD   = SYSDATE - 1 / 3
                 WHERE ROW_ID = CUR_ITEM.ROW_ID;
              end;
            ELSIF CUR_ITEM.TYPE = '2' THEN
              begin
                DELETE FROM SIEBEL.S_ASSET_XM
                 WHERE TYPE = 'ASSETLABEL'
                   AND ATTRIB_04 = CUR_ITEM.SERVICE_97_NO
                   AND NAME = CUR_ITEM.LABEL
                   AND ROWNUM = 1;

                UPDATE CX_NEW_AST_LBL
                   SET STATUS = '成功', LAST_UPD = SYSDATE - 1 / 3
                 WHERE ROW_ID = CUR_ITEM.ROW_ID;
               exception 
                 when others then
                 UPDATE CX_NEW_AST_LBL
                   SET STATUS     = '失败',
                       STATUS_LOG = '标签不存在',
                       LAST_UPD   = SYSDATE - 1 / 3
                 WHERE ROW_ID = CUR_ITEM.ROW_ID;
              end;
            END IF;
       
    END LOOP;
    INSERT  INTO TMP_20000102_XY_LOG
      (NAME, ERROR_CODE, ERROR_MESS, NUM, DT)
    VALUES
      ('complete inc_label', V_ERROR_CODE, V_ERROR_MESS, V_CNT1, SYSDATE);
    COMMIT;


    EXIT WHEN B_FLG = 0;
  end loop;
EXCEPTION
   WHEN OTHERS THEN
     ROLLBACK;
    INSERT  INTO TMP_20000102_XY_LOG
      (NAME, ERROR_CODE, ERROR_MESS, NUM, DT)
    VALUES
      ('Error big_data_label_inc' , V_ERROR_CODE, V_ERROR_MESS, 0, SYSDATE);
    COMMIT;

END;
/*
DELETE FROM CX_NEW_AST_LBL
DELETE FROM  siebel.s_asset_xm 
SELECT * FROM siebel.s_asset_xm order by created desc FOR UPDATE


UPDATE siebel.s_asset_xm xm
  SET (ATTRIB_04, ATTRIB_01)
    = (
    SELECT x_track_num, serial_num  FROM siebel.s_asset a
    WHERE a.row_id = xm.par_row_id)
*/


/

exit;
