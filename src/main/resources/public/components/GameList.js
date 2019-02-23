class GamesList extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            games: []
        };
    }

    componentDidMount() {
        this.fetchAndDisplayGames();
    }

    fetchAndDisplayGames() {

        axios.get("/api/games")
            .then(response => {

                const games = response.data;

                this.setState({
                    games: games
                });
            });
    }

    addGame() {

        axios.post("/api/games").then(response => {
            this.fetchAndDisplayGames();
        });
    }

    render() {
        return (
            <div>
                <Title text={this.props.title} />
                <p><button onClick={() => this.addGame()}>Add game</button></p>
                <ul>
                    {this.state.games.map(game => (
                        <li key={game.id}><a href={"/games/" + game.id}>Game {game.id}</a> is {game.state}</li>
                    ))}
                </ul>
            </div>
        );
    }
}