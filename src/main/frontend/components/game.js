import React from 'react'


export const Game = (props) => {
        const rounds = props.game.rounds.map((r, i) => {
            return (
                <h4 key={"round_" + i}> Round Number: {r.roundNumber} </h4>
            )
        })
        return (
            <div>
                <h1> Game ID: {props.game.id} </h1>
                 {rounds}
            </div>
        )
}