class User:
    def __init__(self):
        self.pr=0
        self.armynumber=""
        self.name=""
        self.password=""
        self.dischargedate=""    
        self.updateDate=""
    
    def setDefault(self,tuple):
        self.pr=tuple[0]
        self.armynumber=tuple[1]
        self.name=tuple[2]
        self.password=tuple[3]
        self.dischargedate=tuple[4]
        self.updateDate=tuple[5]

    def printAll(self):
        print("pr : ",self.pr)
        print("armynumber : "+self.armynumber)
        print("name : "+self.name)
        print("password : "+self.password)
        print("dischargedate : "+self.dischargedate)
        print("updateDate : "+self.updateDate)