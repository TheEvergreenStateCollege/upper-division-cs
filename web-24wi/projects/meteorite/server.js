const express = require("express");
const app = express();
const DEFAULT_PORT = process.env.API_PORT || 5000;


// Start with default port, some servers may already be listening on port 5000
// On Mac OS on Sonoma and afterwards, Mac Control Center listens on this
let port = DEFAULT_PORT;
const path = require("path");
const { PrismaClient } = require('@prisma/client');

const { parsed } = require("dotenv").config();

const prisma = new PrismaClient();

app.use(express.json());
app.use(express.static("static"));

/**
 * app.[method]([route], [route handler])
 */
app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("pages/index.html"));
});

app.get("/users", async () => {
    const allUsers = await prisma.user.findMany();
    res.json(allUsers);
});

app.post("/user", async (req, res) => {
	const newUser = await prisma.user.create({
		data: {
			username: req.body.username,
			password: req.body.passwrord,
},
});

res.json(result);
//console.log("created"); 
});

// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

// http://sub.arcology.builders:5000 
app.on('error', (error) => {
    if (error.code === 'EADDRINUSE') {
        console.error('Error: Address already in use:', error.message);
        // Implement your recovery strategy here (e.g., retry with a different port)
    } else {
        console.error(error); // Handle other errors
    }
});

var myErrorHandler = function(err, req, res, next){
    // note, using the typical middleware pattern, we'd call next() here, but 
    // since this handler is a "provider", i.e. it terminates the request, we 
    // do not.
  console.error(`Error ${error}`)
};

app.use(myErrorHandler);

const http = require('http');
/*
var server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' });
  res.end('okay');
});
*/
const server = http.createServer(app); // Replace with your Express app creation
const serverFunc = () => {
  server.listen(port, () => {
    console.log(`Server listening on port ${port}`);
  });
};

app.get("/randomGraph", async (req, res) => {
  let results = [];
  for (let i = 0; i < 10; i += 1) {
    results.push({ "day": i, "stepCount": Math.round(Math.random() * 1000) });
  }
  res.json({ results });
});

app.get("/graph.html", (req, res) => {
  res.sendFile(path.resolve("pages/graph.html"))
});

app.get("/fetchUSData", async (req, res) => {
  try {
    const response = await fetch("https://indira.arcology.builders/us.json");
    if (!response.ok) {
      throw new Error("Failed to fetch US data");
    }
    const usData = await response.json();
    res.json(usData);
  } catch (error) {
    console.error("Error fetching US data:", error);
    res.status(500).json({ error: "Internal Server Error" });
  }
});

app.get("/cities", async (req, res) => {
    const allCities = await prisma.uSCity.findMany();
    res.json(allCities);
});

app.post("/city", async (req, res) => {
    const result = await prisma.uSCity.create({
        data: {
          longitude: Number(req.body.longitude),
          latitude: Number(req.body.latitude),
          authorId: Number(req.body.authorId),
        }
    });
    res.json(result);
});

server.on('error', (error) => {
  if (error.code === 'EADDRINUSE') {
    console.error(`Port ${port} is already in use. Trying the next port number.`);
    port += 1;
    setTimeout(serverFunc, 1000);
  } else {
    console.error('Error starting server:', error);
  }
  // Optional: Perform additional actions like exiting the application
});
// Kick off the chain of server listen retries with the original port
serverFunc();
