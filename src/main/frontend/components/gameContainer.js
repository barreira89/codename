import React from 'react'
import {gameService} from '../api/gameService.js'
import { Game } from './game'

export class GameContainer extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            game:{},
            rounds:[]
        }
    }

    componentDidMount() {
        console.log(this.props)
        let gameId = this.props.gameId
        if(gameId){

            gameService.getGameById(gameId).done((resp) => {
                console.log("RESPONSE FROM SERVICE")
                console.log(resp)
                let game = resp.entity


                let rounds = game.rounds && game.rounds.map((gameRound, i) => {
                    return gameRound
                })
                console.log(rounds)

                this.setState({
                    game: game,
                    rounds: rounds
                })
            })
        } else {
            console.log("GAME ID NULL")
        }
    }

    render() {
        return (
            <Game game={this.state.game} rounds={this.state.rounds}/>
        )
    }
}