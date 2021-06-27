Hypixel Public API (Java & Project Reactor)
======
This is a forked Java implementation of the Hypixel API using project reactor. Please check out the original project on [github](https://github.com/HypixelDev/PublicAPI).

### Documentation

Hypixel Public API documentation can be found at [https://api.hypixel.net/](https://api.hypixel.net/). Java
documentation can be found in the code.

### Usage

You can use this API as a dependency via [jitpack](https://jitpack.io/#GrizzlT/PublicAPI). You can also use
the [Example Code](https://github.com/GrizzlT/PublicAPI/tree/4.0.0-reactive/hypixel-api-example) as a good starting point.


#### Transports

We include one built-in option for communicating with the Hypixel API, you can include this one or even include the
core API directly and create your own instance of HypixelHTTPClient.

* [Project Reactor Transport](hypixel-api-transport-reactor/README.md) (automatic rate-limiting by default)
