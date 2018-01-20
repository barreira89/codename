import client from './client'


export const gameService = {
    getGames: () => {
        return client({
             method: 'GET',
             path: '/api/games'
        })
    },
    getGameBoards: (game) => {
        return game.rounds.map((r) => {return r.gameBoard})
    },
    createGame: (game) => {
        return client({
           method: 'POST',
           path: '/api/games'
        })
    },
    deleteGame: (id) => {
        return client({
            method: 'DELETE',
            path: '/api/games/' + id
        })
    }
}