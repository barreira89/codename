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
        let gameId = this.props.gameId
        if(gameId){
            gameService.getGameById(gameId).done((resp) => {
                let game = resp.entity
                let rounds = resp.entity.rounds || []

                this.setState({
                    game: game,
                    rounds: rounds
                })
            })
        }
    }

    render() {
        return (
            <Game game={this.state.game} rounds={this.state.rounds}/>
        )
    }
}