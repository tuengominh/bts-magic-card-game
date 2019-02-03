const gamesPromise = fetch("http://localhost:8080/api/games");
//a promise is an object that will contain the data in a while

gamesPromise
    .then(x => x.json())//convert the response to JSON
    .then(function(games){ //the parameter is the one contains data that the end-point sent
    //this anonymous function will be called when the data comes after a while
    console.log(games);

    const c = document.getElementById("container");
    for (let game of games) {
        const p = document.createElement("li");
        p.textContent = `Game ${game.id} is ${game.state}`;

        const a = document.createElement("a");
        a.appendChild(document.createTextNode("Go to this game"));
        a.href = "http://localhost:8080/game.html?gameId=" + game.id;

        c.appendChild(p);
        c.appendChild(a);
    }
});

