import express from 'express'//imports express
import router from './router'
import morgan from 'morgan'
import cors from 'cors'
import {protect} from './modules/auth'
import {createNewUser, signin} from "./handlers/user";
import {homepage} from "./handlers/homepage"
import {createSong} from "./handlers/songs"

const app = express() //creates express server

app.use(cors())
app.use(morgan('dev')) //logs requests and responses
app.use(express.json()) //allows a client to send server json
app.use(express.urlencoded({extended: true})) //?? more info on "creating a custom middleware" in API design course

//custom middleware
app.use((req, res, next) => {
	req.shhh_secret = 'doggy'
	next()
})

app.get('/', (req, res) => {
	console.log("It's alive!")
	res.status(200)
	res.sendfile('pages/index.html')
})

//app.use('/api', router)
//app.use('/api', protect, router)
app.post('/createsong', createSong)

app.post('/signin', signin)
app.post('/signup', createNewUser)

export default app


