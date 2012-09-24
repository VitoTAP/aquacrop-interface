A Java interface for a local or remote [AquaCrop](http://www.fao.org/nr/water/aquacrop.html) instance. This enables programmatic execution of AquaCrop runs, without having to deal with the vast number of input/output plain text files.

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
    <version>0.1</version>
  </dependency>
</dependencies>
```