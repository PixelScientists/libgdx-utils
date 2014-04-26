LibGDX-Utils
============
This repository contains several utilities to handle everyday problems.

Feel free to clone or fork this and steal whatever you may find useful!
Detailed descriptions, articles or tutorials of the code in this repository can be found at:

http://pixelscientists.com/blog

LibgdxUtils
-----------
The main class which is the entry point of the app.

It displays how to seperate a game into several screens and offers convenient access to the assets, rendering batches, a global logger and more.

SplashScreen
------------
Displays a texture while loading assets in the background. The splash screen will be displayed a minimum amount of time.

InventoryScreen
---------------
Displays an inventory when a key is pressed.
Offers many features like:
 - Inventory is placed in a draggable window
 - Grid-based layout
 - Random amount of slots
 - Icons are displayed for the items
 - Items can stack
 - Tooltips with additional information
 - Items can be moved around via Drag and Drop
 - It's possible to define valid and invalid targets
