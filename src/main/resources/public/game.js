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

    if (game.state == "OPEN") {

        const input = document.createElement("input");
        input.type = "text";
        input.name = "username";

        const button = document.createElement("button");
        button.innerHTML = "Join this game";
        button.addEventListener("click", function(){
            updateGame(input.value);
        });

        gameContainer.appendChild(input);
        gameContainer.appendChild(button);
    }
}

function displayError(error) {

    let gameContainer = document.getElementById("game-container");

    const p = document.createElement("p");
    p.textContent = `The game could not be loaded`;
    gameContainer.appendChild(p);
}

function updateGame(username) {
    axios.put("/api/games/" + ${game.id} + "/join", {username})
        .then(function (response) {
            console.log(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    location.reload();
}