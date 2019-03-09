/**
 * Example component that displays another component (Title).
 *
 * Notice every React component needs to have one root element;
 * that's why we put everything in a <div>.
 */
/**const App = (props) => (
    <div>
        <GameList title="Game List"/>
       //<Article title="Some title" text="This is the text in the paragraph" />
    </div>
);*/

import React, { Component } from 'react';
import GameList from "./GameList";

class App extends Component {

    render() {
        return <div>
            <GameList title="Game List"/>
        </div>;
    }
}

export default App;