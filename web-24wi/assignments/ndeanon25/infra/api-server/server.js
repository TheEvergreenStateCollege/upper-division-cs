let express = require("express");
let app = express();
const port = 5000;
const path = require("path");

const { parsed } = require("dotenv").config();
const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
app.use(express.static("pages"));

/**
 *  * app.[method]([route], [route handler])
 *   */
app.get("/", (req, res) => {
	  // sending back an HTML file that a browser can render on the screen.
	   res.sendFile(path.resolve("pages/index.html"));
	   });
	
	   // creates and starts a server for our API on a defined port
	   app.listen(port, () => {
	     console.log(`Example app listening at http://localhost:${port}`);
	     });



          // Return search hit given :hit  URL route parameters
              app.get("/search-hit/:hit", (req, res) => {
          // sending back an HTML file that a browser can render on the screen.
              res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
              });

			  app.get("/randomGraph", async(req,res) =>{
				let results = [];
				for (let i = 0; i < 10; i += 1){
					results.push({"day": i,"stepCount": Math.round(Math.random() * 1000) });
				}
				res.json({ results });
			  });


console.log(Object.keys(prisma));

app.get("/users",async(req,res) => {
    const allUsers = await prisma.user.findMany();
    res.json(allUsers);
});

app.post("/user", async(req,res) => {
    const result = await prisma.user.create({
      data:{
    	username:req.body.username,
	password: req.body.password,
	}
     });
	res.json(result);
}); 


app.get("/cities", async(req,res) => {

	const allCities = await prisma.uSCity.findMany();
	res.json(allCities);
});


app.post("/city", async (req,res) => {
	const result = await prisma.uSCity.create({
	   data:{
		name:req.body.cityName,
		longitude: Number(req.body.longitude),
		latitude: Number(req.body.latitude),
		authorId: Number(req.body.authorId),
	   }
	});
	res.json(result);
});




