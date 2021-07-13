import numpy as np
import cv2
import face_recognition
import os
import math

path = 'imgs/01'
images = []
imagesNames = []
attendance = []

myList = os.listdir(path)
# print(myList)

# i=0
for cimg in myList:
    curImg = cv2.imread(f'{path}/{cimg}')
    images.append(curImg)
    nameWithoutExt = os.path.splitext(cimg)[0]
    nameWithoutDot = nameWithoutExt.split(".")[0]
    imagesNames.append(nameWithoutDot)
# print(imagesNames)

def findEncodings(images):
    encodeList = []
    for img in images:
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        encode = face_recognition.face_encodings(img)[0]
        encodeList.append(encode)
    return encodeList

# Load Encoded Faces
encodingVariable = np.load('imgs/data/images_encodings_file.csv.npy')
encodeListKnown = encodingVariable

#* encodeListKnown = findEncodings(images)
# print("Encoding Complete")

#cap = cv2.VideoCapture(0)
cap = cv2.VideoCapture("video/vid.mp4") # ' ../video/vid.mp4'
frameRate = cap.get(5) # frame rate

while 1 :
    frameId = cap.get(1) #current frame number

    ret, img = cap.read()
    if not ret:
        print('END')
        break
    if (frameId % math.floor(frameRate) == 0):
        imgS = cv2.resize(img,(0,0),None,0.50,0.50)
        imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)
        faceLocCurrFrame = face_recognition.face_locations(imgS)
        encodesCurrFrame = face_recognition.face_encodings(imgS,faceLocCurrFrame)

        for encodeFace,faceLoc in zip(encodesCurrFrame, faceLocCurrFrame):
            matches = face_recognition.compare_faces(encodeListKnown,encodeFace)
            faceDis = face_recognition.face_distance(encodeListKnown, encodeFace) # The distance tells how similar the faces are.
            # the lowest distance will be the best match
            matchIndex = np.argmin(faceDis) # find the lowest distance
            # after when we founded the best match, now we now know which person we talk about

            if matches[matchIndex]:
                name = imagesNames[matchIndex].upper()
                if imagesNames[matchIndex] not in attendance:   # add face to attendance list
                    attendance.append(imagesNames[matchIndex])
                y1,x2,y2,x1 = faceLoc
                y1, x2, y2, x1 = y1*2, x2*2, y2*2, x1*2
                cv2.rectangle(img,(x1,y1),(x2,y2),(0,255,0),2)
                cv2.putText(img,name,(x1+2,y1-5),cv2.FONT_HERSHEY_PLAIN,1,(255,255,255),2)
            else:
                color = (0, 0, 255)  # RED
                top, right, bottom, left = faceLoc
                top, right, bottom, left = top*2, right*2, bottom*2, left*2
                # Display border
                cv2.rectangle(img, (left, top), (right, bottom), color, 3)
                cv2.putText(img,"UNKOWN",(left+2,top-5),cv2.FONT_HERSHEY_PLAIN,1,(255,0,255),2)

        cv2.imshow('video',img)
        # click q to exit
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

# print(len(attendance))
print(attendance)

# When everything's done, release capture
cap.release()
cv2.destroyAllWindows()

