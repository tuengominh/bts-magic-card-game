function displayGames() {
    //const gamesPromise = fetch("/api/games");
    const gamesPromise = axios.get("/api/games");

    gamesPromise
    //.then(x => x.json()) // this is necessary when using fetch
        .then(function(response) {

            const games = response.data; // In axios you get a response object with the data inside

            // This function will be called when the data comes
            // At this point, games contains the data that the end-point sends (the list of games)

            let gamesContainer = document.getElementById("container");

            for (let game of games) {

                const p = document.createElement("p");
                p.textContent = `Game ${game.id} is ${game.state}`;
                gamesContainer.appendChild(p);
            }
        });
}

function createGame() {
    axios.post("/api/games")
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
    window.location.reload();
}