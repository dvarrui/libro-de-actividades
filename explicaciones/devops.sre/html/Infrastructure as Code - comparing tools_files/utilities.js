/* global $, delete_cookie, profileUserCardMe, DOMPurify, getCookie, CONST, productPicker, moment, tinyMCE, tinymce */

var blogStore = {}

function replaceBrokenAvatars(img, defaultImg) {
  if (img.src.indexOf(defaultImg) === -1) {
    return img.src = defaultImg
  }
  return false
}

var delete_cookie = function (name) {
  document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;'
}

var getCookie = function (cname) {
  var name = cname + '='
  var ca = document.cookie.split(';')
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i]
    while (c.charAt(0) === ' ') c = c.substring(1)
    if (c.indexOf(name) === 0) return c.substring(name.length, c.length)
  }
  return 'false'
}

var UTILITIES = {
  CONST: {
    timeZoneNames: '<option title="GMT -08:00 U.S. Pacific Time" value="US/Pacific">GMT -08:00 U.S. Pacific Time</option>' +
      '<option title="GMT -07:00 U.S. Mountain Time" value="US/Mountain">GMT -07:00 U.S. Mountain Time</option>' +
      '<option title="GMT -06:00 U.S. Central Time" value="US/Central">GMT -06:00 U.S. Central Time</option>' +
      '<option title="GMT -05:00 U.S. Eastern Time" value="US/Eastern">GMT -05:00 U.S. Eastern Time</option>' +
      '<option title="GMT -09:00 U.S. Alaska Time" value="US/Alaska">GMT -09:00 U.S. Alaska Time</option>' +
      '<option title="GMT -10:00 U.S. Hawaiian Time" value="US/Hawaii">GMT -10:00 U.S. Hawaiian Time</option>' +
      '<option title="GMT -12:00 Dateline" value="Etc/GMT+12">GMT -12:00 Dateline</option>' +
      '<option title="GMT -11:00 Samoa" value="Pacific/Samoa">GMT -11:00 Samoa</option>' +
      '<option title="GMT -10:00 U.S. Hawaiian Time" value="US/Hawaii">GMT -10:00 U.S. Hawaiian Time</option>' +
      '<option title="GMT -10:00 Aleutian Islands" value="US/Aleutian">GMT -10:00 Aleutian Islands</option>' +
      '<option title="GMT -09:30 Marquesasgit stats" value="Pacific/Marquesas">GMT -09:30 Marquesasgit stats</option>' +
      '<option title="GMT -09:00 Gambier Islands" value="Pacific/Gambier">GMT -09:00 Gambier Islands</option>' +
      '<option title="GMT -09:00 U.S. Alaska Time" value="US/Alaska">GMT -09:00 U.S. Alaska Time</option>' +
      '<option title="GMT -08:00 U.S. Pacific Time" value="US/Pacific">GMT -08:00 U.S. Pacific Time</option>' +
      '<option title="GMT -08:00 Pitcairn Islands" value="Pacific/Pitcairn">GMT -08:00 Pitcairn Islands</option>' +
      '<option title="GMT -07:00 U.S. Mountain Time" value="US/Mountain">GMT -07:00 U.S. Mountain Time</option>' +
      '<option title="GMT -07:00 U.S. Mountain Time (Arizona)" value="US/Arizona">GMT -07:00 U.S. Mountain Time (Arizona)</option>' +
      '<option title="GMT -06:00 U.S. Central Time" value="US/Central">GMT -06:00 U.S. Central Time</option>' +
      '<option title="GMT -06:00 Mexico" value="Mexico/General">GMT -06:00 Mexico</option>' +
      '<option title="GMT -06:00 Costa Rica" value="America/Costa_Rica">GMT -06:00 Costa Rica</option>' +
      '<option title="GMT -05:00 U.S. Eastern Time" value="US/Eastern">GMT -05:00 U.S. Eastern Time</option>' +
      '<option title="GMT -05:00 Colombia, Peru, South America" value="America/Bogota">GMT -05:00 Colombia, Peru, South America</option>' +
      '<option title="GMT -04:00 Atlantic Time" value="Canada/Atlantic">GMT -04:00 Atlantic Time</option>' +
      '<option title="GMT -04:00 Puerto Rico" value="America/Puerto_Rico">GMT -04:00 Puerto Rico</option>' +
      '<option title="GMT -03:30 Newfoundland, Canada" value="Canada/Newfoundland">GMT -03:30 Newfoundland, Canada</option>' +
      '<option title="GMT -03:00 Argentina" value="America/Buenos_Aires">GMT -03:00 Argentina</option>' +
      '<option title="GMT -03:00 Brazil" value="Brazil/East">GMT -03:00 Brazil</option>' +
      '<option title="GMT -02:00 Mid-Atlantic" value="Atlantic/South_Georgia">GMT -02:00 Mid-Atlantic</option>' +
      '<option title="GMT -01:00 Cape Verde" value="Atlantic/Cape_Verde">GMT -01:00 Cape Verde</option>' +
      '<option title="GMT -01:00 Azores" value="Atlantic/Azores">GMT -01:00 Azores</option>' +
      '<option title="GMT ------ U.K." value="Europe/London">GMT ------ U.K.</option>' +
      '<option title="GMT ------ Iceland" value="Iceland">GMT ------ Iceland</option>' +
      '<option title="GMT +01:00 Spain" value="Europe/Madrid">GMT +01:00 Spain</option>' +
      '<option title="GMT +01:00 Algiers, Africa" value="Africa/Algiers">GMT +01:00 Algiers, Africa</option>' +
      '<option title="GMT +01:00 Central Europe" value="Europe/Amsterdam">GMT +01:00 Central Europe</option>' +
      '<option title="GMT +02:00 Eastern Europe" value="Europe/Athens">GMT +02:00 Eastern Europe</option>' +
      '<option title="GMT +02:00 Tripoli, Africa" value="Africa/Tripoli">GMT +02:00 Tripoli, Africa</option>' +
      '<option title="GMT +02:00 Egypt" value="Egypt">GMT +02:00 Egypt</option>' +
      '<option title="GMT +02:00 Israel" value="Israel">GMT +02:00 Israel</option>' +
      '<option title="GMT +03:00 Saudi Arabia" value="Asia/Riyadh">GMT +03:00 Saudi Arabia</option>' +
      '<option title="GMT +03:30 Iran" value="Iran">GMT +03:30 Iran</option>' +
      '<option title="GMT +03:00 Russia" value="Europe/Moscow">GMT +03:00 Russia</option>' +
      '<option title="GMT +04:00 Samara, Europe" value="Europe/Samara">GMT +04:00 Samara, Europe</option>' +
      '<option title="GMT +04:00 Arabian" value="Asia/Dubai">GMT +04:00 Arabian</option>' +
      '<option title="GMT +04:30 Afghanistan" value="Asia/Kabul">GMT +04:30 Afghanistan</option>' +
      '<option title="GMT +05:00 Pakistan, West Asia" value="Asia/Karachi">GMT +05:00 Pakistan, West Asia</option>' +
      '<option title="GMT +05:00 Yekaterinburg, Asia" value="Asia/Yekaterinburg">GMT +05:00 Yekaterinburg, Asia</option>' +
      '<option title="GMT +05:30 India" value="Asia/Calcutta">GMT +05:30 India</option>' +
      '<option title="GMT +06:00 Bangladesh, Central Asia" value="Asia/Thimphu">GMT +06:00 Bangladesh, Central Asia</option>' +
      '<option title="GMT +06:00 Novosibirsk, Asia" value="Asia/Novosibirsk">GMT +06:00 Novosibirsk, Asia</option>' +
      '<option title="GMT +06:30 Burma" value="Asia/Rangoon">GMT +06:30 Burma</option>' +
      '<option title="GMT +07:00 Bangkok, Hanoi, Jakarta" value="Asia/Bangkok">GMT +07:00 Bangkok, Hanoi, Jakarta</option>' +
      '<option title="GMT +07:00 Krasnoyarsk, Asia" value="Asia/Krasnoyarsk">GMT +07:00 Krasnoyarsk, Asia</option>' +
      '<option title="GMT +08:00 China" value="PRC">GMT +08:00 China</option>' +
      '<option title="GMT +08:00 Singapore" value="Singapore">GMT +08:00 Singapore</option>' +
      '<option title="GMT +08:00 Taiwan" value="Asia/Taipei">GMT +08:00 Taiwan</option>' +
      '<option title="GMT +08:00 Australia (WT)" value="Australia/West">GMT +08:00 Australia (WT)</option>' +
      '<option title="GMT +08:00 Irkutsk, Asia" value="Asia/Irkutsk">GMT +08:00 Irkutsk, Asia</option>' +
      '<option title="GMT +09:00 Japan" value="Japan">GMT +09:00 Japan</option>' +
      '<option title="GMT +09:00 Korea" value="Asia/Seoul">GMT +09:00 Korea</option>' +
      '<option title="GMT +09:00 Yakutsk, Russia" value="Asia/Yakutsk">GMT +09:00 Yakutsk, Russia</option>' +
      '<option title="GMT +09:30 Australia (NT)" value="Australia/North">GMT +09:30 Australia (NT)</option>' +
      '<option title="GMT +09:30 Australia (ST)" value="Australia/Adelaide">GMT +09:30 Australia (ST)</option>' +
      '<option title="GMT +10:00 Australia (ET)" value="Australia/Sydney">GMT +10:00 Australia (ET)</option>' +
      '<option title="GMT +10:00 Australia (Queensland)" value="Australia/Queensland">GMT +10:00 Australia (Queensland)</option>' +
      '<option title="GMT +10:00 Magadan, Asia" value="Asia/Magadan">GMT +10:00 Magadan, Asia</option>' +
      '<option title="GMT +10:30 Australia (Lord Howe)" value="Australia/Lord_Howe">GMT +10:30 Australia (Lord Howe)</option>' +
      '<option title="GMT +11:00 Central Pacific" value="Pacific/Guadalcanal">GMT +11:00 Central Pacific</option>' +
      '<option title="GMT +11:30 Norfolk Islands" value="Pacific/Norfolk">GMT +11:30 Norfolk Islands</option>' +
      '<option title="GMT +12:00 New Zealand" value="Pacific/Auckland">GMT +12:00 New Zealand</option>' +
      '<option title="GMT +12:00 Fiji" value="Pacific/Fiji">GMT +12:00 Fiji</option>' +
      '<option title="GMT +12:00 South Pole, Antarctica" value="Antarctica/South_Pole">GMT +12:00 South Pole, Antarctica</option>',
    countries: [
      'Andorra',
      'United Arab Emirates',
      'Afghanistan',
      'Antigua and Barbuda',
      'Anguilla',
      'Albania',
      'Armenia',
      'Angola',
      'Antarctica',
      'Argentina',
      'American Samoa',
      'Austria',
      'Australia',
      'Aruba',
      'Azerbaijan',
      'Bosnia and Herzegovina',
      'Barbados',
      'Bangladesh',
      'Belgium',
      'Burkina Faso',
      'Bulgaria',
      'Bahrain',
      'Burundi',
      'Benin',
      'Saint Barthelemy',
      'Bermuda',
      'Brunei',
      'Bolivia',
      'Brazil',
      'Bahamas',
      'The',
      'Bhutan',
      'Bouvet Island',
      'Botswana',
      'Belarus',
      'Belize',
      'Canada',
      'Cocos (Keeling) Islands',
      'Congo',
      'Democratic Republic of the',
      'Central African Republic',
      'Congo',
      'Republic of the',
      'Switzerland',
      'Cote d\'Ivoire',
      'Cook Islands',
      'Chile',
      'Cameroon',
      'China',
      'Colombia',
      'Costa Rica',
      'Cuba',
      'Cape Verde',
      'Curacao',
      'Christmas Island',
      'Cyprus',
      'Czech Republic',
      'Germany',
      'Djibouti',
      'Denmark',
      'Dominica',
      'Dominican Republic',
      'Algeria',
      'Ecuador',
      'Estonia',
      'Egypt',
      'Western Sahara',
      'Eritrea',
      'Spain',
      'Ethiopia',
      'Finland',
      'Fiji',
      'Falkland Islands (Islas Malvinas)',
      'Micronesia',
      'Federated States of',
      'Faroe Islands',
      'France',
      'France',
      'Metropolitan',
      'Gabon',
      'United Kingdom',
      'Grenada',
      'Georgia',
      'French Guiana',
      'Guernsey',
      'Ghana',
      'Gibraltar',
      'Greenland',
      'Gambia',
      'The',
      'Guinea',
      'Guadeloupe',
      'Equatorial Guinea',
      'Greece',
      'South Georgia and the Islands',
      'Guatemala',
      'Guam',
      'Guinea-Bissau',
      'Guyana',
      'Hong Kong',
      'Heard Island and McDonald Islands',
      'Honduras',
      'Croatia',
      'Haiti',
      'Hungary',
      'Indonesia',
      'Ireland',
      'Israel',
      'Isle of Man',
      'India',
      'British Indian Ocean Territory',
      'Iraq',
      'Iran',
      'Iceland',
      'Italy',
      'Jersey',
      'Jamaica',
      'Jordan',
      'Japan',
      'Kenya',
      'Kyrgyzstan',
      'Cambodia',
      'Kiribati',
      'Comoros',
      'Saint Kitts and Nevis',
      'Korea',
      'North',
      'Korea',
      'South',
      'Kuwait',
      'Cayman Islands',
      'Kazakhstan',
      'Laos',
      'Lebanon',
      'Saint Lucia',
      'Liechtenstein',
      'Sri Lanka',
      'Liberia',
      'Lesotho',
      'Lithuania',
      'Luxembourg',
      'Latvia',
      'Libya',
      'Morocco',
      'Monaco',
      'Moldova',
      'Montenegro',
      'Saint Martin',
      'Madagascar',
      'Marshall Islands',
      'Macedonia',
      'Mali',
      'Burma',
      'Mongolia',
      'Macau',
      'Northern Mariana Islands',
      'Martinique',
      'Mauritania',
      'Montserrat',
      'Malta',
      'Mauritius',
      'Maldives',
      'Malawi',
      'Mexico',
      'Malaysia',
      'Mozambique',
      'Namibia',
      'New Caledonia',
      'Niger',
      'Norfolk Island',
      'Nigeria',
      'Nicaragua',
      'Netherlands',
      'Norway',
      'Nepal',
      'Nauru',
      'Niue',
      'New Zealand',
      'Oman',
      'Panama',
      'Peru',
      'French Polynesia',
      'Papua New Guinea',
      'Philippines',
      'Pakistan',
      'Poland',
      'Saint Pierre and Miquelon',
      'Pitcairn Islands',
      'Puerto Rico',
      'Gaza Strip',
      'West Bank',
      'Portugal',
      'Palau',
      'Paraguay',
      'Qatar',
      'Reunion',
      'Romania',
      'Serbia',
      'Russia',
      'Rwanda',
      'Saudi Arabia',
      'Solomon Islands',
      'Seychelles',
      'Sudan',
      'Sweden',
      'Singapore',
      'Saint Helena',
      'Ascension',
      'and Tristan da Cunha',
      'Slovenia',
      'Svalbard',
      'Slovakia',
      'Sierra Leone',
      'San Marino',
      'Senegal',
      'Somalia',
      'Suriname',
      'South Sudan',
      'Sao Tome and Principe',
      'El Salvador',
      'Sint Maarten',
      'Syria',
      'Swaziland',
      'Turks and Caicos Islands',
      'Chad',
      'French Southern and Antarctic Lands',
      'Togo',
      'Thailand',
      'Tajikistan',
      'Tokelau',
      'Timor-Leste',
      'Turkmenistan',
      'Tunisia',
      'Tonga',
      'Turkey',
      'Trinidad and Tobago',
      'Tuvalu',
      'Taiwan',
      'Tanzania',
      'Ukraine',
      'Uganda',
      'United States Minor Outlying Islands',
      'United States',
      'Uruguay',
      'Uzbekistan',
      'Holy See (Vatican City)',
      'Saint Vincent and the Grenadines',
      'Venezuela',
      'British Virgin Islands',
      'Virgin Islands',
      'Vietnam',
      'Vanuatu',
      'Wallis and Futuna',
      'Samoa',
      'Kosovo',
      'Yemen',
      'Mayotte',
      'South Africa',
      'Zambia',
      'Zimbabwe'
    ],
    states: [
      'Alaska',
      'American Samoa',
      'Arizona',
      'Arkansas',
      'California',
      'Colorado',
      'Connecticut',
      'Delaware',
      'District of Columbia',
      'Federated States of Micronesia',
      'Florida',
      'Georgia',
      'Guam',
      'Hawaii',
      'Idaho',
      'Illinois',
      'Indiana',
      'Iowa',
      'Kansas',
      'Kentucky',
      'Louisiana',
      'Maine',
      'Marshall Islands',
      'Maryland',
      'Massachusetts',
      'Michigan',
      'Minnesota',
      'Mississippi',
      'Missouri',
      'Montana',
      'Nebraska',
      'Nevada',
      'New Hampshire',
      'New Jersey',
      'New Mexico',
      'New York',
      'North Carolina',
      'North Dakota',
      'Northern Mariana Islands',
      'Ohio',
      'Oklahoma',
      'Oregon',
      'Palau',
      'Pennsylvania',
      'Puerto Rico',
      'Rhode Island',
      'South Carolina',
      'South Dakota',
      'Tennessee',
      'Texas',
      'Utah',
      'Vermont',
      'Virgin Island',
      'Virginia',
      'Washington',
      'West Virginia',
      'Wisconsin',
      'Wyoming'
    ],
    cities: [
      'Abilene',
      'Akron',
      'Albany',
      'Albuquerque',
      'Alexandria',
      'Allentown',
      'Amarillo',
      'Anaheim',
      'Anchorage',
      'Ann Arbor',
      'Antioch',
      'Apple Valley',
      'Appleton',
      'Arlington',
      'Arvada',
      'Asheville',
      'Athens',
      'Atlanta',
      'Atlantic City',
      'Augusta',
      'Aurora',
      'Austin',
      'Bakersfield',
      'Baltimore',
      'Barnstable',
      'Baton Rouge',
      'Beaumont',
      'Bel Air',
      'Bellevue',
      'Berkeley',
      'Bethlehem',
      'Billings',
      'Birmingham',
      'Bloomington',
      'Boise',
      'Boise City',
      'Bonita Springs',
      'Boston',
      'Boulder',
      'Bradenton',
      'Bremerton',
      'Bridgeport',
      'Brighton',
      'Brownsville',
      'Bryan',
      'Buffalo',
      'Burbank',
      'Burlington',
      'Cambridge',
      'Canton',
      'Cape Coral',
      'Carrollton',
      'Cary',
      'Cathedral City',
      'Cedar Rapids',
      'Champaign',
      'Chandler',
      'Charleston',
      'Charlotte',
      'Chattanooga',
      'Chesapeake',
      'Chicago',
      'Chula Vista',
      'Cincinnati',
      'Clarke County',
      'Clarksville',
      'Clearwater',
      'Cleveland',
      'College Station',
      'Colorado Springs',
      'Columbia',
      'Columbus',
      'Concord',
      'Coral Springs',
      'Corona',
      'Corpus Christi',
      'Costa Mesa',
      'Dallas',
      'Daly City',
      'Danbury',
      'Davenport',
      'Davidson County',
      'Dayton',
      'Daytona Beach',
      'Deltona',
      'Denton',
      'Denver',
      'Des Moines',
      'Detroit',
      'Downey',
      'Duluth',
      'Durham',
      'El Monte',
      'El Paso',
      'Elizabeth',
      'Elk Grove',
      'Elkhart',
      'Erie',
      'Escondido',
      'Eugene',
      'Evansville',
      'Fairfield',
      'Fargo',
      'Fayetteville',
      'Fitchburg',
      'Flint',
      'Fontana',
      'Fort Collins',
      'Fort Lauderdale',
      'Fort Smith',
      'Fort Walton Beach',
      'Fort Wayne',
      'Fort Worth',
      'Frederick',
      'Fremont',
      'Fresno',
      'Fullerton',
      'Gainesville',
      'Garden Grove',
      'Garland',
      'Gastonia',
      'Gilbert',
      'Glendale',
      'Grand Prairie',
      'Grand Rapids',
      'Grayslake',
      'Green Bay',
      'GreenBay',
      'Greensboro',
      'Greenville',
      'Gulfport-Biloxi',
      'Hagerstown',
      'Hampton',
      'Harlingen',
      'Harrisburg',
      'Hartford',
      'Havre de Grace',
      'Hayward',
      'Hemet',
      'Henderson',
      'Hesperia',
      'Hialeah',
      'Hickory',
      'High Point',
      'Hollywood',
      'Honolulu',
      'Houma',
      'Houston',
      'Howell',
      'Huntington',
      'Huntington Beach',
      'Huntsville',
      'Independence',
      'Indianapolis',
      'Inglewood',
      'Irvine',
      'Irving',
      'Jackson',
      'Jacksonville',
      'Jefferson',
      'Jersey City',
      'Johnson City',
      'Joliet',
      'Kailua',
      'Kalamazoo',
      'Kaneohe',
      'Kansas City',
      'Kennewick',
      'Kenosha',
      'Killeen',
      'Kissimmee',
      'Knoxville',
      'Lacey',
      'Lafayette',
      'Lake Charles',
      'Lakeland',
      'Lakewood',
      'Lancaster',
      'Lansing',
      'Laredo',
      'Las Cruces',
      'Las Vegas',
      'Layton',
      'Leominster',
      'Lewisville',
      'Lexington',
      'Lincoln',
      'Little Rock',
      'Long Beach',
      'Lorain',
      'Los Angeles',
      'Louisville',
      'Lowell',
      'Lubbock',
      'Macon',
      'Madison',
      'Manchester',
      'Marina',
      'Marysville',
      'McAllen',
      'McHenry',
      'Medford',
      'Melbourne',
      'Memphis',
      'Merced',
      'Mesa',
      'Mesquite',
      'Miami',
      'Milwaukee',
      'Minneapolis',
      'Miramar',
      'Mission Viejo',
      'Mobile',
      'Modesto',
      'Monroe',
      'Monterey',
      'Montgomery',
      'Moreno Valley',
      'Murfreesboro',
      'Murrieta',
      'Muskegon',
      'Myrtle Beach',
      'Naperville',
      'Naples',
      'Nashua',
      'Nashville',
      'New Bedford',
      'New Haven',
      'New London',
      'New Orleans',
      'New York',
      'New York City',
      'Newark',
      'Newburgh',
      'Newport News',
      'Norfolk',
      'Normal',
      'Norman',
      'North Charleston',
      'North Las Vegas',
      'North Port',
      'Norwalk',
      'Norwich',
      'Oakland',
      'Ocala',
      'Oceanside',
      'Odessa',
      'Ogden',
      'Oklahoma City',
      'Olathe',
      'Olympia',
      'Omaha',
      'Ontario',
      'Orange',
      'Orem',
      'Orlando',
      'Overland Park',
      'Oxnard',
      'Palm Bay',
      'Palm Springs',
      'Palmdale',
      'Panama City',
      'Pasadena',
      'Paterson',
      'Pembroke Pines',
      'Pensacola',
      'Peoria',
      'Philadelphia',
      'Phoenix',
      'Pittsburgh',
      'Plano',
      'Pomona',
      'Pompano Beach',
      'Port Arthur',
      'Port Orange',
      'Port Saint Lucie',
      'Port St. Lucie',
      'Portland',
      'Portsmouth',
      'Poughkeepsie',
      'Providence',
      'Provo',
      'Pueblo',
      'Punta Gorda',
      'Racine',
      'Raleigh',
      'Rancho Cucamonga',
      'Reading',
      'Redding',
      'Reno',
      'Richland',
      'Richmond',
      'Richmond County',
      'Riverside',
      'Roanoke',
      'Rochester',
      'Rockford',
      'Roseville',
      'Round Lake Beach',
      'Sacramento',
      'Saginaw',
      'Saint Louis',
      'Saint Paul',
      'Saint Petersburg',
      'Salem',
      'Salinas',
      'Salt Lake City',
      'San Antonio',
      'San Bernardino',
      'San Buenaventura',
      'San Diego',
      'San Francisco',
      'San Jose',
      'Santa Ana',
      'Santa Barbara',
      'Santa Clara',
      'Santa Clarita',
      'Santa Cruz',
      'Santa Maria',
      'Santa Rosa',
      'Sarasota',
      'Savannah',
      'Scottsdale',
      'Scranton',
      'Seaside',
      'Seattle',
      'Sebastian',
      'Shreveport',
      'Simi Valley',
      'Sioux City',
      'Sioux Falls',
      'South Bend',
      'South Lyon',
      'Spartanburg',
      'Spokane',
      'Springdale',
      'Springfield',
      'St. Louis',
      'St. Paul',
      'St. Petersburg',
      'Stamford',
      'Sterling Heights',
      'Stockton',
      'Sunnyvale',
      'Syracuse',
      'Tacoma',
      'Tallahassee',
      'Tampa',
      'Temecula',
      'Tempe',
      'Thornton',
      'Thousand Oaks',
      'Toledo',
      'Topeka',
      'Torrance',
      'Trenton',
      'Tucson',
      'Tulsa',
      'Tuscaloosa',
      'Tyler',
      'Utica',
      'Vallejo',
      'Vancouver',
      'Vero Beach',
      'Victorville',
      'Virginia Beach',
      'Visalia',
      'Waco',
      'Warren',
      'Washington',
      'Waterbury',
      'Waterloo',
      'West Covina',
      'West Valley City',
      'Westminster',
      'Wichita',
      'Wilmington',
      'Winston',
      'Winter Haven',
      'Worcester',
      'Yakima',
      'Yonkers',
      'York',
      'Youngstown'
    ]
  }
}

