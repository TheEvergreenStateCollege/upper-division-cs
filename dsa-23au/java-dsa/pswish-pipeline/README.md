## How to use my code

### To run the stand-alone, command-line application do the following.
    
1. Make sure you are on the main branch version or on my pipeline for the latest version
        
2. Check your ability to switch cleanly:
    
    a. run  `git status`, if you have uncommitted changes, you may want to `git stash` them (restore with `git stash pop 0`)

3. Switch to a branch with a working copy
    `git switch main`,  or   `git switch pswish-pipeline`

4. run `python3 dsa-23au/java-dsa/pswish-pipeline/python/DriverData.py`. If you get a file not found, drill down into the directory in the file path and run it with `python3 DriverData.py`

### To run the web based version, perform steps 1-3 above and continue. 

4. run the following from upper-division-cs directory: `python3 dsa-23au/java-dsa/pswish-pipeline/DjangoPr/try2/pswishsite/manage.py runserver 0.0.0.0:8001`

5. Once the server is running ignore the popup and navigate to Public IP viewable: http://18.144.90.119:8001/polls/


## Files in this directory
1. DjangoPr is my django project mentioned above.
2. Homework_problems are problems from the reading assignments.
3. python/ contains python files where most are run-able.
    1. DriverData.py is described above.
    2. NestedDicts.py is a demo of recursion using the data structure created in DriverData.
    3. proof.py is more recursion but with the data hardcoded in for testing. 
4. src is some Java code without a main function to do anything useful.

### you can run any of the python files with >>> `python3 #filename`