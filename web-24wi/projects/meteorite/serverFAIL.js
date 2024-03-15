const express = require("express");
const path = require("path");
const {PrismaClient} = require("@prisma/client");
const parsed = require("dotenv").config();

const app = express();
const port = 5000;

console.log(parsed["DATABASE_URL"]);
console.log(process.env["DATABASE_URL"]);
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


// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

