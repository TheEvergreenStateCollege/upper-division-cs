use std::{
    io::{prelude::*, BufReader},
    net::{TcpListener, TcpStream}, str::FromStr,
};

use std::io::prelude::*;
use std::io::BufWriter;
use prototype_0::types::Board;

struct GamePacket {
    
}

fn write_connection() {
    let mut stream = BufWriter::new(TcpStream::connect("127.0.0.1:34254").unwrap());

    for i in 0..10 {
        stream.write(&[i+1]).unwrap();
    }
    stream.flush().unwrap();

}

fn handle_connection(mut stream: TcpStream) -> String {
    let buf_reader = BufReader::new(&mut stream);
    let http_request: Vec<_> = buf_reader
        .lines()
        .map(|result| result.unwrap())
        .take_while(|line| !line.is_empty())
        .collect();

    println!("Request: {:#?}", http_request);
    http_request.join("\n")
}

fn main() {
    let listener = TcpListener::bind("127.0.0.1:7878").unwrap();

    for stream in listener.incoming() {
        let stream = stream.unwrap();

        let board_string = handle_connection(stream);
        let board = Board::from_str(&board_string);
    }
    println!("Engine");
}