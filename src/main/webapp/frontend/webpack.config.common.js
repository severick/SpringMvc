const webpack = require('webpack')
const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const MiniCssExtractPlugin = require("mini-css-extract-plugin")

module.exports = {
  jsEntry: {
    'controllers/index' : './scripts/controllers/index.js',
    'controllers/home' : './scripts/controllers/home.js'
  },
  jsOutput: {
    path: path.resolve(__dirname, '../../../../target/classes/static/scripts'),
    filename: '[name].js',
    publicPath: ''
  },
  jsResolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    }
  },
  jsModule: {
    rules: [
      {
        test: /\.vue$/,
        include: path.resolve(__dirname, "scripts"),
        use: ['vue-loader'],
      },
      {
        test: /\.css?$/,
        use: ['vue-style-loader', 'css-loader?sourceMap']
      },
      {
        test: /\.scss$/,
        use: ['vue-style-loader', 'css-loader?sourceMap', 'sass-loader?sourceMap']
      },
      {
        test: /\/node_modules\/respond.js\/dest\/respond.src.js$/,
        use: 'imports-loader?this=>window'
      },
      {
        test: /\.js$/,
        include: path.resolve(__dirname, "scripts"),
        exclude: /node_modules/,
        use: {
            loader: 'babel-loader',
            options: {
                presets: [
                    [ '@babel/preset-env', 
                        {
                            'targets': { 'ie': '11' }
                        } 
                    ]
                ],
                plugins: ['@babel/plugin-transform-for-of']
                    } 
            }
    },
    {
      test: /\.(jpe?g|png|gif|svg|ico)$/i,
      use: {
          loader: 'file-loader',
          options: {
              name: '[name].[ext]',
              publicPath: '../images/',
              outputPath: '../../../../target/classes/static/images'
            } 
        }
    },
    ]
  },
  jsResolve: {
    modules: [
      path.resolve(__dirname, './scripts'),
      path.resolve(__dirname, '../../../../node_modules')
    ],
    extensions: [".js", ".json", ".vue"],
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    }
  },
  jsPlugins: [
    new webpack.ProvidePlugin({
      $: "jquery",
      jQuery: "jquery",
      'window.jQuery': "jquery",
      _: "lodash"
    }),
    new VueLoaderPlugin()
  ],
  cssEntry: { 
    main: './styles/main.scss',
    canvas: './styles/canvas_layout.scss',
    layout: './styles/layout.scss'
},
cssOutput: {
    path: path.resolve(__dirname, '../../../../target/classes/static/styles'),
    publicPath: ''
},
cssModule: {
    rules: [
        {
            test: /\.css?$/,
            use: [
                MiniCssExtractPlugin.loader,
                'css-loader',
            ]
        },
        {
            test: /\.scss$/,
            use: [
                MiniCssExtractPlugin.loader,
                'css-loader',
                'sass-loader',
            ]
        },
        {
            test: /\.woff2?$|\.ttf$|\.eot$|\.svg$/,
            loader: 'file-loader'
        },
        {
            test: /\.(jpe?g|png|gif|svg)$/i, 
            loader: "file-loader?name=[name].[ext]&publicPath=../images/&outputPath=../../../../target/classes/static/images"
        }
    ]
},
cssPlugins: [
    new MiniCssExtractPlugin({
        filename: "[name].css"
    })
],
imageEntry: {
    placeholder: './images/webpack.placeholder'
},
imageModule: {
    noParse: [/.*/]
},
imageOutput: {
    path: path.resolve(__dirname, '../../../../target/classes/static/images'),
    publicPath: '',
    filename: '[name].placeholder'
}
}
