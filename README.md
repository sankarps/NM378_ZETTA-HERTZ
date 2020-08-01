# NM378_ZETTA-HERTZ
# AGROLENS
An AI based Crop Identification Mobile App.

# DESCRIPTION
Agrolens, a software mobile application is used to identify the crop type of the captured or uploaded image.  The current model has been trained to identify about 10 different crops of the crop fields.  The software application fetches the data and displays the information of the crops at run time.  The main features of our project are

     -> Crop Detection
     -> Crop History
     -> Crop Details
     -> Crop Find and
     -> ARCore
     
# CROP DETECTION
# PROBLEM
     The crop detection is the heart of the application "Agro Lens".  Nowadays people are not having enough knowledge about 
     the crops and its' details.  They are manually searching for crop details.  In this feature, the captured image or uploaded 
     images can be classified based on the dataset given.  The crop's age is predicted and the application also detects
     the current location of the user and upload it on the local database (sql) and firebase.  In case of capturing in one 
     location, and uploading in another location, a feature to change the location is provided.  
# SOLUTION
We have used these python libraries and technology to achieve crop detection.  In this feature, we used Deep convolutional neural network where the application takes the crop's image as an input and assigns importance to various crops in the image and can be able to differentiate one crop from the other.  We have used tensorflow for image classification and keras as a backend for tensorflow. The application takes the name of the crop with higher accuracy as the crop name in accordance to that image.
    
