import React from 'react'
import { GameRow } from './gameRow'
import { gameBoardStyles } from '../styles/gameBoardStyles'

export const GameBoard = (props) => {

    let rows = props.gameBoard.gameRows.map((row, i) => {
             return <GameRow key={'GR_' + i + '_' + props.gameBoard.id} gameRow={row} />
    })
    return (
        <div>
        <h3 style={gameBoardStyles.h1Style} className='testclass'> THE GAME BOARD IS HERE </h3>
           <table>
               <tbody>{rows}</tbody>
           </table>
        </div>

    )
}