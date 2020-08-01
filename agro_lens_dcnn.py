# -*- coding: utf-8 -*-

!pip install tensorflow-gpu==2.0.0rc0

import tensorflow as tf
print(tf.__version__)

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import cv2

from tensorflow.keras import Sequential
from tensorflow.keras.layers import Flatten , Dense , Dropout , BatchNormalization , Conv2D , MaxPool2D 
from tensorflow.keras.optimizers import Adam 
from tensorflow.keras.preprocessing import image
from sklearn.model_selection import train_test_split
from tqdm import tqdm

from google.colab import drive
drive.mount('/content/gdrive')

data=pd.read_csv('/content/gdrive/My Drive/crop_train.csv')
data.head()

img_height=350
img_width=350

X=[]

for i in tqdm(range(data.shape[0])):
  path='/content/gdrive/My Drive/images/'+data['Id'][i]+'.jpg'
  img=image.load_img(path,target_size=(img_height,img_width,3))
  img=image.img_to_array(img)
  img=img/255.0
  X.append(img)

X=np.array(X)

X.shape

plt.imshow(X[1])

plt.imshow(X[2001])

plt.imshow(X[3999])

plt.imshow(X[5001])

data['Name'][1]

data['Name'][2000]

data['Name'][4000]

data['Name'][5000]

y=data.drop(['Id','Name'],axis=1)
y=y.to_numpy()
y.shape

X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=0,test_size=0.15)

X_train[0].shape

"""#**DCNN**"""

model=Sequential()
model.add(Conv2D(16,(3,3),activation='relu',input_shape=X_train[0].shape))
model.add(BatchNormalization())
model.add(MaxPool2D(2,2))
model.add(Dropout(0.3))

model.add(Conv2D(32,(3,3),activation='relu'))
model.add(BatchNormalization())
model.add(MaxPool2D(2,2))
model.add(Dropout(0.3))

model.add(Conv2D(64,(3,3),activation='relu'))
model.add(BatchNormalization())
model.add(MaxPool2D(2,2))
model.add(Dropout(0.4))

model.add(Conv2D(128,(3,3),activation='relu'))
model.add(BatchNormalization())
model.add(MaxPool2D(2,2))
model.add(Dropout(0.5))

model.add(Flatten())

model.add(Dense(128,activation='relu'))
model.add(BatchNormalization())
model.add(Dropout(0.5))

model.add(Dense(128,activation='relu'))
model.add(BatchNormalization())
model.add(Dropout(0.5))

model.add(Dense(4,activation='sigmoid'))

model.summary()

model.compile(optimizer='adam',loss='binary_crossentropy',metrics=['accuracy'])

model.fit(X_train,y_train,epochs=2,validation_data=(X_test,y_test))

"""#**Test**"""

img=image.load_img('ri1ce.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])

for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

img=image.load_img('test_data_9.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])
leng=len(res)-1
print("this crop is ",classy[res[leng]])

img=image.load_img('mic_test4.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])

for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

img=image.load_img('whe1at.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])

for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

img=image.load_img('cotto1n.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])
#print(y_prob)
for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

img=image.load_img('test_data.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])m

for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

def result_pred(picture):
  img=image.load_img(picture+'.jpg',target_size=(img_height,img_width,3))
  plt.imshow(img)
  img=image.img_to_array(img)
  img=img/255.0
  img=img.reshape(1,img_height,img_width,3)
  classy=data.columns[2:]
  y_prob=model.predict(img)
  res=np.argsort(y_prob[0])
  leng=len(res)-1
  print("this crop is ",classy[res[leng]])

img=image.load_img('cor2n.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])

for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

img=image.load_img('mic_test5.jpg',target_size=(img_height,img_width,3))
plt.imshow(img)
img=image.img_to_array(img)
img=img/255.0

img=img.reshape(1,img_height,img_width,3)
classy=data.columns[2:]
y_prob=model.predict(img)
res=np.argsort(y_prob[0])

for i in range(4):
  print(str(i+1)+' '+classy[res[3-i]])

model_json = model.to_json()
with open("/content/gdrive/My Drive/agro_lens_dcnn.json", "w") as json_file:
    json_file.write(model_json)
# serialize weights to HDF5
model.save_weights("/content/gdrive/My Drive/agro_lens_dcnn.h5")
print("Saved model to drive")

