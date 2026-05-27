
# House Robber Problem

## Problem

You are given an array where each element represents money in a house.

You cannot rob two adjacent houses.

Find the maximum money you can rob.

## Example

```text
houses = [6, 7, 1, 30, 8, 4, 20, 5, 9]
```

This example is tricky because `30` and `20` are large values, but we still need to carefully check adjacent restrictions.

## Formula

For every house, we have two choices:

1. Skip current house.
2. Rob current house and add the best result from two houses before.

```text
dp[i] = max(dp[i - 1], houses[i] + dp[i - 2])
```

Where:

```text
dp[i] = maximum money we can rob from house 0 to house i
```

## Step-by-Step Table

| Index | House Money | Calculation | Best Till Now |
|---|---:|---|---:|
| 0 | 6 | First house | 6 |
| 1 | 7 | max(6, 7) | 7 |
| 2 | 1 | max(7, 1 + 6) | 7 |
| 3 | 30 | max(7, 30 + 7) | 37 |
| 4 | 8 | max(37, 8 + 7) | 37 |
| 5 | 4 | max(37, 4 + 37) | 41 |
| 6 | 20 | max(41, 20 + 37) | 57 |
| 7 | 5 | max(57, 5 + 41) | 57 |
| 8 | 9 | max(57, 9 + 57) | 66 |

Final answer:

```text
66
```

One valid robbery combination:

```text
House 1 = 7
House 3 = 30
House 6 = 20
House 8 = 9

Total = 7 + 30 + 20 + 9 = 66
```

## Java Solution

```java
public class HouseRobber {
    public static int rob(int[] houses) {
        if (houses == null || houses.length == 0) {
            return 0;
        }

        if (houses.length == 1) {
            return houses[0];
        }

        int prev2 = houses[0];
        int prev1 = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            int current = Math.max(prev1, houses[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] houses = {6, 7, 1, 30, 8, 4, 20, 5, 9};
        System.out.println(rob(houses)); // 66
    }
}
```

## Complexity

```text
Time Complexity: O(n)
Space Complexity: O(1)
```
