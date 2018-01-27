import React from 'react'
import { GameListContainer } from './gameListContainer'
import { Game } from './game'
import { Route } from 'react-router-dom'

//render={(props) => <Game game={this.props.games[props.match.params.number]}

export const Main = () => {
    return (
            <div>
                <Route exact path='/' component={GameListContainer} />
                <Route path='/game/:gameId' render={(props) => (
                    <Game game={props.match.params.gameId} />
                )} />
            </div>
    )
}