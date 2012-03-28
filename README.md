#SGAS - Super Generic Arena Shooter
##An example game to teach the basics of libgdx

SGAS is a top-down shooter, in the style of an arcade twin-stick shooter, but controlled with the WASD keys and the mouse instead of sticks or something.  It is not meant to be some paradigm of creativity and game design. It does not pretend to bring anything new or original to the table.  It simply aims to present the basics of game programming using libgdx (for desktop deployment) using familiar concepts and familiar gameplay.

This project makes use of libgdx [http://code.google.com/p/libgdx/](http://code.google.com/p/libgdx/), source copyright [Bad Logic Games](http://www.badlogicgames.com/wordpress/) under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) 

Since the purpose of this project is education, I will be creating a branch for each logical stage, so that it will be easier to follow along, and so that changes can easily be seen from stage-to stage.  I will update the readme to explain the stages as I develop them.

This project also aims to provide a basic engine that builds off of the framework provided by LibGDX (albeit, an engine where every visible asset has circular bounds).


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
+ Hitting enemies disables them, but increases their fire and rotation rates
+ Hitting a nondisabled enemy grants gives 10 points
+ Getting hit by a bullet loses 20 points
+ The game ends when you have -20 points or fewer



###LICENSE  (Simplified BSD License)
Copyright (c) 2012, Christian Maher

All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.