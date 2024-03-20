READ ME:

Author: Griffin Loiselle

!!IMPORTANT NOTE!!
This project is a WIP, and is currently fragmented.
-first run the server to view index.html
-to access login page/test authentication:
	1. change /index.html to /login.html in server.ts
	2. run server by navigating to final project folder and running command: npm run dev
	3. open VSCode
	4. Install Thunder client in the extensions tab
	5. create a new post request to 18.221.73.90:3001/createNewUser, and in the body include fields for username and password:
		ex: {
		username: '<username>',
		password: '<password>'
		}
	-to note: if you make two requests with the same username then the server will crash.
	
	To signin, follow the same process, but replace createNewUser with signin. When you signin/createNewUser you should recieve
	a JSON token.
-finally I setup a subdomain: https://moodmusic.arcology.builders and setup a TLS certification for that subdomain. However I was
unable to serve pages on that server, so I'll include the link to my Final Project Diary to show that I've set it up:
https://github.com/TheEvergreenStateCollege/upper-division-cs/wiki/Final-Project-Diary (the certification picture is at the bottom)

ABOUT:
Moodmusic is a website where users add songs/sounds, a URL to the youtube video, and some sort of genre tag. These tags are used to
populate playlists. Users can combine multiple tags to mix and match. I became motivated to create this website while playing D&D.
I struggle to manage music/ambience while trying to run the game as Dungeon Master. My music and ambience are all in different
playlists and youtube has no good way to mix and match playlists.


ROADBLOCKS:
___________________________________________________________________________________________________________________________________
-many embedded youtube videos won't play outside of youtube. I.e. to watch those videos you must open youtube and navigate away
from moodmusic. This is a problem because I want moodmusic to be a central location for music and ambience.

-I'm struggling to connect login page to index page.

-I'm stuggling to get javascripts and CSS files to load in my HTML files

-TIME

-VIM - I was an idiot for this, but I've made the whole project in the terminal/VIM.
___________________________________________________________________________________________________________________________________

POTENTIAL FUTURE PLANS:
-streamline how users add songs/URLS/tags
-allow users to add songs from other platforms, for example: Spotify, Apple Music, etc.
