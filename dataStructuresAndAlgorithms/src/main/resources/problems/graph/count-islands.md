# Count Islands Problem

## Problem

You are given a 2D array where:

```text
L = land
W = water
```

An island is a group of connected land cells.

Land cells are connected horizontally or vertically, not diagonally.

Find the total number of islands.

## Example

```text
grid = [
  ['L', 'W', 'W', 'L', 'W'],
  ['L', 'L', 'W', 'L', 'W'],
  ['W', 'W', 'L', 'W', 'W'],
  ['W', 'L', 'L', 'W', 'L'],
  ['W', 'W', 'W', 'W', 'L']
]
```

This example is useful because it has multiple separate islands and some islands have more than one land cell.

## Idea

Scan every cell in the grid.

When we find a land cell `L`, we found a new island.

Then we use DFS to visit all connected land cells belonging to that island.

To avoid using an extra `visited` array, we mark every visited land cell as water:

```text
grid[row][col] = 'W'
```

After DFS finishes, all land cells of that island have been changed to `W`.

Then we continue scanning for the next remaining `L`.

## Directions

From one cell, we can move in four directions:

```text
up
down
left
right
```

```text
directions = [
  [-1, 0],  // up
  [1, 0],   // down
  [0, -1],  // left
  [0, 1]    // right
]
```

## Step-by-Step

Input:

```text
grid = [
  ['L', 'W', 'W', 'L', 'W'],
  ['L', 'L', 'W', 'L', 'W'],
  ['W', 'W', 'L', 'W', 'W'],
  ['W', 'L', 'L', 'W', 'L'],
  ['W', 'W', 'W', 'W', 'L']
]
```

Island groups:

```text
Island 1:
(0,0), (1,0), (1,1)

Island 2:
(0,3), (1,3)

Island 3:
(2,2), (3,2), (3,1)

Island 4:
(3,4), (4,4)
```

During DFS, each island is removed from the grid by changing its `L` cells to `W`.

For example, after processing island 1:

```text
(0,0), (1,0), (1,1)
```

those cells become `W`, so they will not be counted again.

Final answer:

```text
4
```

## Java Solution Without Extra Visited Array

We can avoid creating a separate `visited` array.

Whenever we visit a land cell, we mark it as water:

```text
grid[row][col] = 'W'
```

This means the cell is already processed and will not be counted again.

Tradeoff:

```text
This modifies the original grid.
```

```java
public class CountIslands {
    public static int countIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 'L') {
                    count++;
                    dfs(grid, row, col);
                }
            }
        }

        return count;
    }

    private static void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        if (grid[row][col] == 'W') {
            return;
        }

        grid[row][col] = 'W';

        dfs(grid, row - 1, col); // up
        dfs(grid, row + 1, col); // down
        dfs(grid, row, col - 1); // left
        dfs(grid, row, col + 1); // right
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'L', 'W', 'W', 'L', 'W'},
            {'L', 'L', 'W', 'L', 'W'},
            {'W', 'W', 'L', 'W', 'W'},
            {'W', 'L', 'L', 'W', 'L'},
            {'W', 'W', 'W', 'W', 'L'}
        };

        System.out.println(countIslands(grid)); // 4
    }
}
```

## Complexity

```text
Rows = R
Columns = C

Time Complexity: O(R * C)
Extra Space Complexity: O(1)
Recursive Call Stack: O(R * C) in the worst case
```

Every cell is visited at most once.
