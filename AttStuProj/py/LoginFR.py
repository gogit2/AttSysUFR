import numpy as np
import cv2
import face_recognition
import os
import math

path = 'imgs/01'
images = []
imagesNames = []
loggedInStuNameId = ''

myList = os.listdir(path)
for cimg in myList:
    curImg = cv2.imread(f'{path}/{cimg}')
    images.append(curImg)
    nameWithoutExt = os.path.splitext(cimg)[0]
    nameWithoutDot = nameWithoutExt.split(".")[0]
    imagesNames.append(nameWithoutDot)

# Load Encoded Faces
encodingVariable = np.load('imgs/data/images_encodings_file.csv.npy')
encodeListKnown = encodingVariable

cap = cv2.VideoCapture(0,cv2.CAP_DSHOW)

loop = False
while not loop :
    ret, img = cap.read()
    if not ret:
        print('END')
        break
    imgS = cv2.resize(img,(0,0),None,0.50,0.50)
    imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)

    faceLocCurrFrame = face_recognition.face_locations(imgS)
    encodesCurrFrame = face_recognition.face_encodings(imgS,faceLocCurrFrame)

    for encodeFace,faceLoc in zip(encodesCurrFrame, faceLocCurrFrame):
        matches = face_recognition.compare_faces(encodeListKnown,encodeFace)
        faceDis = face_recognition.face_distance(encodeListKnown, encodeFace)
        matchIndex = np.argmin(faceDis)
        if matches[matchIndex]:
            name = imagesNames[matchIndex].upper()
            y1,x2,y2,x1 = faceLoc
            y1, x2, y2, x1 = y1*2, x2*2, y2*2, x1*2
            cv2.rectangle(img,(x1,y1),(x2,y2),(0,255,0),2)
            cv2.putText(img,name,(x1+2,y1-5),cv2.FONT_HERSHEY_PLAIN,1,(255,255,255),2)
            loggedInStuNameId = name
            loop = True
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

print(loggedInStuNameId)

# When everything's done, release capture
cap.release()
cv2.destroyAllWindows()

