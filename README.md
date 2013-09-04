## Smuggler - Coding Challenge

Solution to this: [Clojure Programming Challenge](https://github.com/micahalles/doll-smuggler)


### Installation

	$ git clone git@github.com:dylanswartz/smuggler.git

### Usage

    $ lein run <DATFILE> <MAX-WEIGHT>
Checkout [Leiningan on GitHub](https://github.com/technomancy/leiningen) for more info.

**Sample run:** 
```$ lein run test/smuggler/data.txt 400```

**Sample datafile:**

    luke        9   150
    anthony    13    35
    candice   153   200
    dorothy    50   160
    puppy      15    60
    thomas     68    45
    randal     27    60
    april      39    40
    nancy      23    30
    bonnie     52    10
    marc       11    70
    kate       32    30
    tbone      24    15
    tranny     48    10
    uma        73    40
    grumpkin   42    70
    dusty      43    75
    grumpy     22    80
    eddie       7    20
    tory       18    12
    sally       4    50
    babe       30    10
    
**Sample output:**

	packed dolls: 
	
	name     weight value
	sally        4    50
	eddie        7    20
	grumpy      22    80
	dusty       43    75
	grumpkin    42    70
	marc        11    70
	randal      27    60
	puppy       15    60
	dorothy     50   160
	candice    153   200
	anthony     13    35
	luke         9   150

	total street-value: 1030
	total weight: 396

### Running Tests
	$ lein test
