console.log("Game js loaded");

const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('id');

axios.get("/api/games/" + gameId)
    .then(function(response) {
        const game = response.data;
        displayGame(game);
    })
    .catch(function(error) {
        displayError(error);
    });


function displayGame(game) {

    let gameContainer = document.getElementById("game-container");

    const p = document.createElement("p");
    p.textContent = `Game ${game.id} is ${game.state}, and players are ${game.playerNames}`;
    gameContainer.appendChild(p);
}

function displayError(error) {

    let gameContainer = document.getElementById("game-container");

    const p = document.createElement("p");
    p.textContent = `The game could not be loaded`;
    gameContainer.appendChild(p);
}