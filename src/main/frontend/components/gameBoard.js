import React from 'react'
import { GameBoardRow } from './gameBoardRow'
import { gameBoardStyles } from '../styles/gameBoardStyles'
import { Table } from 'react-bootstrap'

export const GameBoard = (props) => {
    const rows = props.gameBoard.gameRows.map((row, i) => {
             return <GameBoardRow key={'GR_' + i + '_' + props.gameBoard.id} gameRow={row} />
    })
    return (
        <div>
        <h3 style={gameBoardStyles.h1Style} className='testclass'> THE GAME BOARD IS HERE </h3>
           <Table>
               <tbody>{rows}</tbody>
           </Table>
        </div>

    )
}