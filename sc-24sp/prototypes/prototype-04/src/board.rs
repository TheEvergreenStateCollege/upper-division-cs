use std::fmt;
use thiserror::Error;

#[derive(Clone, Copy, PartialEq, Eq)]
pub enum Player {
    X,
    O,
}

#[derive(Error, Debug)]
pub enum BoardError {
    #[error("Cell is out of bounds")]
    OutOfBounds,
    #[error("Cell is taken")]
    CellTaken,
}

#[derive(Clone)]
pub struct Board {
    cells: Vec<Vec<Option<Player>>>,
    dimensions: u8,
}

impl Board {
    pub fn new(dimensions: u8) -> Self {
        Board {
            cells: vec![vec![None; dimensions.into()]; dimensions.into()],
            dimensions,
        }
    }

    pub fn place(&mut self, x: u8, y: u8, new_state: Option<Player>) -> Result<(), BoardError> {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            return Err(BoardError::OutOfBounds);
        }

        if self.get_cell(x, y).is_none() {
            self.cells[x as usize][y as usize] = new_state;
        } else {
            return Err(BoardError::CellTaken);
        }

        Ok(())
    }

    pub fn get_cell(&self, x: u8, y: u8) -> &Option<Player> {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            // TODO: Return Err
            panic!("Out of bounds");
        }

        &self.cells[x as usize][y as usize]
    }

    /// Check if the given player has won
    pub fn check_win(&self, p: Player) -> bool {
        //Diagonal top-left to bottom-right
        if self.cells[0][0] == Some(p) && self.cells[1][1] == Some(p) && self.cells[2][2] == Some(p)
        {
            return true;
        };
        //Diagonal top-right to bottom-left
        if self.cells[2][0] == Some(p) && self.cells[1][1] == Some(p) && self.cells[0][2] == Some(p)
        {
            return true;
        };
        //Row wins
        for y in 0..3 {
            if self.cells[0][y] == Some(p)
                && self.cells[1][y] == Some(p)
                && self.cells[2][y] == Some(p)
            {
                return true;
            };
        }
        //Column wins
        for x in 0..3 {
            if self.cells[x][0] == Some(p)
                && self.cells[x][1] == Some(p)
                && self.cells[x][2] == Some(p)
            {
                return true;
            };
        }

        false
    }
}

// Shamelessly lifted from Gavin
impl fmt::Display for Board {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for row in 0..self.dimensions {
            writeln!(f, "-------------")?;
            for column in 0..self.dimensions {
                write!(
                    f,
                    "| {} ",
                    match self.cells[column as usize][row as usize] {
                        Some(Player::X) => "X",
                        Some(Player::O) => "O",
                        None => " ",
                    }
                );
            }
            writeln!(f, "|")?
        }
        write!(f, "-------------")
    }
}
