use rand::Rng;

use crate::types::{Board,Move};
use crate::validators::winning::{BoardMatch, WinningState, get_winning_states};
use super::enumerator::list_moves;
use rand::Rng;

#[derive(PartialEq, Eq)]
struct RankedMove {
    board_match: BoardMatch,
    next_move: Move,    
}

pub trait Ranker {
    fn rank_moves(board: &mut Board) -> Vec<Move>;
}

pub struct RandomRanker {

}

impl Ranker for RandomRanker {
    fn rank_moves(board: &mut Board) -> Vec<Move> {
        let all_valid_moves = list_moves(board);
        let mut best_moves = Vec::<Move>::with_capacity(all_valid_moves.len());

        

        best_moves
    }    
}

pub struct EmptyRanker {

}

impl Ranker for EmptyRanker {

    fn rank_moves(board: &mut Board) -> Vec<Move> {
        let all_valid_moves = list_moves(board);
        let mut best_moves = Vec::<Move>::with_capacity(all_valid_moves.len());

        let rand_i = rand::thread_rng().gen_range(0..all_valid_moves.len());

        best_moves.push(all_valid_moves[rand_i]);

        // 
        for state in get_winning_states().iter() {
            let (board_match, whose_ahead) = state.match_board(board);
            // YOUR CODE HERE
            // given all moves that help either player win,
            // how to choose moves to return?

            // if (board_match.moves_in_a_row 
        }
        best_moves
    }
}