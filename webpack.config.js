const path = require('path');

module.exports = {
  entry: './src/main/frontend/app.js',
  output: {
    filename: './src/main/resources/static/built/bundle.js',
    path: __dirname
  },
   module: {
          loaders: [
              {
                  test: path.join(__dirname, '.'),
                  exclude: /(node_modules)/,
                  loader: 'babel-loader'
              }
          ]
      },
   watch: true
};