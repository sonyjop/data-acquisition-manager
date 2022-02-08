# Getting Started

### 
ReSTful endpoints and services to manage and configure Asset Device configurations.
This is also got services to orchestrate scheduling the poll jobs into a Queue destination.

# This project uses Apache Ignite as an embedded cache
# This has a dependency on Apache ActiveMQ as a standalone message broker which has to be downloaded at 

Dependency
1. Apache ActiveMQ "https://activemq.apache.org/download-archives". Download version 5.15.10
2. Developed on Java 1.8

In case of an initial dataload is required for a test purpose there is a loader service which requires data files putinto a folder.
To run the application from gradle :
gradle bootRun -Pargs="--application.loader.sourceFolder=C:/InstalledSoftwares/modbus/load_5"
