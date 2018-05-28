import React from 'react'
import {gameService} from '../api/gameService.js'
import { GameBoard } from './gameBoard'
import { Grid } from 'react-bootstrap'

export const Game = (props) => {
    const rounds = props.rounds.map((round, i) => {
            return (
                <div key={'round_div_' + i}>
                    <h4 key={"round_" + i}> Round Number: {round.roundNumber} </h4>
                    <GameBoard gameBoard={round.gameBoard} />
                </div>
            )
    })

    return (
        <Grid>
            <h1> Game ID: {props.game.id} </h1>
            {rounds}
        </Grid>
    )
}