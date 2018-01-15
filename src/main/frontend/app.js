import React from 'react'
import ReactDOM from 'react-dom'
import client from './api/client.js'
import gameService from './api/gameService.js'

const apiEndPoints = {
    GAMES: "/api/games/"

}

class TestClass extends React.Component{
    constructor(props) {
        super(props)
        this.state = {games:[]}
        this.onClick = this.onClick.bind(this)
        this.updateGames = this.updateGames.bind(this)
    }

    onClick() {
        this.updateGames()
        gameService.getGames().done(response => {
            let firstGame = response.entity.content[0]
            console.log("FIRST GAME " + firstGame)
            let result = gameService.getAllGameBoards(response.entity.content)
            console.log(result)
        })
    }

    updateGames(){
        client({method: 'GET', path: '/api/games'}).done(response => {
            this.setState({games: response.entity.content})
        })
    }

    componentDidMount(){
        client({method: 'GET', path: '/api/games'}).done(response => {
            this.setState({games: response.entity.content})
        })
    }

    render() {
        return (<div>
                <h1>Hello World</h1>
                    <GamesList games={this.state.games} onClick={this.onClick}/>
                </div>
        )
    }
}

class GamesList extends React.Component{
    constructor(props) {
        super(props)
        this.handleClick = this.handleClick.bind(this)
    }

    handleClick(e) {
        this.props.onClick()
    }

    render() {
        const games = this.props.games.map((game,i) =>{
            return (
            <tr key={"GAME_" + i}>
                <td> <a href={apiEndPoints.GAMES + game.id}> {game.id} </a> </td>
                <td> {game.status} </td>
                <td> <button> TEST </button> </td>
            </tr>
            )
        })
        return (
            <div>
                <table>
                <tbody>
                    <tr>
                        <th> ID </th>
                        <th> Create Time </th>
                    </tr>
                    {games}
                 </tbody>
                </table>
                <button onClick={this.handleClick}> Click Me </button>
            </div>
            )
    }
}

ReactDOM.render(
    <TestClass />,
    document.getElementById('react')
)