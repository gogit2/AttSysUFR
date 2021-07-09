# import sys ;
import numpy as np
import cv2
import face_recognition
import os
import math

# print(sys.path)
# import sqldb
from builtins import print, zip, ord, len

path = '../imgs/00/imgs'
images = []
imagesNames = []
attendance = []

myList = os.listdir(path)
print(myList)

# i=0
for cimg in myList:
    curImg = cv2.imread(f'{path}/{cimg}')
    images.append(curImg)
    imagesNames.append(os.path.splitext(cimg)[0])
    # i +=1
    # if i >= 3:
    #     break
print(imagesNames)
print("YEEAAAAAAAAAAAAAAH")
def findEncodings(images):
    encodeList = []
    for img in images:
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        encode = face_recognition.face_encodings(img)[0]
        encodeList.append(encode)
    return encodeList

encodeListKnown = findEncodings(images)
print("Encoding Complete") #+ f'{encodeListKnown}

# def imgIdWithEncode(imagesList):
#     imgEncoded = []
#     for imgidName, imgEncode in zip(imagesNames, findEncodings(imagesList)):
#         imgEncoded.append((imgidName, imgEncode))
#     return imgEncoded
# print("printting imgId with Encode: ")
# print(imgIdWithEncode(images))

#cap = cv2.VideoCapture(0)
cap = cv2.VideoCapture("video/v2.mp4")
frameRate = cap.get(5) # frame rate

while 1 :
    frameId = cap.get(1) #current frame number
    # print(frameId)
    # print(frameRate)
    # print(frameId % math.floor(frameRate))
    # print(frameId % math.floor(frameRate)) # if u want frame every 0.5 sec
    ret, img = cap.read()
    if not ret:
        print('END')
        break
    if (frameId % math.floor(frameRate) == 0):
        imgS = cv2.resize(img,(0,0),None,0.50,0.50)
        imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)
        faceLocCurrFrame = face_recognition.face_locations(imgS)
        encodesCurrFrame = face_recognition.face_encodings(imgS,faceLocCurrFrame)

        print('Loc: '+f'{faceLocCurrFrame}')

        for encodeFace,faceLoc in zip(encodesCurrFrame, faceLocCurrFrame):
            matches = face_recognition.compare_faces(encodeListKnown,encodeFace)
            faceDis = face_recognition.face_distance(encodeListKnown, encodeFace)
            # print(faceDis)
            matchIndex = np.argmin(faceDis)
            # print("MI: "+f'{matchIndex}')

            if matches[matchIndex]:
                name = imagesNames[matchIndex].upper()
                if imagesNames[matchIndex] not in attendance:   # add face to attendance list
                    attendance.append(imagesNames[matchIndex])
                # print(name)
                y1,x2,y2,x1 = faceLoc
                y1, x2, y2, x1 = y1*2, x2*2, y2*2, x1*2
                cv2.rectangle(img,(x1,y1),(x2,y2),(0,255,0),2)
                # cv2.rectangle(img,(x1,y2-10),(x2,y2),(0,255,0),cv2.FILLED)
                cv2.putText(img,name,(x1+2,y1-5),cv2.FONT_HERSHEY_PLAIN,1,(255,255,255),2)

        cv2.imshow('video',img)
        # click q to exit
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
print(len(imagesNames))
print(imagesNames)
print(len(attendance))
print(attendance)

# save into db
# sqldb.insertStuAttdb(attendance)

# When everything's done, release capture
cap.release()
cv2.destroyAllWindows()



# while True
#     ret, img = cap.read()
#     cv2.imshow('img', img)
#     # cv2.imshow('currImg', img)
#     cv2.waitKey(0)


# vid = cv2.VideoCapture(0)
# while (True):
#     ret, frame = vid.read()
#     cv2.imshow('frame', frame)
#     if cv2.waitKey(1) & 0xFF == ord('q'):
#         break
# vid.release()
# cv2.destroyAllWindows()