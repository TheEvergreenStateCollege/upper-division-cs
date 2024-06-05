mod utils;

use wasm_bindgen::prelude::*;
use std::fmt;
use colored::Colorize;

#[wasm_bindgen]
#[repr(u8)]
#[derive(Clone, Copy, Debug, PartialEq, Eq)]
pub enum Cell {
    Dead = 0,
    Alive = 1,
    Warm = 2,
}

#[wasm_bindgen]
pub struct Universe {
    width: u32, 
    height: u32, 
    cells: Vec<Cell>,
}

#[wasm_bindgen]
impl Universe {

    pub fn width(&self) -> u32 {
        self.width
    }

    pub fn height(&self) -> u32 {
        self.height
    }

    pub fn cells(&self) -> *const Cell {
        self.cells.as_ptr()
    }
    
    fn get_index(&self, row: u32, column: u32) -> usize {
        (row * self.width + column) as usize
    }

    fn live_neighbor_count(&self, row: u32, column: u32) -> (i32, i32) {
        let mut live_count = 0;
        let mut warm_count = 0;
        for delta_row in [self.height - 1, 0, 1].iter().cloned() {
            for delta_col in [self.width - 1, 0, 1].iter().cloned() {
                if delta_row == 0 && delta_col == 0 {
                    continue;
                }

                let neighbor_row = (row + delta_row) % self.height;
                let neighbor_col = (column + delta_col) % self.width;
                let idx = self.get_index(neighbor_row, neighbor_col);
                match self.cells[idx] as u8 {
                    1 => live_count+=1,
                    2 => warm_count+=1,
                    _ => (),
                }
            }
        }
        (live_count, warm_count)
    }

    pub fn tick(&mut self) {
        let mut next = self.cells.clone();

        for row in 0..self.height {
            for col in 0..self.width {
                let idx = self.get_index(row, col);
                let cell = self.cells[idx];
                let (live_neighbors, warm_neighbors) = self.live_neighbor_count(row, col);
            

                let next_cell = match (cell, live_neighbors) {
                    (Cell::Alive, x) if x < 2 => Cell::Dead,
                    (Cell::Alive, x) if x > 3 => Cell::Dead,
                    (Cell::Alive, x) if x==2 => Cell::Alive,
                    (Cell::Alive, x) if x==3 => Cell::Alive,
                    (Cell::Dead, 3) => Cell::Alive,
                    (otherwise, _) => otherwise
                    
                };

                next[idx] = next_cell;
            }
        }
        self.cells = next;
    }

    pub fn new() -> Universe {
        let width = 64;
        let height = 64;

        let cells = (0..width * height)
            .map(|i| {
                if i % 2 == 0 || i % 7 == 0 {
                    Cell::Alive
                } else {
                    Cell::Dead
                }
            })
            .collect();

        Universe {
            width,
            height,
            cells,
        }
    }

    pub fn render(&self) -> String {
        self.to_string()
    }

}


impl fmt::Display for Universe {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        for line in self.cells.as_slice().chunks(self.width as usize) {
            for &cell in line {
                //let symbol = if cell == Cell::Dead { '0' } else { 'x' }; //◼
                let symbol = match cell {
                    Cell::Dead => String::from("0").yellow(),
                    Cell::Alive => String::from("1").red(),
                    Cell::Warm => String::from("2").blue(),
                    _ => String::from(" ").red(),
                };
                write!(f, "{}", symbol.red())?;
            }
            write!(f, "\n")?;
        }
        Ok(())
    }
}
