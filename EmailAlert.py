
# coding: utf-8

# In[4]:


import time
import serial
import smtplib

TO = 'wish1065@gmail.com'
USER = 'rishabh.rajagopalan@gmail.com'
PW = 'MANcity21'

SUB = 'Arduino Alert'
BODY = 'Your <insert item here> has been opened'

ser = serial.Serial('COM3', 9600)

def send():
    smtpserver = smtplib.SMTP("smtp.gmail.com",587)
    smtpserver.ehlo()
    smtpserver.starttls()
    smtpserver.ehlo
    smtpserver.login(USER, PW)
    header = 'To:' + TO + '\n' + 'From: ' + USER
    header = header + '\n' + 'Subject:' + SUB + '\n'
    msg = header + '\n' + BODY + ' \n\n'
    smtpserver.sendmail(USER, TO, msg)
    smtpserver.close()
    
while True:
    sensorReading = ser.readline()
    print(sensorReading)
    if(sensorReading == b'ALERT\r\n'):
        print("Sending alert")
        send()
    time.sleep(0.25)


# In[5]:




