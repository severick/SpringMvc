const path = require('path')
const CopyWebpackPlugin = require('copy-webpack-plugin')
const Common = require('./webpack.config.common.js')

module.exports =  [
	{    
      mode: 'development',
      context: path.resolve(__dirname),
      entry: Common.jsEntry,
      output: Common.jsOutput,
      module: Common.jsModule,
      resolve: Common.jsResolve,
      optimization: {
        splitChunks: {
          chunks: 'all',
          name: 'common',
          minChunks: Infinity
        }
      },
      plugins: [
        ...Common.jsPlugins
      ],
      devtool: '#eval-source-map',
	},
    {
      mode: 'development',
      context: path.resolve(__dirname),
      entry: Common.cssEntry,
      output: Common.cssOutput,
      module: Common.cssModule,
      plugins: [
          ...Common.cssPlugins
      ],
    },
    {
      mode: 'development',
      context: path.resolve(__dirname),
    entry: Common.imageEntry,
    module: Common.imageModule,
    output: Common.imageOutput,
    plugins: [
      new CopyWebpackPlugin([
        { from: 'images', to: '[path][name].[ext]' }
      ],
      {
        copyUnmodified: true
      })
      ]
    }
]