UTILITIES.checkBoxInit = function (el) {
  var val = el.val()

  if (val === 'on') {
    el.prop('checked', true)
  } else {
    el.prop('checked', false)
  }
}

UTILITIES.setCheckBoxValue = function (el) {
  var val = el.val()

  if (val === 'on') {
    el.prop('checked', false).val('off')
  } else {
    el.prop('checked', true).val('on')
  }
}

UTILITIES.communityEventsCities = function (usage) {
  var items = UTILITIES.CONST.cities.sort()
  var i
  var len = items.length
  var markup = (usage === 'POST') ? '<option value="" selected>Select City</option>' : ''

  for (i = 0; i < len; i++) {
    markup += '<option>' + items[i] + '</option>'
  }
  return markup
}

UTILITIES.resources = {
  CONFIG: {},
  emptyFcn: function () { },
  loadScript: function (url, callback) {
    var head = document.getElementsByTagName(this.CONFIG.scriptPlacement)[0]
    var script = document.createElement('script')
    script.type = 'text/javascript'
    script.src = url

    // bind event to the callback
    script.onreadystatechange = callback
    script.onload = callback

    // fire the loading
    head.appendChild(script)
  },
  _handleConsent: function (att) {
    var timeout
    var attempts = att || 0
    var LIMIT = 10
    var myConsent
    var isRequired
    var me = this

    // console.log(attempts)
    if (attempts < LIMIT) {
      if (window.siteConsent) {
        myConsent = siteConsent.getConsent() // check for consent
        isRequired = siteConsent.isConsentRequired // is consent required

        if (isRequired === false || myConsent[this.CONFIG.category] === true) {
          this.loadScript(
            this.CONFIG.resourceURL,
            this.CONFIG.hasConsentCallback
          )
        } else {
          return this.CONFIG.noConsentCallback()
        }
      } else {
        attempts = attempts + 1
        timeout = setTimeout(function () {
          clearTimeout(timeout)
          return me._handleConsent(attempts)
        }, 1000)
      }
    }
  },
  requiresConsent: function (confObj) {
    // loads a resource only if consent given
    // see myConfig below
    // resource URL required, the url of the resource
    // two optional callbacks: hasConsentCallback and or noConsentCallback
    // required category: 'Analytics', 'SocialMedia' or 'Advertising'
    // scriptPlacement: optional head or some other location such as body
    var myConfig = {
      resourceURL: confObj.resourceURL || undefined,
      category: confObj.category || undefined,
      hasConsentCallback: confObj.hasConsentCallback || this.emptyFcn,
      noConsentCallback: confObj.noConsentCallback || this.emptyFcn,
      scriptPlacement: confObj.scriptPlacement || 'head'
    }
    this.CONFIG = myConfig
    this._handleConsent()
  }
}

UTILITIES.communityEventsStates = function (usage) {
  var items = UTILITIES.CONST.states.sort()
  var i
  var len = items.length
  var markup = (usage === 'POST') ? '<option value="" selected>Select State</option>' : ''

  for (i = 0; i < len; i++) {
    markup += '<option>' + items[i] + '</option>'
  }
  return markup
}

UTILITIES.communityEventsCountries = function (usage) {
  var items = UTILITIES.CONST.countries.sort()
  var i
  var len = items.length
  var markup = ''

  for (i = 0; i < len; i++) {
    if (usage === 'POST' && items[i] === 'United States') {
      markup += '<option selected>' + items[i] + '</option>'
    } else {
      markup += '<option>' + items[i] + '</option>'
    }
  }
  return markup
}

UTILITIES.timeZonePicker = function (post) {
  var ret = UTILITIES.CONST.timeZoneNames

  if (post === 'POST') {
    ret = '<option title="select time zone" selected="selected" value="">Select timezone</option>' + ret
  }
  return ret
}

UTILITIES.productPicker = function (productsCollection) {
  var availProducts = []
  var usedProducts = []
  var MAX_PRODUCTS = 5
  var usedProductsStr = $('#session_product').val()
  var makeSelect = function (arr) {
    var sorted = arr.sort()
    var markup = '<select class="items-dropdown" aria-label="Select product">'
    var i
    var len = sorted.length

    markup += '<option>Select Product</option>'
    for (i = 0; i < len; i++) {
      markup += '<option value="' + sorted[i] + '">' + sorted[i] + '</option>'
    }

    markup += '</select>'
    return markup
  }
  var i
  var len = usedProducts.length
  var tag = '<div class="tag"><span class="val">{0}</span> <span class="remove">&times;</span></div>'
  var tagsMarkup = ''

  availProducts = productsCollection || []

  if (usedProductsStr !== '') {
    usedProducts = usedProductsStr.split(',')
    len = usedProducts.length
  }

  if (len > 0) {
    // make tags from usedProducts
    for (i = 0; i < len; i++) {
      tagsMarkup += tag.replace('{0}', usedProducts[i])
      availProducts.splice($.inArray(usedProducts[i], availProducts), 1)
    }
    $('.product-picker').append(tagsMarkup)
    $('.product-picker').append(makeSelect(availProducts))
  } else {
    $('.product-picker').append(makeSelect(availProducts))
  }

  if (usedProducts.length >= MAX_PRODUCTS) {
    $('.product-picker select').prop('disabled', true)
  }

  $('.product-picker').change(function (e) {
    var myVal
    if (e.target.tagName === 'SELECT') {
      myVal = $(e.target).val()
      usedProducts.push(myVal)
      availProducts.splice($.inArray(myVal, availProducts), 1)
      $(e.target).remove()
      $('.product-picker').append(tag.replace('{0}', myVal))
      $('#session_product').val(usedProducts.join(','))

      if (availProducts.length > 0) {
        $('.product-picker').append(makeSelect(availProducts))
      }

      if (usedProducts.length >= MAX_PRODUCTS) {
        $('.product-picker select').prop('disabled', true)
      }
    }
  })

  $('.product-picker').click(function (e) {
    var myVal

    if ($(e.target).hasClass('remove')) {
      myVal = $(e.target).parent().find('.val').text()
      $(e.target).parent().remove()
      availProducts.push(myVal)
      $('.product-picker').trigger('product-picker:item-removed', myVal)
      $('.product-picker select').remove()

      if (availProducts.length > 0) {
        $('.product-picker').append(makeSelect(availProducts))
      }
      usedProducts.splice($.inArray(myVal, usedProducts), 1)
      $('#session_product').val(usedProducts.join(','))

      if (usedProducts.length < MAX_PRODUCTS) {
        $('.product-picker select').prop('disabled', false)
      }
    }
  })
}

UTILITIES.autoMatchComplete = function (idref, arr, btnId) {
  var inp = document.getElementById(idref);
  var currentFocus;
  inp.addEventListener("input", function (e) {
    var a, b, i, val = this.value;
    closeAllLists();
    if (!val) { return false; }
    currentFocus = -1;
    a = document.createElement("DIV");
    a.setAttribute("id", this.id + "autocomplete-list");
    a.setAttribute("class", "autocomplete-items");
    this.parentNode.appendChild(a);
    for (i = 0; i < arr.length; i++) {
      if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
        b = document.createElement("DIV");
        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
        b.innerHTML += arr[i].substr(val.length);
        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
        b.addEventListener("click", function (e) {
          inp.value = this.getElementsByTagName("input")[0].value;
          document.getElementById(btnId).click();
          closeAllLists();
        });
        a.appendChild(b);
      }
    }
  });
  inp.addEventListener("keydown", function (e) {
    var x = document.getElementById(this.id + "autocomplete-list");
    if (x) x = x.getElementsByTagName("div");
    if (e.keyCode == 40) {
      currentFocus++;
      addActive(x);
    } else if (e.keyCode == 38) { //up
      currentFocus--;
      addActive(x);
    } else if (e.keyCode == 13) {
      e.preventDefault();
      if (currentFocus > -1) {
        if (x) x[currentFocus].click();
      }
    } else if (e.keyCode == 27) {
      closeAllLists();
    }
  });
  function addActive(x) {
    if (!x) return false;
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  document.addEventListener("click", function (e) {
    closeAllLists(e.target);
  });
}

UTILITIES.topicPicker = function (topicCollection) {
  var availTopics = []
  var usedTopics = []
  var MAX_TOPICS = 5
  var usedTopicsStr = $('#videohub_topic').val()
  var makeList = function () {
    var markup = '<div class="videohub_topic_row videhub_tag_row"><input id="videohub_blog_topic_list" class="videohub_blog_topic_list videhub_list_field" aria-label="Find topic" autocomplete="off">'
    markup += '<button type="buttton" class="btn lia-button lia-button-primary" id="video_hub_topic_btn">Add Topic</button>'
    markup += '</div>'
    return markup
  }
  var i
  var len = usedTopics.length
  var topicTag = '<div class="tag"><span class="val">{0}</span> <span class="remove">&times;</span></div>'
  var topicTagsMarkup = ''

  topicCollectionArry = topicCollection.split(",");
  availTopics = topicCollectionArry || []

  if (usedTopicsStr !== '') {
    usedTopics = usedTopicsStr.split(',')
    len = usedTopics.length
  }
  $('.topic-picker').append(makeList())
  UTILITIES.autoMatchComplete("videohub_blog_topic_list", availTopics, 'video_hub_topic_btn')
  if (len > 0) {
    for (i = 0; i < len; i++) {
      topicTagsMarkup += topicTag.replace('{0}', usedTopics[i])
    }
    $('.topic-picker').prepend(topicTagsMarkup)
  }

  if (usedTopics.length >= MAX_TOPICS) {
    $('.topic-picker input').prop('disabled', true)
    $('.topic-picker #video_hub_topic_btn').prop('disabled', true)
    $('.videohub_topic_row').prepend('<div class="hide-field"></div>')
  }

  $('.topic-picker input').on('keydown', function (e) {
    if (e.which == 13) {
      $('#video_hub_topic_btn').click();
    }
  });

  $('#video_hub_topic_btn').click(function () {
    var myTopicVal
    myTopicVal = $('.videohub_blog_topic_list').val()
    if (myTopicVal != '' && myTopicVal != undefined) {
      usedTopics.push(myTopicVal)
      availTopics.splice($.inArray(myTopicVal, availTopics), 1)
      $('.topic-picker').prepend(topicTag.replace('{0}', myTopicVal))
      $('#videohub_topic').val(usedTopics.join(','))
      $('.videohub_blog_topic_list').val('')
      if (usedTopics.length >= MAX_TOPICS) {
        $('.topic-picker input').prop('disabled', true)
        $('.topic-picker #video_hub_topic_btn').prop('disabled', true)
        $('.videohub_topic_row').prepend('<div class="hide-field"></div>')
      }
    }
  })

  $('.topic-picker').click(function (e) {
    var myTopicVal
    if ($(e.target).hasClass('remove')) {
      myTopicVal = $(e.target).parent().find('.val').text()
      $(e.target).parent().remove()
      availTopics.push(myTopicVal)
      usedTopics.splice($.inArray(myTopicVal, usedTopics), 1)
      $('#videohub_topic').val(usedTopics.join(','))

      if (usedTopics.length < MAX_TOPICS) {
        $('.topic-picker input').prop('disabled', false)
        $('.topic-picker #video_hub_topic_btn').prop('disabled', false)
        $('.videohub_topic_row .hide-field').remove();
      }
    }
  })
}

var globalSearchContent = [];

