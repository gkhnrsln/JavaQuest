# JavaQuest
Java Quest is a small RPG written in Java. This Game was mostly written during school days (Around Summer 2013). Since then, nothing big changed. At the moment the goal is "Clean Code".

This project currently uses the libraries of [SuM](https://www.mg-werl.de/sum/).
In the future these will be abandoned. A change to JavaFX is planned.

Level 1

![Level 1](https://i.imgur.com/GJTODLH.png) 

Level 2

![Level 2](https://i.ibb.co/x8Gp27c/Unbenanntt.png)

Level 3

![Level 3](https://i.ibb.co/tHx6hZX/Unbenanntt.png)

## ToDo
### Coop Mode:
* Two players must solve levels together. One player plays with `W` `A` `S` `D` the other with `8` `4` `5` `6`.
* Both players are always in separate rooms, but still have to help each other (like Portal 2).
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
Some pictures are from [Twemoji](https://twemoji.twitter.com/).

Copyright 2020 Twitter, Inc and other contributors

Code licensed under the MIT License: http://opensource.org/licenses/MIT

Graphics licensed under CC-BY 4.0: https://creativecommons.org/licenses/by/4.0/

