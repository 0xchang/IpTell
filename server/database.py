import re
import sqlite3
import time
from unittest import result


def table_create():
    # 创建数据库
    create_table_sql = "create table if not exists mydata (myip text,mytime integer,myvalue text)"
    conn = sqlite3.connect('data.db')
    cursor = conn.cursor()
    cursor.execute(create_table_sql)
    cursor.close()
    conn.commit()
    conn.close()


def select(myip) -> list:
    # 查询ip对应的数据
    select_ip_sql = "select * from mydata where myip='{}'".format(myip)
    conn = sqlite3.connect('data.db')
    cursor = conn.cursor()
    result = cursor.execute(select_ip_sql)
    result = list(result)
    cursor.close()
    conn.commit()
    conn.close()
    return result


def insert(myip, mytime, myvalue):
    mytime = int(mytime)
    insert_into_sql = "insert into mydata (myip,mytime,myvalue) values('{}',{},'{}')".format(
        myip, mytime, myvalue)
    conn = sqlite3.connect('data.db')
    cursor = conn.cursor()
    cursor.execute(insert_into_sql)
    cursor.close()
    conn.commit()
    conn.close()


def update(myip, mytime, myvalue):
    mytime = int(mytime)
    update_sql = "update mydata set mytime={1},myvalue='{2}'  where myip='{0}'".format(
        myip, mytime, myvalue)
    conn = sqlite3.connect('data.db')
    cursor = conn.cursor()
    cursor.execute(update_sql)
    cursor.close()
    conn.commit()
    conn.close()
