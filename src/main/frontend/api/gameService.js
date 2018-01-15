import client from './client'


const gameService = {
    getGames: () => {
        return client({
             method: 'GET',
             path: '/api/games'
        })
    },
    getGameBoards: (game) => {
        return game.rounds.map((r) => {return r.gameBoard})
    }
}

module.exports = gameService