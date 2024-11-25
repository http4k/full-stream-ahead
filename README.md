# http4k Datastar Example

To demonstrate how simple it is to server [Datastar](https://data-star.dev/) webapps from an [http4k](https://http4k.org) application. 

Underlying idea shamelessly stolen from [Josh Graham's post](https://delitescere.medium.com/hotwire-html-over-the-wire-2c733487268c).

## Build/test locally
In your IDE run the `DatastarKt` main or...

```shell script
./gradlew test distZip
unzip build/distributions/Example.zip
Example/bin/Example
```

...  then browse to [http://localhost:8080](http://localhost:8080)
