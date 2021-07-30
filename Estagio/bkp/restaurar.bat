set PGUSER=postgres
set PGPASSWORD=postgres123
cd bkp
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "oficina" --verbose --no-password "bkp.sql"