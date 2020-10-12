#from util.jsonManager import *

import sqlite3
from util.serverLog import LogD

DBLocation = "/home/codespace/workspace/AMDMserver.sqlite3";

def setDBLocation(str):
    DBLocation = str;

def updateIoTData(DM,IoTSocket):
    print(DM.getFileStr())
    print(DBLocation)
    con = sqlite3.connect(DBLocation)
    cur = con.cursor()
    cur.execute("UPDATE PHONECASE SET IS_LOCK=? WHERE PHONECASE_PR=?;",(DM.getData("Lock"),DM.getData("ID")))
    con.commit();
    con.close();

    LogD("UpdateIoTSQL 완료(id : "+str(DM.getData("ID"))+",Lock : "+str(DM.getData("Lock"))+")")
    IoTSocket.sendall('aaa'.encode())
    IoTSocket.close();
    return True

def updateAndroidData(DM,androidSocket):
    print(DM.getFileStr())

    con = sqlite3.connect(DBLocation)
    cur = con.cursor()
    cur.execute("UPDATE PHONE SET IS_LOCK=? WHERE PHONE_PR=?;",(DM.getData("Lock"),DM.getData("ID")))
    cur.execute("INSERT INTO LOCKMANAGE(PHONE_UNIQUENUM,MANAGETIME, IS_LOCK) VALUES(?,CURRENT_TIMESTAMP,?);",(DM.getData("ID"),DM.getData("Lock")))
    con.commit();
    con.close();

    LogD("UpdateAndroidSQL 완료(id : "+str(DM.getData("ID"))+",Lock : "+str(DM.getData("Lock"))+")")
    androidSocket.sendall('aaa'.encode())
    androidSocket.close();
    return True

def requestAndroidDataToIoT(DM,androidSocket):
    print(DM.getFileStr())

    con = sqlite3.connect(DBLocation)
    cur = con.cursor()
    cur.execute("UPDATE PHONECASE SET IS_LOCK=? WHERE PHONECASE_PR=?;",(DM.getData("Lock"),DM.getData("IoTID")))
    con.commit();
    con.close();

    LogD("requestAndroidSQL 완료(AdminID : "+str(DM.getData("ID"))+",IoTID : "+str(DM.getData("IoTID"))+"Lock : "+str(DM.getData("Lock"))+")")
    androidSocket.sendall('aaa'.encode())
    androidSocket.close();
    return True

