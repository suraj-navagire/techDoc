# Buy and Sell Stock Multiple Times

## Problem

You are given an array where each element represents the stock price on that day.

You can buy and sell the stock multiple times.

You can hold only one stock at a time.

You must sell before buying again.

Find the maximum profit.

## Example

```text
prices = [7, 1, 5, 3, 6, 4]
```

Best choices:

```text
Buy at 1, sell at 5
Profit = 5 - 1 = 4

Buy at 3, sell at 6
Profit = 6 - 3 = 3

Total profit = 4 + 3 = 7
```

Final answer:

```text
7
```

## Type

```text
Greedy
Pattern: Add every positive price difference
```

## Idea

Whenever tomorrow's price is higher than today's price, we take that profit.

This works because one long increasing transaction is equal to adding all small increasing steps.

Example:

```text
prices = [1, 5, 6]
```

One transaction:

```text
Buy at 1, sell at 6 = 5
```

Small steps:

```text
(5 - 1) + (6 - 5) = 4 + 1 = 5
```

Both give the same profit.

## Formula

```text
if prices[i] > prices[i - 1]:
    profit += prices[i] - prices[i - 1]
```

## Important Note

This algorithm tells the maximum profit.

It does not tell the exact buy and sell days.

If we need exact transactions, we must track where an increasing sequence starts and ends.

## Step-by-Step Table

Input:

```text
prices = [7, 1, 5, 3, 6, 4]
```

| Day Pair | Price Change | Action | Profit Added | Total Profit |
|---|---:|---|---:|---:|
| 7 -> 1 | -6 | Ignore | 0 | 0 |
| 1 -> 5 | +4 | Take profit | 4 | 4 |
| 5 -> 3 | -2 | Ignore | 0 | 4 |
| 3 -> 6 | +3 | Take profit | 3 | 7 |
| 6 -> 4 | -2 | Ignore | 0 | 7 |

Final answer:

```text
7
```

## Tricky Example

Input:

```text
prices = [7, 1, 2, 500, 1000, 4]
```

The price increases continuously from `1` to `1000`.

The greedy algorithm adds each positive difference:

| Day Pair | Price Change | Profit Added | Total Profit |
|---|---:|---:|---:|
| 7 -> 1 | -6 | 0 | 0 |
| 1 -> 2 | +1 | 1 | 1 |
| 2 -> 500 | +498 | 498 | 499 |
| 500 -> 1000 | +500 | 500 | 999 |
| 1000 -> 4 | -996 | 0 | 999 |

Final answer:

```text
999
```

This is the same as:

```text
Buy at 1
Sell at 1000

Profit = 1000 - 1 = 999
```

The algorithm calculates this profit without explicitly storing:

```text
Buy day
Sell day
```

## Best Java Solution

```java
public class BuySellStockMultipleTimes {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices)); // 7
    }
}
```

## Why This Works

Since multiple transactions are allowed, every upward movement can be converted into profit.

We do not need to find exact buy and sell days first.

We only need to add all positive differences.

## Edge Cases

### Prices Always Increase

```text
prices = [1, 2, 3, 4, 5]
```

Profit:

```text
(2 - 1) + (3 - 2) + (4 - 3) + (5 - 4) = 4
```

Answer:

```text
4
```

### Prices Always Decrease

```text
prices = [7, 6, 4, 3, 1]
```

No profitable transaction is possible.

Answer:

```text
0
```

## Complexity

```text
Time Complexity: O(n)
Space Complexity: O(1)
```
