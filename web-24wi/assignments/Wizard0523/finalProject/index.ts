import * as dotenv from 'dotenv'
import prisma from './db'

dotenv.config()

import app from './server'

app.listen(3001, () => {
	console.log('18.221.73.90:3001')
})
