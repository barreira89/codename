import React from 'react'
import PropTypes from 'prop-types'
import {Game} from './game.js'
import {Link, Route} from 'react-router-dom'
import { Button, Grid, Col, Row, Table } from 'react-bootstrap'

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
        const games =  this.props.games.map((game,i) =>{
          return (
            <tr key={"GAME_" + i}>
                <td> <Link to={"/game/" + game.id}> {game.id} </Link></td>
                <td> {game.status} </td>
                <td> <button onClick={() => this.handleDelete(game.id)}> Delete </button> </td>
            </tr>
        )})

        return (
            <Grid>
                <h1> Games </h1>
                    <Table>
                        <thead>
                            <tr>
                                <th> ID </th>
                                <th> Status </th>
                                <th> Delete </th>
                            </tr>
                        </thead>
                        <tbody>
                                {games}
                        </tbody>
                    </Table>
                <div>
                    <Button onClick={this.handleClick}> Create New Game </Button>
                </div>
            </Grid>
        )
    }
}

GameList.propTypes = {
   onClick: PropTypes.func,
   onClickDelete: PropTypes.func,
   games: PropTypes.array.isRequired
}