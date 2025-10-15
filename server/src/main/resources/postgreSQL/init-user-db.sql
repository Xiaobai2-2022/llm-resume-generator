-- init-user-db.sql

\set dev_password :'DB_DEV_PASSWORD'

-- Init Role
CREATE ROLE fx_app_dev WITH
    LOGIN
    PASSWORD :'dev_password';

-- Init Database
CREATE DATABASE fx_app_db
    OWNER fx_app_dev;

-- Grant Privilege
GRANT ALL PRIVILEGES ON DATABASE fx_app_db TO fx_app_dev;

-- Allow creating additional databases
ALTER ROLE fx_app_dev CREATEDB;
