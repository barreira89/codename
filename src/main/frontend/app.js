import React from 'react'
import ReactDOM from 'react-dom'
import client from './api/client.js'
import gameService from './api/gameService.js'
import {GameListContainer} from './components/gameListContainer.js'
import {BrowserRouter} from 'react-router-dom'

class App extends React.Component{
    render () {
        return (
            <div>
                <GameListContainer />
            </div>
        )
    }
}

ReactDOM.render(
    (<BrowserRouter>
        <App />
    </BrowserRouter>),
    document.getElementById('react')
)