
const audio = document.getElementById('current-audio');
const musicTracker = document.getElementById('music-tracker');
const volumeSlider = document.getElementById('volume-slider');
const playIcon = document.getElementById('play-icon');
var paused = true;

function playAudio() {
    if (paused) {
        audio.play();
    } else {
        audio.pause();
    }
}

audio.onplay = (event) => {
    paused = false;
    playIcon.innerHTML = 'pause';
}

audio.onpause = (event) => {
    paused = true;
    playIcon.innerHTML = 'play_arrow';
}

function seekAudio() {
    var seekTo = audio.duration * (musicTracker.value / 100);
    audio.currentTime = seekTo;
    audio.play();
}

audio.ontimeupdate = function() {
    if (isNaN(audio.duration) || audio.duration === 0) { //prevents invalid values from making the range become the default value (50)
        return;
    }
    var progress = (audio.currentTime / audio.duration) * 100;
    musicTracker.value = progress;
};

window.onload = (event) => {
    let currentAudio = document.getElementById('current-audio');
    currentAudio.src = 'https://gavin-bowers.arcology.builders/audio/test.mp3';
    currentAudio.load();
};

volumeSlider.addEventListener('input', (event) => {
    const value = event.target.value;
    let volumeFraction = value / 100;
    let logVolume = Math.pow(volumeFraction, 2);
    audio.volume = logVolume;
});