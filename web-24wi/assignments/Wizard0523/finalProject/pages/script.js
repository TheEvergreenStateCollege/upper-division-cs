const nameInput = document.querySelector('form[name ="addSongForm"] input[name="nameInput"]')


console.log("test")
addEventListener("submit", (event) => {
	const newSongOnList = document.createElement(li)
	newSongOnList.innerText = nameInput.innerText
	console.log(newSongOnList.innerText)
});
