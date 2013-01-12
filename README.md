A Java interface for [AquaCrop](http://www.fao.org/nr/water/aquacrop.html), a crop-model developed by [FAO](http://www.fao.org) to simulate yield response to water. This enables local or remote programmatic execution of AquaCrop runs, without having to deal with the vast number of input/output plain text files.

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
    <version>0.5.3</version>
  </dependency>
</dependencies>
```

### AquaCrop setup

Unless you are using the interface to connect to a remote instance, you will need to install AquaCrop on either your local machine or remote server.

AquaCrop 3.1
AquaCrop 3.1 plug-in program

Both should be installed to the same base directory (e.g. C:\FAO).

```java
// Get sample project
Project project = SampleData.getProject();

// Create interface using base directory path
AquaCropInterface iface = AquaCropInterface("C:\\FAO"");

// Run with project
try {
  Output output = iface.run(project)
  System.out.println(output.getYield());
}
catch (AquaCropException e) {
  // Project parameters invalid? AquaCrop cannot be found?
  e.printStackTrace();
}
``` 

Linux will require WINE, and a base path override

"z:\home\username\aquacrop" 1> /dev/null 2> /dev/null &


## Remote usage

### Server

```console
java -jar aquacrop-interface-0.5-with-dependencies.jar PORT BASE [PREFIX]
```

PORT the port you wish the server to listen on
BASE the path to where AquaCrop and ACsaV31plus directories can be found
PREFIX the prefix command for running the executables


Start the server.

Even though the AquaCrop plug-in program has no interface, it still creates a window (and dialog boxes if there are errors).

On a headless machine, this will prevent WINE/AquaCrop from running unless an X server exists. As we don't have a physical screen,
we can instead use http://en.wikipedia.org/wiki/Xvfb Xvfb to perform all graphical operations in memory.

The prefix command `xvfb-run`

This command starts up an Xvfb instance for each request and is slow.

Instead we can start up Xvfb before the AquaCrop server, and specify that WINE should run with that instance.

```console
Xvfb :1 -screen 0 800x600x24 &
```

env DISPLAY=:1 wine

44446 /home/jonesrm1/aquacrop/

### Client


### Build

If you wish to build the project from source, rename aquacrop.sample.properties in src/test/resources to aquacrop.properties. In this file, change the executable.path property to the location of the AquaCrop executable on your local machine. To build:

```console
$ mvn clean package
```