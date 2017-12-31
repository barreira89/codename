var fs = require('fs')
var wordList = fs.readFileSync('word.txt', 'utf-8').toString().split(/\n/)
fs.writeFileSync('wordList.json', JSON.stringify({words:wordList}))