UTILITIES.autoCompleteWithAjax = function (idref, contentUrl, srchlength, collection, btnId, search) {
  var search = search || false;
  var arr = collection || [];
  var inp = document.getElementById(idref);
  var currentFocus, timer, waitTime = 500;
  var numberVal = true;
  inp.addEventListener("input", function (e) {
    var a, b, i, val = this.value;
    if (idref != 'videohub_blog_sme_list' && !Number.isInteger(val)) {
      numberVal = false;
      if (val.length < srchlength) { return false }
    }
    closeAllLists();
    currentFocus = -1;
    a = document.createElement("DIV");
    a.setAttribute("id", this.id + "autocomplete-list");
    a.setAttribute("class", "autocomplete-items");
    this.parentNode.appendChild(a);
    if (search) {
      clearTimeout(timer);
      timer = setTimeout(() => {
        ajaxcallForCommon(val, a);
      }, waitTime);
    } else {
      if (contentUrl != '') {
        clearTimeout(timer);
        timer = setTimeout(() => {
          ajaxcall(val, a);
        }, waitTime);
      } else {
        searchWithKey(val, a);
      }
    }

  });

  function ajaxcall(val, a) {
    $.ajax({
      url: contentUrl,
      type: 'post',
      data: { search: val, match: 'true' },
      dataType: 'json',
      success: function (response) {
        arr = [];
        for (var i = 0; i < response.length; i++) {
          if (response[i].hasOwnProperty('title')) {
            var duplCheck = arr.find(o => o.id == response[i].id)
            if (duplCheck == undefined) {
              arr.push(response[i]);
              globalSearchContent.push(response[i]);
            }
          }
        }
        commonSearchKey(val, a, false);
      }
    });
  }

  function ajaxcallForCommon(val, a) {
    $.ajax({
      url: contentUrl,
      type: 'post',
      data: { search: val },
      dataType: 'json',
      success: function (response) {
        arr = [];
        for (var i = 0; i < response.length; i++) {
          if (response[i].hasOwnProperty('title')) {
            var duplCheck = arr.find(o => o.id == response[i].id)
            if (duplCheck == undefined) {
              arr.push(response[i]);
            }
          }
        }
        commonSearchKey(val, a, true);
      }
    });
  }

  inp.addEventListener("keydown", function (e) {
    var x = document.getElementById(this.id + "autocomplete-list");
    var checkFocus = true;
    if (x) x = x.getElementsByTagName("div");
    if (e.keyCode == 40) {
      currentFocus++;
      addActive(x);
    } else if (e.keyCode == 38) { //up
      currentFocus--;
      addActive(x);
    } else if (e.keyCode == 13) {
      e.preventDefault();
      if (currentFocus > -1) {
        if (x) {
          x[currentFocus].click();
        } else if (search) {
          document.getElementById('videohub-search-btn').click();
        }
      } else if (search) {
        document.getElementById('videohub-search-btn').click();
      }
    } else if (e.keyCode == 27) {
      closeAllLists();
    }
  });

  function addActive(x) {
    if (!x) return false;
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  document.addEventListener("click", function (e) {
    closeAllLists(e.target);
  });

  function searchWithKey(val, a) {
    var searchKey;
    for (i = 0; i < arr.length; i++) {
      if (numberVal) {
        searchKey = arr[i].id;
      } else {
        searchKey = arr[i].title;
      }
      if (searchKey.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
        b = document.createElement("DIV");
        b.innerHTML = "<strong>" + arr[i].title.substr(0, val.length) + "</strong>";
        b.innerHTML += arr[i].title.substr(val.length);
        b.innerHTML += "<input type='hidden' value='" + arr[i].title + "'>";
        b.addEventListener("click", function (e) {
          inp.value = this.getElementsByTagName("input")[0].value;
          closeAllLists();
          document.getElementById(btnId).click();
        });
        a.appendChild(b);
      }
    }
  }
  function commonSearchKey(val, a, redirect) {
    for (i = 0; i < arr.length; i++) {
      b = document.createElement("DIV");
      b.innerHTML = "<strong>" + arr[i].title.substr(0, val.length) + "</strong>";
      b.innerHTML += arr[i].title.substr(val.length);
      if (redirect) {
        b.innerHTML += "<input type='hidden' value='" + arr[i].view_href + "' title='" + arr[i].title + "'>";
      } else {
        b.innerHTML += "<input type='hidden' value='" + arr[i].title + "'>";
      }
      b.addEventListener("click", function (e) {
        if (redirect) {
          inp.value = this.getElementsByTagName("input")[0].title;
          location.href = this.getElementsByTagName("input")[0].value;
          closeAllLists();
        } else {
          inp.value = this.getElementsByTagName("input")[0].value;
          closeAllLists();
          document.getElementById(btnId).click();
        }

      });
      a.appendChild(b);
    }
  }
  if (search) {
    var srch_btn = document.getElementById('videohub-search-btn');
    srch_btn.addEventListener("click", function (e) {
      closeAllLists();
      location.href = '/t5/forums/searchpage/tab/message?filter=location&q=' + inp.value + '&location=blog-board:Video-Hub&collapse_discussion=true';
    });


  }

}

UTILITIES.basePicker = function (collection, fieldId, fieldName, btnLabel, pickerClass, max, contentUrl, srchlength) {
  var collection = collection || []
  var usedContent = []
  var usedIDs = []
  var MAX_CONTENT = max
  var usedStr = $('#' + fieldId).val()
  var makeList = function () {
    var markup = '<div class="videohub_' + fieldName + '_row videhub_tag_row"><input id="videohub_blog_' + fieldName + '_list" class="videohub_blog_' + fieldName + '_list videhub_list_field w-100" aria-label="Find ' + fieldName + '" autocomplete="off">'
    markup += '<button type="buttton" class="btn lia-button lia-button-primary lia-base-btn" id="video_hub_' + fieldName + '_btn">' + btnLabel + '</button>'
    markup += '</div>'
    return markup
  }
  var i
  var len = usedContent.length
  var tag = '<div class="tag"><span class="val">{0}</span> <span class="remove">&times;</span></div>'
  var tagsMarkup = ''
  if (usedStr !== '') {
    usedIDs = usedStr.split(',')
    len = usedIDs.length
  }
  $('.' + pickerClass).append(makeList())
  UTILITIES.autoCompleteWithAjax('videohub_blog_' + fieldName + '_list', contentUrl, srchlength, collection, 'video_hub_' + fieldName + '_btn', false)
  if (len > 0) {
    if (collection.length > 0) {
      for (i = 0; i < len; i++) {
        var tmpObj = collection.find(o => o.id == usedIDs[i])
        if (tmpObj != undefined) {
          tagsMarkup += tag.replace('{0}', tmpObj.title)
          usedContent.push(tmpObj.title);
        }
      }
      $('.' + pickerClass).prepend(tagsMarkup)
      $('#' + fieldId).attr('data-title', usedContent.join(','));
      chcekUsedContent();
    } else {
      if (contentUrl != '') {
        pickerAjaxCallForTitle(contentUrl, usedStr)
      }
    }
  }


  function chcekUsedContent() {
    if (usedContent.length >= MAX_CONTENT) {
      $('.' + pickerClass + ' input').prop('disabled', true)
      $('.' + pickerClass + ' #video_hub_' + fieldName + '_btn').prop('disabled', true)
      $('.videohub_' + fieldName + '_row').prepend('<div class="hide-field"></div>')
    }
  }


  $('.' + pickerClass + ' input').on('keydown', function (e) {
    if (e.which == 13) {
      $('#video_hub_' + fieldName + '_btn').click();
    }
  });

  function pickerAjaxCallForTitle(contentUrl, usedStr) {
    $.ajax({
      url: contentUrl,
      type: 'post',
      data: { search: usedStr, match: 'false' },
      dataType: 'json',
      success: function (response) {
        for (var i = 0; i < response.length; i++) {
          if (response[i].hasOwnProperty('title')) {
            collection.push(response[i]);
          }
        }
        for (i = 0; i < len; i++) {
          var tmpObj = collection.find(o => o.id == usedIDs[i])
          if (tmpObj != undefined) {
            tagsMarkup += tag.replace('{0}', tmpObj.title)
            usedContent.push(tmpObj.title);
          }
        }
        $('.' + pickerClass).prepend(tagsMarkup)
        $('#' + fieldId).attr('data-title', usedContent.join(','));
        chcekUsedContent();
      }
    });
  }

  $('#video_hub_' + fieldName + '_btn').click(function () {
    var myVal
    var errorState = 1
    myVal = $('.videohub_blog_' + fieldName + '_list').val()
    if (myVal != '' && myVal != undefined) {
      if (contentUrl != '') {
        for (var i = 0; i < globalSearchContent.length; i++) {
          if (globalSearchContent[i].hasOwnProperty('title')) {
            collection.push(globalSearchContent[i]);
          }
        }
      }
      var tmpObjRef = collection.find(o => o.title == myVal)
      if (tmpObjRef != undefined) {
        errorState = 0
        $('#error-label-videohub-' + fieldName + '-id').addClass('hidden');
        usedContent.push(myVal)
        $('.' + pickerClass).prepend(tag.replace('{0}', myVal))
        usedIDs.push(tmpObjRef.id);
        $('#' + fieldId).val(usedIDs.join(','))
        $('#' + fieldId).attr('data-title', usedContent.join(','));
      }
      if (errorState) {
        $('#error-label-videohub-' + fieldName + '-id').removeClass('hidden');
      } else {
        $('.videohub_blog_' + fieldName + '_list').val('')
      }
      chcekUsedContent();
    }
  });

  $('.' + pickerClass).click(function (e) {
    var myVal
    if ($(e.target).hasClass('remove')) {
      myVal = $(e.target).parent().find('.val').text()
      $(e.target).parent().remove()
      usedContent.splice($.inArray(myVal, usedContent), 1)
      var tmpObjRef = collection.find(o => o.title == myVal)
      if (tmpObjRef != undefined) {
        usedIDs.splice($.inArray(tmpObjRef.id, usedIDs), 1);
      }
      $('#' + fieldId).val(usedIDs.join(','))
      $('#' + fieldId).attr('data-title', usedContent.join(','));
      if (usedContent.length < MAX_CONTENT) {
        $('.' + pickerClass + ' input').prop('disabled', false)
        $('.' + pickerClass + ' #video_hub_' + fieldName + '_btn').prop('disabled', false)
        $('.videohub_' + fieldName + '_row .hide-field').remove();
      }
    }
  })

}

UTILITIES.speakerPicker = {
  allSpeakers: [],
  availSpeakers: [],
  usedSpeakers: [],
  buildNamesFromID: function () {
    var usedStr = $('#event_speakers').val()
    var usedArr = usedStr !== '' ? usedStr.split(',') : []
    var usedItem
    var usedNamesArr = []
    var map = {}
    var speakers = this.allSpeakers
    var len = speakers.length
    var i
    var n

    if (usedArr.length > 0) {
      for (i = 0; i < usedArr.length; i++) {
        usedItem = usedArr[i]
        for (n = 0; n < len; n++) {
          if (usedItem === speakers[n].id) {
            map[usedItem] = speakers[n].name
            break
          }
        }
      }

      for (i = 0; i < usedArr.length; i++) {
        usedItem = usedArr[i]
        usedNamesArr.push(map[usedItem])
      }

      $('#event_speakers_names').val(usedNamesArr.join(','))
      this.processRecords()
    }
  },
  processRecords: function () {
    var i
    var n
    var speakers = JSON.parse(JSON.stringify(this.allSpeakers))
    var temp = JSON.parse(JSON.stringify(speakers))
    var item
    var usedStr = $('#event_speakers').val()
    var usedStrArr = usedStr !== '' ? usedStr.split(',') : []

    this.usedSpeakers = []
    this.availSpeakers = []

    if (usedStrArr.length > 0) {
      for (i = 0; i < usedStrArr.length; i++) {
        item = usedStrArr[i]
        for (n = 0; n < speakers.length; n++) {
          if (item === speakers[n].id) {
            this.usedSpeakers.push(speakers[n])
            break
          }
        }
      }

      for (n = 0; n < this.usedSpeakers.length; n++) {
        for (i = temp.length - 1; i >= 0; i--) {
          if (this.usedSpeakers[n].id === temp[i].id) {
            temp.splice(i, 1)
          }
        }
      }
      this.availSpeakers = temp
    } else {
      this.availSpeakers = this.allSpeakers.slice(0)
    }

    this.renderTags()
  },
  removeRecord: function (speakerName, speakerId) {
    var eventSpeakersIdStr = $('#event_speakers').val()
    var eventSpeakersNameStr = $('#event_speakers_names').val()
    var _removeItems = function (valStr, itemStr) {
      var i
      var arr = valStr !== '' ? valStr.split(',') : []
      var len = arr.length

      for (i = len - 1; i >= 0; i--) {
        if (itemStr === arr[i]) {
          arr.splice(i, 1)
        }
      }
      return arr.join(',')
    }

    $('#event_speakers').val(_removeItems(eventSpeakersIdStr, speakerId))
    $('#event_speakers_names').val(_removeItems(eventSpeakersNameStr, speakerName))

    this.processRecords()
  },
  updateRecords: function (speakerName, speakerId) {
    // adds the item to the list and perhaps call processRecords
    var eventSpeakersIdStr = $('#event_speakers').val()
    var eventSpeakersNameStr = $('#event_speakers_names').val()
    var _addItems = function (valStr, itemStr) {
      var arr = valStr !== '' ? valStr.split(',') : []
      arr.push(itemStr)
      return arr.join(',')
    }
    $('#event_speakers').val(_addItems(eventSpeakersIdStr, speakerId))
    $('#event_speakers_names').val(_addItems(eventSpeakersNameStr, speakerName))

    this.processRecords()
  },
  renderTags: function () {
    var records = this.usedSpeakers
    var len = records.length
    var tag = '<div class="tag"><span data-id="{0}" class="val">{1}</span> <span class="remove">&times;</span></div>'
    var tagsMarkup = ''
    var i

    for (i = 0; i < len; i++) {
      tagsMarkup += tag.replace('{0}', records[i].id).replace('{1}', records[i].name)
    }
    $('.speaker-tags').html(tagsMarkup)
  }
}

UTILITIES.getTimeZoneAbbrevFromId = function () {
  var items = $('.timezone')
  var len = $('.timezone').length
  var i
  var zoneId
  var abbrev
  var start
  var template = '({0})'

  for (i = 0; i < len; i++) {
    zoneId = $(items[i]).attr('data')
    start = $(items[i]).attr('data-time')

    abbrev = moment.tz(parseInt(start), zoneId).format('z')
    $(items[i]).html(template.replace('{0}', abbrev))
  }
}

UTILITIES.timePicker = function () {
  var processUnits = function (unit, val, am) {
    var ret = false
    if (unit === 'hours') {
      if (am === 'true' && val === 12) {
        val = 0
      } else if (am === 'true' && val < 10) {
        val = '0' + val
      } else if (am === 'false' && val < 12) {
        val += 12
      }
      ret = val
    } else if (unit === 'minutes') {
      if (val === 0) {
        ret = '00'
      } else {
        ret = val
      }
    }

    return ret
  }
  var setDateTimeControlValues = function () {
    // set values of the controls

    // timezone name
    var zone = $('#value_event_timezone').val()

    // get start and end values
    var epochStart = parseInt($('#event_start').val())
    var epochEnd = parseInt($('#event_end').val())

    // new moment obj using above values timezone
    var dStart = moment.tz(epochStart, zone)
    var startMinutesValue = parseInt(moment(dStart).format('mm'))
    var startHoursValue = parseInt(moment(dStart).format('HH'))
    var startDateValue = moment(dStart).format('MM/DD/YYYY')

    var dEnd = moment.tz(epochEnd, zone)
    var endMinutesValue = parseInt(moment(dEnd).format('mm'))
    var endHoursValue = parseInt(moment(dEnd).format('HH'))
    var endDateValue = moment(dEnd).format('MM/DD/YYYY')
    var startAm = 'false'
    var endAm = 'false'

    // log out complete format of these moments with tz
    // console.log('set date time control values')
    // console.log('event start: ' + dStart.format())
    // console.log('event end: ' + dEnd.format())

    if (startHoursValue < 12) {
      if (startHoursValue === 0) {
        startHoursValue = 12
      }
      startAm = 'true'
    } else if (startHoursValue > 12) {
      startHoursValue -= 12
    }

    if (endHoursValue < 12) {
      if (endHoursValue === 0) {
        endHoursValue = 12
      }
      endAm = 'true'
    } else if (endHoursValue > 12) {
      endHoursValue -= 12
    }

    if (startMinutesValue === 0) {
      startMinutesValue = '00'
    }

    if (endMinutesValue === 0) {
      endMinutesValue = '00'
    }

    $('.timepicker-event-start .timepicker_hours').val(startHoursValue)
    $('.timepicker-event-start .timepicker_minutes').val(startMinutesValue)
    $('#timepickerDate').val(startDateValue)
    $('.timepicker-event-start .timepicker_am').val(startAm)

    $('.timepicker-event-end .timepicker_hours').val(endHoursValue)
    $('.timepicker-event-end .timepicker_minutes').val(endMinutesValue)
    $('.timepicker_date.timepicker_end').val(endDateValue)
    $('.timepicker-event-end .timepicker_am').val(endAm)
  }

  var getDateTimeValues = function () {
    // get values from controls
    var zone = $('#event_timezone').val()
    // 06/28/2018 Expecting this format
    var startDay = $('#timepickerDate').val().split('/')
    var endDay = $('#timepickerDateEnd').val().split('/')
    var startHours = parseInt($('.timepicker-event-start .timepicker_hours').val())
    var startMinutes = parseInt($('.timepicker-event-start .timepicker_minutes').val())
    var startAm = $('.timepicker-event-start .timepicker_am').val()
    var endHours = parseInt($('.timepicker-event-end .timepicker_hours').val())
    var endMinutes = parseInt($('.timepicker-event-end .timepicker_minutes').val())
    var endAm = $('.timepicker-event-end .timepicker_am').val()
    var startMoment = {}
    var endMoment = {}
    // build time string 2013-11-18 11:55
    var startTimeTemplateStr = '{0}-{1}-{2} {3}:{4}'
    var endTimeTemplateStr = '{0}-{1}-{2} {3}:{4}'

    startTimeTemplateStr = startTimeTemplateStr.replace('{0}', startDay[2]) // year
    startTimeTemplateStr = startTimeTemplateStr.replace('{1}', startDay[0]) // month
    startTimeTemplateStr = startTimeTemplateStr.replace('{2}', startDay[1]) // day
    startTimeTemplateStr = startTimeTemplateStr.replace('{3}', processUnits('hours', startHours, startAm)) // hours
    startTimeTemplateStr = startTimeTemplateStr.replace('{4}', processUnits('minutes', startMinutes)) // minutes

    endTimeTemplateStr = endTimeTemplateStr.replace('{0}', endDay[2]) // year
    endTimeTemplateStr = endTimeTemplateStr.replace('{1}', endDay[0]) // month
    endTimeTemplateStr = endTimeTemplateStr.replace('{2}', endDay[1]) // day
    endTimeTemplateStr = endTimeTemplateStr.replace('{3}', processUnits('hours', endHours, endAm)) // hours
    endTimeTemplateStr = endTimeTemplateStr.replace('{4}', processUnits('minutes', endMinutes)) // minutes

    // ready to make moment obj
    startMoment = moment.tz(startTimeTemplateStr, zone)
    endMoment = moment.tz(endTimeTemplateStr, zone)

    // console.log('getDateTimeValues called')
    // console.log('startTime: ' + startMoment.format('YYYY-MM-DD-HH:mm z'))
    // console.log('endTime: ' + endMoment.format('YYYY-MM-DD-HH:mm z'))
    // console.log('unixStart: ' + startMoment.format('x'))
    // console.log('unixEnd: ' + endMoment.format('x'))

    // write stamp to hidden fields
    $('#event_start').val(startMoment.format('x'))
    $('#event_end').val(endMoment.format('x'))
  }

  if ($('#event_start').val() !== '') {
    // init
    setDateTimeControlValues()
  }

  $('#timepickerDate, #timepickerDateEnd').change(function () {
    if ($('body').hasClass('mtc-event-add')) {
      $('.timepicker_end').val($(this).val())
    }

    if ($(this).val() !== '') {
      $('.timepicker select').removeAttr('disabled')
    } else {
      $('.timepicker select').prop('disabled', true)
    }
    getDateTimeValues()
  })

  $('#event_timezone').change(function () {
    getDateTimeValues()
  })

  $('.timepicker select').change(function () {
    getDateTimeValues()
  })
}

UTILITIES.accessibleAlert = function (alertEle, message, timeToShow) {
  // reusable and discoverable way to generate message alerts for screen readers
  // example: UTILITIES.accessibleAlert('#feedMessages', 'Loading more...')
  // example2: UTILITIES.accessibleAlert('pageLevel', 'Loading more...')

  if (alertEle === 'pageLevel') {
    // param: elertEle
    // value: 'pageLevel' exists top of page as part of primary nav
    // required
    // type: string
    $('#readerAssistMessages').html(message).addClass('live-region')
  } else {
    // custom selector perhaps part of component
    $(alertEle).html(message).addClass('live-region')
  }

  if (timeToShow) {
    // optional
    // param: timeToShow
    // type: number
    // visually display message
    $(alertEle).addClass('show-message')
    setTimeout(function () {
      $(alertEle).removeClass('show-message')
    }, timeToShow)
  }
}

UTILITIES.createKeyboardTrap = function (ele) {
  var collection = $(ele).find('a, button, select, textrea, input, [tabindex=0]').not(':disabled, [tabindex="-1"]')
  var len = collection.length
  var data = {}
  var i

  $(collection).off('keydown')

  for (i = 0; i < len; i++) {
    data = {
      value: i,
      isFirst: i === 0,
      isLast: i === len - 1,
      collection: collection
    }

    $(collection).eq(i).on('keydown', data, function (evt) {
      if (evt.data.isFirst) {
        if (evt.shiftKey && evt.keyCode === 9) {
          evt.preventDefault()
          $(evt.data.collection).last().focus()
        }
      } else if (evt.data.isLast) {
        if (!evt.shiftKey && evt.keyCode === 9) {
          evt.preventDefault()
          $(evt.data.collection).first().focus()
        }
      }
    })
  }
}

// Method for Category Page & Forum Page filters
UTILITIES.initForumCategoryFilters = function (paramMap) {
  paramMap.page = paramMap.page || ''
  paramMap.endpointUrlValue = paramMap.endpointUrlValue || ''
  paramMap.countUrlValue = paramMap.countUrlValue || ''
  paramMap.board = paramMap.board || ''
  paramMap.eventTab = paramMap.eventTab || ''
  paramMap.currentPage = paramMap.currentPage || ''
  paramMap.groupHub = paramMap.groupHub || ''
  paramMap.pageSize = paramMap.pageSize || ''
  paramMap.pageOffset = paramMap.pageOffset || ''
  paramMap.usr = paramMap.usr || ''
  paramMap.nodeId = paramMap.nodeId || ''
  paramMap.optional1 = paramMap.optional1 || ''
  paramMap.optional2 = paramMap.optional2 || ''
  paramMap.autoScrollBool = paramMap.autoScrollBool || false
  paramMap.filters = paramMap.filters || ['all']
  paramMap.autoRefresh = paramMap.autoRefresh || false
  paramMap.repliesNum = paramMap.repliesNum || 1
  paramMap.keepState = paramMap.keepState || false

  var totalCount = null

  var configMap = {
    newest: {
      totalCount: null
    },
    popular: {
      totalCount: null
    },
    experts: {
      totalCount: null
    },
    unanswered: {
      totalCount: null
    },
    unread: {
      totalCount: null
    },
    currentKey: 'newest',
    pageLoad: true,
    ttl: {
      val: null,
      isExpired: function () {
        var ret = true
        var d = new Date()
        var t = d.getTime()

        if (configMap.ttl.val !== null) {
          if (t < configMap.ttl.val) {
            ret = false
          }
        }

        return ret
      },
      setTTL: function () {
        var d = new Date()
        var t = d.getTime()

        configMap.ttl.val = t + 120000
      },
      clearTTL: function () {
        configMap.ttl.val = null
        configMap.newest.totalCount = null
        configMap.popular.totalCount = null
        configMap.experts.totalCount = null
        configMap.unanswered.totalCount = null
      }
    },
    itemKey: function () {
      var feedKeyTemplate = 'feed:' + paramMap.page + ':' + paramMap.nodeId
      return feedKeyTemplate
    },
    getItem: function (key) {
      var isExpired = function (expires) {
        var d = new Date()
        var t = d.getTime()
        return (t > expires)
      }
      var isAnonymous = !!$('body').hasClass('lia-user-status-anonymous')
      var ret = {}
      var myRecord = JSON.parse(window.localStorage.getItem(this.itemKey())) || {}

      if (myRecord.isAnonymous !== isAnonymous) {
        // content only used if stored state match user's auth state
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && isExpired(myRecord.ttl)) {
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && !isExpired(myRecord.ttl) && myRecord.isAnonymous === isAnonymous) {
        ret = myRecord
      }
      return ret
    },
    clearItem: function () {
      if (this.itemKey() !== null) {
        window.localStorage.removeItem(this.itemKey())
      }
    },
    setItem: function (key, content) {
      var myRecord = {
        ttl: this.ttl.val,
        content: $('.recent-conversations-messages-wrapper .message-list').html(),
        tab: this.currentKey,
        newest: this.newest.totalCount,
        popular: this.popular.totalCount,
        experts: this.experts.totalCount,
        unanswered: this.unanswered.totalCount,
        isAnonymous: !!$('body').hasClass('lia-user-status-anonymous')
      }

      // console.log('inside setItem fcn')
      // console.log(myRecord)

      window.localStorage.setItem(
        this.itemKey(),
        JSON.stringify(myRecord)
      )
    }
  }

  var showMoreConversations = function () {
    var existingContent = {}
    var calculateOffset = $('.message-list > ul > li.conversation-' + configMap.currentKey).length
    var myData = {
      board: paramMap.board,
      pageOffsetValue: calculateOffset,
      eventTab: paramMap.eventTab,
      filter: configMap.currentKey,
      categoryId: paramMap.nodeId,
      offset: calculateOffset, // used for category page only
      groupHub: paramMap.groupHub,
      replies: paramMap.repliesNum,
      pageSize: paramMap.pageSize
    }
    existingContent = configMap.getItem()
    // console.log(existingContent)

    // use localStorage:
    // when page is loading
    // when TTL valid

    // optional configuration for board level
    if (paramMap.hasOwnProperty('lingotek')) {
      myData.lmanual = paramMap.lingotek.lmanual
      myData.code = paramMap.lingotek.code
    }

    if ($('body').hasClass('lia-user-status-registered')) {
      // triggered on success of quick reply POST inside of addQuickReployInit for board
      // and category; update localStorage so page refresh would avoid stale data.

      $('.recent-conversations-messages-wrapper').off('recent-conversations-messages-wrapper:quickReply')
      $('.recent-conversations-messages-wrapper').on(
        'recent-conversations-messages-wrapper:quickReply',
        $.proxy(function () {
          // setItem updates the localStorage
          this.setItem()
          // clearItem would clear the feed and do fresh pull
          // this.clearItem()
          // console.log('update localStorage')
        }, configMap)
      )
    }

    if (existingContent.hasOwnProperty('content') && configMap.pageLoad === true && !configMap.ttl.val) {
      // only run once when page first load
      configMap.pageLoad = false

      // set the active tab
      configMap.currentKey = existingContent.tab

      $('.recent-conversations-messages-wrapper .message-list').html(existingContent.content)
      calculateOffset = $('.message-list > ul > li.conversation-' + configMap.currentKey).length
      $('.welcome-spinner').addClass('hidden')

      // set filter UI
      $('.home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $('.home-feed-filters li').each(function () {
        if ($(this).attr('data') === configMap.currentKey) {
          $(this).addClass('active-filter')
        } else {
          $(this).removeClass('active-filter')
        }
      })

      // Quick Reply Init
      if (paramMap.page === 'categoryPage') {
        UTILITIES.addQuickReplyInitForCat(paramMap)
      } else {
        UTILITIES.addQuickReplyInitForBoard(paramMap)
      }

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $('#showMoreButton').prop('disabled', false)
      }
    } else if (existingContent.hasOwnProperty('content') && configMap.currentKey !== existingContent.tab &&
      calculateOffset > 0 && !configMap.ttl.isExpired()) {
      // localStorage refreshed with current filter and records displaying
      $('.message-list > ul > li.conversation').hide()
      $('.message-list > ul > li.conversation-' + configMap.currentKey).show()
      $('.welcome-spinner').addClass('hidden')

      // Quick Reply Init
      if (paramMap.page === 'categoryPage') {
        UTILITIES.addQuickReplyInitForCat(paramMap)
      } else {
        UTILITIES.addQuickReplyInitForBoard(paramMap)
      }

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $('#showMoreButton').prop('disabled', false)
      }

      // save in localStorage
      configMap.setItem()
    } else {
      // make request for items
      configMap.pageLoad = false

      $.ajax({
        type: 'post',
        url: paramMap.endpointUrlValue,
        dataType: 'html',
        data: myData,
        context: $('.showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          if (calculateOffset > 0) {
            // loading more
            $('.welcome-spinner.spinner').first().parent().addClass('loadmore')
            $('.welcome-spinner.spinner').removeClass('hidden')
            UTILITIES.accessibleAlert('#feedMessages', 'Loading more')
          } else {
            $('.welcome-spinner.spinner').first().parent().removeClass('loadmore')
            $('.welcome-spinner.spinner').first().removeClass('hidden')

            if ($('.refresh-feed').hasClass('in-progress')) {
              // specific feed refresh action
              UTILITIES.accessibleAlert('#feedMessages', 'Feed refresh')
            } else {
              // loading feed
              UTILITIES.accessibleAlert('#feedMessages', 'Loading')
            }
          }
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $('.spinner').empty().remove()
        },
        success: function (data) {
          var checkExistingItems = function () {
            return $('.message-list > ul > li.conversation-' + configMap.currentKey).length
          }
          var itemToFocus = checkExistingItems()
          var template1 = 'No messages.'
          var template2 = 'Looks like you have not favorited any Conversation Spaces, ' +
            'which power this feed! Go to communities and favorite conversation spaces ' +
            'that interest you.'
          var markup

          $('div.no-messages-container.lia-text').remove()
          $('.welcome-spinner.spinner').addClass('hidden')
          $('.recent-conversations-messages-wrapper').removeClass('loadmore')
          $('.refresh-feed').removeClass('in-progress')
          UTILITIES.accessibleAlert('#feedMessages', 'Success')

          configMap.ttl.setTTL()

          // CATEGORY / Forum page custom TTL set
          if (data.indexOf('{{{0}}}') === -1) {
            $('.showMoreMessagesHere').removeClass()
            $('.message-list').first().append('<ul class="showMoreMessagesHere"></ul>')

            if (checkExistingItems() > 0) {
              $(this).html(data)
              $('.message-list > ul > li.conversation-' + configMap.currentKey).eq(itemToFocus).find('.message-subject-link').focus()
            } else {
              $(this).html(data)

              // empty message in controller
              if (configMap[configMap.currentKey].totalCount === 0) {
                UTILITIES.accessibleAlert('#feedMessages', template1)
              }
            }

            // set content in localStorage
            // console.log('set content in localStorage')
            configMap.setItem()

            $('#showMoreButton').prop('disabled', false)
          } else if ((data.indexOf('{{{0}}}') !== -1 && checkExistingItems() > 0)) {
            // no more items to show
            $('#showMoreButton').prop('disabled', true)
          } else {
            // messaging for no record
            markup = (configMap.currentKey === 'newest') ? template2 : template1

            $('.error-msg').removeClass('hidden').html(markup)
            $('#showMoreButton').prop('disabled', true)
            UTILITIES.accessibleAlert('#feedMessages', template1)

            configMap.ttl.clearTTL()
            $('.message-list').empty().html('<ul class="showMoreMessagesHere"></ul>')
          }

          // disable if total count is reached after records
          if ((checkExistingItems() >= totalCount)) {
            $('#showMoreButton').prop('disabled', true)
          } else {
            $('#showMoreButton').prop('disabled', false)
          }

          // $('.category-spinner.spinner').addClass('hidden');
          // $(this).removeClass('hidden');

          $('.message-list > ul > li.conversation').hide()
          $('.message-list > ul > li.conversation-' + configMap.currentKey).show()

          // Quick Reply Init
          if (paramMap.page === 'categoryPage') {
            UTILITIES.addQuickReplyInitForCat(paramMap)
          } else {
            UTILITIES.addQuickReplyInitForBoard(paramMap)
          }
        }
      })
    }
  }

  var prepFilters = function (filters) {
    // remove css class hidden from list of filters
    if (filters[0] === 'all') {
      // show all
      $('.home-feed-filters li, .home-feed-filters-select option').removeClass('hidden')
    } else {
      // selectively show and style
      $('.home-feed-filters').addClass('filters-flex-end')

      filters.forEach(function (val, index, arr) {
        var isLast = index === arr.length - 1
        $('.home-feed-filters li[data=' + val + ']').removeClass('hidden').addClass('available')
        $('.home-feed-filters-select option[value=' + val + ']').removeClass('hidden')

        if (isLast) {
          $('.home-feed-filters li[data=' + val + ']').removeClass('hidden').addClass('isLast')
        }
      })
    }
  }

  var autoRefresh = function (seconds) {
    var markup = '<label class="switch"><input aria-describedby="autoRefresh" ' +
      'class="autoRefresh" id="autoRefresh" data-int="{0}" name="autoRefresh" type="checkbox" checked>' +
      '<span class="slider round"></span></label><label for="autoRefresh">Auto-refresh</label>'
    var m = seconds * 1000
    var myTimer = function () {
      $('.refresh-feed').click()
    }

    if (!isNaN(m)) {
      $('.feed-user-settings').removeClass('hidden').html(markup.replace('{0}', m))
      UTILITIES.autoRefreshInt = setInterval(myTimer, m)

      $('.feed-user-settings input').change(function () {
        var checked = $(this).prop('checked')
        var interval = $(this).attr('data-int')
        var m = parseInt(interval)

        if (checked === true) {
          UTILITIES.autoRefreshInt = setInterval(myTimer, m)
        } else {
          clearInterval(UTILITIES.autoRefreshInt)
        }
      })
    }
  }

  var getTotalCount = function () {
    var myData = {
      usr: paramMap.usr,
      nodeId: paramMap.nodeId,
      categoryId: paramMap.nodeId,
      board: paramMap.board,
      groupHub: paramMap.groupHub,
      eventTab: paramMap.eventTab,
      filter: configMap.currentKey
    }
    var makeRequest = true
    var existingContent = configMap.getItem()

    // console.log('get totalCount fcn')
    // console.log(existingContent)

    if (existingContent.hasOwnProperty('content')) {
      if (existingContent[configMap.currentKey] !== null) {
        makeRequest = false
        showMoreConversations()
      }
    }

    if (makeRequest) {
      $.ajax({
        type: 'post',
        url: paramMap.countUrlValue,
        dataType: 'json',
        data: myData,
        context: $('.showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          $('.recent-conversations-messages-wrapper .spinner').removeClass('hidden')
          $('.error-msg').addClass('hidden').empty()
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $('.spinner').empty().remove()
          UTILITIES.accessibleAlert('#feedMessages', errorMarkup)
        },
        success: function (data) {
          totalCount = data.count
          configMap[configMap.currentKey].totalCount = data.count
          configMap.ttl.setTTL()
          showMoreConversations()
        }
      })
    }
  }

  var purgeDuplicatesUnread = function () {
    var collection = $('.conversation')
    var len = collection.length
    var i
    var dataId
    var uniques = []
    var emptyMessage = 'No messages.'

    $(collection).hide()

    for (i = 0; i < len; i++) {
      dataId = $(collection[i]).find('.post.unread').attr('data-id')
      if (dataId && $.inArray(dataId, uniques) === -1) {
        $(collection[i]).show()
        uniques.push(dataId)
      }
    }

    if (uniques.length === 0) {
      $('.error-msg').html(emptyMessage).removeClass('hidden')
      UTILITIES.accessibleAlert('#feedMessages', emptyMessage)
    } else {
      $('.error-msg').empty().addClass('hidden')
    }
  }

  $('.refresh-feed').on('click keypress', function (e) {
    if (e.type === 'click' || e.keyCode === 13) {
      if (!paramMap.keepState) {
        configMap.clearItem()
        configMap.ttl.clearTTL()
        configMap.currentKey = 'newest'
      }

      if (e.hasOwnProperty('originalEvent')) {
        // explicit user action taken is different
        // than programmatic click for screen readers
        $(this).addClass('in-progress')
      }

      // flush container and start over
      $('.recent-conversations-messages-wrapper .message-list').html('<ul class="showMoreMessagesHere"></ul>')
      $('.home-feed-filters li[data=' + configMap.currentKey + ']').click()
    }
  })

  $('.home-feed-filters-select').on('change', function (e) {
    var key = $(this).val()
    var currentFiltersItemsLength = $('.message-list > ul > li.conversation-' + key).length
    var checkExistingItems = function () {
      return $('.message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    configMap.currentKey = key

    $('.home-feed-filters li').each(function () {
      if ($(this).attr('data') === configMap.currentKey) {
        $(this).addClass('active-filter')
      } else {
        $(this).removeClass('active-filter')
      }
    })

    // $('.filter-tag').removeClass('active-filter')
    // $(this).addClass('active-filter')
    $('.message-list > ul > li.conversation').hide()
    $('.error-msg').text('').addClass('hidden')
    $('.no-messages-container.lia-text').remove()
    if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
      configMap.ttl.setTTL()
    }

    if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
      // flush container and start over
      $('.message-list').html('<ul class="showMoreMessagesHere"></ul>')
      getTotalCount()
    } else {
      if (configMap.currentKey === 'unread') {
        purgeDuplicatesUnread()
        $('#showMoreButton').prop('disabled', true)
      } else if (configMap[configMap.currentKey].totalCount) {
        if (currentFiltersItemsLength === 0) {
          showMoreConversations()
        } else {
          $('.message-list > ul > li.conversation').hide()
          $('.message-list > ul > li.conversation-' + key).show()

          // set content in localStorage
          configMap.setItem()

          if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
            $('#showMoreButton').prop('disabled', true)
          } else {
            $('#showMoreButton').prop('disabled', false)
          }
        }
      } else {
        getTotalCount()
      }
    }
  })

  $('.home-feed-filters li').on('keydown click', function (e) {
    var key
    var currentFiltersItemsLength
    var checkExistingItems = function () {
      return $('.message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    if (e.keyCode === 13 || e.type === 'click') {
      key = $(this).attr('data')
      currentFiltersItemsLength = $('.message-list > ul > li.conversation-' + key).length
      configMap.currentKey = key

      $('.home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $('.filter-tag').removeClass('active-filter')
      $(this).addClass('active-filter')
      $('.message-list > ul > li.conversation').hide()
      $('.error-msg').text('').addClass('hidden')
      $('div.no-messages-container.lia-text').remove()

      if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
        configMap.ttl.setTTL()
      }

      if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
        // flush container and start over
        $('.message-list').html('<ul class="showMoreMessagesHere"></ul>')
        getTotalCount()
      } else {
        if (configMap.currentKey === 'unread') {
          purgeDuplicatesUnread()
          $('#showMoreButton').prop('disabled', true)
        } else if (configMap[configMap.currentKey].totalCount) {
          if (currentFiltersItemsLength === 0) {
            showMoreConversations()
          } else {
            $('.message-list > ul > li.conversation').hide()
            $('.message-list > ul > li.conversation-' + key).show()

            // set content in localStorage
            configMap.setItem()

            if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
              $('#showMoreButton').prop('disabled', true)
            } else {
              $('#showMoreButton').prop('disabled', false)
            }
          }
        } else {
          getTotalCount()
        }
      }
    }
  })

  // different css class used at category and forum topic
  $('.category-recent-conversations-component, .forum-page-custom-message-list-wrapper').on('click', function (e) {
    var offset = $('.recent-conversations-messages-wrapper .message-list > ul > li').length
    var self

    if (e.target.id === 'showMoreButton') {
      e.target.blur()

      if (offset > 0) {
        // show only loader on the button
        $('.recent-conversations-messages-wrapper').addClass('loadmore')
      }

      if (configMap[configMap.currentKey].totalCount) {
        showMoreConversations()
      } else {
        getTotalCount()
      }
    } else if ($(e.target).hasClass('kudo-add-button') || $(e.target).hasClass('kudo-remove-button')) {
      self = configMap
      setTimeout(function () {
        self.setItem()
      }, 500)
    }
  }).find('#showMoreButton').click()

  if (paramMap.autoScrollBool) {
    $(window).scroll(function () {
      if ($(window).scrollTop() >= $('#showMoreButton').position().top &&
        $('.showMoreContainer .welcome-spinner').hasClass('hidden') &&
        $(window).width() > 991 &&
        !$('#showMoreButton').prop('disabled')) {
        $('#showMoreButton').click()
      }
    })
  }

  // init filters
  prepFilters(paramMap.filters)

  // auto refresh
  if (paramMap.autoRefresh) {
    autoRefresh(paramMap.autoRefresh)
  }

  $(window).on('beforeunload', function () {
    $('body').addClass('leaving-page')
  })
} // End of initCatForumFilters method

