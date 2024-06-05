//
//1) Create a new Rust function that lets you create either a Vector of random N-S Avenues and a Vector of random E-W Streets with the same parameter types.

//2) Using the abstraction from #1, write code in city_drawer.rs that draws both N-S avenues and E-W streets in a uniform way (not two separate functions).

//3) Stretch goal: how do you render locations next to a street/avenue with a third type, ‘o’ indicating an address?

use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{printcity, add_aves, add_roads, City, Road, RoadDirection, Avenue,Street,AddressAvenue, AddressStreet,WIDTH,HEIGHT};



fn main() {
    let mut oly = City{
        gridman: [0; 2500]
    };
    let mut i = 0;
    let street = vec![1, 5, 29];
    let ave = vec![1, 5, 29];
    oly = add_aves(ave, oly);
    oly = add_roads(street, oly);
    
    printcity(oly)
}

