#SGAS - Super Generic Arena Shooter
##An example game to teach the basics of libgdx

SGAS is a top-down shooter, in the style of an arcade twin-stick shooter, but controlled with the WASD keys and the mouse instead of sticks or something.  It is not meant to be some paradigm of creativity and game design. It does not pretend to bring anything new or original to the table.  It simply aims to present the basics of game programming using libgdx (for desktop deployment) using familiar concepts and familiar gameplay.

This project makes use of libgdx [http://code.google.com/p/libgdx/](http://code.google.com/p/libgdx/), source copyright [Bad Logic Games](http://www.badlogicgames.com/wordpress/) under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) 

Since the purpose of this project is education, I will be creating a branch for each logical stage, so that it will be easier to follow along, and so that changes can easily be seen from stage-to stage.  I will update the readme to explain the stages as I develop them.

This project also aims to provide a basic engine that builds off of the framework provided by LibGDX (albeit, an engine where every visible asset has circular bounds).

NOTE: This project is in a state of development, and nothing should be considered a permanent feature.  Everything is subject to change, including interfaces to well-established classes.

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

####Stage 5
Enemy firing (at intervals).  Bullet typeof(EnemyBullet|PlayerBullet) Player-bullet collisions. Enemy-bullet collisions.  Also has a significant amount of refactoring to handle things with interfaces instead of just class derivation.

####Stage 6
Something approaching an actual sample game (albeit, quite lame).  The main point is to demonstrate how everything done so far fits together.
#####Game Rules
+ WASD moves
+ Left mouse button shoots in direction of mouse
+ Shooting enemies disables them temporarily, but increases their fire and rotation rates
+ Shooting a nondisabled enemy grants 10 points
+ Getting hit by a bullet loses 20 points
+ The game ends when you have -20 points or fewer


####Stage 7
Loading assets and values from properties file


###LICENSE  (MIT License)
Copyright (c) 2012, Christian Maher

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

