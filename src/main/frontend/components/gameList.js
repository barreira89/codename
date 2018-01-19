import React from 'react'
import gameService from '../api/gameService.js'

const apiEndPoints = {
    GAMES: '/api/games/'
}

export class GamesList extends React.Component{
    constructor(props) {
        super(props)
        this.handleClick = this.handleClick.bind(this)
        this.handleDelete = this.handleDelete.bind(this)
    }

    handleClick(e) {
        this.props.onClick()
    }

    handleDelete(e) {
        this.props.onClickDelete(e)
    }

    render() {
        const games = this.props.games.map((game,i) =>{
            return (
            <tr key={"GAME_" + i}>
                <td> <a href={apiEndPoints.GAMES + game.id}> {game.id} </a> </td>
                <td> {game.status} </td>
                <td> <button onClick={() =>     this.handleDelete(game.id)}> Delete </button> </td>
            </tr>
            )
        })
        return (
            <div>
                <table>
                <tbody>
                    <tr>
                        <th> ID </th>
                        <th> Create Time </th>
                    </tr>
                    {games}
                 </tbody>
                </table>
                <button onClick={this.handleClick}> Click Me </button>
            </div>
            )
    }
}