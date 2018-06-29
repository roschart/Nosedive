# nosedive

This applictions is a sandbox for trainning.
We will create a vote application as the serie https://en.wikipedia.org/wiki/Nosedive


## Dependencies
You need git and lein installed in your system

## Installation

* git clone https://github.com/roschart/Nosedive.git
* lein run migrate
 
## Run in development mode
* Lein run [opts]

Try lein run -h for more help

## Developing
To start the diferent services in local use:
    docker-compose run app

This will open a container with a lein repl runing, and mount a volume with the actual folder.

To work, in the repl run (refresh), this will load all code in the repl, when you change something in your code try (refresh) again to bring changes to the repl


## Usage

    lein uberjar
    For generate nosedive-0.1.0-standalone.jar

    $ java -jar nosedive-0.1.0-standalone.jar [args]
    try -h --help for more information

## Options

    -d, --date DATE                  Date ddMMyyyy
    -c, --creator  WHO               Who vote
    -D, --description  DESCRIPTION   The descirption
    -p, --person PERSON              The person that is voted
    -v, --vote vote                  The vote
    -h, --help
