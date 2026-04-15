# DSA Assignment 3 - CSV Graphing

This project writes benchmark results to `results.csv` in this format:

- Header row: sorting algorithm names
- One row per input size, in order: `50, 500, 1000, 2000, 5000, 10000`
- Values are execution times in milliseconds

<!-- CHART:START -->
```mermaid
xychart-beta
    title "Sorting Runtime vs Input Size"
    x-axis "Input Size" [50, 500, 1000, 2000, 5000, 10000]
    y-axis "Time (ms)" 0 --> 67
    line "Insertion" [0.1112, 4.1926, 3.8506, 24.8615, 18.4907, 63.3109]
    line "Heap" [0.0770, 0.7011, 0.5708, 0.7094, 9.4862, 9.6627]
    line "Merge" [0.0497, 0.7619, 0.4601, 0.5461, 6.6006, 14.1106]
    line "Quick" [0.0471, 0.5283, 0.7640, 0.2376, 0.6034, 1.4541]
    line "Quick10" [0.0286, 0.2580, 0.6098, 0.2057, 0.6416, 1.7583]
    line "Quick50" [0.0510, 0.4944, 0.5774, 0.2604, 0.6851, 1.6005]
    line "Quick200" [0.0580, 1.4835, 0.7573, 1.1579, 7.5054, 7.5473]
```
<!-- CHART:END -->

## Chart Key

- `Insertion`: insertion sort
- `Heap`: heap sort
- `Merge`: merge sort
- `Quick`: quick sort (cutoff `1`)
- `Quick10`: quick sort (cutoff `10`)
- `Quick50`: quick sort (cutoff `50`)
- `Quick200`: quick sort (cutoff `200`)