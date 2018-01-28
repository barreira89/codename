import React from 'react'
import { GameListContainer } from './gameListContainer'
import { GameContainer } from './gameContainer'
import { Route } from 'react-router-dom'

//render={(props) => <Game game={this.props.games[props.match.params.number]}

export const Main = () => {
    return (
            <div>
                <Route exact path='/' component={GameListContainer} />
                <Route path='/game/:gameId' render={(props) => (
                    <GameContainer gameId={props.match.params.gameId} />
                )} />
            </div>
    )
}