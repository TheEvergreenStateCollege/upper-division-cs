
const usernameEl = document.querySelector("inputUser");
const passwordEl = document.querySelector("inputPass");
const submitButton = document.querySelector("submitButton");

submitButton.addEventListener("submit", async (event) => {
	event.preventDefault();
	const username = usernameEl.value;
	const password = passwordEl.value;

	console.log(`username: ${username}`);
	console.log(`password: ${password}`);

	const response = await fetch("18.221.73.90:3001", {
		 "method": "post",
		 "headers": {
			          "Content-Type": "application/json",
			      },
		  "body": JSON.stringify({ username, password })
		  console.log(`${username} ${password}`)
		});
}
