import React from 'react'
import {GameTile} from './gameTile.js'
import {gameService} from '../api/gameService.js'

export class GameTileContainer extends React.Component{
    constructor(props) {
        super(props)
        this.state = {tileStyle:{}}
        this.onClick = this.onClick.bind(this)
        this.revealTeam = this.revealTeam.bind(this)
    }

    onClick() {
        this.revealTeam()
    }

    revealTeam(){
        var color
        switch (this.props.gameTile.team){
            case 'BLUE':
                color = 'lightblue'
                break
            case 'RED':
                color = 'red'
                break
            case 'NEUTRAL':
                color = 'tan'
                break
            case 'ASSASSIN':
                color = 'black'
                break
        }
        this.setState(
          {tileStyle:
            {backgroundColor: color}
          }
        )
    }

    render() {
        return (
                <GameTile
                    onClick={this.onClick}
                    gameTile={this.props.gameTile}
                    tileStyle={this.state.tileStyle}
                />
        )
    }
}