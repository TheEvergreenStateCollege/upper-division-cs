pub mod types;
pub mod moves;
pub mod validators;

#[cfg(test)]
mod tests {
    use crate::types::Board;

    #[test]
    fn board_to_string() {
        let board = Board::new();

        // If we use {:?} format specifier, we'll use the Debug trait,
        // which isn't implemented yet,
        // instead of the Display trait
        let board_string = format!("{}", board);
        let expected =
"-------------
|   |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------
Next O
";
        assert_eq!(board_string, expected);
    }

    // YOUR TESTS HERE
    // copy and paste the test above and give it a different function name.
    // Try making a move to mutate the board, and assert what you 
    // think the expected result as an ASCII string should be
}