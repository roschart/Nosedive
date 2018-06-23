# 10.  Use docker and compose in dev
Date: 2018-06-20

## Status
Accepted

## Context
[Kubernetes](https://kubernetes.io/) is the new standar the facto container orchestrator. 
The idea is use Kubernetes in prodution as soon as posible, for that reason we start to use docker as our container.
Why docker, because is the more used of the diferents options.
As we don't have experience with kubenetes for dev envirotments we will start with [compose](https://docs.docker.com/compose/)
## Decision
Start to use docker docker-compose, in our dev envirotment 
## Consequences
 This allow as to use de docker [cockroachdb](https://www.cockroachlabs.com/) in dev and avoid problems with migrations in the future 

