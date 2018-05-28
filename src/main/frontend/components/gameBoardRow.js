import React from 'react'
import { GameTileContainer } from './gameTileContainer'

export const GameBoardRow = (props) => {
    const gameRows = props.gameRow.rowTiles.map((rowTile, i) => {
        return (
            <GameTileContainer key={'GT_' + i} gameTile={rowTile} />
        )
    })
    return (
        <tr>{gameRows}</tr>
    )
}
//<td key={'GT_'+ i + rt.word}><b>{rt.word}</b></td>