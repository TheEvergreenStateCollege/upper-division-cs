import app from '../server'
import supertest from 'supertest'

describe('GET /', () => {
    it('should send data', async () => {
        const res = await supertest(app)
        .get('/')
    })

})