# Oracle SQL Quick Reference

## Table to List All Tables
~~~
dba_tables
~~~

## Run sql file in oracle sql client using
~~~
"@{full path till sql script}" => @C:\folder\dummy.sql
~~~

## Use following technique to make alter script idempotent (Even if this script gets executed multiple times it will not fail)
~~~
-- Add OBJECT_STATUS column if it does not exist
DECLARE
    v_Count INTEGER := 0;
BEGIN
    SELECT COUNT(*) INTO v_Count 
    FROM USER_TAB_COLUMNS 
    WHERE TABLE_NAME = 'LIMIT_TABLE' 
      AND COLUMN_NAME = 'OBJECT_STATUS';
    IF v_Count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE LIMIT_TABLE ADD OBJECT_STATUS VARCHAR2(255)';
    END IF;
END;
/

-- Add comment (this can be safely run multiple times)
COMMENT ON COLUMN LIMIT_TABLE.OBJECT_STATUS IS 'Holds the current status of the entity.';
~~~
