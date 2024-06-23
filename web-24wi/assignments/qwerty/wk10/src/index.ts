//import * as dotenv from 'dotenv'
//dotenv.config()
//import config from './config'
//import app from './server'

//app.listen(config.port, () => {
  //  console.log('hello on http://localhost:${config.port}')
//})

import * as dotenv from 'dotenv'
dotenv.config()

import app from './server'

app.listen(3001, () => {
  console.log('hello on http://localhost:3001')
})