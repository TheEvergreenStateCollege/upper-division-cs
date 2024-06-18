use std::fmt;
use regex_lite::Regex;

use super::{Board,Cell,Player,Move};
use crate::moves::MoveError;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [ Cell::None, Cell::None, Cell::None,],
    [ Cell::None, Cell::None, Cell::None,],
    [ Cell::None, Cell::None, Cell::None,],
];

impl Board<'_> {
    pub fn new() -> Self {
        Board { 
            next_to_move: &Player::O,
            cells: EMPTY_BOARD,
        }
    }
    pub fn make_move<'a>(&'a mut self, new_move: &'a Move, player: &Player) -> (Option<MoveError>) {
        //let mut new_board = Board::clone(self);
        //if move_player == self.next_to_move {
            let new_row = new_move.coords.0;
            let new_col = new_move.coords.1;
            if new_row < 3 && new_col < 3 {
                if self.cells[new_row as usize][new_col as usize] != None {
                    Some(MoveError::CellTaken)
                } else if player != self.next_to_move {
                    Some(MoveError::WrongPlayer)
                } else {
                    self.cells[new_row as usize][new_col as usize] = Some(self.next_to_move);
                    self.next_to_move = self.next_to_move.other();
                    Option::None
                }
            } else {
                Some(MoveError::OutOfBounds)
            }
        //} else {
        //    (Some(MoveError::WrongPlayer))
        //}
    }
}

// From Gavin Bowers via Discord
impl fmt::Display for Board<'_> {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for i in 0..3 {
            write!(f, "-------------\n")?;
            for j in 0..3 {
                write!(f, "| {} ", self.cells[i][j].map_or(" ", |p| match p {Player::O => "O", Player::X => "X"}))?
            }
            write!(f, "|\n")?
        }
        write!(f, "-------------\n");
        write!(f, "Next {:?}\n", self.next_to_move)
    }
}

use std::str::FromStr;

pub struct ParseBoardErr<'a> {
    message: &'a str,
}

// From https://rust-lang-nursery.github.io/rust-cookbook/text/string_parsing.html
impl<'a> FromStr for Board<'a> {

    fn from_str(board_string: &str) -> Result<Self, Self::Err> {

        let rows: Vec<&str> = board_string.split("\n").collect();
        let mut cells = EMPTY_BOARD.clone();
        for (i, row) in rows[0..3].iter().enumerate() {
            let cols: Vec<Cell> = row
                .split(r"\s*|\s*")
                .map(|x| match x {
                    "X" => Some(&Player::X),
                    "O" => Some(&Player::O),
                    _ => None,
                })
                .collect();
            cells[i] = cols.try_into().expect("cell size mismatch");
        }

        let re = Regex::new(r"Next ([XO])").unwrap();

        let mut caps_iter = re.captures_iter(&rows[4]).map(|c| c.extract());
        let (_, [player_cap]) = caps_iter.next().unwrap();

        let next_player: &Player = match player_cap {
            "X" => &Player::X,
            "O" => &Player::O,
            _ => return Err(ParseBoardErr { message: "Unparseable next player" } ),
        };

        Ok(Board{
            next_to_move: next_player,
            cells,
        })
    }
    
    type Err = ParseBoardErr<'a>;
}
