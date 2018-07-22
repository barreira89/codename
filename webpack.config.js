const path = require('path');

//module.exports = {
//  entry: './src/main/frontend/app.js',
//  output: {
//    filename: './src/main/resources/static/built/bundle.js',
//    path: __dirname
//  },
//  module: {
//          rules: [
//              {
//                  test: path.join(__dirname, '.'),
//                  exclude: /(node_modules)/,
//                  loader: 'babel-loader'
//              }
//          ]
//      },
//   watch: true
//};

const config = {
  entry: {
      bundle: path.resolve(path.join(__dirname, '.'), './src/main/resources/static/built/bundle.js'),
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      }
    ]
  },
  output:{
    filename: '[name].js'
  }
}

module.exports = config