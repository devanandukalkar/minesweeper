# Minesweeper Game

This project is a command-line implementation of the Minesweeper game. The game allows users to specify the grid size and the number of mines, and then play the game by uncovering squares on the grid. The game ends when all non-mine squares are uncovered or when a mine is uncovered.

## Requirements
- **Java Version**: 17 or higher
- **Maven Version**: 3.6.0 or higher
- **Environment**: Windows/Linux

## How to Run the Project
### 1. Clone the Repository
```
git clone https://github.com/devanandukalkar/minesweeper.git
cd minesweeper
```

### 2. Build the project using maven
```
mvn clean install
```

### 3. Run the Application
```
mvn exec:java
```

### 4. Run the Tests
```
mvn test
```

### Example Gameplay
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid):
4
Enter the number of mines to place on the grid (Maximum allowed mines : 5)
5
Here is your minefield:
 1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g. A1):
A3
Here is your minefield:
 1 2 3 4
A _ _ 3 _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g. A1):
D2
Here is your minefield:
 1 2 3 4
A _ _ 3 _
B _ _ _ _
C _ _ _ _
D _ 1 _ _

Select a square to reveal (e.g. A1):
A1
Here is your minefield:
 1 2 3 4
A * * 3 _
B _ * _ *
C _ _ * _
D _ 1 _ _

Oh no, you detonated a mine! Game over.
Press any key to play again or type exit to stop...

```