use super::city_drawer::{Grid,BOUND};

pub struct OlympiaAddresser {
    locations: HashMap<(usize,usize), String>;
}

impl CityAddresser for OlympiaAddresser {

} 

pub fn city_drawer<'a>(in_grid: &'a mut Grid, roads: &'a Vec<Road>) -> &'a mut Grid {
    //roads.sort_by(|a,b| a.coord.cmp(&b.coord));

    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                for w in 0..BOUND {
                    match road.road_type {
                        RoadDirection::NorthSouth => {
                            in_grid[road.coord as usize][w] = '#';
                            in_grid[w][road.coord] = '#';
                            if road.coord > 0 && in_grid[road.coord - 1 as usize][w] == '.' {
                                in_grid[road.coord - 1 as usize][w] = 'o';
                            }
                            if road.coord < BOUND - 1  && in_grid[road.coord as usize + 1][w] == '.' {
                                in_grid[road.coord as usize + 1][w] = 'o';
                            }
                        }
                        RoadDirection::EastWest => {
                            in_grid[w][road.coord] = '#';
                            if road.coord > 0 && in_grid[w][road.coord - 1 as usize] == '.' {
                                in_grid[w][road.coord as usize - 1 as usize] = 'o';
                            }
                            if road.coord < BOUND && in_grid[w][road.coord + 1 as usize] == '.' {
                                in_grid[w][road.coord + 1 as usize] = 'o';
                            }
                        }
                    }
                }
            }
            None => {
                // no more 
                break;
            }
        }
    }
    in_grid

}
