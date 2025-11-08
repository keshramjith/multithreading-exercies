# Exercises to (re)learn multithreaded programming
I've been interested in getting better at the [Scala](https://www.scala-lang.org/) programming language and to be productive in writing Scala software systems, one generally needs to choose an effectful system and their associated ecosystems. These systems are very mature and powerful and introduced powerful concepts and features that the JVM didn't have by default, such as green threads.

I decided to learn the [ZIO](https://zio.dev/) ecosystem. This repo serves as practicing multithreaded programming on the ZIO runtime. Whilst building a foundation of general multithreaded programming concepts that are programming language agnostic.

I used Claude Sonnet 4.5 to come up with exercises:

Prompt:

Can you list some exercises or projects i can build that would practice skill in multithreading in programming? specifically forking, join threads, thread interruption etc. I’m going to use the Scala programming language with the ZIO ecosystem

Response:

Here are some excellent multithreading exercises and projects for practicing with ZIO in Scala:

## Beginner Exercises

**Parallel File Processor** - Read multiple files concurrently and aggregate results (word counts, find patterns). Practice forking fibers for each file and joining them to combine results.

**Rate-Limited API Client** - Build a client that makes concurrent requests while respecting rate limits. Use semaphores to control concurrent access and practice fiber interruption when requests timeout.

**Concurrent Web Scraper** - Scrape multiple web pages simultaneously, with a configurable level of parallelism. Practice managing a pool of worker fibers and handling interruptions gracefully.

## Intermediate Projects

**Producer-Consumer Queue System** - Implement multiple producers and consumers sharing a bounded queue. Practice fiber coordination, backpressure handling, and clean shutdown with interruption.

**Parallel Image Processing Pipeline** - Process images through multiple stages (resize, filter, compress) with each stage running concurrently. Chain fibers and practice error handling across the pipeline.

**Concurrent Cache with TTL** - Build a thread-safe cache where entries expire after a time-to-live. Practice background fibers for cleanup, atomic operations, and proper resource management.

**Task Scheduler** - Create a system that executes scheduled tasks at specific times or intervals. Practice forking background fibers, cancellation, and rescheduling.

## Advanced Projects

**Distributed Work Queue** - Simulate a distributed system where workers pull tasks from a shared queue. Practice failure recovery, retry logic, and coordinated shutdown.

**Real-time Chat Server** - Handle multiple concurrent client connections with message broadcasting. Practice managing long-lived fibers, connection cleanup, and graceful degradation.

**Parallel Database Migration Tool** - Migrate data between databases using multiple parallel workers. Practice batching, error handling, progress tracking, and cancellation of long-running operations.

**Stock Price Aggregator** - Subscribe to multiple data streams simultaneously and aggregate quotes in real-time. Practice streaming with ZIO Streams, backpressure, and fiber interruption.

Each project will give you hands-on experience with ZIO's fiber model, structured concurrency, and resource-safe interruption handling. Start with the simpler exercises and work your way up!
