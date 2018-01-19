import React from 'react'
import ReactDOM from 'react-dom'
import client from './api/client.js'
import gameService from './api/gameService.js'
import {TestClass} from './components/testClass.js'

class App extends React.Component{
    render () {
        return (
            <div>
                <TestClass />
            </div>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)