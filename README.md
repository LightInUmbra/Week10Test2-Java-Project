# Week 10 Test 2 – CreateNotes

A console-based Java application that lets users create and append to `.txt` note files on disk. The program validates directory paths, handles file creation, accepts multi-line input, and prints the file contents when finished.

---

## Project Structure

```
Week10Test2/
├── src/
│   └── (default package)
│       ├── CreateNotes.java   # Main controller / entry point
│       ├── NotesFile.java     # File and directory operations
│       └── UserInput.java     # Keyboard input handling
├── doc/
│   ├── CreateNotes.html       # Javadoc for CreateNotes
│   ├── NotesFile.html         # Javadoc for NotesFile
│   ├── UserInput.html         # Javadoc for UserInput
│   ├── index.html             # Javadoc entry point
│   └── ...                    # Supporting Javadoc files
└── README.md
```

### Class Overview

| Class | Role |
|---|---|
| `CreateNotes` | Orchestrates the full workflow via `main()` |
| `NotesFile` | Validates paths, creates directories/files, reads and writes |
| `UserInput` | Wraps `BufferedReader` to prompt and collect user input |

---

## How It Works

The program runs through six steps:

1. **Get a directory** – Prompts for an absolute path (`C:\temp` or `/temp`). Validates format, checks existence, and optionally creates the directory if it doesn't exist.
2. **Get a file name** – Must end in `.txt`. If the file already exists, the user chooses to edit it or enter a different name.
3. **Create the file** – If the file is new, it is created on disk immediately.
4. **Accept input** – The user types lines of text. Pressing **Enter on a blank line** ends input.
5. **Write lines** – Each non-empty line is appended to the file.
6. **Print contents** – The full file is read back and printed to the console.

---

## Running the Project

### Prerequisites
- Java 8 or higher (project uses JDK 26)
- A terminal or IDE (e.g., IntelliJ, Eclipse, VS Code with Java extension)

### Compile

```bash
javac src/CreateNotes.java src/NotesFile.java src/UserInput.java
```

### Run

```bash
java -cp src CreateNotes
```

### Example Session

```
Enter directory name: C:\temp
Enter file name (.txt): JavaNotes.txt
File created.
Enter text (press Enter twice to finish):
> Today I learned about file I/O in Java.
> BufferedReader wraps an InputStreamReader.
>
--- File Contents ---
Today I learned about file I/O in Java.
BufferedReader wraps an InputStreamReader.
```

---

## Javadoc

Full API documentation is available in the `doc/` folder. Open `doc/index.html` in a browser to browse the generated Javadoc for all three classes.

To regenerate the Javadoc:

```bash
javadoc -d doc src/CreateNotes.java src/NotesFile.java src/UserInput.java
```

---

## Key Design Decisions

- **Static helpers on `NotesFile`** – `isValidFormat()`, `directoryExistsForPath()`, and `createDirectoryForPath()` are static so the main controller can call them before a `NotesFile` object is constructed.
- **Append mode writing** – `writeLine()` opens the file with `FileWriter(file, true)`, so re-editing an existing file adds to it rather than overwriting it.
- **Private constructors** – `CreateNotes` and `UserInput` use private constructors to signal they are not meant to be instantiated; all access goes through static methods.
- **Input loop termination** – An empty line (bare Enter) breaks the write loop, matching the "press Enter twice to finish" instruction shown to the user.

---

## Author

**Umbra Ortiz** – Version 1.0
