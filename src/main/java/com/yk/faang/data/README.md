# Data

Contains experiments with writing and reading data for tests.

## Some interesting results.
```text
n = 10 000 000
Binary file size: 38.1 MB (40,000,004 bytes)
Text file size: 75.2 MB (78,888,984 bytes)
```

Output:
```text
2025-11-24 23:33:46 [main] INFO  com.yk.faang.utils.TimeUtils – label: Write int array as text into text file. Time ms: 583.
2025-11-24 23:33:46 [main] INFO  com.yk.faang.utils.TimeUtils – label: Write bytes into binary file. Time ms: 100.
2025-11-24 23:33:47 [main] INFO  com.yk.faang.utils.TimeUtils – label: Read int array from text file. Time ms: 785.
2025-11-24 23:33:47 [main] INFO  com.yk.faang.utils.TimeUtils – label: Read int array from binary file. Time ms: 97.
```