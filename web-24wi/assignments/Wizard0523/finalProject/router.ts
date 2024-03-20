import {Router} from 'express'
import {body, validationResult} from "express-validator"
//import {getSong, getSongs, createSong, deleteSong} from "./handlers/songs";
import {createSong} from "./handlers/songs"
import express from 'express'

const router = Router()

/*
router.use(express.json());
router.use(express.urlencoded({
	extended: true
}))res.json({data: song})
*/

router.get('/songs', () => {}) //get all of the products

router.get('/songs/:name', (req, res) => {
	res.json({message:'heyo'})
})

//add song to song
/*
router.post('/songs/:name', body('name').isString(), (req, res)=> {
	const errors = validationResult(req)
	console.log(errors)

	if (!errors.isEmpty()) {
		res.status(400);
		res.json({errors: errors.array()});
	}
})
*/
router.post('/songs', createSong);
//router.post('/songs/:name', deleteSong);
//router.post(deleteSong);

//router.delete('/songs/:name', () => {}) //delete song from Songs


export default router
