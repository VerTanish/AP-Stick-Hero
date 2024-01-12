<div align="center">
  <h1 style="font-family: 'Helvetica', sans-serif;"><span style="color:#00FFFF;">Documentation: FlatLands, a game</span></h1>
</div>

<div align="center">
  <h6 style="font-family: 'Helvetica', sans-serif;"> © Sarthak Gupta and Tanish Verma</h6>
</div>

***









## Class Structure

``` mathematica
Root Directory: package.Tanishwala
├── StickClass: Singleton pattern
├── PillarFactory: Factory pattern
├── StartPageController: Controller for Starting Page
├── GamePageController: Controller for Game Page
├── SavedStates: A class to store saved states
├── loader
├── loader
└── makefile


```

>Some names provided in the UML have changed.
## How to Play
1. Press `SPACEBAR` to elongate the stick.
2. Press `DOWNKEY `to flip the charachter.
3. Press `P` to pause the game.
4. To save state, we first pause with P, then click on save state button.
5. To load state, we have a button on left top corner of home screen.
6. The player is revived if he has more than 5 cherries but his design is changed to indicate he has been revived.

> Starting file: Flatlands.java
> If we get an exception while loading, that means there was no saved state. We have handled it.
## Bonus Implemented 
### MultiThreading
- We have used a different thread which runs side by side main thread, and makes the yellow currency into a pink currency.  


## OOPS Patters Implemented
- **DESIGN PATTERNS:**
    - SIngleton: The StickClass, which only allows the presence of one stick.
    - Factory: We use the class to generate new platforms.

- **THREADING:** We have called executor service to randomly change the colour of the ball to make it a better currency.
- We have used JAVA FX and UML for this project.
- **JUNIT:** We have added multiple tests.
- **File I/O:** We have used file input output to save our state in file savedstate.txt
- **OBJECT CLASS:** We have used object class in toString() methods and toEquals() method.
- **ABSTRACT CLASS:** We have made the pillar class Abstract.
- **INHERITANCE:** Platform and Obstacle class both inherit from Pillar class.
- **INTERFACES:** We have used a similar concept for Abstract class.
- Multiple Class relationships exist including association, aggregation, dependacy, etc.
- **Polymorphism** has been used in small small places.
- **Basic Principles like Encapsulation etc.** are followed throughout.
- **Collection Frameworks** have been implemented.




## Special Functionalities
1. The colour of the platform keeps changing.
2. The currency changes its position once player stick is about to fall so player must have a presence of mind to gain it or he can miss it.
3. The yellow currency can change colour randomly.
4. Once the player has revived, his colour changes. We did it so other playes know someone has revived using cherries and not just playing with their own skill.
5. We have added media like animmations and sounds.

Note: All media used are royalty free, and it is played with the help of an https link.