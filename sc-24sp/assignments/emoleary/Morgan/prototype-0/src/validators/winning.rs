use std::{collections::HashSet, hash::Hasher};
use std::hash::Hash;

use crate::types::{Player,Move,Board};

// A set of moves by the same player that would win a 3x3 game
#[derive(PartialEq,Eq)]
pub struct WinningState {
    three_moves: HashSet<Move>,
}

// https://internals.rust-lang.org/t/implementing-hash-for-hashset-hashmap/3817/2
impl Hash for WinningState {
    fn hash<H: Hasher>(&self, state: &mut H) {
        for elt in self.three_moves.iter() {
            state.write_u8(elt.coords.0);
            state.write_u8(elt.coords.1);
        }
        state.finish();
    }
}

#[derive(PartialEq, Eq)]
pub struct BoardMatch {
    pub player: Player,
    pub moves_in_a_row: u8,
}

pub enum WhoseAhead {
    X,
    O,
    TIE,
}

// If both X and O can win
impl WinningState {
    pub fn match_board(&self, board: &Board) -> (BoardMatch, WhoseAhead) {
        let mut x_set = BoardMatch { player: Player::X, moves_in_a_row: 0 };
        let mut o_set = BoardMatch { player: Player::O, moves_in_a_row: 0 };
        for each_move in self.three_moves.iter() {
            println!("{:?}", each_move);
            match board.cells[each_move.coords.0 as usize][each_move.coords.1 as usize] {
                Some(Player::X) => { x_set.moves_in_a_row += 1; } 
                Some(Player::O) => { o_set.moves_in_a_row += 1; }
                None => {}
            }
        }

        if x_set.moves_in_a_row == o_set.moves_in_a_row {
            // if it's a tie, return X's board match arbitrarily
            return (x_set, WhoseAhead::TIE);
        }
        else if x_set.moves_in_a_row > o_set.moves_in_a_row {
            return (x_set, WhoseAhead::X);
        } else {
            return (o_set, WhoseAhead::O);
        }
    }
}

pub fn get_winning_states() -> HashSet<WinningState> {
    let mut results = HashSet::<WinningState>::new();
    for row in 0..3 {
        results.insert(winning_row(row));
    }

    for col in 0..3 {
        results.insert(winning_col(col));
    }
    results.insert(winning_diag(true));
    results.insert(winning_diag(false));
    results
}

fn winning_row(row: u8) -> WinningState {
    let mut win = HashSet::<Move>::new();
    for i in 0..3 {
        win.insert(Move { coords: (row, i) });
    }
    WinningState { three_moves: win }
}

fn winning_col(col: u8) -> WinningState {
    let mut win = HashSet::<Move>::new();
    for i in 0..3 {
        win.insert(Move { coords: (i, col) });
    }
    WinningState { three_moves: win }
}

// forward == true is Down, right diagonal win validator
//  * 
//    *
//      *
// forward == false is Down, left diagonal win validator
//      *
//    *
//  *    
fn winning_diag(forward: bool) -> WinningState {
    let mut win = HashSet::<Move>::new();
    let c1: i8 = if forward { 1 } else { -1 };
    let c2: i8 = if forward { 0 } else { 1 };
    for i in 0..3 {
        let next_move = Move { coords: (i, (c2*2 + c1*(i as i8)).try_into().unwrap()) };
        //println!("{:?}", next_move);
        win.insert(next_move);
    }
    WinningState { three_moves: win }
}