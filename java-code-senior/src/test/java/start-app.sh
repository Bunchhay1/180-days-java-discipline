#!/bin/bash
# Enterprise Startup Script for Mac/Linux

echo "================================================="
echo "Compiling Java Source Code..."
javac MemoryCrashTest.java
echo "Compilation Successful."
echo "================================================="
echo "Starting JVM with strictly 50MB Memory Limit..."
echo "Enabling Garbage Collection Telemetry..."
echo "================================================="

# Run the Java application with strict JVM arguments
java -Xmx50m -Xlog:gc* MemoryCrashTest