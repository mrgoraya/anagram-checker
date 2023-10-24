# Anagram Checker

The Anagram Checker is a Java program that allows you to input a list of texts and determine if they are anagrams of each other. It also lets you find anagrams of a given text.

## Features

- Input any number of texts to compare.
- Detect anagrams among the provided texts.
- Find anagrams of a given text.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this program, you need:

- Java Development Kit (JDK) installed on your machine.

### Installation

1. Clone the repository to your local machine.

```
git clone https://github.com/mrgoraya/anagram-checker.git
```

2. Navigate to the project directory.

```
cd anagram-checker
```

### Usage
1. Compile the Java source code:

```
javac AnagramChecker.java
```

2. Run the program:

```
java AnagramChecker
```

3. Follow the prompts to input texts and find anagrams.

### Example
Here's a simple example of how the program works:
```
How many texts do you want to compare? 4
Enter text A: listen
Enter text B: silent
Enter text C: enlist
Enter text D: apple
Are 'listen' and 'silent' anagrams? true
Are 'listen' and 'enlist' anagrams? true
Are 'listen' and 'apple' anagrams? false
Enter a text to find its anagrams: listen
Anagrams of 'listen': [enlist, silent]
Anagrams of 'apple': []
```