/**
 * Theme: Abstack - Bootstrap 4 Web App kit
 * Author: Coderthemes
 * VectorMap
 */

! function($) {
	"use strict";

	var VectorMap = function() {
	};

	VectorMap.prototype.init = function() {
		//various examples
		
		$('#world-map-markers').vectorMap({
			map : 'world_mill_en',
			normalizeFunction : 'polynomial',
			hoverOpacity : 0.7,
			hoverColor : false,
			regionStyle : {
				initial : {
					fill : '#5d6dc3'
				}
			},
			 markerStyle: {
                initial: {
                    r: 9,
                    'fill': '#45bbe0',
                    'fill-opacity': 0.9,
                    'stroke': '#fff',
                    'stroke-width' : 7,
                    'stroke-opacity': 0.4
                },

                hover: {
                    'stroke': '#fff',
                    'fill-opacity': 1,
                    'stroke-width': 1.5
                }
            },
			backgroundColor : 'transparent',
			markers : [{
				latLng : [41.90, 12.45],
				name : 'Vatican City'
			}, {
				latLng : [43.73, 7.41],
				name : 'Monaco'
			}, {
				latLng : [-0.52, 166.93],
				name : 'Nauru'
			}, {
				latLng : [-8.51, 179.21],
				name : 'Tuvalu'
			}, {
				latLng : [43.93, 12.46],
				name : 'San Marino'
			}, {
				latLng : [47.14, 9.52],
				name : 'Liechtenstein'
			}, {
				latLng : [7.11, 171.06],
				name : 'Marshall Islands'
			}, {
				latLng : [17.3, -62.73],
				name : 'Saint Kitts and Nevis'
			}, {
				latLng : [3.2, 73.22],
				name : 'Maldives'
			}, {
				latLng : [35.88, 14.5],
				name : 'Malta'
			}, {
				latLng : [12.05, -61.75],
				name : 'Grenada'
			}, {
				latLng : [13.16, -61.23],
				name : 'Saint Vincent and the Grenadines'
			}, {
				latLng : [13.16, -59.55],
				name : 'Barbados'
			}, {
				latLng : [17.11, -61.85],
				name : 'Antigua and Barbuda'
			}, {
				latLng : [-4.61, 55.45],
				name : 'Seychelles'
			}, {
				latLng : [7.35, 134.46],
				name : 'Palau'
			}, {
				latLng : [42.5, 1.51],
				name : 'Andorra'
			}, {
				latLng : [14.01, -60.98],
				name : 'Saint Lucia'
			}, {
				latLng : [6.91, 158.18],
				name : 'Federated States of Micronesia'
			}, {
				latLng : [1.3, 103.8],
				name : 'Singapore'
			}, {
				latLng : [1.46, 173.03],
				name : 'Kiribati'
			}, {
				latLng : [-21.13, -175.2],
				name : 'Tonga'
			}, {
				latLng : [15.3, -61.38],
				name : 'Dominica'
			}, {
				latLng : [-20.2, 57.5],
				name : 'Mauritius'
			}, {
				latLng : [26.02, 50.55],
				name : 'Bahrain'
			}, {
				latLng : [0.33, 6.73],
				name : 'São Tomé and Príncipe'
			}]
		});

		$('#usa').vectorMap({
			map : 'us_aea_en',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#4489e4'
				}
			}
		});

//		$('#india').vectorMap({
//			map : 'in_mill',
//			backgroundColor : 'transparent',
//			regionStyle : {
//				initial : {
//					fill : '#79797957'
//				}
//			}
//		});
		
		$('#india').vectorMap({
			map : 'in_mill',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#79797957'
				}
			},
			markerStyle: {
                initial: {
                    r: 9,
                    'fill': '#45bbe0',
                    'fill-opacity': 0.9,
                    'stroke': '#fff',
                    'stroke-width' : 7,
                    'stroke-opacity': 0.4
                },

                hover: {
                    'stroke': '#fff',
                    'fill-opacity': 1,
                    'stroke-width': 1.5
                }
            },
            backgroundColor : 'transparent',
			markers : [{
				latLng : [28.70, 77.10],
				name : 'Delhi'
			}, {
				latLng : [17.38, 78.48],
				name : 'Hyderabad'
			}, {
				latLng : [19.07, 72.87],
				name : 'Mumbai'
			}, {
				latLng : [18.52, 73.85],
				name : 'Pune'
			}, {
				latLng : [20.29, 85.82],
				name : 'Bhubaneswar'
			}, {
				latLng : [47.14, 9.52],
				name : 'Liechtenstein'
			}, {
				latLng : [12.97, 77.59],
				name : 'Bangalore'
			}, {
				latLng : [19.87, 75.34],
				name : 'Aurangabad'
			}, {
				latLng : [15.49, 73.82],
				name : 'Panaji'
			}, {
				latLng : [9.93, 76.26],
				name : 'Kochi'
			}, {
				latLng : [13.08, 80.27],
				name : 'Chennai'
			}, {
				latLng : [16.50, 80.64],
				name : 'Vijayawada'
			}, {
				latLng : [11.94, 79.80],
				name : 'Pondicherry'
			}, {
				latLng : [26.91 , 75.78],
				name : 'Jaipur'
			}, {
				latLng : [21.25 , 81.62],
				name : 'Raipur'
			}, {
				latLng : [21.14 , 79.08],
				name : 'Nagpur'
			}, {
				latLng : [23.79 , 86.43],
				name : 'Dhanbad'
			}, {
				latLng : [15.82, 78.03],
				name : 'Kurnool'
			}, {
				latLng : [15.13, 76.92],
				name : 'Bellary'
			}]
		});

		$('#uk').vectorMap({
			map : 'uk_mill_en',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#4fbde9'
				}
			}
		});

		$('#chicago').vectorMap({
			map : 'us-il-chicago_mill_en',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#f9bc0b'
				}
			}
		});


		$('#australia').vectorMap({
			map : 'au_mill',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#f36270'
				}
			}
		});
		
		
		$('#canada').vectorMap({
			map : 'ca_lcc',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#9368f3'
				}
			}
		});
		
		$('#germany').vectorMap({
			map : 'de_mill',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#e061c9'
				}
			}
		});
		
		$('#asia').vectorMap({
			map : 'asia_mill',
			backgroundColor : 'transparent',
			regionStyle : {
				initial : {
					fill : '#313a46'
				}
			}
		});
	},
	//init
	$.VectorMap = new VectorMap, $.VectorMap.Constructor =
	VectorMap
}(window.jQuery),

//initializing
function($) {
	"use strict";
	$.VectorMap.init()
}(window.jQuery);
