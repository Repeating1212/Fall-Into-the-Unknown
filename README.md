# Fall Into the Unknown

<img width="600" height="600" alt="Untitled 158" src="https://github.com/user-attachments/assets/2e56fc28-78fb-4b3f-a9b3-31a3fefcecb4" />

## About The Project

**“ Equip – Challenge – Survive – Upgrade "**

*Fall Into the Unkown* is a survive action game, providing with various skill and enemies. Defeat waves of enemies, challenge complex creatures,
earn victory rewards, and updates game character.

Note: *This project was developed as a summer game development project and is currently non-commercial.*

## Getting Started

Prerequisites
1. Windows Operating System

Installation and running

1. Clone or download the repository
2. Navigate to the main folder
3. Double-click `Game.bat` to start

## How to play

Game Controls:
- Movement: WASD Keys
- Select skill: Scroll mouse wheel
- Active skill: Mouse click

Game Objective:

Survive each level by defeating enemies and avoiding damage. Gain rewards to upgrade your skills and prepare for next level.

## Coding style

The codebase is organized into four main packages:

Data (src/main/java/Data):
- Game configuration classes
- Loader classes (image and scene loader etc.)
- Data classes
- Purpose: Provides support functionality for all other package

Game File (src/main/java/Game_File):
- Store player progression (Upgrades, current resources etc.)

Game UI (src/main/java/Game_UI):
- Handle all game UI excluding level-related UI.
- Handle game logic of scene, including skill equipment and skill upgrades.

Level (src/main/java/Level):
- Handle all level related logic, including UI and object interaction logic.

## Build With
- Java 23 - Core programming language
- JavaFx - UI framework
- GNU License - Open source licensing

## Roadmap

Completed Features:
- Game save and load system
- Game UI system
- Game level example
- Skill equipment and upgrade system


Incomplete Features:
- Encyclopedia scene
- Character upgrades
- Game sound effect and music
- Level designs and enemies design
- Game enemies and economy balances


## License
This project is licensed under the GNU General Public License - see the 
LICENSE file for details.

This means you are free to:
- Use the code for any purpose
- Study how it works
- Share it with others
- Modify and improve it

Under the condition that any distributed modifications must also be open 
source under GPL.
