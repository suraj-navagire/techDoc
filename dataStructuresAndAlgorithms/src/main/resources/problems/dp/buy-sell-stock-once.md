# Buy and Sell Stock Once

## Problem

You are given an array where each element represents the stock price on that day.

You can buy the stock once and sell it once.

You must buy before you sell.

Find the maximum profit.

## Example

```text
prices = [7, 1, 5, 3, 6, 4]
```

Best choice:

```text
Buy at 1
Sell at 6

Profit = 6 - 1 = 5
```

Final answer:

```text
5
```

## Type

```text
Greedy
Pattern: Minimum so far
```

## Idea

While scanning prices from left to right:

1. Keep track of the minimum price seen so far.
2. For each current price, calculate the profit if we sell today.
3. Keep the maximum profit.

We do not need to try every pair of buy and sell days.

## Formula

```text
minPrice = minimum price seen before or at current day
profit = currentPrice - minPrice
maxProfit = max(maxProfit, profit)
```

## Step-by-Step Table

Input:

```text
prices = [7, 1, 5, 3, 6, 4]
```

| Day | Price | Min Price So Far | Profit If Sold Today | Max Profit |
|---|---:|---:|---:|---:|
| 0 | 7 | 7 | 0 | 0 |
| 1 | 1 | 1 | 0 | 0 |
| 2 | 5 | 1 | 4 | 4 |
| 3 | 3 | 1 | 2 | 4 |
| 4 | 6 | 1 | 5 | 5 |
| 5 | 4 | 1 | 3 | 5 |

Final answer:

```text
5
```

## Best Java Solution

```java
public class BuySellStockOnce {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];

            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            } else {
                int profit = currentPrice - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices)); // 5
    }
}
```

## Why This Works

For every selling day, the best buying day is the lowest price before it.

So while scanning, we only need:

```text
minimum price so far
maximum profit so far
```

## Edge Cases

### Prices Always Decrease

```text
prices = [7, 6, 4, 3, 1]
```

No profitable transaction is possible.

Answer:

```text
0
```

### Only One Price

```text
prices = [5]
```

Cannot buy and sell.

Answer:

```text
0
```

## Complexity

```text
Time Complexity: O(n)
Space Complexity: O(1)
```
