# AMDM
💙 ☁️ 👍 🔥 🙌ㅠㅠㅠ

## 팀소개 및 프로잭트 설명 동영상
[![Watch the video](https://img.youtube.com/vi/LjX3eVQdIyk/0.jpg)](https://www.youtube.com/watch?time_continue=117&v=LjX3eVQdIyk)

## 기능 설계
 -  발사믹, 카카오 오븐 등 본인이 편한 목업 프레임워크를 이용하여 제작 후 링크 
 - 수기로 작성시 찍어서 올려주세요

## 컴퓨터 구성 / 필수 조건 안내 (Prerequisites)
* ECMAScript 6 지원 브라우저 사용
* 권장: Google Chrome 버젼 77 이상

## 기술 스택 (Technique Used) (예시)
### Server(back-end)
 - Python 3버전을 통해 개발
 - TCP 소켓 통신 서버
 - SQLite로 DB 구현
 
### IoT

## 설치 안내 (Installation Process)
```bash
$ git clone git주소
$ yarn or npm install
$ yarn start or npm run start
```

## 프로젝트 사용법 (Getting Started)
**마크다운 문법을 이용하여 자유롭게 기재**

## 팀 정보 (Team Information)
- 김영호 (yeongho.kim2000@gmail.com), Github Id: yeonghokim
- 부규필 (@gmail.com), Github Id: Qfeel-Dev

## 저작권 및 사용권 정보 (Copyleft / End User License)
 * [MIT](https://github.com/osam2020-WEB/Sample-ProjectName-TeamName/blob/master/license.md)

# Android
### 화면 리스트
* 로그인 전 메인페이지
* 회원가입 페이지
* 로그인 페이지
* 병사 메인페이지
  * 반납 페이지
  * 통계 페이지
* 간부 메인페이지
* 관리자 메인페이지(많음)

# Server
python으로 이루어진 TCP 소켓 서버입니다.
## IoT장비 데이터 교환
### 데이터 수신 형태 `IoT -> Server`
  * **Type** : 기기의 형태
  * **ID** : 기기의 아이디(초기 설정때 서버에서 지급)
  * **Lock** : 현재 기기의 잠금 유무
  * **PhoneLock** : 기기안의 핸드폰의 잠금 상태
```json
{
        "Type": "IoT",
        "ID": 1234567,
        "Lock": 0,
        "PhoneLock": {
                "19-760730001": 0,
                "19-760730002": 0,
                "19-760730003": 1,
                "19-760730004": 0
        }
}
```
### 데이터 송신 형태 `Server -> IoT` 
  * **ForceLock** : 현재 기기의 강제 잠금 유무
  * **PhoneLock** : 기기안의 핸드폰의 강제 잠금 유무
  * **PhoneUnLock** : 기기안의 핸드폰의 강제 잠금 유무
```json
{
        "ForceLock": 0,
        "PhoneLock": {
                "19-760730003": 1
        },
        "PhoneUnLock": {
                "19-760730004": 0
        }
}
```
## Android 데이터 교환
### 데이터 수신 형태 Type1 `Android -> Server` 
  * **Type** : 기기의 형태
  * **RequestType**: 요청 타입
  * **ID** : 기기의 아이디(초기 설정때 서버에서 지급)
  * **Lock** : 현재 기기의 잠금 유무
  * **Time** : 기기의 잠금 시간
```json
{
        "Type": "Android",
        "RequestType": 1,
        "ID": 1234567,
        "Lock": 1,
        "Time": "2020-10-04 13:49:12"
}
```
### 데이터 수신 형태 Type2 `Android -> Server`
  * **Type** : 기기의 형태
  * **RequestType**: 요청 타입
  * **ID** : 기기의 아이디(초기 설정때 서버에서 지급)
  * **IoTID** : IoT기기의 아이디
  * **Lock** : 잠글지 열지
  * **Time** : 요청 시간
```json
{
        "Type": "Android",
        "RequestType": 2,
        "ID": 1234567,
        "IoTID" : 123,
        "Lock" : 1,
        "Time": "2020-10-04 13:49:12"
}
```
### 데이터 수신 형태 Type3 `Android -> Server`
  * **Type** : 기기의 형태
  * **RequestType**: 요청 타입
  * **ID** : 기기의 아이디(초기 설정때 서버에서 지급)
  * **TurnOnTime** : 기기가 켜진 시간
```json
{
        "Type": "Android",
        "RequestType": 3,
        "ID": 1234567,
        "TurnOnTime": "2020-10-04 13:49:12"
}
```
# DataBase (SQLite)
### User Table
칼럼이름 | 타입 | 널 유무  | 외래키 유무
-------- | -------- | ---------- | ----------
USER_PR | INTEGER | PRIMARY | 
USER_ARMYNUMBER | CHAR | X
USER_NAME | CHAR | X
PASSWORD | CHAR | X
USER_DISCHARGEDATE | DATETIME | O 
UPDATEDATE | DATETIME | X
### Phone TableW
칼럼이름 | 타입 | 널 유무  | 외래키 유무
-------- | -------- | ---------- | ----------
PHONE_PR | INTEGER | PRIMARY
USER_UNIQUENUM | INTEGER | X | USER.USER_PR
PHONE_IP | CHAR | X
IS_LOCK | INTEGER | X
### PhoneCase Table
칼럼이름 | 타입 | 널 유무  | 외래키 유무
-------- | -------- | ---------- | ----------
PHONECASE_PR | INTEGER | PRIMARY
IS_LOCK | INTEGER | X
PHONE1_ID | INTEGER | O | PHONE.PHONE_PR
PHONE2_ID | INTEGER | O | PHONE.PHONE_PR
PHONE3_ID | INTEGER | O | PHONE.PHONE_PR
PHONE4_ID | INTEGER | O | PHONE.PHONE_PR
### LockManage Table
칼럼이름 | 타입 | 널 유무  | 외래키 유무
-------- | -------- | ---------- | ----------
LOCKMANAGE_PR | INTEGER | PRIMARY
PHONE_UNIQUENUM | INTEGER | X | PHONE.PHONE_PR
MANAGETIME | DATETIME | X
IS_LOCK | INTEGER | X