// Method for Home Page filters
UTILITIES.initHomeFilters = function (paramMap) {
  paramMap.page = paramMap.page || ''
  paramMap.endpointUrlValue = paramMap.endpointUrlValue || ''
  paramMap.countUrlValue = paramMap.countUrlValue || ''
  paramMap.board = paramMap.board || ''
  paramMap.eventTab = paramMap.eventTab || ''
  paramMap.currentPage = paramMap.currentPage || ''
  paramMap.pageSize = paramMap.pageSize || '5'
  paramMap.pageOffset = paramMap.pageOffset || ''
  paramMap.usr = paramMap.usr || ''
  paramMap.nodeId = paramMap.nodeId || ''
  paramMap.optional1 = paramMap.optional1 || ''
  paramMap.optional2 = paramMap.optional2 || ''
  paramMap.autoScrollBool = paramMap.autoScrollBool || false
  paramMap.repliesNum = paramMap.repliesNum || 1

  var totalCount = null

  var configMap = {
    newest: {
      totalCount: null
    },
    popular: {
      totalCount: null
    },
    experts: {
      totalCount: null
    },
    unanswered: {
      totalCount: null
    },
    unread: {
      totalCount: null
    },
    currentKey: 'newest',
    pageLoad: true,
    ttl: {
      val: null,
      isExpired: function () {
        var ret = true
        var d = new Date()
        var t = d.getTime()

        if (configMap.ttl.val !== null) {
          if (t < configMap.ttl.val) {
            ret = false
          }
        }

        return ret
      },
      setTTL: function () {
        var d = new Date()
        var t = d.getTime()

        configMap.ttl.val = t + 120000
      },
      clearTTL: function () {
        configMap.ttl.val = null
        configMap.newest.totalCount = null
        configMap.popular.totalCount = null
        configMap.experts.totalCount = null
        configMap.unanswered.totalCount = null
      }
    },
    itemKey: function () {
      var feedKeyTemplate = 'feed:' + paramMap.page + ':' + paramMap.nodeId
      return feedKeyTemplate
    },
    clearItem: function () {
      if (this.itemKey() !== null) {
        window.localStorage.removeItem(this.itemKey())
      }
    },
    getItem: function (key) {
      var isExpired = function (expires) {
        var d = new Date()
        var t = d.getTime()
        return (t > expires)
      }
      var isAnonymous = !!$('body').hasClass('lia-user-status-anonymous')
      var ret = {}
      var myRecord = JSON.parse(window.localStorage.getItem(this.itemKey())) || {}

      if (myRecord.isAnonymous !== isAnonymous) {
        // content only used if stored state match user's auth state
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && isExpired(myRecord.ttl)) {
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && !isExpired(myRecord.ttl) && myRecord.isAnonymous === isAnonymous) {
        ret = myRecord
      }
      return ret
    },
    setItem: function (key, content) {
      var myRecord = {
        ttl: this.ttl.val,
        content: $('.recent-conversations-messages-wrapper .message-list').html(),
        tab: this.currentKey,
        newest: this.newest.totalCount,
        popular: this.popular.totalCount,
        experts: this.experts.totalCount,
        unanswered: this.unanswered.totalCount,
        isAnonymous: !!$('body').hasClass('lia-user-status-anonymous')
      }

      // console.log('inside setItem fcn')
      // console.log(myRecord)

      window.localStorage.setItem(
        this.itemKey(),
        JSON.stringify(myRecord)
      )
    }
  }

  var showMoreConversations = function () {
    var existingContent = {}
    var calculateOffset = $('.message-list > ul > li.conversation-' + configMap.currentKey).length
    var myData = {
      board: paramMap.board,
      pageOffsetValue: calculateOffset,
      eventTab: paramMap.eventTab,
      filter: configMap.currentKey,
      replies: paramMap.repliesNum,
      pageSize: paramMap.pageSize
    }
    existingContent = configMap.getItem()

    // use localStorage:
    // when page is loading
    // when TTL valid

    if (existingContent.hasOwnProperty('content') && configMap.pageLoad === true && !configMap.ttl.val) {
      // only run once when page first load
      configMap.pageLoad = false

      // set the active tab
      configMap.currentKey = existingContent.tab

      $('.recent-conversations-messages-wrapper .message-list').html(existingContent.content)
      calculateOffset = $('.message-list > ul > li.conversation-' + configMap.currentKey).length
      $('.welcome-spinner').addClass('hidden')

      // set filter UI
      $('.home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $('.home-feed-filters li').each(function () {
        if ($(this).attr('data') === configMap.currentKey) {
          $(this).addClass('active-filter')
        } else {
          $(this).removeClass('active-filter')
        }
      })

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $('#showMoreButton').prop('disabled', false)
      }
    } else if (existingContent.hasOwnProperty('content') && configMap.currentKey !== existingContent.tab &&
      calculateOffset > 0 && !configMap.ttl.isExpired()) {
      // localStorage refreshed with current filter and records displaying
      $('.message-list > ul > li.conversation').hide()
      $('.message-list > ul > li.conversation-' + configMap.currentKey).show()
      $('.welcome-spinner').addClass('hidden')

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $('#showMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $('#showMoreButton').prop('disabled', false)
      }

      // save in localStorage
      configMap.setItem()
    } else {
      // make request for items
      configMap.pageLoad = false

      $.ajax({
        type: 'post',
        url: paramMap.endpointUrlValue,
        dataType: 'html',
        data: myData,
        context: $('.showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          $('.recent-conversations-messages-wrapper .spinner').removeClass('hidden')
          $('.error-msg').addClass('hidden').empty()

          if (calculateOffset > 0) {
            // load more items
            UTILITIES.accessibleAlert('#feedMessages', 'Loading more')
          } else {
            if ($('.refresh-feed').hasClass('in-progress')) {
              // specific feed refresh action
              UTILITIES.accessibleAlert('#feedMessages', 'Feed refresh')
            } else {
              // loading feed
              UTILITIES.accessibleAlert('#feedMessages', 'Loading')
            }
          }
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $('.spinner').empty().remove()
        },
        success: function (data) {
          var checkExistingItems = function () {
            return $('.message-list > ul > li.conversation-' + configMap.currentKey).length
          }
          var itemToFocus = checkExistingItems()
          var template1 = paramMap.lang.noMessageText
          var template2 = 'Looks like you have not favorited any Conversation Spaces, ' +
            'which power this feed! Go to communities and favorite conversation spaces ' +
            'that interest you.'
          var markup

          $('div.no-messages-container.lia-text').remove()
          $('.recent-conversations-messages-wrapper .spinner').addClass('hidden')
          $('.recent-conversations-messages-wrapper').removeClass('loadmore')
          $('.refresh-feed').removeClass('in-progress')
          UTILITIES.accessibleAlert('#feedMessages', 'Success')

          configMap.ttl.setTTL()

          if (data.indexOf('{{{0}}}') === -1) {
            $('.showMoreMessagesHere').removeClass()
            $('.recent-message-list').first().append('<ul class="showMoreMessagesHere"></ul>')

            if (checkExistingItems() > 0) {
              $(this).html(data)
              $('.message-list > ul > li.conversation-' + configMap.currentKey).eq(itemToFocus).find('.subject-link').focus()
            } else {
              $(this).html(data)

              // empty message in controller
              if (configMap[configMap.currentKey].totalCount === 0) {
                UTILITIES.accessibleAlert('#feedMessages', template1)
              }
            }

            // set content in localStorage
            configMap.setItem()
            $('#showMoreButton').prop('disabled', false)
          } else if ((data.indexOf('{{{0}}}') !== -1 && checkExistingItems() > 0)) {
            // no more items to show
            $('#showMoreButton').prop('disabled', true)
          } else {
            // messaging for no records
            if (paramMap.board === 'all') {
              markup = template1
            } else {
              markup = (configMap.currentKey === 'newest') ? template2 : template1
            }
            $('.error-msg').removeClass('hidden').html(markup)
            $('#showMoreButton').prop('disabled', true)
            configMap.ttl.clearTTL()
            $('.recent-message-list').empty().html('<ul class="showMoreMessagesHere"></ul>')
          }

          // disable if total count is reached after records
          if ((checkExistingItems() >= totalCount)) {
            $('#showMoreButton').prop('disabled', true)
          } else {
            $('#showMoreButton').prop('disabled', false)
          }

          $('.message-list > ul > li.conversation').hide()
          $('.message-list > ul > li.conversation-' + configMap.currentKey).show()

          profileUserCardMe()

          $('.recent-conversations-messages-wrapper').off()
          $('.recent-conversations-messages-wrapper').on('click', '.reply-body', function () {
            var myHref = $(this).attr('data-href')

            if (myHref) {
              document.location = $(this).attr('data-href')
            }
          })
        }
      })
    }
  }

  var getTotalCount = function () {
    var myData = {
      usr: paramMap.usr,
      nodeId: paramMap.nodeId,
      board: paramMap.board,
      filter: configMap.currentKey
    }
    var makeRequest = true
    var existingContent = configMap.getItem()

    // console.log(existingContent)

    if (existingContent.hasOwnProperty('content')) {
      if (existingContent[configMap.currentKey] !== null) {
        makeRequest = false
        showMoreConversations()
      }
    }

    if (makeRequest) {
      $.ajax({
        type: 'post',
        url: paramMap.countUrlValue,
        dataType: 'json',
        data: myData,
        context: $('.showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          $('.recent-conversations-messages-wrapper .spinner').removeClass('hidden')
          $('.error-msg').addClass('hidden').empty()
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $('.spinner').empty().remove()
        },
        success: function (data) {
          totalCount = data.count
          configMap[configMap.currentKey].totalCount = data.count
          showMoreConversations()
        }
      })
    }
  }
  var purgeDuplicatesUnread = function () {
    var collection = $('.conversation')
    var len = collection.length
    var i
    var dataId
    var uniques = []
    var emptyMessage = 'No messages.'

    $(collection).hide()

    for (i = 0; i < len; i++) {
      dataId = $(collection[i]).find('.post.unread').attr('data-id')
      if (dataId && $.inArray(dataId, uniques) === -1) {
        $(collection[i]).show()
        uniques.push(dataId)
      }
    }

    if (uniques.length === 0) {
      $('.error-msg').html(emptyMessage).removeClass('hidden')
      UTILITIES.accessibleAlert('#feedMessages', emptyMessage)
    } else {
      $('.error-msg').empty().addClass('hidden')
    }
  }

  $('.refresh-feed').on('click keypress', function (e) {
    if (e.type === 'click' || e.keyCode === 13) {
      configMap.clearItem()
      configMap.ttl.clearTTL()
      configMap.currentKey = 'newest'

      if (e.hasOwnProperty('originalEvent')) {
        // explicit user action taken is different
        // than programmatic click for screen readers
        $(this).addClass('in-progress')
      }

      // flush container and start over
      $('.recent-conversations-messages-wrapper .message-list').html('<ul class="showMoreMessagesHere"></ul>')
      $('.home-feed-filters li[data=newest]').click()
    }
  })

  $('.home-feed-filters-select').on('change', function (e) {
    var key = $(this).val()
    var currentFiltersItemsLength = $('.message-list > ul > li.conversation-' + key).length
    var checkExistingItems = function () {
      return $('.message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    configMap.currentKey = key

    $('.home-feed-filters li').each(function () {
      if ($(this).attr('data') === configMap.currentKey) {
        $(this).addClass('active-filter')
      } else {
        $(this).removeClass('active-filter')
      }
    })

    $('.message-list > ul > li.conversation').hide()
    $('.error-msg').text('').addClass('hidden')

    if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
      configMap.ttl.setTTL()
    }

    if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
      // flush container and start over
      $('.recent-message-list').html('<ul class="showMoreMessagesHere"></ul>')
      getTotalCount()
    } else {
      if (configMap.currentKey === 'unread') {
        purgeDuplicatesUnread()
        $('#showMoreButton').prop('disabled', true)
      } else if (configMap[configMap.currentKey].totalCount) {
        if (currentFiltersItemsLength === 0) {
          showMoreConversations()
        } else {
          $('.message-list > ul > li.conversation').hide()
          $('.message-list > ul > li.conversation-' + key).show()

          // set content in localStorage
          configMap.setItem()

          if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
            $('#showMoreButton').prop('disabled', true)
          } else {
            $('#showMoreButton').prop('disabled', false)
          }
        }
      } else {
        getTotalCount()
      }
    }
  })

  $('.home-feed-filters li').on('keydown click', function (e) {
    var key
    var currentFiltersItemsLength
    var checkExistingItems = function () {
      return $('.message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    if (e.keyCode === 13 || e.type === 'click') {
      key = $(this).attr('data')
      currentFiltersItemsLength = $('.message-list > ul > li.conversation-' + key).length
      configMap.currentKey = key

      $('.home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $('.filter-tag').removeClass('active-filter')
      $(this).addClass('active-filter')
      $('.message-list > ul > li.conversation').hide()
      $('.error-msg').text('').addClass('hidden')
      $('div.no-messages-container').remove()

      if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
        configMap.ttl.setTTL()
      }

      if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
        // flush container and start over
        $('.recent-conversations-messages-wrapper .message-list').html('<ul class="showMoreMessagesHere"></ul>')
        getTotalCount()
      } else {
        if (configMap.currentKey === 'unread') {
          purgeDuplicatesUnread()
          $('#showMoreButton').prop('disabled', true)
        } else if (configMap[configMap.currentKey].totalCount) {
          if (currentFiltersItemsLength === 0) {
            showMoreConversations()
          } else {
            $('.message-list > ul > li.conversation').hide()
            $('.message-list > ul > li.conversation-' + key).show()

            // set content in localStorage
            configMap.setItem()

            if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
              $('#showMoreButton').prop('disabled', true)
            } else {
              $('#showMoreButton').prop('disabled', false)
            }
          }
        } else {
          getTotalCount()
        }
      }
    }
  })

  $('.category-recent-conversations-component').on('click', function (e) {
    var offset = $('.recent-conversations-messages-wrapper .message-list > ul > li').length
    var self

    if (e.target.id === 'showMoreButton') {
      e.target.blur()
      if (offset > 0) {
        // show only loader on the button
        $('.recent-conversations-messages-wrapper').addClass('loadmore')
      }

      if (configMap[configMap.currentKey].totalCount) {
        showMoreConversations()
      } else {
        getTotalCount()
      }
    } else if ($(e.target).hasClass('kudo-add-button') || $(e.target).hasClass('kudo-remove-button')) {
      self = configMap
      setTimeout(function () {
        self.setItem()
      }, 500)
    }
  }).find('#showMoreButton').click()

  if (paramMap.autoScrollBool) {
    $(window).scroll(function () {
      if ($(window).scrollTop() >= $('#showMoreButton').position().top &&
        $('.welcome-spinner').hasClass('hidden') &&
        $(window).width() > 991 &&
        !$('#showMoreButton').prop('disabled')) {
        $('#showMoreButton').click()
      }
    })
  }

  $('.trending-boards').click(function (e) {
    var board = paramMap.board
    if (e.target.parentElement.tagName === 'BUTTON' && board === 'favorite') {
      if ($('.home-feed-filters .active-filter').attr('data') !== 'unread') {
        configMap.ttl.clearTTL()
        $('.recent-message-list, .message-list').html('<ul class="showMoreMessagesHere"></ul>')
        $('.home-feed-filters .active-filter').click()
      }
    }
  })

  $(window).on('beforeunload', function () {
    $('body').addClass('leaving-page')
  })
}

UTILITIES.addQuickReplyInitForCat = function (paramMap) {
  $('.recent-conversations-messages-wrapper').off('click')
  $('.recent-conversations-messages-wrapper').on('click', '.reply-body', function () {
    var myHref = $(this).attr('data-href')

    if (myHref) {
      document.location = myHref
    }
  })

  $('.message-add-reply .message-textarea').off('focus')
  $('.message-add-reply .message-textarea').on('focus', function () {
    $('.cancel-button', $(this).parent().parent()).css('display', 'block')
    $('.reply-button', $(this).parent().parent()).css('display', 'block')
    $('.pnl-full-text-editor', $(this).parent().parent()).css('display', 'block')
  })

  $('.message-add-reply .dropdown-menu').off('click')
  $('.message-add-reply .dropdown-menu').on('click', 'li', function (e) {
    var textarea = $(this).closest('.dropdown').next()
    var textareaValue = textarea.val()
    var textareaHiddenValue = textarea.attr('data-hidden')
    var textareaHiddenArr = textareaHiddenValue === '' ? [] : textareaHiddenValue.split(',')
    var dataValue = $(this).attr('data-value')
    var dataUrl = $(this).attr('data-url')
    var mentionQueryStr = $mentionQueryStr
    var replaceTextareaValue = textareaValue.replace(mentionQueryStr, dataValue)
    var templateHiddenItem = '{0}*|*{1}'
    e.preventDefault()

    templateHiddenItem = templateHiddenItem.replace('{0}', '@' + dataValue).replace('{1}', dataUrl)
    textareaHiddenArr.push(templateHiddenItem)

    textarea.val(replaceTextareaValue)
    $(e.currentTarget).closest('.dropdown').removeClass('open')
    textarea.attr('data-hidden', textareaHiddenArr.join(','))
  })

  $('.message-add-reply .message-textarea').off('keyup')
  $('.message-add-reply .message-textarea').on('keyup', function (e) {
    var myStr = $(this).val()
    var cursorPos = myStr.substr(0, e.target.selectionStart)
    var pos = cursorPos.lastIndexOf('@')
    var qStr = pos === -1 ? '' : cursorPos.substr(pos + 1)

    $('.dropdown').removeClass('open')

    if (pos !== -1 && qStr.length > 2) {
      $mentionQueryStr = qStr

      $.ajax({
        type: 'post',
        url: paramMap.optional1,
        data: { q: qStr },
        dataType: 'json',
        error: function (err) {
          console.log(err)
        },
        context: $(this),
        success: function (data) {
          var i
          var users = data.users
          var len = users.length
          var item
          var markup = ''
          var defaultAvatar
          var template
          var loginStr

          $(this.context).prev().removeClass('open')

          if (len > 0) {
            defaultAvatar = '/html/assets/default-avatar.png'
            template = '<li data-value="{5}" data-user-id="{4}" data-url="{3}">' +
              '<a href="#"><div class="div-user-avatar"><img class="user-avatar-message" style="border-radius: 51%; border: 3px solid #ccc; border-color: {7}"' +
              'src="{0}" onerror="this.src=\'{6}\'"></div><div class="div-user-name"><strong>{1}</strong><br><small>({2})</small></div></a></li>'

            for (i = 0; i < len; i++) {
              item = users[i]
              loginStr = item.login

              if (users[i - 1] && item.login === users[i - 1].login) {
                // compare previous
                loginStr = item.login + '(ID #' + item.id + ')'
              } else if (users[i + 1] && item.login === users[i + 1].login) {
                // compare next
                loginStr = item.login + '(ID #' + item.id + ')'
              }

              if (item.avatar === 'unknown' || item.avatar === '') {
                item.avatar = defaultAvatar
              }

              markup += template.replace('{0}', item.avatar)
                .replace('{1}', loginStr)
                .replace('{2}', item.rank_name)
                .replace('{3}', item.url)
                .replace('{4}', item.id)
                .replace('{5}', loginStr)
                .replace('{6}', item.default_avatar)
                .replace('{7}', '#' + item.rank_color)
            }
            $(this.context).prev().addClass('open')
            $(this.context).prev().find('.dropdown-menu').html(markup)
          }
        }
      })
    }
  })

  profileUserCardMe()

  $('.CategoryPage .message-add-reply .reply-button').off('click')
  $('.CategoryPage .message-add-reply .reply-button').on('click', function () {
    var msgId = $(this).attr('msg-id')
    var textBox = $('textarea', $(this).parent())
    var replyText = $.trim(textBox.val())
    var replyHiddenText = $.trim(textBox.attr('data-hidden'))

    if (replyText.length === 0) {
      // validation error, report to client.
      if ($('.error', $(this).parent()).length === 0) {
        $(this).before('<div class="error">Your reply cannot be empty</div>')
      }
    } else {
      if (replyHiddenText.length !== 0) {
        var users = replyHiddenText.split(',')
        for (var i = 0; i < users.length; i++) {
          var user = users[i].split('*|*')
          if (user.length > 1) {
            var userName = user[0]
            var userUrl = user[1]
            var hasIdStart = userName.indexOf('(')
            var userLogin = userName
            if (hasIdStart > 0) {
              userLogin = userName.substring(0, hasIdStart)
            }
            var userContent = '<a href="' + userUrl + '">' + userLogin + '</a>'
            replyText = replyText.replace(userName, userContent)
          }
        }
      }

      $.ajax({
        type: 'post',
        url: paramMap.optional2,
        data: { msgId: msgId, replyText: replyText },
        dataType: 'json',
        context: this,
        error: function () {
          $('.error', $(this).parent()).empty().remove()
          $(this).before('<div class="error">Unknown error occured</div>')
          UTILITIES.accessibleAlert('#feedMessages', 'Unknown error occured')
        },
        beforeSend: function () {
          $(this).parent().prepend('<div class="spinner welcome-spinner"></div>')
        },
        success: function (data) {
          var sanitize
          var config = {
            SAFE_FOR_JQUERY: true,
            USE_PROFILES: { html: true }
          }

          $('.error', $(this).parent()).empty().remove()

          if (data.status === 'success') {
            sanitize = DOMPurify.sanitize(data.payload, config)
            $(this).closest('.message').find('.post-reply').remove()
            $(this).closest('.message').find('.post').after('<div class="post-reply"></div>')
            $(this).closest('.message').find('.post-reply').append(sanitize)
            $(this).closest('.message').find('.post-reply .UserAvatar img').attr('src', '/html/assets/default-avatar.png')
            textBox.val('')

            $(this).css('display', 'none')
            $('.cancel-button', $(this).parent()).css('display', 'none')
            $('.pnl-full-text-editor', $(this).parent()).css('display', 'none')

            UTILITIES.accessibleAlert('#feedMessages', 'Reply to message success')
            $(this).closest('.message').find('.message-subject-link').focus()

            // trigger the event and no need to wrap because can't do this unauth
            $('.recent-conversations-messages-wrapper').trigger('recent-conversations-messages-wrapper:quickReply')
          } else {
            $(this).before('<div class="error">' + data.message + '</div>')
            UTILITIES.accessibleAlert('#feedMessages', data.message)
          }
        },
        complete: function () {
          $('.welcome-spinner.spinner', $(this).parent()).empty().remove()
        }
      })
    }
  })

  $('.add-reply-wrapper .cancel-button').off('click')
  $('.add-reply-wrapper .cancel-button').on('click', function () {
    var textBox = $('textarea', $(this).parent())
    $.trim(textBox.val(''))
    $(this).css('display', 'none')
    $('.reply-button', $(this).parent()).css('display', 'none')
    $('.pnl-full-text-editor', $(this).parent()).css('display', 'none')
    $('.error', $(this).parent()).empty().remove()
  })
  // Replies end
}

