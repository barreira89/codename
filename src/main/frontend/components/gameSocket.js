import React from 'react';
import SockJsClient from 'react-stomp';

export class GameSocket extends React.Component {

    constructor(props) {
      super(props);
    }

    sendMessage = (msg) => {
        this.clientRef.sendMessage('/topics/all', msg);
    }

    handleData(data) {
      let result = JSON.parse(data);
      this.setState({count: this.state.count + result.movement});
    }

    render() {
      return (
        <div>
            <SockJsClient url='http://localhost:8080/ws' topics={['/topics/all']}
            onMessage={(msg) => { console.log(msg);}}
            ref= {(client) => {this.clientRef = client}} />
          </div>
      );
    }
  }