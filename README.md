# Rasp Smart Lamp - Android App
This project implements an app to control a smart lamp. You can compile this app by Android Studio.

## How to use

### Connecting a smart lamp
The App need to be pair with the lamp, after that, in the app select your lamp to connect.

### Choosing color
After connecting on the lamp, you will see a screen with a color palette. You can choose color on the palette then click on “Enviar '' to send your color to lamp.

## How the app works
The app sends a message to a smart lamp with a code of color (0xFFFFFFFF). When the user closes the app, the app sends a message to a smart lamp with a code quit (quit000000).

## Code used
This project used the code of duanhong169.
https://github.com/duanhong169/ColorPicker

## Platform tested
Samsung S7 edge - Android 8.0.0
