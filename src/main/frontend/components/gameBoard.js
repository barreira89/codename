import React from 'react'

export const GameBoard = (props) => {
    console.log(props)
    let rows = []

    props.gameBoard.gameRows.map((row, i) => {
        rows.push(
            row.rowTiles.map((rowTile, i) => {
                <p> rowTile.word </p>
            })
        )
        //Game Row Component

        //Game tile component
    })
    return (
        <div>
        <h1> THE GAME BOARD IS HERE </h1>
            {rows}
        </div>

    )
}