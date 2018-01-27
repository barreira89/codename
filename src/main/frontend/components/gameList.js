import React from 'react'
import PropTypes from 'prop-types'
import {Game} from './game.js'
import {Link, Route} from 'react-router-dom'

const apiEndPoints = {
    GAMES: '/api/games/'
}

export class GameList extends React.Component{
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
        const games = []
        const gameDetails = []

        this.props.games.forEach((game,i) =>{
          games.push((
            <tr key={"GAME_" + i}>
                <td> The Link: </td>
                <td> <Link to={"/game/" + game.id}> {game.id} </Link></td>
                <td> {game.status} </td>
                <td> <button onClick={() => this.handleDelete(game.id)}> Delete </button> </td>
            </tr>
            ))
          gameDetails.push((
            <div key={"game_" + i}>
                <Game game={game}/>
            </div>
          ))
        })
        return (
            <div>
                <h1> Games </h1>
                <table>
                    <tbody>
                        <tr>
                            <th> ID </th>
                            <th> Create Time </th>
                            <th></th>
                        </tr>
                            {games}
                    </tbody>
                </table>
                <div>
                    <button onClick={this.handleClick}> Create New Game </button>
                </div>
            </div>
        )
    }
}

GameList.propTypes = {
   onClick: PropTypes.func,
   onClickDelete: PropTypes.func,
   games: PropTypes.array.isRequired
}