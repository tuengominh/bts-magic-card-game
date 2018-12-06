# magic-card-game
Battle card game with 2 users comparing skills to get points - Using TDD

 * Creating a game:
    - A game is created with a deck of cards (each card has 3 numbers (>=1) that added make 10).
    - Note: the 3 numbers represent magic, strength, intelligence.
    - When a game is created, its state is OPEN.
 
 * Joining a game:
    - A player can join an OPEN game (a player is indicated by its username).
    - When 2 players join the game, the state of the game changes to PLAYING.
    - A player can't join if the game state is not OPEN.
 
 * Picking cards:
    - When the game is PLAYING, any player that joined the game can pick a card. 
    - After picking a card, a player must keep it or discard it before picking another one.
    - A player can only discard 2 cards (i.e. must pick 3 cards).
 
 * The battle (point calculation):
    - When the 2 players have picked 3 cards, the winner of that round is calculated:
    - Each player adds all magics, all strengths and all intelligences
    - Totals of each category is compared between players
    - Player who wins in 2 categories earns a point (there may be no winner)
 
 * After the points are calculated, a new battle starts (players pick cards again).
 * If there are less than 10 cards in the deck, the game changes to state FINISHED.
