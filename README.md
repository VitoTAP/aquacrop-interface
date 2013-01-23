A Java interface for [AquaCrop](http://www.fao.org/nr/water/aquacrop.html), a crop-model developed by [FAO](http://www.fao.org) to simulate yield response to water. This enables local or remote programmatic/automated execution of AquaCrop runs, without having to deal with the vast number of input/output plain text files.

## Getting started

The interface is available on the UncertWeb Maven repository, hosted at the [University of Münster](http://www.uni-muenster.de/). Adding the following snippet to your `pom.xml` file will include the repository in your project.

```xml
<repositories>
  <!-- Other repositories may be here too -->
  <repository>
    <id>UncertWebMavenRepository</id>
    <name>UncertWeb Maven Repository</name>
    <url>http://giv-uw.uni-muenster.de/m2/repo</url>
  </repository>
</repositories>
```

The dependency for the interface can then be added.

```xml
<dependencies>
  <!-- Other dependencies may be here too -->
  <dependency>
    <groupId>org.uncertweb</groupId>
    <artifactId>aquacrop-interface</artifactId>
    <version>0.5.5</version>
  </dependency>
</dependencies>
```

### AquaCrop setup

Unless you are using the interface to connect to a remote instance, you will need to install AquaCrop on either your local machine or remote server. This will require installation of both [AquaCrop 3.1](http://www.fao.org/nr/water/jsp/downloadAquacrop/index.htm?dUrl=http://www.fao.org/nr/water/docs/AquaCropV31.zip) and the [AquaCrop 3.1 plug-in program](http://www.fao.org/nr/water/jsp/downloadAquacrop/index.htm?dUrl=http://www.fao.org/nr/water/docs/ACsaV31plus.zip). Both should be installed to the same base directory (e.g. `C:\FAO`), and make a note of the path to this directory.

## Client

Interfacing with either a local or remote AquaCrop instance requires to you build a `Project` object, which contains all of the parameters for an AquaCrop project - including climate, crop, and soil characteristics. The first step is to create the climate characteristics data. In this example, we will use a daily frequency for all measurements, and a start date of 1st October 2012:

```java
// Start date for measurements
LocalDate date = new LocalDate(2012, 10, 1);

// Create rainfall measurements
RainfallMeasurements rainfall = new RainfallMeasurements(Frequency.Daily, date);
rainfall.addMeasurement(61.7); // etc...

// Create evapotranspiration measurements
EvapotranspirationMeasurements eto = new EvapotranspirationMeasurements(Frequency.Daily, date);
eto.addMeasurement(1.1); // etc...

// Create temperature measurements
TemperatureMeasurements temperature = new TemperatureMeasurements(Frequency.Daily, date);
temperature.addMeasurement(8.3, 14.9); // etc...

// Create CO2 measurements
Co2Measurements co2 = new Co2Measurements();
co2.addMeasurement(2000, 385.6); // etc...

// Group all in climate characteristics
ClimateCharacteristics climate = new ClimateCharacteristics(rainfall, eto, temperature, co2);
```

Then, create the crop and soil characteristics:

```java
// Create crop characteristics
CropCharacteristics crop = new CropCharacteristics();
crop.setUpperTemperature(26.0); // etc...

// Create soil characteristics
SoilCharacteristics soil = new SoilCharacteristics();
soil.addSoilHorizon(new SoilHorizon("Horion1", 80.0, 33.7, 45.1, 47.9, 0.6)); // etc...
```

Finally, create the project:

```java
// Build project
Project project = new Project(climate, crop, soil);
```

Alternatively, a sample project is available to test your setup:

```java
// Get sample project
Project project = SampleData.getProject();
```

### Interfacing with a local installation

The `AquaCropInterface` class can be used for interfacing with a local AquaCrop installation. It will generate the required files for a run, execute the plug-in program, and parse the output to Java objects. Once a run is complete, the interface will clean up any generated files. An interface can be instantiated and used to run a project like so:

```java
// Create interface using base directory path
AquaCropInterface iface = AquaCropInterface("C:\\FAO");

// Run with project
try {
  Output output = iface.run(project)
  System.out.println(output.getYield());
}
catch (AquaCropException e) {
  // Project parameters invalid? AquaCrop executable cannot be found?
}
```

### Interfacing with a remote server

The `AquaCropClient` class is used to connect to a remote AquaCrop interface:

```java
// Create client
AquaCropClient client = new AquaCropClient("localhost", 44445);

// Send project
try {
  Output output = client.send(project);
  System.out.println(output.getYield());
}
catch (IOException e) {
  // IO error communicating with remote instance
}
catch (AquaCropRemoteException e) {
  // Error communicating with remote instance
}
catch (AquaCropException e) {
  // Project parameters invalid?
}
```

## Server

Running an AquaCrop server enables clients to remotely send projects to be run. The server requires the same basic AquaCrop setup as the client, and once setup can be started from the command line:

```console
$ java -jar aquacrop-interface-0.5.5-with-dependencies.jar -port=44445 -path="C:\FAO"
```

Parameters can be set by passing command line arguments:

- *keep* keep generated files after run.
- *override=&lt;override&gt;* the path where the executable should look for the files.
- *path=&lt;path&gt;* the path to where AquaCrop and ACsaV31plus directories can be found.
- *port=&lt;port&gt;* the port for the server to listen on.
- *prefix=&lt;prefix&gt;* the prefix command for running the executable.

## Linux

AquaCrop can be executed in Linux using [WINE](http://www.winehq.org/) and some extra parameters. As AquaCrop requires a Windows path to locate the project files, in addition to the path, we need to set the override parameter when using WINE. For example, an override of `z:\home\username\aquacrop\FAO` can typically be used for the path `/home/username/aquacrop/FAO`.

Even though the AquaCrop plug-in program has no interface, it still creates a window (and dialog boxes if there are errors). On a headless machine, this will prevent WINE/AquaCrop from running unless an X11 server exists. As we don't have a physical screen, we can instead use [Xvfb](http://en.wikipedia.org/wiki/Xvfb) to perform all graphical operations in memory. The xvfb-run script creates an Xvfb instance, runs the a given program, and destroys the Xvfb instance once the program has terminated. To use xvfb-run, simply set a prefix command of `xvfb-run wine`. However, as creating and destroying Xvfb instances is slow, it may be more efficient to create a single Xvfb instance which can then be used for all AquaCrop runs. An Xvfb instance can be created from the terminal:

```console
$ Xvfb :1 -screen 0 800x600x24 &
```

This Xvfb instance can then be used by WINE/AquaCrop by setting a prefix command of `env DISPLAY=:1 wine`.

## Build

If you wish to build the project from source, rename `aquacrop.sample.properties` in `src/test/resources` to `aquacrop.properties`. In this file, change the `basePath` property to the location of the AquaCrop executable on your local machine, and `port`, `basePathOverride`, and `prefixCommand` properties if required. To build:

```console
$ mvn clean package
```

## Limitations

The AquaCrop plug-in program only provides error messages in a modal dialog box, thus cannot be read by the interface. These messages aren't very informative anyway, take for example my favourite - "Floating point divison by zero".
