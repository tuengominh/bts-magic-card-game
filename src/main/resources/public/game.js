const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');

axios.get("/api/games/" + gameId)
    .then(function(response) {
        const game = response.data;
        displayGame(game);
    })
    .catch(function(error) {
        displayError(error);
    });


function displayGame(game) {

    let c = document.getElementById("game-container");

    const t = document.createElement("h1");
    t.textContent = `Game ${game.id}`;
    c.appendChild(t);

    const p = document.createElement("li");
    p.textContent = `State: ${game.state}`;
    c.appendChild(p);

    const p2 = document.createElement("li");
    p2.textContent = `Players: ${game.playerNames.toString()}`;
    c.appendChild(p2);

    //const a = document.createElement("a");
    //a.appendChild(document.createTextNode("Go back to game list"));
    //a.href = "http://localhost:8080/games.html";
    //c.appendChild(a);
}

function displayError(error) {

    let gameContainer = document.getElementById("game-container");

    const p = document.createElement("p");
    p.textContent = `The game could not be loaded`;
    gameContainer.appendChild(p);
}



