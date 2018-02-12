var interceptor = require('rest/interceptor');
var UrlPattern = require('url-pattern')

var gameUrlPattern = new UrlPattern('/api/games/:id')

var gameMapper = interceptor({
                 init: (config) => {
                    return config
                 },
                 response: (response, config, meta) => {
                     if(gameUrlPattern.match(response.url)){
                         if(response.entity){
                             response.game = response.entity
                              if(response.game.rounds){
                                  response.rounds = response.game.rounds
                                  if(Array.isArray(response.game.rounds)){
                                     response.gameBoards = response.game.rounds.map((round) => {
                                         return round.gameBoard
                                     })
                                  }
                              }
                         }
                     }
                     return response
                 }
             })

module.exports = gameMapper