import React from 'react'
import {GamesList} from './gameList.js'
import gameService from '../api/gameService.js'

export class TestClass extends React.Component{
    constructor(props) {
        super(props)
        this.state = {games:[]}
        this.onClick = this.onClick.bind(this)
        this.updateGames = this.updateGames.bind(this)
        this.createGame = this.createGame.bind(this)
        this.onClickDelete = this.deleteGame.bind(this)
    }

    onClick() {
        this.updateGames()
    }

    deleteGame(id) {
        gameService.deleteGame(e).done(response => {
            console.log(response)
            this.updateGames()
        })
    }

    createGame() {
        gameService.createGame().done(response => {
            console.log("CREATED GAME: " + response.entity.id)
            this.updateGames()
        })
    }

    updateGames(){
        gameService.getGames().done(response => {
            this.setState({games: response.entity.content})
        })
    }

    componentDidMount(){
        this.updateGames()
    }

    render() {
        return (<div>
                <h1>Games</h1>
                    <GamesList
                        games={this.state.games}
                        onClick={this.onClick}
                        onClickDelete={this.onClickDelete}
                     />
                <button onClick={this.createGame} > Create Game </button>
                </div>
        )
    }
}