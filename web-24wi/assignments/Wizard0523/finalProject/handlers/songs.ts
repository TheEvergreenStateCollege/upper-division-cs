import prisma from "../db"
/*
export const getSongs = async (req, res) => {
	const user = await prisma.user.findUnique({
		where: {
			id: req.user.id
		},
		//include: {
			//songs: true
		//}
	})
	//res.json({data: user.songs})
}

export const getSong = async (req, res) => {
	const id = req.params.name
	
	const song = await prisma.songs.findFirst({
		where: {
			name: id,
			//belongsToId: req.user.id
		}
	})
	res.json({data: song})
}
*/
export const createSong = async (req, res) => {
	
	const song = await prisma.songs.create({
		data: {
			name: req.body.name,
			URL: req.body.URL,
			tag: req.body.tag
			//belongsToId: req.user.id
		}
	})
	//res.json({data: song})
}

/*
export const deleteSong = async (req, res) => {
	const deleted = await prisma.songs.delete({
		where: {
			name_belongsToId: {
				name: req.params.name,
				belongsToId: req.user.id
			}
		}
	})
	res.json({data: deleted})
}
*/

			