UTILITIES.addQuickReplyInitForBoard = function (paramMap) {
  $('.recent-conversations-messages-wrapper').off('click')
  $('.recent-conversations-messages-wrapper').on('click', '.reply-body', function () {
    var myHref = $(this).attr('data-href')

    if (myHref) {
      document.location = myHref
    }
  })

  $('.message-add-reply .message-textarea').off('focus')
  $('.message-add-reply .message-textarea').on('focus', function () {
    $('.cancel-button', $(this).parent().parent()).css('display', 'block')
    $('.reply-button', $(this).parent().parent()).css('display', 'block')
    $('.pnl-full-text-editor', $(this).parent().parent()).css('display', 'block')
  })

  $('.message-add-reply .dropdown-menu').off('click')
  $('.message-add-reply .dropdown-menu').on('click', 'li', function (e) {
    var textarea = $(this).closest('.dropdown').next()
    var textareaValue = textarea.val()
    var textareaHiddenValue = textarea.attr('data-hidden')
    var textareaHiddenArr = textareaHiddenValue === '' ? [] : textareaHiddenValue.split(',')
    var dataValue = $(this).attr('data-value')
    var dataUrl = $(this).attr('data-url')
    var mentionQueryStr = $mentionQueryStr
    var replaceTextareaValue = textareaValue.replace(mentionQueryStr, dataValue)
    var templateHiddenItem = '{0}*|*{1}'
    e.preventDefault()

    templateHiddenItem = templateHiddenItem.replace('{0}', '@' + dataValue).replace('{1}', dataUrl)
    textareaHiddenArr.push(templateHiddenItem)

    textarea.val(replaceTextareaValue)
    $(e.currentTarget).closest('.dropdown').removeClass('open')
    textarea.attr('data-hidden', textareaHiddenArr.join(','))
  })

  $('.message-add-reply .message-textarea').off('keyup')
  $('.message-add-reply .message-textarea').on('keyup', function (e) {
    var myStr = $(this).val()
    var cursorPos = myStr.substr(0, e.target.selectionStart)
    var pos = cursorPos.lastIndexOf('@')
    var qStr = pos === -1 ? '' : cursorPos.substr(pos + 1)

    $('.dropdown').removeClass('open')

    if (pos !== -1 && qStr.length > 2) {
      $mentionQueryStr = qStr

      $.ajax({
        type: 'post',
        url: paramMap.optional1,
        data: { q: qStr },
        dataType: 'json',
        error: function (err) {
          console.log(err)
        },
        context: $(this),
        success: function (data) {
          var i
          var users = data.users
          var len = users.length
          var item
          var markup = ''
          var defaultAvatar
          var template
          var loginStr

          $(this.context).prev().removeClass('open')

          if (len > 0) {
            defaultAvatar = '/html/assets/default-avatar.png'
            template = '<li data-value="{5}" data-user-id="{4}" data-url="{3}">' +
              '<a href="#"><div class="div-user-avatar"><img class="user-avatar-message" style="border-radius: 51%; border: 3px solid #ccc; border-color: {7}"' +
              'src="{0}" onerror="this.src=\'{6}\'"></div><div class="div-user-name"><strong>{1}</strong><br><small>({2})</small></div></a></li>'

            for (i = 0; i < len; i++) {
              item = users[i]
              loginStr = item.login

              if (users[i - 1] && item.login === users[i - 1].login) {
                // compare previous
                loginStr = item.login + '(ID #' + item.id + ')'
              } else if (users[i + 1] && item.login === users[i + 1].login) {
                // compare next
                loginStr = item.login + '(ID #' + item.id + ')'
              }

              if (item.avatar === 'unknown' || item.avatar === '') {
                item.avatar = defaultAvatar
              }

              markup += template.replace('{0}', item.avatar)
                .replace('{1}', loginStr)
                .replace('{2}', item.rank_name)
                .replace('{3}', item.url)
                .replace('{4}', item.id)
                .replace('{5}', loginStr)
                .replace('{6}', item.default_avatar)
                .replace('{7}', '#' + item.rank_color)
            }
            $(this.context).prev().addClass('open')
            $(this.context).prev().find('.dropdown-menu').html(markup)
          }
        }
      })
    }
  })

  profileUserCardMe()

  $('.ForumPage .message-add-reply .reply-button').off('click')
  $('.ForumPage .message-add-reply .reply-button').on('click', function () {
    var msgId = $(this).attr('msg-id')
    var textBox = $('textarea', $(this).parent())
    var replyText = $.trim(textBox.val())
    var replyHiddenText = $.trim(textBox.attr('data-hidden'))

    if (replyText.length === 0) {
      // validation error, report to client.
      if ($('.error', $(this).parent()).length === 0) {
        $(this).before('<div class="error">Unknown error</div>')
      }
    } else {
      if (replyHiddenText.length !== 0) {
        var users = replyHiddenText.split(',')
        for (var i = 0; i < users.length; i++) {
          var user = users[i].split('*|*')
          if (user.length > 1) {
            var userName = user[0]
            var userUrl = user[1]
            var hasIdStart = userName.indexOf('(')
            var userLogin = userName
            if (hasIdStart > 0) {
              userLogin = userName.substring(0, hasIdStart)
            }
            var userContent = '<a href="' + userUrl + '">' + userLogin + '</a>'
            replyText = replyText.replace(userName, userContent)
          }
        }
      }

      $.ajax({
        type: 'post',
        url: paramMap.optional2,
        data: { msgId: msgId, replyText: replyText },
        dataType: 'json',
        scope: {
          paramMap: paramMap,
          context: this
        },
        error: function () {
          $('.error', $(this.scope.context).parent()).empty().remove()
          $(this.scope.context).before('<div class="error">Unknown error occured</div>')
          UTILITIES.accessibleAlert('#feedMessages', 'Unknown error occured')
        },
        beforeSend: function () {
          $(this.scope.context).parent().prepend('<div class="spinner welcome-spinner"></div>')
        },
        success: function (data) {
          var sanitize
          var config = {
            SAFE_FOR_JQUERY: true,
            USE_PROFILES: { html: true }
          }
          var scope = this.scope
          var repliesNum = scope.paramMap.repliesNum
          var actualReplies = $(scope.context).closest('.message').find('.post-reply > .message-reply').length
          $('.error', $(scope.context).parent()).empty().remove()
          if (data.status === 'success') {
            sanitize = DOMPurify.sanitize(data.payload, config)

            if (actualReplies === 0) {
              // add a repl
              $(scope.context).closest('.message').find('.post-reply').remove()
              $(scope.context).closest('.message').find('.post').after('<div class="post-reply"></div>')
              $(scope.context).closest('.message').find('.post-reply').append(sanitize, config)
                .find('.UserAvatar img').attr('src', '/html/assets/default-avatar.png')
            } else if (actualReplies < repliesNum) {
              // add to list
              $(scope.context).closest('.message').find('.post-reply').prepend(sanitize, config)
                .find('.UserAvatar img').attr('src', '/html/assets/default-avatar.png')
            } else if (actualReplies >= repliesNum) {
              // remove last prepend latest
              $(scope.context).closest('.message').find('.post-reply').prepend(sanitize, config)
                .find('.UserAvatar img').attr('src', '/html/assets/default-avatar.png')
              $(scope.context).closest('.message').find('.post-reply > .message-reply').last().remove()
            }

            UTILITIES.accessibleAlert('#feedMessages', 'Reply to message success')
            $(scope.context).closest('.message').find('.message-subject-link').focus()

            // from closure
            textBox.val('')

            $(scope.context).css('display', 'none')
            $('.cancel-button', $(scope.context).parent()).css('display', 'none')
            $('.pnl-full-text-editor', $(scope.context).parent()).css('display', 'none')

            // trigger the event and no need to wrap because can't do this unauth
            $('.recent-conversations-messages-wrapper').trigger('recent-conversations-messages-wrapper:quickReply')
          } else {
            $(scope.context).before('<div class="error">' + data.message + '</div>')
            UTILITIES.accessibleAlert('#feedMessages', data.message)
          }
        },
        complete: function () {
          $('.spinner', $(this.scope.context).parent()).empty().remove()
          profileUserCardMe()
        }
      })
    }
  })

  $('.add-reply-wrapper .cancel-button').off('click')
  $('.add-reply-wrapper .cancel-button').on('click', function () {
    var textBox = $('textarea', $(this).parent())
    $.trim(textBox.val(''))
    $(this).css('display', 'none')
    $('.reply-button', $(this).parent()).css('display', 'none')
    $('.pnl-full-text-editor', $(this).parent()).css('display', 'none')
    $('.error', $(this).parent()).empty().remove()
  })
}

