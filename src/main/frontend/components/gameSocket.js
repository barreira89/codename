import React from 'react';
import Websocket from 'react-websocket';

export class GameSocket extends React.Component {

    constructor(props) {
      super(props);
      this.state = {
        count: 90
      };
    }

    handleData(data) {
      let result = JSON.parse(data);
      this.setState({count: this.state.count + result.movement});
    }

    render() {
      return (
        <div>
          Count: <strong>{this.state.count}</strong>

          <Websocket url='ws://localhost:8080/topic/greetings'
              onMessage={this.handleData.bind(this)}
              debug={true}/>
        </div>
      );
    }
  }