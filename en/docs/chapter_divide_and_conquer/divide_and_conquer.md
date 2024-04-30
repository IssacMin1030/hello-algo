---
comments: true
---

# 12.1 &nbsp; Divide and conquer algorithms

<u>Divide and conquer</u>, fully referred to as "divide and rule", is an extremely important and common algorithm strategy. Divide and conquer is usually based on recursion and includes two steps: "divide" and "conquer".

1. **Divide (partition phase)**: Recursively decompose the original problem into two or more sub-problems until the smallest sub-problem is reached and the process terminates.
2. **Conquer (merge phase)**: Starting from the smallest sub-problem with a known solution, merge the solutions of the sub-problems from bottom to top to construct the solution to the original problem.

As shown in Figure 12-1, "merge sort" is one of the typical applications of the divide and conquer strategy.

1. **Divide**: Recursively divide the original array (original problem) into two sub-arrays (sub-problems), until the sub-array has only one element (smallest sub-problem).
2. **Conquer**: Merge the ordered sub-arrays (solutions to the sub-problems) from bottom to top to obtain an ordered original array (solution to the original problem).

![Merge sort's divide and conquer strategy](divide_and_conquer.assets/divide_and_conquer_merge_sort.png){ class="animation-figure" }

<p align="center"> Figure 12-1 &nbsp; Merge sort's divide and conquer strategy </p>

## 12.1.1 &nbsp; How to identify divide and conquer problems

Whether a problem is suitable for a divide and conquer solution can usually be judged based on the following criteria.

1. **The problem can be decomposed**: The original problem can be decomposed into smaller, similar sub-problems and can be recursively divided in the same manner.
2. **Sub-problems are independent**: There is no overlap between sub-problems, and they are independent and can be solved separately.
3. **Solutions to sub-problems can be merged**: The solution to the original problem is obtained by merging the solutions of the sub-problems.

Clearly, merge sort meets these three criteria.

1. **The problem can be decomposed**: Recursively divide the array (original problem) into two sub-arrays (sub-problems).
2. **Sub-problems are independent**: Each sub-array can be sorted independently (sub-problems can be solved independently).
3. **Solutions to sub-problems can be merged**: Two ordered sub-arrays (solutions to the sub-problems) can be merged into one ordered array (solution to the original problem).

## 12.1.2 &nbsp; Improving efficiency through divide and conquer

**Divide and conquer can not only effectively solve algorithm problems but often also improve algorithm efficiency**. In sorting algorithms, quicksort, merge sort, and heap sort are faster than selection, bubble, and insertion sorts because they apply the divide and conquer strategy.

Then, we may ask: **Why can divide and conquer improve algorithm efficiency, and what is the underlying logic?** In other words, why are the steps of decomposing a large problem into multiple sub-problems, solving the sub-problems, and merging the solutions of the sub-problems into the solution of the original problem more efficient than directly solving the original problem? This question can be discussed from the aspects of the number of operations and parallel computation.

### 1. &nbsp; Optimization of operation count

Taking "bubble sort" as an example, it requires $O(n^2)$ time to process an array of length $n$. Suppose we divide the array from the midpoint into two sub-arrays as shown in Figure 12-2, then the division requires $O(n)$ time, sorting each sub-array requires $O((n / 2)^2)$ time, and merging the two sub-arrays requires $O(n)$ time, with the total time complexity being:

$$
O(n + (\frac{n}{2})^2 \times 2 + n) = O(\frac{n^2}{2} + 2n)
$$

![Bubble sort before and after array partition](divide_and_conquer.assets/divide_and_conquer_bubble_sort.png){ class="animation-figure" }

<p align="center"> Figure 12-2 &nbsp; Bubble sort before and after array partition </p>

Next, we calculate the following inequality, where the left and right sides are the total number of operations before and after the partition, respectively:

$$
\begin{aligned}
n^2 & > \frac{n^2}{2} + 2n \newline
n^2 - \frac{n^2}{2} - 2n & > 0 \newline
n(n - 4) & > 0
\end{aligned}
$$

**This means that when $n > 4$, the number of operations after partitioning is fewer, and the sorting efficiency should be higher**. Please note that the time complexity after partitioning is still quadratic $O(n^2)$, but the constant factor in the complexity has decreased.

Further, **what if we keep dividing the sub-arrays from their midpoints into two sub-arrays** until the sub-arrays have only one element left? This idea is actually "merge sort," with a time complexity of $O(n \log n)$.

Furthermore, **what if we set several more partition points** and evenly divide the original array into $k$ sub-arrays? This situation is very similar to "bucket sort," which is very suitable for sorting massive data, and theoretically, the time complexity can reach $O(n + k)$.

### 2. &nbsp; Optimization through parallel computation

We know that the sub-problems generated by divide and conquer are independent of each other, **thus they can usually be solved in parallel**. This means that divide and conquer can not only reduce the algorithm's time complexity, **but also facilitate parallel optimization by the operating system**.

Parallel optimization is especially effective in environments with multiple cores or processors, as the system can process multiple sub-problems simultaneously, making fuller use of computing resources and significantly reducing the overall runtime.

For example, in the "bucket sort" shown in Figure 12-3, we distribute massive data evenly across various buckets, then the sorting tasks of all buckets can be distributed to different computing units, and the results are merged after completion.

![Bucket sort's parallel computation](divide_and_conquer.assets/divide_and_conquer_parallel_computing.png){ class="animation-figure" }

<p align="center"> Figure 12-3 &nbsp; Bucket sort's parallel computation </p>

## 12.1.3 &nbsp; Common applications of divide and conquer

On one hand, divide and conquer can be used to solve many classic algorithm problems.

- **Finding the closest point pair**: This algorithm first divides the set of points into two parts, then finds the closest point pair in each part, and finally finds the closest point pair that spans the two parts.
- **Large integer multiplication**: For example, the Karatsuba algorithm, which breaks down large integer multiplication into several smaller integer multiplications and additions.
- **Matrix multiplication**: For example, the Strassen algorithm, which decomposes large matrix multiplication into multiple small matrix multiplications and additions.
- **Tower of Hanoi problem**: The Tower of Hanoi problem can be solved recursively, a typical application of the divide and conquer strategy.
- **Solving inverse pairs**: In a sequence, if a number in front is greater than a number behind, these two numbers form an inverse pair. Solving the inverse pair problem can utilize the idea of divide and conquer, with the aid of merge sort.

On the other hand, divide and conquer is very widely applied in the design of algorithms and data structures.

- **Binary search**: Binary search divides an ordered array from the midpoint index into two parts, then decides which half to exclude based on the comparison result between the target value and the middle element value, and performs the same binary operation in the remaining interval.
- **Merge sort**: Already introduced at the beginning of this section, no further elaboration is needed.
- **Quicksort**: Quicksort selects a pivot value, then divides the array into two sub-arrays, one with elements smaller than the pivot and the other with elements larger than the pivot, and then performs the same partitioning operation on these two parts until the sub-array has only one element.
- **Bucket sort**: The basic idea of bucket sort is to distribute data to multiple buckets, then sort the elements within each bucket, and finally retrieve the elements from the buckets in order to obtain an ordered array.
- **Trees**: For example, binary search trees, AVL trees, red-black trees, B-trees, B+ trees, etc., their operations such as search, insertion, and deletion can all be considered applications of the divide and conquer strategy.
- **Heap**: A heap is a special type of complete binary tree, whose various operations, such as insertion, deletion, and heapification, actually imply the idea of divide and conquer.
- **Hash table**: Although hash tables do not directly apply divide and conquer, some hash collision resolution solutions indirectly apply the divide and conquer strategy, for example, long lists in chained addressing being converted to red-black trees to improve query efficiency.

It can be seen that **divide and conquer is a subtly pervasive algorithmic idea**, embedded within various algorithms and data structures.