import {Router} from 'express'
import { body, oneOf, validationResult } from 'express-validator'
import { handleInputErrors } from './modules/middleware'
import { createPost, getPost, getPosts, updatePost, deletPost } from './handlers/posts'
import { createNewUser, signin, deletUser, updateUser, updatePass } from './handlers/user'
import path from 'path'

const router = Router()
//user


router.get('/profile', (req,res) => {
    res.status(200)
    res.sendFile(path.resolve("../client/dist/profile/index.html"))
})

router.post('/delete', deletUser)

router.post('/updateUser', updateUser)

router.post('/updatePass', updatePass)

router.post('/post', createPost)

router.get('/posts', getPosts)

router.get('/post', getPosts)

router.get('/post/:id', () => {})
router.put('/post/:id', body('name'),handleInputErrors, (req, res) => {
    
})
router.post('/post',body('name').isString(), handleInputErrors,createPost)
router.delete('/post/:id', () => {})

//update
router.get('/update', () => {})
router.get('/update/:id', () => {})
router.put('/update/:id', 
    body('title').optional,
    body('body').optional, 
    body('status').isIn(['IN_PROGRESS', 'SHIPPED', 'DEPRECATED']).optional(),
    body('version').optional,
    () => {}
)
router.post('/update', 
    body('title').exists().isString(),
    body('body').exists().isString(),
    body('postId').exists().isString(),
  )
router.delete('/update/:id', () => {})

// updatepoint
router.get('/updatepoint', () => {})
router.get('/updatepoint/:id', () => {})
router.put('/updatepoint/:id',
    body('name').optional().isString(), 
    body('description').optional().isString(),
    () => {} 
)
router.post('/updatepoint', 
    body('name').isString(), 
    body('description').isString(),
    body('updateId').exists().isString(),
    () => {}
)
router.delete('/updatepoint/:id', () => {})

export default router