UTILITIES.DASH = {
  colorPicker: {
    AVAIL: [
      '750b1c',
      '005b70',
      '5c2e91',
      '881798',
      '0b6a0b',
      '393939',
      '004e8c',
      '986f0b',
      'ca5010',
      '69797e',
      'da3b01',
      '498205'
    ],
    available: [],
    selected: [],
    colorChips: function () {
      $('.chips').each(function (n) {
        var collection = $('.chips:eq(' + n + ') > li')
        var myColor
        var i
        var available = UTILITIES.DASH.colorPicker.available

        $('.chips:eq(' + n + ') > li.chip').removeAttr('style').removeAttr('data-color')
          .removeClass('colored')

        for (i = 0; i < available.length; i++) {
          myColor = '#' + available[i]
          $(collection[i]).css({ background: myColor, 'border-color': myColor }).attr('data-color', myColor).addClass('colored')
        }
      })
    },
    pickerSelectUpdate: function () {
      // color chips: subtract selected from available collection
      var i
      var x
      var selected = UTILITIES.DASH.colorPicker.selected
      var available = UTILITIES.DASH.colorPicker.available
      var len = selected.length

      for (i = 0; i < len; i++) {
        for (x = available.length; x >= 0; x--) {
          if (selected[i].slice(1) === available[x]) {
            UTILITIES.DASH.colorPicker.available.splice(x, 1)
          }
        }
      }
    },
    updateSelected: function () {
      // updates the selected pill
      var i
      var collection = $('.pills')
      var len = collection.length
      var item
      var color

      UTILITIES.DASH.colorPicker.selected = []

      for (i = 0; i < len; i++) {
        item = collection[i]
        color = $(item).attr('data-color').slice(1) || ''
        UTILITIES.DASH.colorPicker.selected.push(color)
      }
    },
    removeAllColors: function () {
      $('.pill.colored').removeClass('colored').removeAttr('style').removeAttr('data-color')
      UTILITIES.DASH.colorPicker.selected = []
      UTILITIES.DASH.colorPicker.available = UTILITIES.DASH.colorPicker.AVAIL.slice(0)
      UTILITIES.DASH.colorPicker.colorChips()
    },
    updateColors: function (color) {
      var getIndex = function (c, arr) {
        var len = arr.length
        var i
        var ret = -1

        for (i = 0; i < len; i++) {
          if (arr[i] === c.slice(1)) {
            ret = i
            break
          }
        }

        return ret
      }
      var index = getIndex(color, UTILITIES.DASH.colorPicker.available)

      // removes items from constant array
      UTILITIES.DASH.colorPicker.available.splice(index, 1)

      // colors the picker
      UTILITIES.DASH.colorPicker.colorChips()

      // update the selected array
      UTILITIES.DASH.colorPicker.updateSelected()
    },
    colorSelected: function (color, ele) {
      $('.color-picker-container').addClass('hidden')
      $('.opacity-layer-ideas').addClass('hidden')
      UTILITIES.DASH.colorPicker.updateColors(color)
    }
  },
  lang: {
    labels: {
      EMPTY_MSG: 'There are no predefined labels'
    }
  },
  const: {
    // written here from init()
  },
  getPramFromUrl: function (name) {
    var results = new RegExp('[?&]' + name + '=([^&#]*)').exec(window.location.href)

    return results ? decodeURI(results[1]) : ''
  },
  setSelectFromParam: function () {
    var i
    var myParam = this.getPramFromUrl('id')
    var options = $('.cat-select option')
    var len = options.length

    for (i = 0; i < len; i++) {
      if (options[i].value === myParam) {
        $(options[i]).attr('selected', 'selected')
        break
      }
    }
  },
  leaveFormDirty: function () {
    var formIsDirty = $('.content-panel:visible form').hasClass('dirty')
    var ret

    if (formIsDirty) {
      if (window.confirm('Are you sure?\nLeave without saving?')) {
        ret = true
      } else {
        ret = false
      }
    } else {
      ret = true
    }
    return ret
  },
  refreshPredefinedLabelsValue: function () {
    var collection = $('.label-tag')
    var len = collection.length
    var i
    var ret = []
    var item

    for (i = 0; i < len; i++) {
      item = $(collection[i]).find('.label-tag-text').text()
      ret.push(item)
    }

    return ret
  },
  setTTL: function () {
    var now = new Date().getTime()
    var TTL = 5 * 60 * 1000

    return now + TTL
  },
  expiredTTL: function (ttl) {
    var now = new Date().getTime()
    return (now > ttl)
  },
  addStore: function (users, board, roleId, roleStrId) {
    var splitRole = roleStrId.split(':')
    var roleType = splitRole[0]

    blogStore[board + roleStrId] = JSON.stringify({
      data: users,
      ttl: this.setTTL(),
      userRoleId: roleId,
      roleType: roleType
    })
    // console.log('add to store')
    // console.log(blogStore[board + roleStrId])
  },
  removeItemBlogData: function (blogId, roleName, userId, templateRecord) {
    var i
    var record = JSON.parse(blogStore[blogId + roleName])
    var items = record.data
    var len = items.length
    var item
    var templateRecord1

    for (i = 0; i < len; i++) {
      item = items[i]
      if (item.id === userId) {
        items.splice(i, 1)
        break
      }
    }

    if (items.length === 0) {
      templateRecord1 = {
        first_name: '',
        id: '',
        last_name: '',
        login: '',
        userRoleId: templateRecord.userRoleId,
        roleType: templateRecord.roleType,
        emptyMessage: true
      }
      items.push(templateRecord1)
    }

    // value equals new stringified record
    blogStore[blogId + roleName] = JSON.stringify({
      ttl: this.setTTL(),
      userRoleId: templateRecord.userRoleId,
      data: items
    })

    // console.log('action - remove item')
    // console.log(blogStore[blogId + roleName])

    this.getRoles(roleName, blogId, items)
  },
  addItemBlogData: function (blogId, roleName, templateRecord) {
    var record = JSON.parse(blogStore[blogId + roleName])
    var items = record.data

    items.push(templateRecord)

    blogStore[blogId + roleName] = JSON.stringify({
      ttl: this.setTTL(),
      userRoleId: templateRecord.userRoleId,
      data: items
    })

    // console.log('action - add item')
    // console.log(blogStore[blogId + roleName])

    this.getRoles(roleName, blogId, items)
  },
  successMessage: function () {
    $('.success-message:visible').addClass('success-show')
    setTimeout(function () {
      $('.success-message').removeClass('success-show')
    }, 2000)
  },
  errorMessage: function () {
    $('.error-message:visible').addClass('error-show')
  },
  renderPagedUsersWithRoles: function (users) {
    var customSort = function (a, b) {
      if (a.text < b.text) {
        return -1
      } else if (a.text > b.text) {
        return 1
      } else {
        return 0
      }
    }
    var prepedItems = users.map(function (item) {
      var template = '<div class="tag"><span data-author-id="{0}" data-role-id="{3}">{1} {2}</span><span class="remove">&times;</span></div>'
      var template1 = '<div class="tag"><span data-author-id="{0}" data-role-id="{3}">{4}</span><span class="remove">&times;</span></div>'
      var template2 = '<div class="tag hidden"><span data-role-id="{3}"></span></div>'
      var retObj = {}

      retObj.isEmpty = false
      retObj.userRoleId = item.userRoleId

      if (item.emptyMessage === true) {
        // empty collection still has roleID from endpoint in empty record
        retObj.tag = template2.replace('{3}', item.userRoleId)
        retObj.text = 'empty-record'
        retObj.isEmpty = true
      } else if (item.first_name === '') {
        // user login istead of first, last
        retObj.tag = template1.replace('{0}', item.id)
          .replace('{4}', item.login)
          .replace('{3}', item.userRoleId)
        retObj.text = item.login
      } else {
        retObj.tag = template.replace('{0}', item.id)
          .replace('{1}', item.first_name)
          .replace('{2}', item.last_name)
          .replace('{3}', item.userRoleId)
        retObj.text = (item.first_name + ' ' + item.last_name)
      }

      return retObj
    })

    $('#blogAuthorPagedTags').pagination({
      dataSource: prepedItems.sort(customSort),
      pageSize: 5,
      callback: function (data, pagination) {
        var i
        var len = data.length
        var markup = ''

        if (len === 0 || (data[0].isEmpty === true && len === 1)) {
          markup += '<p>No users currently assigned this role</p>'
        } else {
          for (i = 0; i < len; i++) {
            markup += data[i].tag
          }
        }

        $('.data-container').html(markup)
        $('#go').attr('data-role-id', data[0].userRoleId)
        $('.lookupUser, .lookupUserLabel, .blog-author-tags-label, #blogAuthorPagedTags').removeClass('hidden')
      }
    })
  },
  addRemoveRoles: function (userId, roleId, actionType, templateRecord) {
    var myURL = UTILITIES.DASH.const.addRemoveRolesUser
    var self = this

    $.ajax({
      type: 'post',
      url: myURL,
      dataType: 'json',
      scope: {
        self: self,
        userId: userId,
        roleId: roleId,
        actionType: actionType,
        templateRecord: templateRecord
      },
      data: {
        roleId: roleId,
        userId: userId,
        actionType: actionType
      },
      error: function (err) {
        console.log(err)
        UTILITIES.DASH.errorMessage()
        $('#go').prop('disabled', false)
      },
      success: function (data) {
        var blogId = $('#blogAuthors').val()
        var roleStrName = $('#rolePicker').val()
        var roleIDtemplate = roleStrName === 'PrivateMember' ? 't:{0}-{1}' : 'b:{0}:{1}'
        var roleName = roleIDtemplate.replace('{0}', blogId).replace('{1}', roleStrName)

        // modify cached data after userAction is taken
        if (this.scope.actionType === 'addRole') {
          this.scope.self.addItemBlogData(blogId, roleName, this.scope.templateRecord)
        } else {
          this.scope.self.removeItemBlogData(blogId, roleName, userId, this.scope.templateRecord)
        }

        $('#lookupUser').val('')
        $('#go').prop('disabled', false)
        this.scope.self.successMessage()
      }
    })
  },
  getUserInfo: function (userId, actionType) {
    var myURL = UTILITIES.DASH.const.getUserInfo
    var self = this

    $.ajax({
      type: 'post',
      url: myURL,
      dataType: 'json',
      data: {
        userId: userId
      },
      error: function (err) {
        console.log(err)
        UTILITIES.DASH.errorMessage()
        $('#go').prop('disabled', false)
      },
      scope: {
        self: self,
        action: actionType
      },
      success: function (data) {
        var roleStrName = $('#rolePicker').val()
        var roleType = roleStrName === 'PrivateMember' ? 't' : 'b'
        var message = 'Are you sure?\nGrant {2} {3} role for {0}, user id {1}?'
        var removeMessage = 'Are you sure?\nRemove {2} {3} role from {0}, user id {1}?'
        var messageStr
        var blogId = $('#blogAuthors').val()
        var roleId = $('#go').attr('data-role-id')
        var actionType = data.length > 0 ? this.scope.action : 'noUser'
        var templateRecord = data.length > 0 ? {
          first_name: data[0].first_name,
          id: data[0].id,
          last_name: data[0].last_name,
          login: data[0].login,
          userRoleId: roleId,
          roleType: roleType,
          emptyMessage: false
        } : {}

        if (actionType === 'addRole') {
          if (data[0].first_name !== '') {
            messageStr = message.replace('{0}', (data[0].first_name + ' ' + data[0].last_name))
              .replace('{1}', data[0].id).replace('{2}', blogId).replace('{3}', roleStrName)
          } else {
            messageStr = message.replace('{0}', data[0].login)
              .replace('{1}', data[0].id).replace('{2}', blogId).replace('{3}', roleStrName)
          }
        } else if (actionType === 'noUser') {
          messageStr = 'User has been deleted or does not exist'
        } else {
          if (data[0].first_name !== '') {
            messageStr = removeMessage.replace('{0}', (data[0].first_name + ' ' + data[0].last_name))
              .replace('{1}', data[0].id).replace('{2}', blogId).replace('{3}', roleStrName)
          } else {
            messageStr = removeMessage.replace('{0}', data[0].login)
              .replace('{1}', data[0].id).replace('{2}', blogId).replace('{3}', roleStrName)
          }
        }

        if (actionType === 'noUser') {
          window.alert(messageStr)
          $('#go').prop('disabled', false)
        } else {
          if (window.confirm(messageStr)) {
            this.scope.self.addRemoveRoles(data[0].id, roleId, actionType, templateRecord)
          } else {
            $('#go').prop('disabled', false)
          }
        }
      }
    })
  },
  getRolesCommunity: function (role, board, users) {
    var CONST = UTILITIES.DASH.const
    var communityURL = CONST.fetchUsersWithRoleCommunity
    var boardURL = CONST.fetchUsersWithRole
    // different endpoint based on the type of role
    var myURL = role.indexOf('t:') !== -1 ? communityURL : boardURL
    var self = this
    var makeRequest = false
    var record
    var eleStrTemplate = '#blogAuthors option[value={0}]'
    var labelStrTemplate = 'Users with {1} role for {0}'
    var eleStr = eleStrTemplate.replace('{0}', board)
    var blogTitle = $(eleStr).text()
    var roleText = role.slice(role.lastIndexOf(':') + 1, role.length)

    $('.blog-author-tags-label').html(labelStrTemplate.replace('{0}', blogTitle).replace('{1}', roleText))
    $('#blogAuthorPagedTags > .data-container').html('<div class="spinner"></div>')

    if (!blogStore[board + role]) {
      makeRequest = true
    } else {
      if (blogStore.hasOwnProperty(board + role)) {
        record = JSON.parse(blogStore[board + role])
        if (self.expiredTTL(record.ttl)) {
          makeRequest = true
        } else {
          self.renderPagedUsersWithRoles(users || record.data)
        }
      }
    }

    if (makeRequest) {
      // pull list of users with this roll.
      $.ajax({
        type: 'post',
        url: myURL,
        dataType: 'json',
        data: {
          roleId: role,
          boardId: board
        },
        scope: {
          role: role,
          board: board,
          self: self
        },
        error: function (err) {
          console.log(err)
          UTILITIES.DASH.errorMessage()
        },
        success: function (users) {
          var board = this.scope.board
          var roleId = users[0].userRoleId
          var roleStrId = this.scope.role

          UTILITIES.DASH.addStore(users, board, roleId, roleStrId)

          UTILITIES.DASH.renderPagedUsersWithRoles(users)
        }
      })
    }
  },
  getRoles: function (role, board, users) {
    var myURL = UTILITIES.DASH.const.fetchUsersWithRole
    var self = this
    var makeRequest = false
    var record
    var eleStrTemplate = '#blogAuthors option[value={0}]'
    var labelStrTemplate = 'Users with {1} role for {0}'
    var eleStr = eleStrTemplate.replace('{0}', board)
    var blogTitle = $(eleStr).text()
    var roleText = role.slice(role.lastIndexOf(':') + 1, role.length)

    $('.blog-author-tags-label').html(labelStrTemplate.replace('{0}', blogTitle).replace('{1}', roleText))

    if (!blogStore[board + role]) {
      makeRequest = true
    } else {
      if (blogStore.hasOwnProperty(board + role)) {
        record = JSON.parse(blogStore[board + role])
        if (self.expiredTTL(record.ttl)) {
          makeRequest = true
        } else {
          self.renderPagedUsersWithRoles(users || record.data)
        }
      }
    }

    if (makeRequest) {
      // pull list of users with this roll.
      $.ajax({
        type: 'post',
        url: myURL,
        dataType: 'json',
        data: {
          roleId: role,
          boardId: board
        },
        scope: {
          role: role,
          board: board,
          self: self
        },
        error: function (err) {
          console.log(err)
          UTILITIES.DASH.errorMessage()
        },
        success: function (users) {
          var board = this.scope.board
          var roleId = users[0].userRoleId
          var roleStrId = this.scope.role

          UTILITIES.DASH.addStore(users, board, roleId, roleStrId)

          UTILITIES.DASH.renderPagedUsersWithRoles(users)
        }
      })
    }
  },
  refreshResourcesContent: function (nodeLevel, nodeId) {
    var myURL = UTILITIES.DASH.const.resourcesRefresh

    $.ajax({
      type: 'post',
      url: myURL,
      dataType: 'json',
      data: {
        nodeLevel: nodeLevel,
        nodeId: nodeId
      },
      error: function (err) {
        console.log(err)
        UTILITIES.DASH.errorMessage()
      },
      success: function (data) {
        var labelText = this.data.split('nodeId=')

        tinymce.activeEditor.setContent(data.message)
        tinyMCE.activeEditor.startContent = tinyMCE.activeEditor.save() // will reset dirty flag?

        $('#currentResources').html(data.message)
        $('.custom-admin-dash form').removeClass('dirty')
        $('.content-panel.resources .save-button').prop('disabled', true)
        $('#currentResourceIndicator').text(labelText[1])
      }
    })
  },
  initBlogForm: function () {
    var checkRoleBoardLevel = function (blogId) {
      var myURL = UTILITIES.DASH.const.checkRoleBoard

      $.ajax({
        type: 'post',
        url: myURL,
        dataType: 'json',
        data: {
          boardId: blogId,
          catId: UTILITIES.DASH.getPramFromUrl('id')
        },
        error: function (err) {
          console.log(err)
          UTILITIES.DASH.errorMessage()
        },
        success: function (data) {
          var continueOn = function () {
            var blogId = $('#blogAuthors').val() || ''
            var roleStr = $('#rolePicker').val() || ''
            var roleIDtemplate = 'b:{0}:{1}'
            var roleName = roleIDtemplate.replace('{0}', blogId).replace('{1}', roleStr)

            // clear input for user id
            $('#lookupUser').val('')

            if (blogId !== '' && roleStr !== '') {
              UTILITIES.DASH.getRoles(roleName, blogId, null)
            } else {
              $('.lookupUser, .lookupUserLabel, .blog-author-tags, .blog-author-tags-label, #blogAuthorPagedTags').addClass('hidden')
              $('#rolePicker, #rolePickerLabel').addClass('hidden')
            }
          }
          var boardLevelRoleStr = data['board-roles']
          console.log('Board Level: ' + boardLevelRoleStr)
          var hasRole = function (str) {
            return boardLevelRoleStr.indexOf(str) !== -1
          }
          var isAdmin = UTILITIES.DASH.const.isAdmin
          var enums = [
            '<option value="BlogAuthor">BlogAuthor</option>',
            '<option value="BlogPublisher">BlogPublisher</option>',
            '<option value="BlogAdmin">BlogAdmin</option>',
            '<option value="BlogEditor">BlogEditor</option>'
          ]
          var allowedEnums = []
          // stash the value used compare before refreshing enums
          var roleEnumValue = $('#rolePicker').val()
          var collection
          var len
          var i

          $('.no-blog-perm').addClass('hidden')

          // show enums based on the role
          if (boardLevelRoleStr === '' && blogId === '') {
            $('#rolePicker').html('')
            $('.no-blog-perm').removeClass('hidden')
          } else if (hasRole('ProductAdmin') || isAdmin === 'true') {
            allowedEnums = enums.slice(0).sort()
            $('#rolePicker').html(allowedEnums)
          } else if (hasRole('BlogAdmin')) {
            allowedEnums = enums.slice(0).sort()
            $('#rolePicker').html(allowedEnums)
          } else {
            // no permissions
            $('#rolePicker').html('')
            $('.no-blog-perm').removeClass('hidden')
          }

          if (roleEnumValue && roleEnumValue !== $('#rolePicker').val()) {
            collection = $('#rolePicker option')
            len = collection.length

            for (i = 0; i < len; i++) {
              if (collection[i].value === roleEnumValue) {
                // if it exists set the value
                $('#rolePicker').val(roleEnumValue)
                break
              }
            }
          }

          $('#rolePicker, #rolePickerLabel').removeClass('hidden')

          continueOn()
        }
      })
    }

    $('#blogAuthors, #rolePicker').change(function () {
      var blogId = $('#blogAuthors').val()

      $('#blogAuthorPagedTags .data-container').html('<div class="spinner"></div>')
      checkRoleBoardLevel(blogId)
    })

    $('#go').click(function (evt) {
      var userId = $('#lookupUser').val()
      var ACTION = 'addRole'
      var patt = /[0-9]/g
      var isValid = patt.test(userId)

      evt.preventDefault()

      if (isValid) {
        $('.error-label.error-lookup-user').addClass('hidden')
        $(this).prop('disabled', true)

        UTILITIES.DASH.getUserInfo(userId, ACTION)
      } else {
        $('.error-label.error-lookup-user').removeClass('hidden')
        $(this).prop('disabled', false)
      }
    })

    $('#blogAuthorPagedTags').click(function (e) {
      var userId
      var ACTION = 'removeRole'

      if (e.target.className === 'remove') {
        userId = $(e.target).prev().attr('data-author-id')
        UTILITIES.DASH.getUserInfo(userId, ACTION)
      }
    })
  },
  initGeneralForm: function () {
    $('.content-panel.general .save-button').prop('disabled', true)

    $('.content-panel.general .save-button').click(function (e) {
      var myURL = UTILITIES.DASH.const.generalUpdate
      var announcementValue = tinyMCE.activeEditor.getContent()
      var myParam = UTILITIES.DASH.getPramFromUrl('id')

      e.preventDefault()
      $('.content-panel.general .save-button').prop('disabled', true)

      if ($('#formGeneral').hasClass('dirty')) {
        $.ajax({
          type: 'post',
          url: myURL,
          dataType: 'json',
          data: {
            catId: myParam,
            announcement: announcementValue
          },
          error: function (err) {
            console.log(err)
            UTILITIES.DASH.errorMessage()
          },
          success: function (data) {
            $('.content-panel.general .save-button').prop('disabled', true)
            UTILITIES.DASH.successMessage()

            setTimeout(function () {
              $('#formGeneral').removeClass('dirty')
              $('.custom-admin-dash-wrapper-tabs > .active > a').click()
            }, 3000)
          }
        })
      }
    })

    $('.content-panel.general .cancel-button').click(function (e) {
      e.preventDefault()

      if ($('#formGeneral').hasClass('dirty')) {
        if (window.confirm('Are you sure?\nDiscard Changes?')) {
          tinymce.remove('textarea')
          $('#formGeneral').removeClass('dirty')
          $('.custom-admin-dash-wrapper-tabs li.active a').click()
        }
      }
    })

    tinymce.remove('textarea')

    tinymce.init({
      selector: '#announcementMessage',
      toolbar: 'bold | italic | link | image | numlist | bullist | code',
      plugins: 'lists link image code',
      branding: false,
      convert_urls: false,
      init_instance_callback: function (editor) {
        editor.on('Dirty', function (e) {
          $('#formGeneral').addClass('dirty')
          $('.content-panel.general .save-button').prop('disabled', false)
        })
      }
    })
  },
  initResourcesForm: function () {
    $('.content-panel.resources .save-button').prop('disabled', true)

    $('.content-panel.resources .save-button').click(function (e) {
      var myURL = UTILITIES.DASH.const.resourcesUpdate
      var resourcesValue = tinyMCE.activeEditor.getContent()
      var myParam = UTILITIES.DASH.getPramFromUrl('id')
      var mode = $('#resourcesSelectMode').val()
      var boardId = $('#resourcesSelectBoard').val()

      e.preventDefault()
      $('.content-panel.resources .save-button').prop('disabled', true)

      if ($('#formResources').hasClass('dirty')) {
        $.ajax({
          type: 'post',
          url: myURL,
          dataType: 'json',
          data: {
            catId: (mode === 'category') ? myParam : boardId,
            resources: resourcesValue,
            nodeLevel: (mode === 'board') ? 'boards' : 'categories'
          },
          error: function (err) {
            console.log(err)
            UTILITIES.DASH.errorMessage()
          },
          success: function (data) {
            $('.content-panel.resources .save-button').prop('disabled', true)
            UTILITIES.DASH.successMessage()

            setTimeout(function () {
              $('#formResources').removeClass('dirty')
              $('.custom-admin-dash-wrapper-tabs > .active > a').click()
            }, 3000)
          }
        })
      }
    })

    $('.content-panel.resources .cancel-button').click(function (e) {
      e.preventDefault()

      if ($('#formResources').hasClass('dirty')) {
        if (window.confirm('Are you sure?\nDiscard Changes?')) {
          tinymce.remove('textarea')
          $('#formResources').removeClass('dirty')
          $('.custom-admin-dash-wrapper-tabs li.active a').click()
        }
      }
    })

    $('#resourcesSelectMode').change(function () {
      var myVal = $(this).val()
      var categoriesVal = UTILITIES.DASH.getPramFromUrl('id')

      if (myVal === 'board') {
        $('#resourcesSelectBoard').prop('disabled', false)
      } else {
        $('#resourcesSelectBoard').prop('disabled', true)
        $('#resourcesSelectBoard').val('')
        UTILITIES.DASH.refreshResourcesContent('categories', categoriesVal)
      }
    })

    $('#resourcesSelectBoard').change(function () {
      var myVal = $(this).val()
      var categoriesVal = UTILITIES.DASH.getPramFromUrl('id')

      // categories or boards
      if (myVal !== '') {
        UTILITIES.DASH.refreshResourcesContent('boards', myVal)
        $(this).prop('disabled', false)
        $('#resourcesSelectMode').val('board')
      } else {
        UTILITIES.DASH.refreshResourcesContent('categories', categoriesVal)
        $(this).prop('disabled', true)
        $('#resourcesSelectMode').val('category')
      }
    })

    tinymce.remove('textarea')

    tinymce.init({
      selector: '#currentResources',
      toolbar: 'bold | italic | link | image | numlist | bullist | code',
      plugins: 'lists link image code',
      branding: false,
      convert_urls: false,
      init_instance_callback: function (editor) {
        editor.on('Dirty', function (e) {
          $('#formResources').addClass('dirty')
          $('.content-panel.resources .save-button').prop('disabled', false)
        })
      }
    })
  },
  initIdeasForm: function () {
    if ($('.opacity-layer-ideas').length === 0) {
      // click layer for colorpicker
      $('body').prepend('<div class="opacity-layer-ideas hidden"></div>')
      $('.opacity-layer-ideas').click(function () {
        $('.color-picker-container').addClass('hidden')
        $(this).addClass('hidden')
      })
    }

    $('.content-panel.ideas .save-button').prop('disabled', true)
    $('#formIdeas').removeClass('dirty')

    $('.idea-status-table .clear-colors').click(function (e) {
      e.preventDefault()
      UTILITIES.DASH.colorPicker.removeAllColors()
    })

    $('.idea-status-table').on('idea-status-table:addColors', function () {
      $('.color-picker > .wrapper > a').click(function (e) {
        e.preventDefault()
        var ele = $(this).parent().find('.color-picker-container')

        if ($(ele).hasClass('hidden')) {
          $('.color-picker-container').addClass('hidden')
          $(ele).removeClass('hidden')
          $('.opacity-layer-ideas').removeClass('hidden')
        } else {
          $(ele).addClass('hidden')
          $('.opacity-layer-ideas').addClass('hidden')
        }
      })

      $('li.chip').click(function (e) {
        var i
        var ele = $(this)
        var myColor = $(this).attr('data-color')
        var pillColor
        var previousPillColor
        var pill = $(this).closest('tr').find('.pill')
        var collection
        var len
        var isValidColorChip = $(e.currentTarget).hasClass('chip colored')

        if (isValidColorChip) {
          // find the row color the chip
          if (pill.hasClass('colored')) {
            previousPillColor = pill.attr('data-color')
            UTILITIES.DASH.colorPicker.available.push(previousPillColor.slice(1))
          }

          pill.css({ 'background-color': myColor, 'border-color': myColor }).addClass('colored').attr('data-color', myColor)
          UTILITIES.DASH.colorPicker.colorSelected(myColor, ele)
          collection = $('.pill.colored')
          len = collection.length
          UTILITIES.DASH.colorPicker.selected = []

          for (i = 0; i < len; i++) {
            pillColor = $(collection[i]).attr('data-color')
            UTILITIES.DASH.colorPicker.selected.push(pillColor.slice(1))
          }

          $('#formIdeas').addClass('dirty')
          $('.content-panel.ideas .save-button').prop('disabled', false)
          // console.log(UTILITIES.DASH.colorPicker.selected)
        }
      })

      UTILITIES.DASH.colorPicker.pickerSelectUpdate()
      UTILITIES.DASH.colorPicker.colorChips()
    })

    $('.content-panel.ideas .save-button').click(function (e) {
      var i
      var colors = []
      var color
      var formValues = {}
      var myURL = UTILITIES.DASH.const.ideasStatusColorsAdd
      var myID = $('#selectIdeasBoard').val()
      var collection = $('.pill')
      var len = collection.length;
      var dash = UTILITIES.DASH

      for (i = 0; i < len; i++) {
        if ($(collection[i]).hasClass('colored')) {
          color = $(collection[i]).attr('data-color')
        } else {
          color = 'x'
        }
        colors.push(color)
      }

      formValues = {
        boardId: myID,
        colors: colors.length === 0 ? '' : colors.join(',')
      }

      e.preventDefault()
      $('.content-panel.ideas .save-button').prop('disabled', true)

      if ($('#formIdeas').hasClass('dirty')) {
        $.ajax({
          type: 'post',
          url: myURL,
          dataType: 'json',
          data: formValues,
          error: function (err) {
            console.log(err)
            UTILITIES.DASH.errorMessage()
          },
          success: function (data) {
            $('.content-panel.ideas .save-button').prop('disabled', true)
            UTILITIES.DASH.successMessage()

            setTimeout(function () {
              $('#formIdeas').removeClass('dirty')
              $('.custom-admin-dash-wrapper-tabs > .active > a').click()
            }, 3000)
          }
        });

        // save idea welcom message
        $.ajax({
          type: 'post',
          url: dash.const.ideaWelcomeMessage,
          data: {
            boardId: myID,
            message: tinyMCE.activeEditor.getContent()
          },
          dataType: 'html',
          error: function (jqXHR, exception) {
            console.log(exception);
          }
        });
      }
    })

    $('.content-panel.ideas .cancel-button').click(function (e) {
      e.preventDefault()

      if ($('#formIdeas').hasClass('dirty')) {
        if (window.confirm('Are you sure?\nDiscard Changes?')) {
          $('#formIdeas').removeClass('dirty')
          $('.custom-admin-dash-wrapper-tabs li.active a').click()
        }
      }
    })

    $('#selectIdeasBoard').change(function () {
      var myID = $(this).val()
      var dash = UTILITIES.DASH
      var myURL = dash.const.ideasStatus
      var AVAIL = dash.colorPicker.AVAIL.slice(0)

      UTILITIES.DASH.colorPicker.available = AVAIL
      $('.content-panel.ideas .save-button').prop('disabled', true)

      if (myID !== '') {
        $.ajax({
          type: 'post',
          url: myURL,
          dataType: 'json',
          data: {
            boardId: myID
          },
          scope: {
            boardId: myID
          },
          error: function (err) {
            console.log(err)
            UTILITIES.DASH.errorMessage()
          },
          success: function (data) {
            var i
            var scope = this.scope
            var myURL = UTILITIES.DASH.const.ideasStatusColorsGet
            var enums = data.enums || []
            var len = enums.length
            var myMarkup = ''
            var emptyRow = '<tr><td colspan="2" class="empty">No items to dispay</td></tr>'
            var colorPickerTemplate = '<div class="color-picker"><div class="wrapper"><a href="#">Edit</a>' +
              '<div class="color-picker-container arrow_box hidden"><ul class="chips">' +
              '<li class="chip"></li><li class="chip"></li><li class="chip"></li><li class="chip"></li>' +
              '<li class="chip"></li><li class="chip"></li><li class="chip"></li><li class="chip"></li>' +
              '<li class="chip"></li><li class="chip"></li><li class="chip"></li><li class="chip"></li>' +
              '</ul></div></div></div>'
            var template = '<tr><td class="name"><span class="pill">{0}</span></td><td class="colors">{1}</td></tr>'

            template = template.replace('{1}', colorPickerTemplate)

            for (i = 0; i < len; i++) {
              myMarkup += template.replace('{0}', enums[i].name)
            }

            if (len > 0) {
              $('.idea-status-table tbody').html(myMarkup)
              $('.bulk-actions').removeClass('hidden')

              $.ajax({
                type: 'post',
                url: myURL,
                dataType: 'json',
                data: {
                  boardId: scope.boardId
                },
                error: function (err) {
                  console.log(err)
                  UTILITIES.DASH.errorMessage()
                },
                success: function (data) {
                  // color pills and process stored colored data
                  // ideal format for data.statusColors is '#fdb813,x,#044881,x,x,x'
                  var i
                  var collection = $('.idea-status-table .pill')
                  var len = collection.length
                  var storedArr = []

                  if (data.hasOwnProperty('statusColors')) {
                    if (data.statusColors !== '') {
                      storedArr = data.statusColors.split(',')

                      // process & color the pills
                      for (i = 0; i < len; i++) {
                        if (storedArr[i] && storedArr[i].length >= 4) {
                          $(collection[i]).addClass('colored').attr('data-color', storedArr[i]).css({ 'background-color': storedArr[i], 'border-color': storedArr[i] })
                        } else {
                          storedArr[i] = 'x' // placeholder
                        }
                      }
                    }
                  }

                  // console.log('placeholder: ' + (storedArr.length !== 0 ? storedArr.join(',') : 'none'))
                  // store in colorPicker continue on
                  UTILITIES.DASH.colorPicker.selected = storedArr
                  $('.idea-status-table').trigger('idea-status-table:addColors', [])
                }
              })
            } else {
              // empty row in grid
              $('.idea-status-table tbody').html(emptyRow)
              $('.bulk-actions').addClass('hidden')

              if (data.hasOwnProperty('status') && data.status === 'error') {
                console.log(data)
                UTILITIES.DASH.errorMessage()
              }
            }
          }
        });
        // load idea welcom message
        $.ajax({
          type: 'post',
          url: dash.const.ideaWelcomeMessage,
          data: {
            boardId: myID
          },
          dataType: 'html',
          error: function (jqXHR, exception) {
          },
          success: function (data) {
            tinymce.activeEditor.setContent(data)
            tinyMCE.activeEditor.startContent = tinyMCE.activeEditor.save();
            $('#idea-welcome-message').html(data.message);

            $('.content-panel.ideas .save-button').prop('disabled', true)
            $('#formIdeas').removeClass('dirty')
          }
        });

      } else {
        // no value so empty
        $('.idea-status-table tbody').html('<tr><td colspan="2" class="empty">No items to dispay</td></tr>')
        $('.bulk-actions').addClass('hidden')
      }
    })

    tinymce.remove('textarea')
    tinymce.init({
      selector: '#idea-welcome-message',
      toolbar: 'bold | italic | link | image | numlist | bullist | code',
      plugins: 'lists link image code',
      branding: false,
      convert_urls: false,
      init_instance_callback: function (editor) {
        editor.on('Dirty', function (e) {
          $('#formIdeas').addClass('dirty');
          $('.content-panel.ideas .save-button').prop('disabled', false);
        });
      }
    });
  },
  initLabelsForm: function () {
    $('#selectBoard').on('dashLabels:RolesChecked', function (e, boardId, userRole) {
      var myURL = UTILITIES.DASH.const.labelsPredefined

      // make request for fields in this board
      if (boardId !== '') {
        $.ajax({
          type: 'post',
          url: myURL,
          dataType: 'json',
          data: {
            boardId: boardId
          },
          scope: {
            userRole: userRole
          },
          error: function (err) {
            console.log(err)
            UTILITIES.DASH.errorMessage()
          },
          success: function (data) {
            var scope = this.scope
            var userRole = scope.userRole
            var isAdmin = UTILITIES.DASH.const.isAdmin

            var labelPicker = function (labelStr) {
              var labels = labelStr.length > 0 ? labelStr.split(',') : []
              var i
              var len = labels.length
              var markup = ''
              var labelTagTemplate = '<div class="label-tag"><span class="label-tag-text">{0}</span><span class="remove">&times;</span></div>'
              var item

              if (len > 0) {
                for (i = 0; i < len; i++) {
                  item = labelTagTemplate.replace('{0}', labels[i].trim())
                  markup += item
                }
              } else {
                markup += UTILITIES.DASH.lang.labels.EMPTY_MSG
              }

              return markup
            }
            var setControlValue = function (ele, val) {
              var i
              var collection = $(ele + ' option')
              var len = collection.length

              for (i = 0; i < len; i++) {
                if ($(collection[i]).val() === val) {
                  $(collection[i]).prop('selected', true)
                  break
                }
              }
            }

            setControlValue('#requireLabels', data.required)
            setControlValue('#labelType', data.allowed)

            $('#predefinedLabels').val(data.labels)
            $('.label-tags-container').html(labelPicker(data.labels))

            // BlogAdmin, BlogAuthor, GuestBlogger should not have access to settings for labels
            if (userRole === 'ProductAdmin' || userRole === 'BlogModerator' || isAdmin === 'true') {
              $('.label-setting, #requireLabels, #labelType').removeClass('hidden')
            } else {
              // showing only labels controls
              $('.label-tags-container, .add-label, #addLabel, label[for=addLabel]').removeClass('hidden')
              $('.label-tags-container').css('padding-top', '15px')
            }

            $('.label-tags-container').click(function (e) {
              if ($(e.target).hasClass('remove')) {
                $(e.target).closest('.label-tag').remove()
                $('#predefinedLabels').val(UTILITIES.DASH.refreshPredefinedLabelsValue())
                $('#formLabels').trigger('formLabels:isDirty', [])

                if ($('.label-tags-container').text() === '') {
                  $('.label-tags-container').html(UTILITIES.DASH.lang.labels.EMPTY_MSG)
                }
              }
            })

            $('.content-panel.labels #go').click(function (e) {
              var labelTagTemplate = '<div class="label-tag"><span class="label-tag-text">{0}</span><span class="remove">&times;</span></div>'
              var tagsContainerCont = $('.label-tags-container').text()
              var myLabelText = $('#addLabel').val()

              e.preventDefault()

              if (myLabelText !== '') {
                if (tagsContainerCont !== 'There are no predefined labels') {
                  $('.label-tags-container').append(labelTagTemplate.replace('{0}', myLabelText))
                } else {
                  $('.label-tags-container').html(labelTagTemplate.replace('{0}', myLabelText))
                }

                $('#predefinedLabels').val(UTILITIES.DASH.refreshPredefinedLabelsValue())
                $('#formLabels').trigger('formLabels:isDirty', [])
                $('#addLabel').val('')
              }
            })

            $('.content-panel.labels .cancel-button').click(function (e) {
              e.preventDefault()

              if ($('#formLabels').hasClass('dirty')) {
                if (window.confirm('Are you sure?\nDiscard Changes?')) {
                  $('#formLabels').removeClass('dirty')
                  $('.custom-admin-dash-wrapper-tabs li.active a').click()
                }
              }
            })

            $('.content-panel.labels .save-button').prop('disabled', true)
            $('.content-panel.labels .save-button').click(function (e) {
              var myURL = UTILITIES.DASH.const.labelsUpdate
              var formValues = {
                boardId: $('#selectBoard').val(),
                requireLabels: $('#requireLabels').val(),
                labelType: $('#labelType').val(),
                predefinedLabels: $('#predefinedLabels').val()
              }

              e.preventDefault()
              $('.content-panel.labels .save-button').prop('disabled', true)

              if ($('#formLabels').hasClass('dirty')) {
                $.ajax({
                  type: 'post',
                  url: myURL,
                  dataType: 'json',
                  data: formValues,
                  error: function (err) {
                    console.log(err)
                    UTILITIES.DASH.errorMessage()
                  },
                  success: function (data) {
                    $('.content-panel.labels .save-button').prop('disabled', true)
                    UTILITIES.DASH.successMessage()

                    setTimeout(function () {
                      $('#formLabels').removeClass('dirty')
                      $('.custom-admin-dash-wrapper-tabs > .active > a').click()
                    }, 3000)
                  }
                })
              }
            })
          }
        })
      } else {
        $('.label-setting, #requireLabels, #labelType').addClass('hidden')
      }

      $('#formLabels').on('formLabels:isDirty', function () {
        $('#formLabels').addClass('dirty')
        $('.content-panel.labels .save-button').prop('disabled', false)
      })

      $('#requireLabels, #labelType').change(function () {
        $('#formLabels').trigger('formLabels:isDirty', [])
      })
    })

    $('#selectBoard').change(function () {
      var myURL = UTILITIES.DASH.const.checkRoleBoard
      var blogId = $(this).val() || ''

      if (blogId !== '') {
        $.ajax({
          type: 'post',
          url: myURL,
          dataType: 'json',
          data: {
            boardId: blogId,
            catId: UTILITIES.DASH.getPramFromUrl('id')
          },
          error: function (err) {
            console.log(err)
            UTILITIES.DASH.errorMessage()
          },
          success: function (data) {
            var boardLevelRoleStr = data['board-roles']
            var boardId = $('#selectBoard').val()
            $('.label-setting, #requireLabels, #labelType').addClass('hidden').removeAttr('style')
            $('#selectBoard').trigger('dashLabels:RolesChecked', [boardId, boardLevelRoleStr])
          }
        })
      } else {
        // hide the content
        $('.label-setting, #requireLabels, #labelType').addClass('hidden').removeAttr('style')
      }
    })
  },
  fetchFormContent: function (cont, catId, isSynthetic) {
    var checkRole = function () {
      var checkRoleURL = UTILITIES.DASH.const.checkRole

      $.ajax({
        type: 'post',
        url: checkRoleURL,
        dataType: 'json',
        data: {
          catId: catId
        },
        scope: {
          cont: cont,
          isSynthetic: isSynthetic
        },
        error: function (err) {
          console.log(err)
          UTILITIES.DASH.errorMessage()
        },
        success: function (data) {
          console.log('categoryCheck: ' + data.roles)
          // role biz logic
          var rolesStr = data.roles
          var userHasRole = function (roleName) {
            return rolesStr.indexOf(roleName) !== -1
          }
          var tabToShow = ''
          var fullPermission = function () {
            $('.no-perm').addClass('hidden')
            $('.custom-admin-dash-wrapper-tabs').removeClass('hidden')
            $('.custom-admin-dash-wrapper-tabs li').show()
            $('.custom-admin-dash-wrapper-container').removeClass('hidden')
          }
          var noPermission = function () {
            $('.no-perm').removeClass('hidden')
            $('.custom-admin-dash-wrapper-tabs').addClass('hidden')
            $('.custom-admin-dash-wrapper-container').addClass('hidden')
            $('.custom-admin-dash .spinner').remove()
          }
          var blogsTwoTab = function () {
            fullPermission()
            $('li#general').hide()
            $('li#resources').hide()
            $('li#ideas').hide()
          }
          var ideasTwoTab = function () {
            fullPermission()
            $('li#general').hide()
            $('li#resources').hide()
            $('li#blogs').hide()
          }

          // userHasRole('ProductAdmin')
          // userHasRole('BlogModerator')
          // userHasRole('BlogAdmin')
          // userHasRole('BlogAuthor')
          // userHasRole('PrivateBoardAdmin')

          if (data.status === 'success') {
            if (userHasRole('ProductAdmin')) {
              fullPermission()
            } else if (userHasRole('BlogAdmin')) {
              blogsTwoTab()
              tabToShow = 'blogs'
            } else if (userHasRole('IdeasAdmin')) {
              ideasTwoTab()
              tabToShow = 'ideas'
            } else {
              noPermission()
            }
          } else {
            noPermission()
          }

          // role determines "tabToShow" and becomes active tab
          $('.custom-admin-dash').trigger('dash:roleChecked', [tabToShow, isSynthetic]).off()
        }
      })
    }

    $('.content-panel.' + cont + ' form').html('<div class="spinner"></div>')
    $('.content-panel.' + cont).show()

    $('.custom-admin-dash').on('dash:roleChecked', function (e, roleKey, isSynthetic) {
      var urlMap = {
        general: UTILITIES.DASH.const.general,
        resources: UTILITIES.DASH.const.resources,
        blogs: UTILITIES.DASH.const.blogs,
        labels: UTILITIES.DASH.const.labels,
        ideas: UTILITIES.DASH.const.ideas
      }
      // uses closure cont and catId
      // full permissions
      var myURL = urlMap[cont]
      var myKey = cont

      if (roleKey !== '' && isSynthetic === true) {
        // fetch proper content for the role
        myKey = roleKey
        myURL = urlMap[myKey]
        $('.content-panel form').empty()
        $('.content-panel.' + myKey + ' form').html('<div class="spinner"></div>')
        $('.content-panel').hide()
        $('.content-panel.' + myKey).show()
        $('.custom-admin-dash-wrapper-tabs li').removeClass('active')
        $('.custom-admin-dash-wrapper-tabs li a[data-content="' + myKey + '"]').parent().addClass('active')
      }

      $.ajax({
        type: 'post',
        url: myURL,
        dataType: 'html',
        data: {
          catId: catId
        },
        scope: myKey,
        error: function (err) {
          console.log(err)
          UTILITIES.DASH.errorMessage()
        },
        success: function (data) {
          $('.content-panel.' + this.scope + ' form').html(data)

          // each form will have its own init
          if (this.scope === 'blogs') {
            UTILITIES.DASH.initBlogForm()
          } else if (this.scope === 'general') {
            UTILITIES.DASH.initGeneralForm()
          } else if (this.scope === 'resources') {
            UTILITIES.DASH.initResourcesForm()
          } else if (this.scope === 'labels') {
            UTILITIES.DASH.initLabelsForm()
          } else if (this.scope === 'ideas') {
            UTILITIES.DASH.initIdeasForm()
          }
        }
      })
    })

    if (UTILITIES.DASH.const.isAdmin === 'false') {
      checkRole()
    } else {
      $('.custom-admin-dash').trigger('dash:roleChecked').off()
    }
  },
  init: function (configMap) {
    // freemarker constants
    UTILITIES.DASH.const = configMap

    $('.cat-select').change(function () {
      var myVal = $(this).val()
      var urlTemplate = '/t5/custom/page/page-id/admin-dashboard?id={0}'
      var myContent
      var collection = $('.custom-admin-dash-wrapper .custom-admin-dash-wrapper-tabs li')
      var i
      var len = collection.length
      var isSynthetic = true

      if (UTILITIES.DASH.leaveFormDirty()) {
        for (i = 0; i < len; i++) {
          if ($(collection[i]).hasClass('active')) {
            myContent = $(collection[i]).find('a').attr('data-content')
            break
          }
        }

        window.history.pushState('', '', urlTemplate.replace('{0}', myVal))
        // empty all forms
        $('.content-panel form').removeClass('dirty')
        $('.custom-admin-dash-wrapper .content-panel form').empty()
        // refresh active form
        UTILITIES.DASH.fetchFormContent(myContent, myVal, isSynthetic)
      } else {
        // reset the value
        UTILITIES.DASH.setSelectFromParam()
      }
    })

    $('.custom-admin-dash-wrapper .custom-admin-dash-wrapper-tabs a').click(function (e) {
      var myContent = $(this).attr('data-content')
      var catId = UTILITIES.DASH.getPramFromUrl('id')
      // programatic click event
      var isSynthetic = !e.hasOwnProperty('originalEvent')

      e.preventDefault()

      if (UTILITIES.DASH.leaveFormDirty()) {
        $('.custom-admin-dash-wrapper-tabs li').removeClass('active')
        $(this).parent().addClass('active')
        $('.content-panel:visible form').removeClass('dirty')
        $('.content-panel').hide()

        // empty all forms
        $('.custom-admin-dash-wrapper .content-panel form').empty()
        UTILITIES.DASH.fetchFormContent(myContent, catId, isSynthetic)
      }
    }).filter(':first').click()

    UTILITIES.DASH.setSelectFromParam()
    $('.go-back-history').click(function (e) {
      var template = '/t5/Access/ct-p/{0}'
      var myParam = UTILITIES.DASH.getPramFromUrl('id')

      e.preventDefault()
      window.location.href = template.replace('{0}', myParam)
    })
  }
}
UTILITIES.carousel_init = function (singleEle) {
  // custom pause, live-area-messaging, focus after rotate
  // HTML attribute 'data-custom-play-menu' required
  // .ele-to-focus CSS required on item to focus after rotation
  var carouselCollection = $('.carousel.slide')
  var markup = '<button class="pause-btn" aria-label="pause button to stop auto sliding"><i class="lia-fa lia-fa-pause"></i> Pause</button>' +
    '<button class="play-btn" style="display: none" aria-label="play button to resume auto sliding"><i class="lia-fa lia-fa-play"></i> Play</button>'

  if (singleEle) {
    // init a single carousel
    carouselCollection = $(singleEle)
  }

  carouselCollection.each(function () {
    var myId = $(this).attr('id') ? '#' + $(this).attr('id') : undefined

    if ($(this)[0].hasAttribute('data-custom-play-menu') && myId) {
      $(this).find('.carousel-indicators').wrap('<div class="carousel-footer-wrapper"></div>')
      $(this).find('.carousel-footer-wrapper').prepend(markup)

      $(myId).delegate('.pause-btn', 'keypress click', function (e) {
        if ((e.type === 'click') || (e.type === 'keypress' && e.keyCode === 13)) {
          $(myId).find('.play-btn').show()
          $(myId).find('.pause-btn').hide()
          $(myId).find('.play-btn').focus()
          $(myId).carousel('pause')
          UTILITIES.accessibleAlert('pageLevel', 'Carousel paused')
        }
      })

      $(myId).delegate('.play-btn', 'keypress click', function (e) {
        if ((e.type === 'click') || (e.type === 'keypress' && e.keyCode === 13)) {
          $(myId).find('.play-btn').hide()
          $(myId).find('.pause-btn').show()
          $(myId).find('.pause-btn').focus()
          $(myId).carousel('cycle')
          UTILITIES.accessibleAlert('pageLevel', 'Carousel started')
        }
      })

      $(myId).on('slide.bs.carousel', function () {
        if (document.getElementById(myId.slice(1)).contains(document.activeElement)) {
          UTILITIES.accessibleAlert('pageLevel', 'Carousel rotation complete')
          setTimeout(function () {
            $(myId).find('.active .ele-to-focus > a').focus()
          }, 1000)
        }
      })
    }
  })
}
UTILITIES.skipLinksNavigationInit = function () {
  var pageKey
  var pageTemplateKey
  var navProperty
  var createLink = function (linkObj) {
    // takes linkObj and adds links to DOM
    var template = '<div class="skip-link"><a href="{0}">{1}</a></div>'
    var skipLink
    var wrapper = []
    var prepend = []

    if (linkObj.goto && linkObj.text) {
      skipLink = template.replace('{0}', linkObj.goto)
      skipLink = skipLink.replace('{1}', linkObj.text)

      if (linkObj.wrapper && linkObj.wrapper.length === 3) {
        // wrapAll and prepend
        wrapper = linkObj.wrapper
        $(wrapper[0]).wrapAll(wrapper[1])
        $(wrapper[2]).prepend(skipLink)
      } else if (linkObj.prepend && linkObj.prepend.length === 1) {
        // prepend using existing markup
        prepend = linkObj.prepend

        if (linkObj.prependID && linkObj.prependLabel) {
          $(prepend[0]).attr('id', linkObj.prependID).attr('aria-label', linkObj.prependLabel)
        }

        $(prepend[0]).prepend(skipLink)
      } else {
        // console.log('missing some info')
        console.log(linkObj)
      }
    }
  }
  /* skip link config
   * wrapper property <array> uses wrapAll method where you supply ele wrapper
   * wrapper[0] element(s) to wrap
   * wrapper[1] the wrapper element
   * wrapper[2] the selector id of wrapper element
   * OR
   * prepend property adds optional id and label copy to an existing element
   * prependID property is the id for existing element
   * prependLabel property is the label for existing element
   * OTHER
   * goto property provides inpage anchor for next item
   * text property is the link label for next item
   * pageObj ex: 'home' object is collection of all skip links on homepage
   * pageObj reusable but depends on DOM structure ex: home used on events home
   */
  var page = {
    home: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.lia-quilt-row-hero-content, .lia-quilt-row-banner-content',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#latestActivity',
        text: 'Skip to Latest Activity Feed'
      },
      feed: {
        wrapper: [
          '.category-recent-conversations-wrapper',
          '<section id="latestActivity" aria-label="latest activity feed"></section>',
          '#latestActivity'
        ],
        goto: '#sidebarContent',
        text: 'Skip to sidebar content'
      },
      sidebar: {
        prepend: ['.lia-quilt-column-side-content'],
        prependID: 'sidebarContent',
        prependLabel: 'sidebar content',
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    superCategory: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.community-banner-image',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#latestActivity',
        text: 'Skip to Latest Activity Feed'
      },
      feed: {
        prepend: ['.category-recent-conversations'],
        prependID: 'latestActivity',
        prependLabel: 'latest activity feed',
        // wrapper: [
        //   '.category-recent-conversations-wrapper',
        //   '<section id="latestActivity" aria-label="latest activity feed"></section>',
        //   '#latestActivity'
        // ],
        goto: '#sidebarContent',
        text: 'Skip to sidebar content'
      },
      sidebar: {
        prepend: ['.lia-quilt-column-side-content'],
        prependID: 'sidebarContent',
        prependLabel: 'sidebar content',
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    superBoard: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.custom-forum-component',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#latestActivity',
        text: 'Skip to Latest Activity Feed'
      },
      feed: {
        prepend: ['.forum-page-custom-message-list-wrapper'],
        prependID: 'latestActivity',
        prependLabel: 'latest activity feed',
        goto: '#sidebarContent',
        text: 'Skip to sidebar content'
      },
      sidebar: {
        prepend: ['.lia-quilt-column-side-content'],
        prependID: 'sidebarContent',
        prependLabel: 'sidebar content',
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    blogsLanding: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.custom-blog-hero',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#latestBlogArticles',
        text: 'Skip to Latest Blog Articles'
      },
      articlesTop: {
        wrapper: [
          '.custom-blog-articles-latest-top',
          '<section id="latestBlogArticles" aria-label="Latest Blog Articles"></section>',
          '#latestBlogArticles'
        ],
        goto: '#sidebarContent',
        text: 'Skip to sidebar content'
      },
      sidebar: {
        prepend: ['.lia-quilt-column-side-content'],
        prependID: 'sidebarContent',
        prependLabel: 'sidebar content',
        goto: '#moreBlogArticles',
        text: 'Skip to recent blog articles'
      },
      articlesBottom: {
        wrapper: [
          '.custom-blog-articles-latest-bottom',
          '<section id="moreBlogArticles" aria-label="recent blog articles"></section>',
          '#moreBlogArticles'
        ],
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    mtcEvents: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.lia-quilt-row-header, .lia-quilt-row-hero-content',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#communityEvents',
        text: 'Skip to Community Events'
      },
      events: {
        wrapper: [
          '.category-board-browser-wrapper',
          '<section id="communityEvents" aria-label="community events"></section>',
          '#communityEvents'
        ],
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    blogPage: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: ['.custom-forum-banner, .blog-bar',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#blogArticles',
        text: 'Skip to Recent Blog Articles'
      },
      articles: {
        wrapper: ['.lia-component-articles',
          '<section id="blogArticles" aria-label="recent blog articles"></section>',
          '#blogArticles'
        ],
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    blogArticlePage: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: ['.lia-quilt-blog-topic-message',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    forumTopicPage: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.lia-component-common-widget-page-title',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#topicMessage',
        text: 'Skip to Topic Message'
      },
      topic: {
        prepend: ['.lia-component-topic-message'],
        prependID: 'topicMessage',
        prependLabel: 'topic message',
        goto: '#sidebarContent',
        text: 'Skip to sidebar content'
      },
      sidebar: {
        prepend: ['.lia-quilt-column-side-content'],
        prependID: 'sidebarContent',
        prependLabel: 'sidebar content',
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    },
    ideaExchange: {
      primary: {
        prepend: ['#primaryNav'],
        goto: '#content',
        text: 'Skip to main content'
      },
      main: {
        wrapper: [
          '.custom-forum-component',
          '<main id="content" aria-label="main content"></main>',
          '#content'
        ],
        goto: '#latestActivity',
        text: 'Skip to Latest Ideas'
      },
      feed: {
        prepend: ['.custom-idea-filter-component'],
        prependID: 'latestActivity',
        prependLabel: 'latest ideas',
        goto: '#sidebarContent',
        text: 'Skip to sidebar content'
      },
      sidebar: {
        prepend: ['.custom-sidebar-welcome-component'],
        prependID: 'sidebarContent',
        prependLabel: 'sidebar content',
        goto: '#footerContent',
        text: 'Skip to footer content'
      },
      footer: {
        wrapper: [
          '.shell-footer-wrapper',
          '<footer id="footerContent" aria-label="footer content"></footer>',
          '#footerContent'
        ],
        goto: '#primaryNav',
        text: 'Skip to primary navigation'
      }
    }
  }

  var pageMap = {
    // keys = unique quilt class for page
    // value = page obj template for page
    'lia-quilt-community-page': 'home',
    'lia-quilt-category-page-events': 'home',
    'lia-quilt-category-page-supergroup': 'superCategory',
    'lia-quilt-forum-page-supergroup': 'superBoard',
    'lia-quilt-blogs': 'blogsLanding',
    'lia-quilt-category-page-mtc-events': 'mtcEvents',
    'lia-quilt-blog-page': 'blogPage',
    'lia-quilt-blog-article-page': 'blogArticlePage',
    'lia-quilt-forum-topic-page': 'forumTopicPage',
    'lia-quilt-idea-exchange-page': 'ideaExchange'
  }

  // find page template from quilt
  for (pageKey in pageMap) {
    if ($('.' + pageKey).length !== 0) {
      pageTemplateKey = pageMap[pageKey]
      break
    }
  }

  if (pageTemplateKey) {
    // console.log('page template: ' + pageTemplateKey)
    // create skip-links for page
    for (navProperty in page[pageTemplateKey]) {
      createLink(page[pageTemplateKey][navProperty])
    }
  }
}

UTILITIES.initHomeFiltersScoped = function (paramMap) {
  paramMap.page = paramMap.page || ''
  paramMap.endpointUrlValue = paramMap.endpointUrlValue || ''
  paramMap.countUrlValue = paramMap.countUrlValue || ''
  paramMap.board = paramMap.board || ''
  paramMap.eventTab = paramMap.eventTab || ''
  paramMap.currentPage = paramMap.currentPage || ''
  paramMap.pageSize = paramMap.pageSize || '5'
  paramMap.pageOffset = paramMap.pageOffset || ''
  paramMap.usr = paramMap.usr || ''
  paramMap.nodeId = paramMap.nodeId || ''
  paramMap.optional1 = paramMap.optional1 || ''
  paramMap.optional2 = paramMap.optional2 || ''
  paramMap.autoScrollBool = paramMap.autoScrollBool || false
  paramMap.repliesNum = paramMap.repliesNum || 1
  paramMap.parentElement = paramMap.parentElement || ''

  var parentElement = paramMap.parentElement;

  var totalCount = null

  var configMap = {
    newest: {
      totalCount: null
    },
    popular: {
      totalCount: null
    },
    experts: {
      totalCount: null
    },
    unanswered: {
      totalCount: null
    },
    unread: {
      totalCount: null
    },
    currentKey: 'newest',
    pageLoad: true,
    ttl: {
      val: null,
      isExpired: function () {
        var ret = true
        var d = new Date()
        var t = d.getTime()

        if (configMap.ttl.val !== null) {
          if (t < configMap.ttl.val) {
            ret = false
          }
        }

        return ret
      },
      setTTL: function () {
        var d = new Date()
        var t = d.getTime()

        configMap.ttl.val = t + 120000
      },
      clearTTL: function () {
        configMap.ttl.val = null
        configMap.newest.totalCount = null
        configMap.popular.totalCount = null
        configMap.experts.totalCount = null
        configMap.unanswered.totalCount = null
      }
    },
    itemKey: function () {
      var feedKeyTemplate = 'feed:' + paramMap.page + ':' + paramMap.nodeId
      return feedKeyTemplate
    },
    clearItem: function () {
      if (this.itemKey() !== null) {
        window.localStorage.removeItem(this.itemKey())
      }
    },
    getItem: function (key) {
      var isExpired = function (expires) {
        var d = new Date()
        var t = d.getTime()
        return (t > expires)
      }
      var isAnonymous = !!$('body').hasClass('lia-user-status-anonymous')
      var ret = {}
      var myRecord = JSON.parse(window.localStorage.getItem(this.itemKey())) || {}

      if (myRecord.isAnonymous !== isAnonymous) {
        // content only used if stored state match user's auth state
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && isExpired(myRecord.ttl)) {
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && !isExpired(myRecord.ttl) && myRecord.isAnonymous === isAnonymous) {
        ret = myRecord
      }
      return ret
    },
    setItem: function (key, content) {
      var myRecord = {
        ttl: this.ttl.val,
        content: $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html(),
        tab: this.currentKey,
        newest: this.newest.totalCount,
        popular: this.popular.totalCount,
        experts: this.experts.totalCount,
        unanswered: this.unanswered.totalCount,
        isAnonymous: !!$('body').hasClass('lia-user-status-anonymous')
      }

      // console.log('inside setItem fcn')
      // console.log(myRecord)

      window.localStorage.setItem(
        this.itemKey(),
        JSON.stringify(myRecord)
      )
    }
  }

  var showMoreConversations = function () {
    var existingContent = {}
    var calculateOffset = $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
    var myData = {
      board: paramMap.board,
      pageOffsetValue: calculateOffset,
      eventTab: paramMap.eventTab,
      filter: configMap.currentKey,
      replies: paramMap.repliesNum,
      pageSize: paramMap.pageSize
    }
    existingContent = configMap.getItem()

    // use localStorage:
    // when page is loading
    // when TTL valid

    if (existingContent.hasOwnProperty('content') && configMap.pageLoad === true && !configMap.ttl.val) {
      // only run once when page first load
      configMap.pageLoad = false

      // set the active tab
      configMap.currentKey = existingContent.tab

      $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html(existingContent.content)
      calculateOffset = $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
      $(parentElement + ' .welcome-spinner').addClass('hidden')

      // set filter UI
      $(parentElement + ' .home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $(parentElement + ' .home-feed-filters li').each(function () {
        if ($(this).attr('data') === configMap.currentKey) {
          $(this).addClass('active-filter')
        } else {
          $(this).removeClass('active-filter')
        }
      })

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', false)
      }
    } else if (existingContent.hasOwnProperty('content') && configMap.currentKey !== existingContent.tab &&
      calculateOffset > 0 && !configMap.ttl.isExpired()) {
      // localStorage refreshed with current filter and records displaying
      $(parentElement + ' .message-list > ul > li.conversation').hide()
      $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).show()
      $(parentElement + ' .welcome-spinner').addClass('hidden')

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', false)
      }

      // save in localStorage
      configMap.setItem()
    } else {
      // make request for items
      configMap.pageLoad = false

      $.ajax({
        type: 'post',
        url: paramMap.endpointUrlValue,
        dataType: 'html',
        data: myData,
        context: $(parentElement + ' .showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          $(parentElement + ' .recent-conversations-messages-wrapper .spinner').removeClass('hidden')
          $(parentElement + ' .error-msg').addClass('hidden').empty()

          if (calculateOffset > 0) {
            // load more items
            UTILITIES.accessibleAlert(parentElement + ' #homeRecentConvfeedMessages', 'Loading more')
          } else {
            if ($(parentElement + ' .refresh-feed').hasClass('in-progress')) {
              // specific feed refresh action
              UTILITIES.accessibleAlert(parentElement + ' #homeRecentConvfeedMessages', 'Feed refresh')
            } else {
              // loading feed
              UTILITIES.accessibleAlert(parentElement + ' #homeRecentConvfeedMessages', 'Loading')
            }
          }
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $(parentElement + ' .spinner').empty().remove()
        },
        success: function (data) {
          var checkExistingItems = function () {
            return $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
          }
          var itemToFocus = checkExistingItems()
          var template1 = paramMap.lang.noMessageText
          var template2 = 'Looks like you have not favorited any Conversation Spaces, ' +
            'which power this feed! Go to communities and favorite conversation spaces ' +
            'that interest you.'
          var markup

          $(parentElement + ' div.no-messages-container.lia-text').remove()
          $(parentElement + ' .recent-conversations-messages-wrapper .spinner').addClass('hidden')
          $(parentElement + ' .recent-conversations-messages-wrapper').removeClass('loadmore')
          $(parentElement + ' .refresh-feed').removeClass('in-progress')
          UTILITIES.accessibleAlert(parentElement + ' #homeRecentConvfeedMessages', 'Success')

          configMap.ttl.setTTL()

          if (data.indexOf('{{{0}}}') === -1) {
            $(parentElement + ' .showMoreMessagesHere').removeClass()
            $(parentElement + ' .recent-message-list').first().append('<ul class="showMoreMessagesHere"></ul>')

            if (checkExistingItems() > 0) {
              $(this).html(data)
              $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).eq(itemToFocus).find('.subject-link').focus()
            } else {
              $(this).html(data)

              // empty message in controller
              if (configMap[configMap.currentKey].totalCount === 0) {
                UTILITIES.accessibleAlert(parentElement + ' #homeRecentConvfeedMessages', template1)
              }
            }

            // set content in localStorage
            configMap.setItem()
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', false)
          } else if ((data.indexOf('{{{0}}}') !== -1 && checkExistingItems() > 0)) {
            // no more items to show
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
          } else {
            // messaging for no records
            if (paramMap.board === 'all') {
              markup = template1
            } else {
              markup = (configMap.currentKey === 'newest') ? template2 : template1
            }
            $(parentElement + ' .error-msg').removeClass('hidden').html(markup)
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
            configMap.ttl.clearTTL()
            $(parentElement + ' .recent-message-list').empty().html('<ul class="showMoreMessagesHere"></ul>')
          }

          // disable if total count is reached after records
          if ((checkExistingItems() >= totalCount)) {
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
          } else {
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', false)
          }

          $(parentElement + ' .message-list > ul > li.conversation').hide()
          $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).show()

          profileUserCardMe()

          $(parentElement + ' .recent-conversations-messages-wrapper').off()
          $(parentElement + ' .recent-conversations-messages-wrapper').on('click', '.reply-body', function () {
            var myHref = $(this).attr('data-href')

            if (myHref) {
              document.location = $(this).attr('data-href')
            }
          })
        }
      })
    }
  }

  var getTotalCount = function () {
    var myData = {
      usr: paramMap.usr,
      nodeId: paramMap.nodeId,
      board: paramMap.board,
      filter: configMap.currentKey
    }
    var makeRequest = true
    var existingContent = configMap.getItem()

    // console.log(existingContent)

    if (existingContent.hasOwnProperty('content')) {
      if (existingContent[configMap.currentKey] !== null) {
        makeRequest = false
        showMoreConversations()
      }
    }

    if (makeRequest) {
      $.ajax({
        type: 'post',
        url: paramMap.countUrlValue,
        dataType: 'json',
        data: myData,
        context: $(parentElement + ' .showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          $(parentElement + ' .recent-conversations-messages-wrapper .spinner').removeClass('hidden')
          $(parentElement + ' .error-msg').addClass('hidden').empty()
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $(parentElement + ' .spinner').empty().remove()
        },
        success: function (data) {
          totalCount = data.count
          configMap[configMap.currentKey].totalCount = data.count
          showMoreConversations()
        }
      })
    }
  }
  var purgeDuplicatesUnread = function () {
    var collection = $(parentElement + ' .conversation')
    var len = collection.length
    var i
    var dataId
    var uniques = []
    var emptyMessage = 'No messages.'

    $(collection).hide()

    for (i = 0; i < len; i++) {
      dataId = $(collection[i]).find('.post.unread').attr('data-id')
      if (dataId && $.inArray(dataId, uniques) === -1) {
        $(collection[i]).show()
        uniques.push(dataId)
      }
    }

    if (uniques.length === 0) {
      $(parentElement + ' .error-msg').html(emptyMessage).removeClass('hidden')
      UTILITIES.accessibleAlert(parentElement + ' #homeRecentConvfeedMessages', emptyMessage)
    } else {
      $(parentElement + ' .error-msg').empty().addClass('hidden')
    }
  }

  $(parentElement + ' .refresh-feed').on('click keypress', function (e) {
    if (e.type === 'click' || e.keyCode === 13) {
      configMap.clearItem()
      configMap.ttl.clearTTL()
      configMap.currentKey = 'newest'

      if (e.hasOwnProperty('originalEvent')) {
        // explicit user action taken is different
        // than programmatic click for screen readers
        $(this).addClass('in-progress')
      }

      // flush container and start over
      $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html('<ul class="showMoreMessagesHere"></ul>')
      $(parentElement + ' .home-feed-filters li[data=newest]').click()
    }
  })

  $(parentElement + ' .home-feed-filters-select').on('change', function (e) {
    var key = $(this).val()
    var currentFiltersItemsLength = $(parentElement + ' .message-list > ul > li.conversation-' + key).length
    var checkExistingItems = function () {
      return $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    configMap.currentKey = key

    $(parentElement + ' .home-feed-filters li').each(function () {
      if ($(this).attr('data') === configMap.currentKey) {
        $(this).addClass('active-filter')
      } else {
        $(this).removeClass('active-filter')
      }
    })

    $(parentElement + ' .message-list > ul > li.conversation').hide()
    $(parentElement + ' .error-msg').text('').addClass('hidden')

    if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
      configMap.ttl.setTTL()
    }

    if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
      // flush container and start over
      $(parentElement + ' .recent-message-list').html('<ul class="showMoreMessagesHere"></ul>')
      getTotalCount()
    } else {
      if (configMap.currentKey === 'unread') {
        purgeDuplicatesUnread()
        $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
      } else if (configMap[configMap.currentKey].totalCount) {
        if (currentFiltersItemsLength === 0) {
          showMoreConversations()
        } else {
          $(parentElement + ' .message-list > ul > li.conversation').hide()
          $(parentElement + ' .message-list > ul > li.conversation-' + key).show()

          // set content in localStorage
          configMap.setItem()

          if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
          } else {
            $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', false)
          }
        }
      } else {
        getTotalCount()
      }
    }
  })

  $(parentElement + ' .home-feed-filters li').on('keydown click', function (e) {
    var key
    var currentFiltersItemsLength
    var checkExistingItems = function () {
      return $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    if (e.keyCode === 13 || e.type === 'click') {
      key = $(this).attr('data')
      currentFiltersItemsLength = $(parentElement + ' .message-list > ul > li.conversation-' + key).length
      configMap.currentKey = key

      $(parentElement + ' .home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $(parentElement + ' .filter-tag').removeClass('active-filter')
      $(this).addClass('active-filter')
      $(parentElement + ' .message-list > ul > li.conversation').hide()
      $('.error-msg').text('').addClass('hidden')
      $(parentElement + ' div.no-messages-container').remove()

      if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
        configMap.ttl.setTTL()
      }

      if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
        // flush container and start over
        $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html('<ul class="showMoreMessagesHere"></ul>')
        getTotalCount()
      } else {
        if (configMap.currentKey === 'unread') {
          purgeDuplicatesUnread()
          $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
        } else if (configMap[configMap.currentKey].totalCount) {
          if (currentFiltersItemsLength === 0) {
            showMoreConversations()
          } else {
            $(parentElement + ' .message-list > ul > li.conversation').hide()
            $(parentElement + ' .message-list > ul > li.conversation-' + key).show()

            // set content in localStorage
            configMap.setItem()

            if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
              $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', true)
            } else {
              $(parentElement + ' #homeFeedShowMoreButton').prop('disabled', false)
            }
          }
        } else {
          getTotalCount()
        }
      }
    }
  })

  $(parentElement + ' .category-recent-conversations-component').on('click', function (e) {
    var offset = $(parentElement + ' .recent-conversations-messages-wrapper .message-list > ul > li').length
    var self

    if (e.target.id === 'homeFeedShowMoreButton') {
      e.target.blur()
      if (offset > 0) {
        // show only loader on the button
        $(parentElement + ' .recent-conversations-messages-wrapper').addClass('loadmore')
      }

      if (configMap[configMap.currentKey].totalCount) {
        showMoreConversations()
      } else {
        getTotalCount()
      }
    } else if ($(e.target).hasClass('kudo-add-button') || $(e.target).hasClass('kudo-remove-button')) {
      self = configMap
      setTimeout(function () {
        self.setItem()
      }, 500)
    }
  }).find('#homeFeedShowMoreButton').click()

  if (paramMap.autoScrollBool) {
    $(window).scroll(function () {
      if ($(window).scrollTop() >= $(parentElement + ' #homeFeedShowMoreButton').position().top &&
        $(parentElement + ' .welcome-spinner').hasClass('hidden') &&
        $(window).width() > 991 &&
        !$(parentElement + ' #homeFeedShowMoreButton').prop('disabled')) {
        $(parentElement + ' #homeFeedShowMoreButton').click()
      }
    })
  }

  $(parentElement + ' .trending-boards').click(function (e) {
    var board = paramMap.board
    if (e.target.parentElement.tagName === 'BUTTON' && board === 'favorite') {
      if ($(parentElement + ' .home-feed-filters .active-filter').attr('data') !== 'unread') {
        configMap.ttl.clearTTL()
        $(parentElement + ' .recent-message-list, .message-list').html('<ul class="showMoreMessagesHere"></ul>')
        $(parentElement + ' .home-feed-filters .active-filter').click()
      }
    }
  })

  $(window).on('beforeunload', function () {
    $('body').addClass('leaving-page')
  })
}

