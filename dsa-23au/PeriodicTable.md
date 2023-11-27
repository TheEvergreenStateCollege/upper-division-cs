# Periodic Table of DSA

| Data Structure Name | Create                        || Read                                             ||||| Update         | Delete      |
|---------------------|---------------|---------------|--------|-----------|--------------|-------|----------|----------------|-------------|
| From size n, afterwards...  | size increases to n+1 || size stays the same at `n`   ||||| size stays the same at `n`         | size decreases to `n-1`      |
|                     | insertAt(i,v) | insertLast(v) | size() | isEmpty() | searchFor(v) | min() | max()    | replaceAt(i,v) | removeAt(i) |
| Arrays              | [O(?)](#arrays-insert-at)          |               |        |           |              |       |          |                |             |
| Linked lists        | [O(1)](#Linked-lists-insert-at)              |  [O(1)](#Linked-lists-insert-at)             |        |           |[O(1)](#arrays-search-for)|       |          |                |             |
| Stacks              | [O(1)](#stacks-insert-at)             |               |        |           |              |       |          |                |             |
| Queues              |               |               |        |           |              |       |          |                |             |
| Heaps               |               |               |        |           |              |       |          |                |             |
| Binary Search Trees |               |               |        |    [O(1)](#BinarySearchTree-isEmpty)       |              |       |          |                |             |
Hash map | [O(1)](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/027ccf7fbb6086255319aee7f481a04160c912b5/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/main/java/com/pswishcorp/app/HighwayTune.java#L13)          |               |        |           |              |       |          |                |             |

## Arrays

### Arrays Insert At

[O(?)](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/arrays-links/src/main/java/dev/codewithfriends/ArrayWrapper.java#L122)
