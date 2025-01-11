DECLARE
tbl_count number;
sql_stmt long;

BEGIN
    SELECT COUNT(*) INTO tbl_count 
    FROM dba_tables
	WHERE table_name = 'table_name_to_be_drop' and owner = {currentSchema};

    IF(tbl_count == 0)
        THEN
        sql_stmt:='DROP TABLE table_name_to_be_drop';
        EXECUTE IMMEDIATE sql_stmt;
    END IF;
END;