// Method for Category Page & Forum Page filters
UTILITIES.initForumCategoryFiltersScoped = function (paramMap) {
  paramMap.page = paramMap.page || ''
  paramMap.endpointUrlValue = paramMap.endpointUrlValue || ''
  paramMap.countUrlValue = paramMap.countUrlValue || ''
  paramMap.board = paramMap.board || ''
  paramMap.eventTab = paramMap.eventTab || ''
  paramMap.currentPage = paramMap.currentPage || ''
  paramMap.groupHub = paramMap.groupHub || ''
  paramMap.pageSize = paramMap.pageSize || ''
  paramMap.pageOffset = paramMap.pageOffset || ''
  paramMap.usr = paramMap.usr || ''
  paramMap.nodeId = paramMap.nodeId || ''
  paramMap.optional1 = paramMap.optional1 || ''
  paramMap.optional2 = paramMap.optional2 || ''
  paramMap.autoScrollBool = paramMap.autoScrollBool || false
  paramMap.filters = paramMap.filters || ['all']
  paramMap.autoRefresh = paramMap.autoRefresh || false
  paramMap.repliesNum = paramMap.repliesNum || 1
  paramMap.keepState = paramMap.keepState || false
  paramMap.parentElement = paramMap.parentElement || ''

  var parentElement = paramMap.parentElement;
  var totalCount = null

  var configMap = {
    newest: {
      totalCount: null
    },
    popular: {
      totalCount: null
    },
    experts: {
      totalCount: null
    },
    unanswered: {
      totalCount: null
    },
    unread: {
      totalCount: null
    },
    currentKey: 'newest',
    pageLoad: true,
    ttl: {
      val: null,
      isExpired: function () {
        var ret = true
        var d = new Date()
        var t = d.getTime()

        if (configMap.ttl.val !== null) {
          if (t < configMap.ttl.val) {
            ret = false
          }
        }

        return ret
      },
      setTTL: function () {
        var d = new Date()
        var t = d.getTime()

        configMap.ttl.val = t + 120000
      },
      clearTTL: function () {
        configMap.ttl.val = null
        configMap.newest.totalCount = null
        configMap.popular.totalCount = null
        configMap.experts.totalCount = null
        configMap.unanswered.totalCount = null
      }
    },
    itemKey: function () {
      var feedKeyTemplate = 'feed:' + paramMap.page + ':' + paramMap.nodeId
      return feedKeyTemplate
    },
    getItem: function (key) {
      var isExpired = function (expires) {
        var d = new Date()
        var t = d.getTime()
        return (t > expires)
      }
      var isAnonymous = !!$('body').hasClass('lia-user-status-anonymous')
      var ret = {}
      var myRecord = JSON.parse(window.localStorage.getItem(this.itemKey())) || {}

      if (myRecord.isAnonymous !== isAnonymous) {
        // content only used if stored state match user's auth state
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && isExpired(myRecord.ttl)) {
        this.clearItem()
        ret = {}
      } else if (myRecord.hasOwnProperty('ttl') && !isExpired(myRecord.ttl) && myRecord.isAnonymous === isAnonymous) {
        ret = myRecord
      }
      return ret
    },
    clearItem: function () {
      if (this.itemKey() !== null) {
        window.localStorage.removeItem(this.itemKey())
      }
    },
    setItem: function (key, content) {
      var myRecord = {
        ttl: this.ttl.val,
        content: $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html(),
        tab: this.currentKey,
        newest: this.newest.totalCount,
        popular: this.popular.totalCount,
        experts: this.experts.totalCount,
        unanswered: this.unanswered.totalCount,
        isAnonymous: !!$('body').hasClass('lia-user-status-anonymous')
      }

      // console.log('inside setItem fcn')
      // console.log(myRecord)

      window.localStorage.setItem(
        this.itemKey(),
        JSON.stringify(myRecord)
      )
    }
  }

  var showMoreConversations = function () {
    var existingContent = {}
    var calculateOffset = $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
    var myData = {
      board: paramMap.board,
      pageOffsetValue: calculateOffset,
      eventTab: paramMap.eventTab,
      filter: configMap.currentKey,
      categoryId: paramMap.nodeId,
      offset: calculateOffset, // used for category page only
      groupHub: paramMap.groupHub,
      replies: paramMap.repliesNum,
      pageSize: paramMap.pageSize
    }
    existingContent = configMap.getItem()
    // console.log(existingContent)

    // use localStorage:
    // when page is loading
    // when TTL valid

    // optional configuration for board level
    if (paramMap.hasOwnProperty('lingotek')) {
      myData.lmanual = paramMap.lingotek.lmanual
      myData.code = paramMap.lingotek.code
    }

    if ($('body').hasClass('lia-user-status-registered')) {
      // triggered on success of quick reply POST inside of addQuickReployInit for board
      // and category; update localStorage so page refresh would avoid stale data.

      $(parentElement + ' .recent-conversations-messages-wrapper').off('recent-conversations-messages-wrapper:quickReply')
      $(parentElement + ' .recent-conversations-messages-wrapper').on(
        'recent-conversations-messages-wrapper:quickReply',
        $.proxy(function () {
          // setItem updates the localStorage
          this.setItem()
          // clearItem would clear the feed and do fresh pull
          // this.clearItem()
          // console.log('update localStorage')
        }, configMap)
      )
    }

    if (existingContent.hasOwnProperty('content') && configMap.pageLoad === true && !configMap.ttl.val) {
      // only run once when page first load
      configMap.pageLoad = false

      // set the active tab
      configMap.currentKey = existingContent.tab

      $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html(existingContent.content)
      calculateOffset = $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
      $(parentElement + ' .welcome-spinner').addClass('hidden')

      // set filter UI
      $(parentElement + ' .home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $(parentElement + ' .home-feed-filters li').each(function () {
        if ($(this).attr('data') === configMap.currentKey) {
          $(this).addClass('active-filter')
        } else {
          $(this).removeClass('active-filter')
        }
      })

      // Quick Reply Init
      if (paramMap.page === 'categoryPage') {
        UTILITIES.addQuickReplyInitForCat(paramMap)
      } else {
        UTILITIES.addQuickReplyInitForBoard(paramMap)
      }

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $(parentElement + ' #showMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $(parentElement + ' #showMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $(parentElement + ' #showMoreButton').prop('disabled', false)
      }
    } else if (existingContent.hasOwnProperty('content') && configMap.currentKey !== existingContent.tab &&
      calculateOffset > 0 && !configMap.ttl.isExpired()) {
      // localStorage refreshed with current filter and records displaying
      $(parentElement + ' .message-list > ul > li.conversation').hide()
      $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).show()
      $(parentElement + ' .welcome-spinner').addClass('hidden')

      // Quick Reply Init
      if (paramMap.page === 'categoryPage') {
        UTILITIES.addQuickReplyInitForCat(paramMap)
      } else {
        UTILITIES.addQuickReplyInitForBoard(paramMap)
      }

      // disable if total count is reached after records
      if (existingContent[configMap.currentKey] !== null && calculateOffset >= existingContent[configMap.currentKey]) {
        $(parentElement + ' #showMoreButton').prop('disabled', true)
        // console.log('reached local storage totalcount')
      } else if (configMap[configMap.currentKey].totalCount !== null && calculateOffset >= configMap[configMap.currentKey].totalCount) {
        $(parentElement + ' #showMoreButton').prop('disabled', true)
        // console.log('reached configMap totalcount')
      } else {
        $(parentElement + ' #showMoreButton').prop('disabled', false)
      }

      // save in localStorage
      configMap.setItem()
    } else {
      // make request for items
      configMap.pageLoad = false

      $.ajax({
        type: 'post',
        url: paramMap.endpointUrlValue,
        dataType: 'html',
        data: myData,
        context: $(parentElement + ' .showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          if (calculateOffset > 0) {
            // loading more
            $(parentElement + ' .welcome-spinner.spinner').first().parent().addClass('loadmore')
            $(parentElement + ' .welcome-spinner.spinner').removeClass('hidden')
            UTILITIES.accessibleAlert(parentElement + ' #feedMessages', 'Loading more')
          } else {
            $(parentElement + ' .welcome-spinner.spinner').first().parent().removeClass('loadmore')
            $(parentElement + ' .welcome-spinner.spinner').first().removeClass('hidden')

            if ($(parentElement + ' .refresh-feed').hasClass('in-progress')) {
              // specific feed refresh action
              UTILITIES.accessibleAlert(parentElement + ' #feedMessages', 'Feed refresh')
            } else {
              // loading feed
              UTILITIES.accessibleAlert(parentElement + ' #feedMessages', 'Loading')
            }
          }
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $(parentElement + ' .spinner').empty().remove()
        },
        success: function (data) {
          var checkExistingItems = function () {
            return $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
          }
          var itemToFocus = checkExistingItems()
          var template1 = 'No messages.'
          var template2 = 'Looks like you have not favorited any Conversation Spaces, ' +
            'which power this feed! Go to communities and favorite conversation spaces ' +
            'that interest you.'
          var markup

          $(parentElement + ' div.no-messages-container.lia-text').remove()
          $(parentElement + ' .welcome-spinner.spinner').addClass('hidden')
          $(parentElement + ' .recent-conversations-messages-wrapper').removeClass('loadmore')
          $(parentElement + ' .refresh-feed').removeClass('in-progress')
          UTILITIES.accessibleAlert('#feedMessages', 'Success')

          configMap.ttl.setTTL()

          // CATEGORY / Forum page custom TTL set
          if (data.indexOf('{{{0}}}') === -1) {
            $(parentElement + ' .showMoreMessagesHere').removeClass()
            $(parentElement + ' .message-list').first().append('<ul class="showMoreMessagesHere"></ul>')

            if (checkExistingItems() > 0) {
              $(this).html(data)
              $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).eq(itemToFocus).find('.message-subject-link').focus()
            } else {
              $(this).html(data)

              // empty message in controller
              if (configMap[configMap.currentKey].totalCount === 0) {
                UTILITIES.accessibleAlert(parentElement + ' #feedMessages', template1)
              }
            }

            // set content in localStorage
            // console.log('set content in localStorage')
            configMap.setItem()

            $(parentElement + ' #showMoreButton').prop('disabled', false)
          } else if ((data.indexOf('{{{0}}}') !== -1 && checkExistingItems() > 0)) {
            // no more items to show
            $(parentElement + ' #showMoreButton').prop('disabled', true)
          } else {
            // messaging for no record
            markup = (configMap.currentKey === 'newest') ? template2 : template1

            $(parentElement + ' .error-msg').removeClass('hidden').html(markup)
            $(parentElement + ' #showMoreButton').prop('disabled', true)
            UTILITIES.accessibleAlert(parentElement + ' #feedMessages', template1)

            configMap.ttl.clearTTL()
            $(parentElement + ' .message-list').empty().html('<ul class="showMoreMessagesHere"></ul>')
          }

          // disable if total count is reached after records
          if ((checkExistingItems() >= totalCount)) {
            $(parentElement + ' #showMoreButton').prop('disabled', true)
          } else {
            $(parentElement + ' #showMoreButton').prop('disabled', false)
          }

          // $('.category-spinner.spinner').addClass('hidden');
          // $(this).removeClass('hidden');

          $(parentElement + ' .message-list > ul > li.conversation').hide()
          $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).show()

          // Quick Reply Init
          if (paramMap.page === 'categoryPage') {
            UTILITIES.addQuickReplyInitForCat(paramMap)
          } else {
            UTILITIES.addQuickReplyInitForBoard(paramMap)
          }
        }
      })
    }
  }

  var prepFilters = function (filters) {
    // remove css class hidden from list of filters
    if (filters[0] === 'all') {
      // show all
      $(parentElement + ' .home-feed-filters li, .home-feed-filters-select option').removeClass('hidden')
    } else {
      // selectively show and style
      $(parentElement + ' .home-feed-filters').addClass('filters-flex-end')

      filters.forEach(function (val, index, arr) {
        var isLast = index === arr.length - 1
        $(parentElement + ' .home-feed-filters li[data=' + val + ']').removeClass('hidden').addClass('available')
        $(parentElement + ' .home-feed-filters-select option[value=' + val + ']').removeClass('hidden')

        if (isLast) {
          $(parentElement + ' .home-feed-filters li[data=' + val + ']').removeClass('hidden').addClass('isLast')
        }
      })
    }
  }

  var autoRefresh = function (seconds) {
    var markup = '<label class="switch"><input aria-describedby="autoRefresh" ' +
      'class="autoRefresh" id="autoRefresh" data-int="{0}" name="autoRefresh" type="checkbox" checked>' +
      '<span class="slider round"></span></label><label for="autoRefresh">Auto-refresh</label>'
    var m = seconds * 1000
    var myTimer = function () {
      $(parentElement + ' .refresh-feed').click()
    }

    if (!isNaN(m)) {
      $(parentElement + ' .feed-user-settings').removeClass('hidden').html(markup.replace('{0}', m))
      UTILITIES.autoRefreshInt = setInterval(myTimer, m)

      $(parentElement + ' .feed-user-settings input').change(function () {
        var checked = $(this).prop('checked')
        var interval = $(this).attr('data-int')
        var m = parseInt(interval)

        if (checked === true) {
          UTILITIES.autoRefreshInt = setInterval(myTimer, m)
        } else {
          clearInterval(UTILITIES.autoRefreshInt)
        }
      })
    }
  }

  var getTotalCount = function () {
    var myData = {
      usr: paramMap.usr,
      nodeId: paramMap.nodeId,
      categoryId: paramMap.nodeId,
      board: paramMap.board,
      groupHub: paramMap.groupHub,
      eventTab: paramMap.eventTab,
      filter: configMap.currentKey
    }
    var makeRequest = true
    var existingContent = configMap.getItem()

    // console.log('get totalCount fcn')
    // console.log(existingContent)

    if (existingContent.hasOwnProperty('content')) {
      if (existingContent[configMap.currentKey] !== null) {
        makeRequest = false
        showMoreConversations()
      }
    }

    if (makeRequest) {
      $.ajax({
        type: 'post',
        url: paramMap.countUrlValue,
        dataType: 'json',
        data: myData,
        context: $(parentElement + ' .showMoreMessagesHere'),
        beforeSend: function (xhr, opts) {
          $(parentElement + ' .recent-conversations-messages-wrapper .spinner').removeClass('hidden')
          $(parentElement + ' .error-msg').addClass('hidden').empty()
        },
        error: function (e) {
          var errorMarkup = '<div class="error">There was an error loading messages.</div>'
          $(this).html(errorMarkup)
          $(parentElement + ' .spinner').empty().remove()
          UTILITIES.accessibleAlert(parentElement + ' #feedMessages', errorMarkup)
        },
        success: function (data) {
          totalCount = data.count
          configMap[configMap.currentKey].totalCount = data.count
          configMap.ttl.setTTL()
          showMoreConversations()
        }
      })
    }
  }

  var purgeDuplicatesUnread = function () {
    var collection = $(parentElement + ' .conversation')
    var len = collection.length
    var i
    var dataId
    var uniques = []
    var emptyMessage = 'No messages.'

    $(collection).hide()

    for (i = 0; i < len; i++) {
      dataId = $(collection[i]).find('.post.unread').attr('data-id')
      if (dataId && $.inArray(dataId, uniques) === -1) {
        $(collection[i]).show()
        uniques.push(dataId)
      }
    }

    if (uniques.length === 0) {
      $(parentElement + ' .error-msg').html(emptyMessage).removeClass('hidden')
      UTILITIES.accessibleAlert('#feedMessages', emptyMessage)
    } else {
      $('.error-msg').empty().addClass('hidden')
    }
  }

  $(parentElement + ' .refresh-feed').on('click keypress', function (e) {
    if (e.type === 'click' || e.keyCode === 13) {
      if (!paramMap.keepState) {
        configMap.clearItem()
        configMap.ttl.clearTTL()
        configMap.currentKey = 'newest'
      }

      if (e.hasOwnProperty('originalEvent')) {
        // explicit user action taken is different
        // than programmatic click for screen readers
        $(this).addClass('in-progress')
      }

      // flush container and start over
      $(parentElement + ' .recent-conversations-messages-wrapper .message-list').html('<ul class="showMoreMessagesHere"></ul>')
      $(parentElement + ' .home-feed-filters li[data=' + configMap.currentKey + ']').click()
    }
  })

  $(parentElement + ' .home-feed-filters-select').on('change', function (e) {
    var key = $(this).val()
    var currentFiltersItemsLength = $(parentElement + ' .message-list > ul > li.conversation-' + key).length
    var checkExistingItems = function () {
      return $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    configMap.currentKey = key

    $(parentElement + ' .home-feed-filters li').each(function () {
      if ($(this).attr('data') === configMap.currentKey) {
        $(this).addClass('active-filter')
      } else {
        $(this).removeClass('active-filter')
      }
    })

    // $('.filter-tag').removeClass('active-filter')
    // $(this).addClass('active-filter')
    $(parentElement + ' .message-list > ul > li.conversation').hide()
    $(parentElement + ' .error-msg').text('').addClass('hidden')
    $(parentElement + ' .no-messages-container.lia-text').remove()
    if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
      configMap.ttl.setTTL()
    }

    if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
      // flush container and start over
      $(parentElement + ' .message-list').html('<ul class="showMoreMessagesHere"></ul>')
      getTotalCount()
    } else {
      if (configMap.currentKey === 'unread') {
        purgeDuplicatesUnread()
        $(parentElement + ' #showMoreButton').prop('disabled', true)
      } else if (configMap[configMap.currentKey].totalCount) {
        if (currentFiltersItemsLength === 0) {
          showMoreConversations()
        } else {
          $(parentElement + ' .message-list > ul > li.conversation').hide()
          $(parentElement + ' .message-list > ul > li.conversation-' + key).show()

          // set content in localStorage
          configMap.setItem()

          if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
            $(parentElement + ' #showMoreButton').prop('disabled', true)
          } else {
            $(parentElement + ' #showMoreButton').prop('disabled', false)
          }
        }
      } else {
        getTotalCount()
      }
    }
  })

  $(parentElement + ' .home-feed-filters li').on('keydown click', function (e) {
    var key
    var currentFiltersItemsLength
    var checkExistingItems = function () {
      return $(parentElement + ' .message-list > ul > li.conversation-' + configMap.currentKey).length
    }

    if (e.keyCode === 13 || e.type === 'click') {
      key = $(this).attr('data')
      currentFiltersItemsLength = $(parentElement + ' .message-list > ul > li.conversation-' + key).length
      configMap.currentKey = key

      $(parentElement + ' .home-feed-filters-select option').each(function () {
        if ($(this).val() === configMap.currentKey) {
          $(this).attr('selected', 'selected')
        } else {
          $(this).removeAttr('selected')
        }
      })

      $(parentElement + ' .filter-tag').removeClass('active-filter')
      $(this).addClass('active-filter')
      $(parentElement + ' .message-list > ul > li.conversation').hide()
      $(parentElement + ' .error-msg').text('').addClass('hidden')
      $(parentElement + ' div.no-messages-container.lia-text').remove()

      if (configMap.ttl.isExpired() && configMap.ttl.val === null) {
        configMap.ttl.setTTL()
      }

      if (configMap.ttl.isExpired() && configMap.currentKey !== 'unread') {
        // flush container and start over
        $(parentElement + ' .message-list').html('<ul class="showMoreMessagesHere"></ul>')
        getTotalCount()
      } else {
        if (configMap.currentKey === 'unread') {
          purgeDuplicatesUnread()
          $(parentElement + ' #showMoreButton').prop('disabled', true)
        } else if (configMap[configMap.currentKey].totalCount) {
          if (currentFiltersItemsLength === 0) {
            showMoreConversations()
          } else {
            $(parentElement + ' .message-list > ul > li.conversation').hide()
            $(parentElement + ' .message-list > ul > li.conversation-' + key).show()

            // set content in localStorage
            configMap.setItem()

            if ((checkExistingItems() === configMap[configMap.currentKey].totalCount)) {
              $(parentElement + ' #showMoreButton').prop('disabled', true)
            } else {
              $(parentElement + ' #showMoreButton').prop('disabled', false)
            }
          }
        } else {
          getTotalCount()
        }
      }
    }
  })

  // different css class used at category and forum topic
  $(parentElement + ' .category-recent-conversations-component, .forum-page-custom-message-list-wrapper').on('click', function (e) {
    var offset = $(parentElement + ' .recent-conversations-messages-wrapper .message-list > ul > li').length
    var self

    if (e.target.id === 'showMoreButton') {
      e.target.blur()

      if (offset > 0) {
        // show only loader on the button
        $(parentElement + ' .recent-conversations-messages-wrapper').addClass('loadmore')
      }

      if (configMap[configMap.currentKey].totalCount) {
        showMoreConversations()
      } else {
        getTotalCount()
      }
    } else if ($(e.target).hasClass('kudo-add-button') || $(e.target).hasClass('kudo-remove-button')) {
      self = configMap
      setTimeout(function () {
        self.setItem()
      }, 500)
    }
  }).find('#showMoreButton').click()

  if (paramMap.autoScrollBool) {
    $(window).scroll(function () {
      if ($(window).scrollTop() >= $(parentElement + ' #showMoreButton').position().top &&
        $(parentElement + ' .showMoreContainer .welcome-spinner').hasClass('hidden') &&
        $(window).width() > 991 &&
        !$(parentElement + ' #showMoreButton').prop('disabled')) {
        $(parentElement + ' #showMoreButton').click()
      }
    })
  }

  // init filters
  prepFilters(paramMap.filters)

  // auto refresh
  if (paramMap.autoRefresh) {
    autoRefresh(paramMap.autoRefresh)
  }

  $(window).on('beforeunload', function () {
    $('body').addClass('leaving-page')
  })
} // End of initCatForumFilters method