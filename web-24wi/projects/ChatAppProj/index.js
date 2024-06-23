//import express from 'express'
//import router from './router'
//dotenv.config();
const express = require('express');
const http = require('http');
const socketIO = require('socket.io');
//import required modules ^
const app = express();
const server = http.createServer(app);
const io = socketIO(server);
//create a socket attached to the http server ^
const PORT = process.env.PORT || 3000;

app.use(express.static('public'));
//map storing conected users
const users = new Map();
//map storing chat history
const chatHistory = new Map();

io.on('connection',(socket) => {
    let username = '';
    let currentRoom = '';
    let recipient = '';

    socket.on('login',(user) =>{
        if(users.has(user)){
            socket.emit('loginError','Username is already taken. Please choose another one');
        }
        else{
            username = user; 
            users.set(username,socket.id);
            console.log(`${username} logged in`);
            io.emit('userCount',users.size);
        }
    })

    socket.on('typing',(isTyping) => {
        if(currentRoom){
            socket.to(currentRoom).emit('typing',{username,isTyping});
        }
    })

    socket.on('privateChat',(selectedUser) => {
        recipient = selectedUser.trim();
        if(users.has(recipient)){
            if(currentRoom){
                socket.leave(currentRoom);
            }
            const recipientSocketId = users.get(recipient);
            if(recipientSocketId){
                io.to(recipientSocketId).emit('startPrivateChat',username);
                currentRoom = '';
                console.log(`${username} started a private chat with ${recipient}`);
            }
        }
        else{
            socket.emit('privateChatError', `User ${recipient} not found.`);
        }
    })
    
    socket.on('joinRoom',(roomName) =>{
        if(currentRoom){
            socket.leave(currentRoom);
        }
        socket.join(roomName);
        currentRoom = roomName;
        console.log(`${username} joined room ${roomName}`);

        if(chatHistory.has(currentRoom)){
            const messageHistory = chatHistory.get(currentRoom);
            socket.emit('messageHistory', messageHistory);
        }
    })

    socket.on('chatMessage', (message) => {
        console.log("currentRoom: ", currentRoom, "Recipient: ",recipient);

        if(currentRoom){
            if(!chatHistory.has(currentRoom)){
              chatHistory.set(currentRoom,[]);  
            }
            chatHistory.get(currentRoom).push({user:username,message});
        }

        if(currentRoom && currentRoom !== username){
            io.to(currentRoom).emit('chatMessage', `${username}: ${message}`)
        }

        else if(!currentRoom && recipient){
            const recipientSocketId = users.get(recipient);
            const senderSocketId = users.get(username);
            console.log('recipient socket id:', recipientSocketId);
            if(recipientSocketId){
                io.to(recipientSocketId).emit('chatMessage',`(Private) ${username}: ${message}`)
                io.to(senderSocketId).emit('chatMessage', `(Private) ${username}: ${message}`);
            }
        }
    })

    socket.on('disconnect',() => {
        console.log('A user disconected');
        users.delete(username);
        console.log(`${username} logged out`);
        io.emit('userCount',users.size);
        
        if(currentRoom){
            socket.to(currentRoom).emit('typing',{username,isTyping: false});
        }
    })
})

server.listen(PORT,() => {
    console.log("Server running on port 3000");
})
//tells the browser than API is accessable to all users 
//app.use(cors())