from socket import *
import threading
import os

from util.DBManager import updateIoTData
from util.DBManager import updateAndroidData
from util.DBManager import requestAndroidDataToIoT
from util.jsonManager import JsonToDataManager
from util.serverLog import LogD
from util.serverLog import setLogDirectory


Debug = 1

host = "127.0.0.1"
port = 12346

serverSocket = socket(AF_INET,SOCK_STREAM)
serverSocket.bind((host,port)) 
serverSocket.listen(5)

LogD("서버 생성완료. 대기중입니다.")
setLogDirectory(os.path.dirname(os.path.realpath(__file__))+"/logs/")

while(True):
    connectionSocket,addr = serverSocket.accept() #accept 할동안 기다림
    LogD(str(addr) + "에서 접속함")
    data =connectionSocket.recv(1024)
    dataDM = JsonToDataManager(data.decode("utf-8"))

    if(dataDM.getData("Type")=="Android"):
        LogD("Android Data " + dataDM.getFileStr())

        if(dataDM.getData("RequestType")==1):
            # 핸드폰이 잠길때
            # 핸드폰이 열릴때
            LogD("Android 데이터 수신")
            t = threading.Thread(target=updateAndroidData, args=(dataDM,connectionSocket))
            t.start()
            
        elif(dataDM.getData("RequestType")==2):
            # Iot가 열릴때 (관리자) Android -> Server -> IoT
            LogD("Android 데이터 전송 요청")
            LogD("IoT Data " + dataDM.getFileStr())
            t = threading.Thread(target=requestAndroidDataToIoT, args=(dataDM,connectionSocket))
            t.start()
        
        elif(dataDM.getData("RequestType")==3):
            # 핸드폰 잠금 유무 확인(관리자)
            LogD("Android 데이터 요청")

            # 문제점 발생

    elif(dataDM.getData("Type")=="IoT"):
        # Iot가 잠길때
        LogD("IoT Data " + dataDM.getFileStr())
        t = threading.Thread(target=updateIoTData, args=(dataDM,connectionSocket))
        t.start()

serverSocket.close()

# Iot 강제 잠금(관리자) 서버가 요청

