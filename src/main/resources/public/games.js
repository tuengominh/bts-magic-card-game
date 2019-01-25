//for the homework
/** const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get("gameId");
console.log(gameId);
const gamesPromise = fetch("http://localhost:8080/api/games" + gameId);

url: /?gameId=1
*/

const gamesPromise = fetch("http://localhost:8080/api/games");
//a promise is an object that will contain the data in a while

gamesPromise
    .then(x => x.json())//convert the response to JSON
    .then(function(games){ //the parameter is the one contains data that the end-point sent
    //this anonymous function will be called when the data comes after a while
    console.log(games);

    for (let game of games) {
        console.log(`${game.id} - ${game.state}`);
    }

    let c = document.getElementById("container");
    for (let game of games) {
        const p = document.createElement("li");
        p.textContent = `${game.id} - ${game.state}`;
        c.appendChild(p);
    }
});

