import React from 'react'
import ReactDOM from 'react-dom'
import client from './api/client.js'
import gameService from './api/gameService.js'
import { GameListContainer } from './components/gameListContainer.js'
import { HashRouter } from 'react-router-dom'
import { Main } from './components/main.js'

class App extends React.Component{
    render () {
        return (
            <div>
                <Main />
            </div>
        )
    }
}

ReactDOM.render(
    (<HashRouter>
        <App />
    </HashRouter>),
    document.getElementById('react')
)