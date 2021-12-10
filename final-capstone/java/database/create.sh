#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=final_capstone
USR=tristanguckenberger
PGPASSWORD=Fpw26gcg!!
psql -U $USR -f "$BASEDIR/dropdb.sql" &&
createdb -U $USR $DATABASE &&
psql -U $USR -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U $USR -d $DATABASE -f "$BASEDIR/user.sql"
