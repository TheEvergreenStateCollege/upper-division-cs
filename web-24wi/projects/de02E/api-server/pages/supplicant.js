const button = document.getElementById("login");

const usernameElt = document.getElementById("user-id");
const passwordElt = document.getElementById("password");


button.addEventListener("click", async (event) => {
    event.preventDefault();
  
    const username = usernameElt.value;
    const password = passwordElt.value;
    
    console.log(`username ${username}`);
    console.log(`password ${password}`);
  
  // add a fetch here
     const response = await fetch("https://indira.arcology.builders/signin", {
      "method": "post",
      "headers": {
        "Content-Type": "application/json",
      },
      "body": JSON.stringify({ username, password })
    });
  
  // response has a json body
  const jsonBody = await response.json();
    console.log(`Response ${JSON.stringify(jsonBody)}`);
    if (jsonBody.hasOwnProperty("token")) {
      
    }
  });

