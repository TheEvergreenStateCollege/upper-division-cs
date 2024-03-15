// possible idea for project


Plot.plot({
    projection: {type: "orthographic", rotate: [-longitude, -30]},
    r: {transform: (d) => Math.pow(10, d)}, // convert Richter to amplitude
    marks: [
      Plot.geo(land, {fill: "currentColor", fillOpacity: 0.2}),
      Plot.sphere(),
      Plot.dot(earthquakes, {x: "longitude", y: "latitude", r: "magnitude", stroke: "red", fill: "red", fillOpacity: 0.2})
    ]
  })