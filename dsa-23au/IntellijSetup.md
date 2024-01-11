# IntellJ Setup

IntelliJ (Community Edition) is by a large margin the best development experience for Java and JVM languages.
It is freely available, and was my primary development environment when I worked on the Search Infrastructure Team
at Etsy, and at many other tech companies.

https://www.jetbrains.com/idea/download/?section=windows

## Download

These instructions were developed with the following version:
```
IntelliJ IDEA 2023.2 (Community Edition)
Build #IC-232.8660.185, built on July 25, 2023
Runtime version: 17.0.7+7-b1000.6 aarch64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 13.4.1
GC: G1 Young Generation, G1 Old Generation
Memory: 2048M
Cores: 8
Metal Rendering is ON
Registry:
    ide.experimental.ui=true
Kotlin: 232-1.9.0-IJ8660.185
```
but should remain largely unchanged for later versions close to this.


## Visual Settings

### Color Scheme

If you wish, you can change the theme to something other than bright white.

<img width="567" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/8dfdb862-959b-4e98-81c4-9537b9b1fa25">

## Remote Development

On the class server `anti-villain`.

Use JetBrains Gateway to SSH into a remote machine.

<img width="801" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/0233eae2-7950-4c70-9a88-4917277d8872">

<img width="728" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/e1f9976b-5918-4ccf-976d-1a69137db907">


## Troubleshooting

### Linting and Building

If files don't lint (the syntax coloring doesn't show errors, and clicking "Build" doesn't have any effect), perform
the following steps

* Delete the `/.idea` files.
* Close IntelliJ and re-open the project.
* When you receive an error message that the `.idea` project files are missing, click `Build -> Rebuild the Project`

<img width="326" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/ad19e9df-f545-43c9-9288-98c04d7749a8">
