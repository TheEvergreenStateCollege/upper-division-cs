pub mod ranker;

pub mod enumerator {

    use crate::types::{Move, Board, Cell};

    pub fn list_moves<'a>(board: &'a Board) -> (Vec<Move>) {
        let mut moves = Vec::<Move>::new();
        for i in 0..3 {
            for j in 0..3 {
                if board.cells[i as usize][j as usize] == Cell::None {
                    moves.push(Move{ coords: (i,j) })
                }
            }
        }
        (moves)
    }
    
}

use crate::types::{Move, Board, Cell};
pub trait Ranker {
    fn rank_moves(board: &mut Board) -> Vec<Move>;
}

#[derive(Debug)]
pub enum MoveError {
    WrongPlayer, // wrong player tried to move next 
    CellTaken, // the cell for the move was chosen in the past
    OutOfBounds, // outside of 3x3 grid
}