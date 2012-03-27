#SGAS - Super Generic Arena Shooter
##An example game to teach the basics of libgdx

SGAS is a top-down shooter, in the style of an arcade twin-stick shooter, but controlled with the WASD keys and the mouse instead of sticks or something.  It is not meant to be some paradigm of creativity and game design. It does not pretend to bring anything new or original to the table.  It simply aims to present the basics of game programming using libgdx (for desktop deployment) using familiar concepts and familiar gameplay.

This project makes use of libgdx [http://code.google.com/p/libgdx/](http://code.google.com/p/libgdx/), source copyright [Bad Logic Games](http://www.badlogicgames.com/wordpress/) under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) 

Since the purpose of this project is education, I will be creating a branch for each logical stage, so that it will be easier to follow along, and so that changes can easily be seen from stage-to stage.  I will update the readme to explain the stages as I develop them.

This project also aims to provide a basic framework that builds off of LibGDX (albeit, a framework where every visible asset has circular bounds).

###Stages

####Stage 0
An empty application.  Nothing exciting about this, but it can be reused for any libgdx desktop application.

####Stage 1
Player creation.  Includes WASD movement, rotating to face mouse.

####Stage 2
Bullets and shooting.  Woo! Action.

####Stage 3
Stationary enemies and collision detection

####Stage 4
Enemy rotating.  More importantly, refactoring code to be reusable.