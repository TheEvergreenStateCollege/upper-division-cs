mod utils;
use wasm_bindgen::prelude::*;





#[wasm_bindgen]
#[repr(u8)]
#[derive(Clone, Copy, Debug, PartialEq, Eq)]
pub enum Cell {
    Dead = 0,
    Alive = 1,
}

#[wasm_bindgen]
pub struct Universe {
    width: u32,
    height: u32,
    cells: Vec<Cell>,
}

extern crate web_sys;
use web_sys::console;

pub struct Timer<'a> {
    name: &'a str,
}
impl<'a> Timer<'a> {
    pub fn new(name: &'a str) -> Timer<'a> {
        console::time_with_label(name);
        Timer { name }
    }
}

impl<'a> Drop for Timer<'a> {
    fn drop(&mut self) {
        console::time_end_with_label(self.name);
    }
}


#[wasm_bindgen]
impl Universe {
    //return a pointer of the index, following the formula: row * width + column
    fn get_index(&self, row: u32, column: u32) -> usize {
        (row * self.width + column) as usize
    }

    fn live_neighbor_count(&self, row: u32, column: u32) -> u8 {
        let mut count = 0;

        for delta_row in [self.height - 1, 0, 1].iter().cloned() {
            for delta_col in [self.width - 1, 0, 1].iter().cloned() {
                if delta_row == 0 && delta_col == 0 {
                    continue;
                }

                let neighbor_row = (row + delta_row) % self.height;
                let neighbor_col = (column + delta_col) % self.width;
                let idx = self.get_index(neighbor_row, neighbor_col);
                count += self.cells[idx] as u8;
            }
        }
        count
    }
    
    pub fn tick(&mut self) {
        let _timer = Timer::new("Universe::tick");
        
        let mut next = {
            //let _timer = Timer::new("allocate next cells");
            self.cells.clone()
        };

        {
            //let _timer = Timer::new("new generation");
            for row in 0..self.height {
                for col in 0..self.width {
                    let idx = self.get_index(row, col);
                    let cell = self.cells[idx];
                    let live_neighbors = self.live_neighbor_count(row, col);

                    let next_cell = match (cell, live_neighbors) {
                        //Rule 1:
                        (Cell::Alive, x) if x < 2 => Cell::Dead,
                        //Rule 2:
                        (Cell::Alive, 2) | (Cell::Alive, 3) => Cell::Alive,
                        //Rule 3:
                        (Cell::Alive, x) if x > 3 => Cell::Dead,
                        //Rule 4:
                        (Cell::Dead, 3) => Cell::Alive,
                        //all other cells remain in same state
                        (otherwise, _) => otherwise,
                    };

                    next[idx] = next_cell;
                }
            }
        }
        
        //let _timer = Timer::new("free old cells");
        self.cells = next;

        macro_rules! log {
            ( $( $t:tt )* ) => {
                web_sys::console::log_1(&format!( $( $t )* ).into());
            }
        }

        let mut next = self.cells.clone();
        
        for row in 0..self.height {
            for col in 0..self.width {
                let idx = self.get_index(row, col);
                let cell = self.cells[idx];
                let live_neighbors = self.live_neighbor_count(row, col);

                log!(
                    "cell[{}, {}] is initially {:?} and has {} live neighbors",
                    row,
                    col,
                    cell,
                    live_neighbors
                );

                let next_cell = match (cell, live_neighbors) {
                    //Rule 1:
                    (Cell::Alive, x) if x < 2 => Cell::Dead,
                    //Rule 2:
                    (Cell::Alive, 2) | (Cell::Alive, 3) => Cell::Alive,
                    //Rule 3:
                    (Cell::Alive, x) if x > 3 => Cell::Dead,
                    //Rule 4:
                    (Cell::Dead, 3) => Cell::Alive,
                    //all other cells remain in same state
                    (otherwise, _) => otherwise,
                };

                log!("  it becomes {:?}", next_cell);

                next[idx] = next_cell;
            }
        }
        self.cells = next;
    }

    pub fn new() -> Universe {
        utils::set_panic_hook();
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

    pub fn set_width(&mut self, width: u32) {
        self.width = width;
        self.cells = (0..width * self.height).map(|_i| Cell::Dead).collect();
    }

    pub fn set_height(&mut self, height: u32) {
        self.height = height;
        self.cells = (0..self.width * height).map(|_i| Cell::Dead).collect();
    }

    pub fn toggle_cell(&mut self, row: u32, column: u32) {
        let idx = self.get_index(row, column);
        self.cells[idx].toggle();
    }
}

impl Universe {
    //get the dead and alive alues of the universe
    pub fn get_cells(&self) -> &[Cell] {
        &self.cells
    }

    //set cells to be alive in a universe by passing the row and column of each cell as an array
    pub fn set_cells(&mut self, cells: &[(u32, u32)]) {
        for (row, col) in cells.iter().cloned() {
            let idx = self.get_index(row, col);
            self.cells[idx] = Cell::Alive;
        }
    }
}

use std::fmt;

impl fmt::Display for Universe {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        for line in self.cells.as_slice().chunks(self.width as usize) {
            for &cell in line {
                let symbol = if cell == Cell::Dead { '◻' } else { '◼' };
                write!(f, "{}", symbol)?;
            }
            write!(f, "\n")?; 
        }
        Ok(())   
    }
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
}

impl Cell {
    fn toggle(&mut self) {
        *self = match *self {
            Cell::Dead => Cell::Alive,
            Cell::Alive => Cell::Dead,
        };
    }
}