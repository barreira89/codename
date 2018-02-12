import React from 'react'


let dvStyle = {
    fontSize: '1.0em',
    fontStyle: 'bold'
}

export class GameTile extends React.Component {
    constructor(props){
        super(props)
    }

    render(){
        return(
            <td style={dvStyle}>{this.props.gameTile.word}</td>
        )
    }
}