# AISH Lab 1

4/4/24
Gavin Bowers & Jonah Eadie

In this lab, one of the tasks was to update a Python2 neural network program (`network.py`) to Python3. Thankfully not much was broken. The program used `xrange()`, which doesn't exist in Python3. Apparently, in Python2 `xrange()` uses a lazy-evaluated list for the range, while `range()` is eager. In Python3, they got rid of `xrange()` and made `range()` lazy-evaluated. The other change was that Python2 `print` statements don't use parentheses.

![](Pasted%20image%2020240404143944.png)
The above image shows network.py after the `print` statements were fixed but before `xrange()` was fixed.

We transcribed the screenshots provided in the notes for today. Here's one of them:

```Python
f = open("train-labels-idx1-ubyte", "rb")
data = f.read()

def convert_four(arr):
    result = 0
    for i in arr:
        print(f"result {result} {i}")
        result *= 255
        result += i
    return result

size = int(data[6]*256+data[7])
print(f"Number of images {size}")

labels = []

f2 = open("labels.txt", "w")

for i in range(size):
    print(chr(data[8+i]+48))
    f2.write(chr(data[8+i]+48))

f2.close()
f.close()
```

This is one of two scripts which process the MNIST training data into a format that `network.py` can read. This one processes the labels, and the other one processes the images. These scripts are replacements for the `mnist_loader` script from the [Original instructions](http://neuralnetworksanddeeplearning.com/chap1.html#implementing_our_network_to_classify_digits). According to Paul, it had breaking changes in trying to update it from Python2 to Python3.

We were unable to try running `network.py` because `load_images.py` took too long to run.