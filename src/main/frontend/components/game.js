import React from 'react'
import {gameService} from '../api/gameService.js'


export class Game extends React.Component {
    constructor(props){
        super(props)
        this.state = {game:{}}
    }

    componentDidMount(){
        let gameId = this.props.game && this.props.game.id || ''
        if(gameId){
            gameService.getGameById(gameId).done((resp) => {
                this.setState({
                    game: resp.entity
                })
            })
        } else {
            console.log("GAME ID NULL")
        }
    }

    render(){
        let gameRounds = this.state.game.rounds
        let rounds = []
        if (gameRounds) {
            rounds = gameRounds.map((r, i) => {
                return (
                    <h4 key={"round_" + i}> Round Number: {r.roundNumber} </h4>
                )
            })
        }

        return (
            <div>
                <h1> Game ID: {this.state.game.id} </h1>
            </div>
        )
    }
}