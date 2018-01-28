import React from 'react'
import { GameTile } from './gameTile'

export const GameListRow = (props) => {
    const games =  this.props.games.map((game,i) =>{
          return (
            <tr key={"GAME_" + i}>
                <td> <Link to={"/game/" + game.id}> {game.id} </Link></td>
                <td> {game.status} </td>
                <td> <button onClick={() => this.handleDelete(game.id)}> Delete </button> </td>
            </tr>
        )})
    return (
        <tr>{gameRows}</tr>
    )
}
//<td key={'GT_'+ i + rt.word}><b>{rt.word}</b></td>