use crate::breed::request_breed_list;
use crate::pet::{request_pets, Pet};
use dioxus::prelude::*;

static ANIMALS: &[&str] = &["Bird", "Cat", "Dog", "Rabbit", "Reptile"];

#[component]
pub fn SearchParams(cx: Scope) -> Element {
    let animal = use_state(cx, || "".to_string());
    let location = use_state(cx, || "".to_string());
    let breed = use_state(cx, || "".to_string());

    let pets = use_future(cx, (), |_| {
        request_pets(animal.to_string(), location.to_string(), breed.to_string())
    });
    let breeds = use_future(cx, (animal,), |(animal,)| {
        request_breed_list(animal.to_string())
    });

    cx.render(rsx! {
        div {
            class: "search-params",
            form {
                label {
                    r#for: "location",
                    "Location",
                    input {
                        id: "location",
                        value: "{location}",
                        placeholder: "Location",
                        onchange: |event| location.set(event.value.clone())
                    },
                },
                label {
                    r#for: "animal",
                    "Animal",
                    select {
                        id: "animal",
                        value: "{animal}",
                        onchange: |event| {
                            animal.set(event.value.clone());
                            breed.set("".to_string());
                        },
                        for animal in ANIMALS {
                            option {
                                value: "{animal}",
                                "{animal}"
                            }
                        },
                    }
                },
                label {
                    r#for: "breed",
                    "Breed",
                    select {
                        id: "breed",
                        match breeds.value() {
                            Some(Ok(list)) => {
                                rsx! {
                                    for breed in list {
                                        option {
                                            value: "{breed}",
                                            "{breed}"
                                        }
                                    }
                                }
                            },
                            Some(Err(err)) => {
                                rsx! {
                                    option {
                                        "Error rendering options: {err}"
                                    }
                                }
                            },
                            None => {
                                rsx! {
                                    option {
                                        "HI"
                                    }
                                }
                            },
                        },
                        // disabled: !breeds.len() as i64,
                        // value: "{breed}",
                        // onchange: |event| breed.set(event.value.clone()),
                        // for breed in breeds {
                        //     option {
                        //         value: "{breed}",
                        //         "{breed}"
                        //     }
                        // }
                    }
                }
                button { "Submit" },
            }
            match pets.value() {
                Some(Ok(list)) => {
                    rsx! {
                        for pet in list {
                            Pet { name: &pet.name, animal: &pet.animal, breed: &pet.breed  }
                        }
                    }
                },
                Some(Err(err)) => {
                    rsx! { "An error occurred while fetching pets: {err}" }
                },
                None => {
                    rsx! { "Loading pets..." }
                }
            }
        }
    })
}
