const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');
console.log(gameId);

const gamePromise = axios.get("http://localhost:8080/api/games/" + gameId);

gamePromise
    .then(function(response){
        const game = response.data;

        const c = document.getElementById("game-container");

        const t = document.createElement("h1");
        t.textContent = `Game ${game.id}`;
        c.appendChild(t);

        const p = document.createElement("li");
        p.textContent = `State: ${game.state}`;
        c.appendChild(p);

        const p2 = document.createElement("li");
        p2.textContent = `Players: ${game.playerNames.toString()}`;
        c.appendChild(p2);

        const a = document.createElement("a");
        a.appendChild(document.createTextNode("Go back to game list"));
        a.href = "http://localhost:8080/games.html";
        c.appendChild(a);
    });

