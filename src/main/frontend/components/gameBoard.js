import React from 'react'

//TODO: Clean this up
export const GameBoard = (props) => {

    let rows = []
    props.gameBoard.gameRows.map((row, i) => {
        rows.push(
                row.rowTiles.map((rowTile, x) => {
                     return (<td key={'RT_'+i+'_'+x}> {rowTile.word} </td>)
                })
        )
        //Game Row Component
        //Game tile component
    })
    let wrappedRows = rows.map((r, i) => {
        return (<tr key={"RO_" + i}>{r}</tr>)
    })
    console.log (rows)
    return (
        <div>
        <h1> THE GAME BOARD IS HERE </h1>
           <table>
               <tbody>
                   {wrappedRows}
               </tbody>
           </table>
        </div>

    )
}