use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{city_drawer, city_builder, Address, AddressesMap, Road, RoadDirection, BOUND};
use std::collections::HashMap;

// Can we have a "parent type" to Avenue and Street,
// let's call it Road
fn gen_random_roads(bound: usize, direction: RoadDirection) -> Vec<Road> {
    let mut directional_roads = Vec::<Road>::new();
    let mut w = 0;
    loop {
        let next_w = rand::thread_rng().gen_range(3..10);
        w += next_w;
        
        // check if we've exceeded the bound
        if w >= bound {
            break;
        }
        directional_roads.push(Road {coord: w, direction: direction });
        println!("Added Road {:?}", w);

    }   
    directional_roads
}


fn main() {
    let unaddress: &String = &String::from("Unaddressed");

    println!("Hello, city!");
    let size = 50;

    let mut addresses = AddressesMap::new();

    let mut roads = gen_random_roads(size, RoadDirection::NorthSouth);

    roads.extend(gen_random_roads(size, RoadDirection::EastWest));

    let mut grid = [['.'; BOUND]; BOUND];

    city_builder(&mut addresses, &mut grid, &mut roads);

    let query = (0,7);
    let address_string = addresses.get(&query).unwrap_or(unaddress);

    println!("The address at coordinates {:?} is {} ", query, address_string);

    city_drawer(&grid);

    println!("{:?} addresses were generated and populated into our hashmap.", addresses.len());
    
    let mut road_count = 0;
    let mut ave_count = 0;
    for road in roads.iter() {
        match road.direction {
            RoadDirection::NorthSouth => road_count+=1,
            RoadDirection::EastWest => ave_count+=1
        }
    }
    //println!("# Streets: {:?}", roads.iter().filter(|road| road.direction == road.direction));
    println!("# Roads: {:?}", road_count);
    println!("# Avenues: {:?}", ave_count);

    let mut i = 0;
    for addr in addresses.iter() {
        println!("{:?}", addr);
        i+=1;
        if i > 4 {
            break;
        }
    }
}
