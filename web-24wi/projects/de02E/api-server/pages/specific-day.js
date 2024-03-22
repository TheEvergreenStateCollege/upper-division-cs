
window.onload = function() {
    const button = document.getElementById("submit-button");
    const dateBox = document.getElementById("date");
    const messages = document.getElementById("centered2")
    console.log(button)
    button.addEventListener("click", async (event) => {
        console.log('hello');
        event.preventDefault();

        const date = dateBox.value;
        
        console.log(`date ${dateBox}`);
        messages.textContent = getDate(date).messages +  "  Messages were sent on the day of " + getDate(date).date;
    });
}
