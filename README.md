# JavaQuest
Java Quest is a small RPG written in Java since 23.04.2013!

This project currently uses the libraries of [SuM](https://www.mg-werl.de/sum/).
In the future these will be abandoned. A change to JavaFX is planned.

## ToDo
CLEAN CODE!!!
### Coop Mode:
* Two players must solve levels together. One player plays with `W` `A` `S` `D` the other with `8` `4` `5` `6`.
* The two players are always in separate rooms, but still have to help each other (like in portal 2)
* At the end both players meet again in the same room and the game is over.

## Classic Mode:
### Game
- Fixed window size
- Favicon
- Replace swords with arrows (why would you need a new sword for every opponent?)
- At the end: steps and time (highscore). With rating from "A" - "F".

### Design
- Label Replace text with speech bubbles.
  
### Sound
- add missing sounds

### Failures
- If you move the blocks, the position of the blocks should be saved each time you want to return to the previous level with the blocks.
  
### Bugs
If you continue at the edge, you get an error => it's because of the distance between player and princess (almost the same code as opa, but opa doesn't make problems)
  
### Level
  + Level with invisible walls and and invisible teleporters.
  + Move blocks (into holes or something).
  + Add green, blue and yellow balls.
  + Level with logical operators
  + control is different (`S` = `D`, `W` = `A`, `A` = `W`, `D` = `S`)

## Licence
### Images
Some pictures are from "Twemoji".

Copyright 2020 Twitter, Inc and other contributors

Code licensed under the MIT License: http://opensource.org/licenses/MIT

Graphics licensed under CC-BY 4.0: https://creativecommons.org/licenses/by/4.0/

