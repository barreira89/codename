import React from 'react'

let dvStyle = {
    fontSize: '1.0em',
    fontStyle: 'bold'
}

export class GameTile extends React.Component {
    constructor(props){
        super(props)
        this.handleClick = this.handleClick.bind(this)
    }

    handleClick(e) {
       console.log(e)
       this.props.onClick()
    }

    render(){
        return(
            <td style={this.props.tileStyle} onClick={this.handleClick}>{this.props.gameTile.word.value}</td>
        )
    }
}