var fs = require('fs')
var wordList = fs.readFileSync('word.txt', 'utf-8').toString().replace(/\r/g, "").split(/\n/)
fs.writeFileSync('wordList.json', JSON.stringify({words:wordList}))
