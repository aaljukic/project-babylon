FROM postgres:alpine3.18
COPY 01_init.sql /docker-entrypoint-initdb.d/
COPY 02_data.sql /docker-entrypoint-initdb.d/
