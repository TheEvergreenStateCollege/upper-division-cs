import prisma from '../db';
import {createJWT, hashPassword, comparePasswords} from "../modules/auth";


export const createNewUser = async (req, res, next) => {
	const hash = await hashPassword(req.body.password);

	const user = await prisma.user.create({
		data: {
			username: req.body.username,
			password: hash,
		}
	});

	const token = createJWT(user);
	//res.sendFile("/home/ubuntu/upper-division-cs/web-24wi/assignments/Wizard0523/finalProject/pages/index.html")
	res.json({token})
	
};

export const signin = async (req, res) => {
	const user = await prisma.user.findUnique({
		where: {
			username: req.body.username,
		}	
	})

	const isValid = await comparePasswords(req.body.password, user.password)
	if(!isValid) {
		res.status(401)
		res.json({message: 'nope'})
		return
	}
	const token = createJWT(user);
	res.json({token});
	
	
}
