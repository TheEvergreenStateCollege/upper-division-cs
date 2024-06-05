pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub struct City {
    pub gridman: [usize; WIDTH * HEIGHT]

}

// rows (east/west)
pub fn add_roads(roads: Vec<usize>, mut city: City) -> City {
        let mut i ;
        let mut bound ;
        
        for road in roads.iter() {
            i = road*WIDTH;
            bound = (road+1)*WIDTH;
            
            while i < bound {
                city.gridman[i] = 1;
                i=i+1;
            }
        }
        city
}

// columns (north/south)
pub fn add_aves(aves: Vec<usize>, mut city: City) -> City {
        let mut i ;
        let mut bound ;
        
        for ave in aves.iter() {
            
            i = ave.clone();
            bound = HEIGHT*WIDTH;
            while i < bound {
                city.gridman[i] = 1;
                i=i+WIDTH;
            }
        }
        city
    }

pub fn printcity(city: City) {
    let mut n = 0;
    print!("0 ");
    while n < city.gridman.len() {
        if (n+1)%WIDTH == 0 {
            println!("{:?}", city.gridman[n]);
            print!("{:?} ", n/WIDTH);
        } else {
            print!("{:?}", city.gridman[n]);
        }
        n+=1;
    }
    
}

pub struct Road {
    road_type: RoadDirection,
    coord: usize,
}

pub enum RoadDirection {
    NorthSouth, 
    EastWest,
}

pub trait RoadTrait {
    fn newroad() -> Self;
}

impl RoadTrait for Vec<Avenue> {
    fn newroad() -> Vec<Avenue> {
        let mut n_s_avenues = Vec::<Avenue>::new();             // 1. Establish a new vector of passed type
        n_s_avenues
    }
}


pub struct Street {
    pub y: usize
}

pub struct Avenue {
    pub x: usize
}

pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub x: usize,
}


