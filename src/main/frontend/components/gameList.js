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
        const games = this.props.games.map((game,i) =>{
            return (
            <tr key={"GAME_" + i}>
                <td> <a href={apiEndPoints.GAMES + game.id}> {game.id} </a> </td>
                <td> <Link to={"/game/" + i}> {game.id} </Link></td>
                <td> {game.status} </td>
                <td> <button onClick={() => this.handleDelete(game.id)}> Delete </button> </td>
            </tr>
            )
        })
        const gameDetails = this.props.games.map((game,i) => {
            return (
                <div key={"game_" + i}>
                   <Game game={game}/>
                </div>
            )
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
                <Route path='/game/:number' render={(props) => <Game game={this.props.games[props.match.params.number]} />} />
            </div>
        )
    }
}

GameList.propTypes = {
   onClick: PropTypes.func,
   onClickDelete: PropTypes.func,
   games: PropTypes.array.isRequired
}