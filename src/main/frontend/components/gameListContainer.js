import React from 'react'
import {GameList} from './gameList.js'
import {gameService} from '../api/gameService.js'

export class GameListContainer extends React.Component{
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
        gameService.deleteGame(id).done(response => {
            console.log("DELTED GAME: " + id)
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
        return (
                <GameList
                    onClick={this.createGame}
                    onClickDelete={this.onClickDelete}
                    games={this.state.games}
                />
        )
    }
}